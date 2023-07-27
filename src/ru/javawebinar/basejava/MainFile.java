package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static String filePath = ".gitignore";
    public static File dir = new File(".\\src\\ru\\javawebinar\\basejava");

    public static void main(String[] args) {

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printAllFileNames(dir);
    }

    public static void printAllFileNames(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if(files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println(file.getName());
                        printAllFileNames(file);
                    } else {
                        System.out.println(file.getName());
                    }
                }
            }
        }

    }
}
