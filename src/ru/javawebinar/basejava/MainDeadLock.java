package ru.javawebinar.basejava;

public class MainDeadLock {
        String LOCK1 = "LOCK1";
        String LOCK2 = "LOCK2";
    public static void main(String[] args) {
        MainDeadLock mdl = new MainDeadLock();

        final int PAUSE = 50000;
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                synchronized (mdl.LOCK1){
                    System.out.println("Thread1 " + mdl.LOCK1);
                    MainDeadLock.suspend(PAUSE);
                    synchronized (mdl.LOCK2){
                        System.out.println("Thread1 " + mdl.LOCK2);
                    }
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (mdl.LOCK2){
                    System.out.println("Thread2 " + mdl.LOCK2);
                    MainDeadLock.suspend(PAUSE);
                    synchronized (mdl.LOCK1){
                        System.out.println("Thread2 " + mdl.LOCK1);
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
    }

    private static void suspend(int PAUSE) {
        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
