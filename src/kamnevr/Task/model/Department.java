package kamnevr.Task;

import java.util.*;

/**
 * Created by rkamnev on 15.05.2017.
 */
public class Department {

    /*   Список строк получаемых из исходного файла содерж фамилию и заработную плату */
    List<String> employeesBeforeDividing = new ArrayList<>();

    /*   Map включающая фамилию сотрудника и его зарботную плату */
    Map<String, Integer> employees = new HashMap<>();

    /*   Список сотрудников */
    List<Employee> listOfEmployees = new ArrayList<>();

    /*   Номер департамента */
    private int numberOfDepartment;

    /*   Средняя заплата по департаменту */
    private int middleSalaryOfDepartment;

    int getNumberOfDepartment() {
        return numberOfDepartment;
    }

    void setNumberOfDepartment(int numberOfDepartment) {
        this.numberOfDepartment = numberOfDepartment;
    }

    private void setMiddleSalaryOfDepartment(int middleSalaryOfDepartment) {
            this.middleSalaryOfDepartment = middleSalaryOfDepartment;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /*   Метод генерирующий объекты "Employee" из списка строк */
    void listOfEmployeesFromStrings() {
        for (String str : employeesBeforeDividing) {    /*   Проходим по строкам списка */
                char[] chars = str.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                        if (Character.isDigit(chars[i])) {
                                String name = str.substring(0, i - 1);
                                name.trim();
                                String salaryStr = str.substring(i);
                                salaryStr.trim();
                                Integer salary = Integer.parseInt(salaryStr);
                                employees.put(name, salary);    /*   Заносим эти данные в Map */
                                Employee employee = new Employee(name, salary);
                                listOfEmployees.add(employee);    /*    Заносим объект в список сотрудников */
                                break;
                        }
                }
        }
    }

    /*   Метод расчитывает и устанавливает среднюю заработную плату по департаменту */
    void findToMiddleSalary() {
            int middleSalary = 0;
            int summ = 0;
            List<Integer> values = new LinkedList<Integer>(employees.values());
            Integer[] counts = values.toArray(new Integer[values.size()]);
            for (int i = 0; i < counts.length; i++) {
                summ = summ + counts[i];
            }
            middleSalary = summ / counts.length;

            setMiddleSalaryOfDepartment(middleSalary);
    }

    /*
            Метод определяющий возможность перевода сотрудников из данного департамента
                    Если з/п сотрудника меньше средней зарплаты по отделу -
                            устанавливаем поле isReadyToTransfer - true
                                                                                                   */
    void settingToTransfer() {
            for (Employee e : listOfEmployees) {
                    if (e.getSalary() < middleSalaryOfDepartment) {
                            e.setReadyToTransfer();
                    }
            }
    }

    /*   Метод определяющий потребность в данном сотруднике */
    boolean needToEmployee(Employee e) {         //Метод определяющий по
        return e.getSalary() > middleSalaryOfDepartment;
    }
}
