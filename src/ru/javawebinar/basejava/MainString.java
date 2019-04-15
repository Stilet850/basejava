package ru.javawebinar.basejava;

public class MainString<main> {
    public static void main(String [] args){
        String [] strArray = new String[]{ "1","2","3","4","5"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArray.length ; i++) {
            sb.append(strArray[i]).append(",");
        }
        System.out.println(sb.toString());
    }
}
