package com.alpha.singleton;

import java.io.*;

public class SingletonWriter {
    private static BufferedWriter writer;

    /**
     * Создание и получение writer в случае, если он был создан
     *
     * @return writer
     */
    public static BufferedWriter getWriter() {
        if (writer == null) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream("log.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Something went wrong. File for logs cant be created or opened.");
                System.exit(1);
            }

            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        }

        return writer;
    }
}
