package kamnevr.Task;

import kamnevr.Task.model.Department;
import kamnevr.Task.model.Employee;
import kamnevr.Task.utils.SortStringByCountOfDep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkamnev on 15.05.2017.
 */
public class General {

    static int findNumberOfDepartment(String str) {
        int numberOfDepartment = 0;
        String numberOfDep = "";
        str.trim();
        String[] strings = str.split("\\|");
        char[] chars = strings[0].toCharArray();

        for (int i = 10; i < chars.length; i++) {

            if (Character.isDigit(chars[i])) {
                numberOfDep += String.valueOf(chars[i]); /*    Если злемент массива - число объединяем его со строкой numberOfDep  */
            }
        }
        numberOfDepartment = Integer.parseInt(numberOfDep);
        return numberOfDepartment;
    }

    public Department[] countOfDepartmets(String[] arr) {
        int countOfDepartment = 0;
        List<Department> forDepartments = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains("Department")) {
            /*
                  Если элемент массива содержит "Department" - создем объект типа Department
                   с помощью метода findNumberOfDepartment находим его номер и  помещаем в список
                   */
                Department dep = new Department();
                int numberOfDepartment = findNumberOfDepartment(arr[i]);
                dep.setNumberOfDepartment(numberOfDepartment);
                forDepartments.add(countOfDepartment, dep);
                countOfDepartment++;
            }
        }
        Department[] departments = forDepartments.toArray(new Department[forDepartments.size()]);
        return departments;
    }

    public Department[] subDivide(String[] strings, Department[] departments) {
        int countOfDepartment = -1;

        for (String str : strings) {
            if (str.contains("Department")) { /*    Если строка содержит Department увеличиваем счетчик на 1  */
                countOfDepartment++;
                continue;
            } else if (str.equals("")) continue;
            departments[countOfDepartment].getEmployeesBeforeDividing().add(str);
        }
        return departments;
    }

    public List<String> reportCreation(Department[] departments) {
        List<Employee> listOfEmployesForTransfer = new ArrayList<>();
        List<Employee> finalList = null;

        for (Department d : departments) {
            for (Employee e : d.getListOfEmployees()) {
                e.toString();
            }
            d.settingToTransfer();
        }
        List<String> stringsForReport = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        stringsForReport.add("Средняя зарплата по отделу: ");

        for (Department d : departments) {
            stringsForReport.add("Средняя зарплата в отделе: " + " " + d.getNumberOfDepartment() + ":"
                    + " " + String.valueOf(d.getMiddleSalaryOfDepartment()));
        }
        stringsForReport.add("\n");
        stringsForReport.add("Возможные варианты перевода сотрудников: ");

        for (Department d : departments) {
            for (Employee e : d.getListOfEmployees()) {
                if (e.isReadyToTransfer()) {

                    /*    Если isReadyToTransfer сотрудника - true добавляем его имя в строку для отчета */
                    String str = "Сотрудник " + e.getName() + "(Thread ID: " + e.getCurrentThread() + ")" +
                            " отдела № " + e.getNumberOfDepartment() + " может быть переведен: ";

                    for (Department dep : departments) {
                        if (dep.needToEmployee(e)) {
                        /*    Ссылку на данного сотрудника  во все департаменты если его заработная плата
                                выше чем средняя по деартаменту добвляем номер департамента в строку для отчета
                                    */
                            str += "Отдел " + dep.getNumberOfDepartment() + " ";
                            e.setCountOfAvaliableDepartments(e.getCountOfAvaliableDepartments() + 1);
                        }
                    }

                    if (str.contains("Отдел")) {
                    /*    Если строка содержит "Department" значит есть хотя бы один департамент куда мы
                              можем перевести сотрудника данная строка может быть добавленна для отчета
                               */
                        e.setReportString(str);
                        listOfEmployesForTransfer.add(e);
                    }
                }
            }
        }
        finalList = SortStringByCountOfDep.sortList(listOfEmployesForTransfer);

        for (Employee e : finalList) {
            stringsForReport.add(e.getReportString());
        }
        return stringsForReport;
    }
}
