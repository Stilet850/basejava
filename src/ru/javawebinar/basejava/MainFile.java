package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args){
        String filePath = "D:\\Personal\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("D:/Personal");
        System.out.println(dir.isDirectory());
        String [] list = dir.list();
        if(list!=null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fin = new FileInputStream(filePath);) {
            System.out.println(fin.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

        }

        printDirectoryDeeply(new File(filePath));
    }

    public static void printDirectoryDeeply (File dir){
        File [] files = dir.listFiles();

        if(files != null){
            for (File fileTmp :files){
                if(fileTmp.isFile()){
                    System.out.println( "File:" + fileTmp.getName());
                }else if(fileTmp.isDirectory()){
                    System.out.println( "Directory:" + fileTmp.getName());
                    printDirectoryDeeply(fileTmp);
                }
            }
        }
    }
}
