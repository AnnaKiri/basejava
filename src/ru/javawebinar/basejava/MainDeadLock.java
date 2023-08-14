package ru.javawebinar.basejava;

public class MainDeadLock {
    public static void main(String[] args) {
        final String lock1 = "lock1";
        final String lock2 = "lock2";

        new Thread(() -> doDeadLock(lock1, lock2)).start();
        new Thread(() -> doDeadLock(lock2, lock1)).start();
    }

    private static void doDeadLock(String lock1, String lock2) {
        synchronized (lock1) {
            System.out.println(lock1 + "was taken");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {
                System.out.println(lock2 + "was taken");
            }
        }
    }
}
