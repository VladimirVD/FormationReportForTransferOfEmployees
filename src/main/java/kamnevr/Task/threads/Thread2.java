package kamnevr.Task.threads;

import kamnevr.Task.General;
import kamnevr.Task.Main;
import kamnevr.Task.exception.NoDelimetersException;
import kamnevr.Task.exception.WrongDataException;
import kamnevr.Task.model.Department;
import kamnevr.Task.model.Employee;
import kamnevr.Task.utils.ReadingAndWriting;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by rkamnev on 31.05.2017.
 */
public class Thread2 extends Thread {
    private static String[] str;

    static void setStr(String[] str) {
        Thread2.str = str;
    }

    private static final Object monitor = new Object();

    static Object getMonitor() {
        return monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            try {
                System.out.println("Thread 2 is waiting");
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ReadingAndWriting readingAndWriting = (ReadingAndWriting) Main.getContext().getBean("readingAndWriting");
        String[] strings = Main.getStrings();
        String outName = strings[1];
        String errorName = strings[2];
//        str = main.getOriginalFile();
        General general = new General();
        Department[] departments = general.countOfDepartmets(str);
        departments = general.subDivide(str, departments);

        for (Department dep : departments) {
            for (String string : dep.getEmployeesBeforeDividing()) {
                try {
                    dep.listOfEmployeesFromStrings(string);
                } catch (NoDelimetersException e) {
                    String error = "Вы ввели некорректное число разделителей\n";
                    readingAndWriting.appendUsingFileWriter(errorName, error);
                } catch (WrongDataException e) {
                    String error = "Вы ввели неполные данные. Пожалуйста перепроверьте введенные данные\n";
                    readingAndWriting.appendUsingFileWriter(errorName, error);
                }
            }
        }

        for (Department dep : departments) {
            dep.findToMiddleSalary();
        }

        for (Department dep : departments) {
            dep.settingToTransfer();
        }

        List<String> reportList = general.reportCreation(departments);
        readingAndWriting.writeFromList(outName, reportList);
        System.out.println("Thread 2 was finished");
    }
}

