package com.alpha.handler;

import java.io.*;

public class OtherHandler extends Thread {
    private final File file;
    private final BufferedWriter writer;

    public OtherHandler(File file, BufferedWriter writer) {
        this.file = file;
        this.writer = writer;
    }

    /**
     * Удаление файла
     */
    @Override
    public void run() {
        String text = file.delete() ? "File was deleted + " + "\n" : "File was not deleted" + "\n";
        try {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
