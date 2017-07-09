package kamnevr.Task.threads;

import kamnevr.Task.Main;
import kamnevr.Task.utils.ReadingAndWriting;
import kamnevr.Task.utils.ReadingFromFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static kamnevr.Task.model.Department.setErrorCheckName;


/**
 * Created by rkamnev on 31.05.2017.
 */
public class Thread1 extends Thread {
    private static String[] strings;
    private boolean endOfTheFile;
    private static final Object monitor = new Object();

    public static String[] getStrings() {
        return strings;
    }

    @Override
    public void run() {
        strings = Main.getStrings();
        ReadingAndWriting readingAndWriting = (ReadingAndWriting) Main.getContext().getBean("readingAndWriting");
        String errorName = strings[2];
        setErrorCheckName(strings[2]);
        readingAndWriting.writeFirstStringInError(errorName);
        String[] str = new String[0];
        LinkedList<Thread> threadlist = new LinkedList<>();
        final List<String> temp = Collections.synchronizedList(new ArrayList<String>());

        for (int i = 0; i < 5; i++) {
            threadlist.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!endOfTheFile) {
                            ReadingFromFile.readingWithRandomAcessFile(temp);
                        }
                    } catch (NullPointerException e) {
                        synchronized (monitor) {
                            endOfTheFile = true;
                            monitor.notifyAll();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        for (Thread thread : threadlist) {
            thread.start();
        }

        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        str = ReadingFromFile.fillArray(temp);
        Thread2.setStr(str);

        synchronized (Thread2.getMonitor()) {
            Thread2.getMonitor().notifyAll();
            System.out.println("Await");
        }
        System.out.println("Thread 1 was finished");
    }
}
