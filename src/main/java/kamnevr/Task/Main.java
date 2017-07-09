package kamnevr.Task;

import kamnevr.Task.threads.Thread1;
import kamnevr.Task.threads.Thread2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by rkamnev on 15.05.2017.
 */

public class Main {
    private static String[] strings;

    static ApplicationContext context
            = new ClassPathXmlApplicationContext("context.xml");

    public static ApplicationContext getContext() {
        return context;
    }

    public static String[] getStrings() {
        return strings;
    }

    public static void main(String[] args) {
        strings = args;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread1 thread1 = (Thread1) context.getBean("thread1");
        Thread2 thread2 = (Thread2) context.getBean("thread2");
        thread1.start();
        thread2.start();
    }
}
