package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static volatile int counter;
    private static final Object LOCK = new Object();
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " " + getState());

                throw new IllegalStateException();
            }
        };
        thread0.start();

        Thread thread1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState()));
        thread1.start();

        System.out.println(thread0.getState());


        final MainConcurrency mc = new MainConcurrency();

        int THREADS_NUMBER = 10000;
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mc.increment();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mc.counter);
    }


    private synchronized void increment() {
//        double a = Math.sin(13.2);
//        synchronized (MainConcurrency.class) {
//        synchronized (LOCK) {
//        synchronized (this){
                counter++;

//    }
//        }
//        }
    }
}
