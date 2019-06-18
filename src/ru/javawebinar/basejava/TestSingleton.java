package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.SectionType;

public class TestSingleton {
    private static TestSingleton ourInstance;
    //private static TestSingleton ourInstance = new TestSingleton();

    public static TestSingleton getInstance(){
        if (ourInstance == null){
            ourInstance = new TestSingleton();
        }
        return ourInstance;
    }

    private TestSingleton() {
    }

    public static void main(String[] args) {
        System.out.println(TestSingleton.getInstance().toString());
        Singleton singleton =  Singleton.valueOf("INSTANCE");
        System.out.println(singleton);
        System.out.println(singleton.name());
        System.out.println(singleton.ordinal());

        for (SectionType sectionType: SectionType.values()) {
            System.out.println(sectionType.getTitle());
        }
    }

    public enum Singleton{
        INSTANCE;
    }
}
