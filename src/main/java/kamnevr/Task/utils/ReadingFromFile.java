package kamnevr.Task.utils;

import kamnevr.Task.Main;
import kamnevr.Task.threads.Thread1;

import java.io.*;
import java.util.*;

/**
 * Created by rkamnev on 09.06.2017.
 */
public class ReadingFromFile {
    private static String filepath = Thread1.getStrings()[0];
    private static RandomAccessFile accessFile;
    private static long cursor;

    static {
        try {
            accessFile = new RandomAccessFile(filepath, "rw");
            long filelenght = accessFile.length();
            accessFile.setLength(filelenght);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readingWithRandomAcessFile(List<String> list) throws IOException {
        String correctLine;
        synchronized (ReadingFromFile.class) {
            accessFile.seek(cursor);
            String string1 = accessFile.readLine();
            correctLine = new String(string1.getBytes("ISO-8859-1"), "UTF-8");
            cursor = accessFile.getFilePointer();
        }
        list.add(correctLine + " | " + Thread.currentThread().getName());
    }

    public static String[] fillArray(List<String> list) {
        String[] arr = list.toArray(new String[list.size()]);
        return arr;
    }
}
