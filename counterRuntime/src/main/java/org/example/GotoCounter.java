package org.example;

import soot.*;
import soot.options.Options;
import soot.jimple.*;
import soot.util.Chain;

import java.util.ArrayList;
import java.util.Iterator;

public class GotoCounter {

    public static void main(String[] args) {
        String className = "ForLoop"; // 要插桩的类名
        SootClass appClass = loadApplicationClass(className);
        addGotoCounter(appClass);
        PackManager.v().runPacks();
        PackManager.v().writeOutput();
    }

    private static SootClass loadApplicationClass(String className) {
        Options.v().set_prepend_classpath(true);
        Options.v().set_whole_program(true);
        Options.v().set_app(true);
        Options.v().set_output_format(Options.output_format_jimple);

        SootClass appClass = Scene.v().loadClassAndSupport(className);
        Scene.v().loadNecessaryClasses();
        appClass.setApplicationClass();
        return appClass;
    }

    private static void addGotoCounter(SootClass appClass) {
        for (SootMethod method : appClass.getMethods()) {
            if (!method.isConcrete()) {
                continue;
            }
            Body body = method.retrieveActiveBody();
            Chain<Unit> units = body.getUnits();
            for (Iterator<Unit> iter = units.snapshotIterator(); iter.hasNext(); ) {
                Unit unit = iter.next();
                if (unit instanceof GotoStmt) {
                    // 插入记录goto语句执行的代码
                    SootMethod recordMethod = getRecordMethod();
                    InvokeStmt invokeStmt = Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(recordMethod.makeRef()));
                    units.insertBefore(invokeStmt, unit);
                }
            }
        }
    }

    private static SootMethod getRecordMethod() {
        // 定义一个记录方法，在这里你可以记录goto语句的执行
        SootClass recordClass = new SootClass("GotoCounterRuntime", Modifier.PUBLIC);
        recordClass.setSuperclass(Scene.v().getSootClass("java.lang.Object"));
        Scene.v().addClass(recordClass);

        SootMethod recordMethod = new SootMethod("recordGoto", new ArrayList<>(), VoidType.v(), Modifier.PUBLIC | Modifier.STATIC);
        recordClass.addMethod(recordMethod);

        JimpleBody body = Jimple.v().newBody(recordMethod);
        recordMethod.setActiveBody(body);
        Chain<Unit> units = body.getUnits();

        // 插入记录逻辑，可以写入文件或增加计数器
        units.add(Jimple.v().newReturnVoidStmt());

        return recordMethod;
    }
}