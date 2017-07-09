package kamnevr.Task.model;

import kamnevr.Task.Main;
import kamnevr.Task.exception.NoDelimetersException;
import kamnevr.Task.exception.WrongDataException;
import kamnevr.Task.exception.WrongFormatOfMobileNumber;
import kamnevr.Task.utils.ReadingAndWriting;

import java.util.*;

/**
 * Created by rkamnev on 15.05.2017.
 */
public class Department {
    private List<String> employeesBeforeDividing = new ArrayList<>();
    private List<Employee> listOfEmployees = new ArrayList<>();
    private Map<Employee, Integer> mapOfEmployees = new HashMap<>();
    private int numberOfDepartment;
    private int middleSalaryOfDepartment;
    private static String errorCheckName;

    public int getNumberOfDepartment() {
        return numberOfDepartment;
    }

    public void setNumberOfDepartment(int numberOfDepartment) {
        this.numberOfDepartment = numberOfDepartment;
    }

    public int getMiddleSalaryOfDepartment() {
        return middleSalaryOfDepartment;
    }

    private void setMiddleSalaryOfDepartment(int middleSalaryOfDepartment) {
        this.middleSalaryOfDepartment = middleSalaryOfDepartment;
    }

    public List<String> getEmployeesBeforeDividing() {
        return employeesBeforeDividing;
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }

    public static void setErrorCheckName(String errorCheckName) {
        Department.errorCheckName = errorCheckName;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void listOfEmployeesFromStrings(String str) throws NoDelimetersException, WrongDataException {

        if (!(isRightCountOfDelimeters(str))) {  /*    Если число разделителей не равно 4 то выбрасываем исключение  */
            throw new NoDelimetersException();
        }
        String[] strings = str.split("\\|");

        if ((strings.length != 5) || (checkEmptyArray(strings))) {   /*    Если число элементов массива не равно 5 или элемент массива
                                                                            пуст то выбрасываем исключение  */
            throw new WrongDataException();
        }
        String fio = strings[0].trim();
        Integer salary = Integer.parseInt(strings[1].trim());
        String adress = strings[2].trim();
        String currentThread = strings[4].trim();

        if (!(checkMobileNumberPhone(strings[3].trim())))
        /*    Если число цифр в строке не равно 11 то выбрасываем исключение затем обрабатываем его в блоке
                  выбрасываем предупреждения и заносим в поля объекта все данные, кроме mobilephone */
            try {
                throw new WrongFormatOfMobileNumber();
            } catch (WrongFormatOfMobileNumber wrongFormatOfMobileNumber) {
                ReadingAndWriting readingAndWriting = new ReadingAndWriting();
                String error = "Вы ввели некорректный номер мобильного телефона сотрудника: " + " " + fio + "\n";
                readingAndWriting.appendUsingFileWriter(errorCheckName, error);
            }

        String mobilePhone = strings[3].trim();
        listOfEmployees.add(new Employee(fio, salary, adress, mobilePhone, this.getNumberOfDepartment(), currentThread));
        mapOfEmployees.put(new Employee(fio, salary, adress, mobilePhone, this.getNumberOfDepartment(), currentThread), salary);
    }

    public void findToMiddleSalary() {
        int middleSalary = 0;
        int summ = 0;
        List<Integer> values = new LinkedList<Integer>(mapOfEmployees.values());
        Integer[] counts = values.toArray(new Integer[values.size()]);

        for (int i = 0; i < counts.length; i++) {
            summ = summ + counts[i];
        }

        middleSalary = summ / counts.length;
        setMiddleSalaryOfDepartment(middleSalary);
    }

    public void settingToTransfer() {
        for (Employee e : listOfEmployees) {

            if (e.getSalary() < middleSalaryOfDepartment) {
                e.setReadyToTransfer();   /*    Если зарплата сотрудника меньше средней по департаменту
                                                                    устанавливаем его isReadyToTransfer - true  */
            }
        }
    }

    public boolean needToEmployee(Employee e) {         //Метод определяющий по
        return e.getSalary() > middleSalaryOfDepartment;
    }

    public boolean isRightCountOfDelimeters(String str) {
        int countOfDelimeters = 0;
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '|') countOfDelimeters++;   /*    Если элемент массива == '|' увеличиваем счетчик на 1  */
        }

        if (countOfDelimeters == 4) {  /*    Если число разделителей равно 4 возвращаем true  */
            return true;
        }
        return false;
    }

    public boolean checkMobileNumberPhone(String string) {
        int countOfDigits = 0;
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {  /*    Если элемент массива число - увеличиваем счетчик  */
                countOfDigits++;
            }
        }

        if (countOfDigits == 11) {  /*    Если число чисел в строке - 11 возвращаем true  */
            return true;
        }
        return false;
    }

    public boolean checkEmptyArray(String[] strings) {

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].trim().length() == 0) { /* Если элемент массива пуст значит мы ввели неверные данные - метод возвращает true  */
                return true;
            }
        }
        return false;
    }
}
