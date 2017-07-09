package kamnevr.Task.utils;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReadingAndWriting {

    public void writeFromList(String filepath, List<String> wordList) {
        PrintWriter printWriter = null;
        FileWriter writer = null,
                writerForCleanString = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filepath, true)));
            writerForCleanString = new FileWriter(filepath, false);
            writerForCleanString.write("");
            for (String string : wordList) {
                printWriter.println(string);
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }

    public void appendUsingFileWriter(String filePath, String text) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            printWriter.println(text);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }

    public void writeFirstStringInError(String filePath) {
        FileWriter fstream1 = null;
        BufferedWriter out1 = null;
        try {
            fstream1 = new FileWriter(filePath);
            out1 = new BufferedWriter(fstream1);
            out1.write("Ошибки в программе:\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

