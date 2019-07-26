package ru.javawebinar.basejava;

public class MainDeadLock2{
    private static final int PAUSE = 10000;
    private final String LOCK1;
    private final String LOCK2;

    public MainDeadLock2(String lock1, String lock2) {
        LOCK1 = lock1;
        LOCK2 = lock2;
    }

   public static void main(String[] args) {
        MainDeadLock2 md1 = new MainDeadLock2("Lock1", "Lock2");
        deadLock(md1.LOCK1, md1.LOCK2);
        deadLock(md1.LOCK2, md1.LOCK1);
    }

    private static void deadLock(String str1, String str2){
        new Thread(()->{
            System.out.println("Waiting lock: " + str1);
            synchronized (str1){
                System.out.println("Holding lock" + str1);
                doSleep(5000);
                System.out.println("Waiting lock: " + str2);
                synchronized (str2){
                    System.out.println("Holding lock" + str2);
                }
            }
        }).start();
    }

    private static void doSleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
