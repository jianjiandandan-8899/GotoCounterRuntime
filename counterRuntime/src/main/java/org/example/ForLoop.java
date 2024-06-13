package org.example;

public class ForLoop {
    public static void main(String[] args) {
        int count = 0;

        outer: // 标签1
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("Breaking outer loop");
                    break outer; // 模拟goto
                }
                if (i == 3) {
                    System.out.println("Continuing outer loop");
                    continue outer; // 模拟goto
                }
                count++;
            }
        }

        System.out.println("First loop done, count = " + count);

        inner: // 标签2
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 1 && j == 3) {
                    System.out.println("Breaking inner loop");
                    break inner; // 模拟goto
                }
                count++;
            }
        }

        System.out.println("Second loop done, count = " + count);

        // 输出goto语句的执行次数
        System.out.println("Goto statements executed: " + GotoCounterRuntime.getGotoCount());
    }
}
