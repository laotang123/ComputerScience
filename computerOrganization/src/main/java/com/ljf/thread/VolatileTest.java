package com.ljf.thread;

/**
 * @author ：ljf
 * @date ：2020/7/3 23:16
 * @description：测试volatile的线程可见性
 * @modified By：
 * @version: $ 1.0
 */
public class VolatileTest {
//    private static volatile int COUNTER = 0;
    private static /*volatile*/ int COUNTER = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    /**
     * 监听COUNTER变量，只要改变就打印出改变的值
     */
    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int threadValue = COUNTER;
            while (threadValue < 5) {
                if (threadValue != COUNTER) {
                    System.out.println("Get change for COUNTER: " + COUNTER);
                    threadValue = COUNTER;
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int threadValue = COUNTER;
            while (threadValue < 5) {
                System.out.println("Increment COUNTER to " + (threadValue + 1));
                COUNTER = ++threadValue;

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
