package ru.javawebinar.basejava.util;

import static java.lang.Math.sin;

public class LazySingleton {
    int i;
    volatile private static LazySingleton INSTANCE;

    double sin = sin(13.3);


    private  static class LazySingletonHolder{
        private static  final LazySingleton INSTANCE = new LazySingleton();

        public static LazySingleton getInstance() {
            return LazySingletonHolder.getInstance();
        }
    }



//classic
//
//    public static LazySingleton getInstance() {
//        if (INSTANCE == null) {// pattern double-check locking
//                synchronized (LazySingleton.class) {
//                    if (INSTANCE == null) {
//                        int i = 13;
//                        INSTANCE = new LazySingleton();
//                    }
//            }
//        }
//        return INSTANCE;
//    }

    private LazySingleton() {
    }
}
