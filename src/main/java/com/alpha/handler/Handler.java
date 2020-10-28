package com.alpha.handler;

import com.alpha.singleton.SingletonWriter;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public abstract class Handler extends Thread {
    private final File file;
    private final BufferedWriter writer;

    public Handler(File file, BufferedWriter writer) {
        this.file = file;
        this.writer = writer;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            writer.write("Start handling of the file " + new Date() + "\n");
            getWordCount(file);
            writer.write("Time of working: " + (System.currentTimeMillis() - startTime) + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getWordCount(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            int lines = 0;
            while (scanner.hasNextLine()) {
                lines++;
                scanner.nextLine();
            }
            writer.write("Number of lines: " + lines + "\n");
        }
    }
}
