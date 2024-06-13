package org.example;

public class GotoCounterRuntime {
    private static int gotoCount = 0;

    public static void recordGoto() {
        gotoCount++;
    }

    public static int getGotoCount() {
        return gotoCount;
    }
}
