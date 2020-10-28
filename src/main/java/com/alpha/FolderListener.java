package com.alpha;

import com.alpha.handler.JsonHandler;
import com.alpha.handler.OtherHandler;
import com.alpha.handler.XmlHandler;
import com.alpha.singleton.SingletonWriter;


import java.io.*;
import java.nio.file.*;
import java.util.Date;

public class FolderListener {
    private static final String folderPath = "/Users/user1234/IdeaProjects/AlphaProject/src/main/java/com/alpha/listener/";

    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(folderPath);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                BufferedWriter writer = SingletonWriter.getWriter();
                writer.write("Event kind:" + event.kind() + "\n");
                writer.write("Name of file: " + event.context() + "." + "\n");
                writer.write("File was created: " + new Date() + "\n");
                File file = getFile(event);
                String extension = figureOutExtension(file);
                writer.write("Extension of file: " + extension + "\n");
                writer.flush();

                if (extension.equals("xml") && file.length() > 0) {
                    new XmlHandler(file, writer).start();
                } else if (extension.equals("json") && file.length() > 0) {
                    new JsonHandler(file, writer).start();
                } else {
                    new OtherHandler(file, writer).start();
                }
            }
            key.reset();
        }
    }

    /**
     * Вычисляем расширение файла
     *
     * @param file файл
     * @return расширение
     */
    private static String figureOutExtension(File file) {
        String[] temp = file.getName().split("\\.");
        return temp[temp.length - 1];
    }

    /**
     * Получение файла по его пути и имени
     *
     * @param event событие появления файла в директории
     * @return файл
     */
    private static File getFile(WatchEvent event) {
        return new File(folderPath + event.context().toString());
    }
}

