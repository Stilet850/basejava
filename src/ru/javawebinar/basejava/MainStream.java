package ru.javawebinar.basejava;

import java.util.Arrays;

public class MainStream {
    public static void main(String[] args) {
        int [] array = {1, 4, 5, 3, 4, 7, 12, 3};

        Arrays.stream(array).distinct().forEach(System.out::println);
    }
}
