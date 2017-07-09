package kamnevr.Task.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkamnev on 15.05.2017.
 */
public class Employee {
    private boolean isReadyToTransfer;
    private String name;
    private int salary;
    private String adress;
    private String mobilephonenumber;
    private int numberOfDepartment;
    private int countOfAvaliableDepartments;
    private String reportString;
    private String currentThread;

    public Employee() {
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, int salary, String adress) {
        this.name = name;
        this.salary = salary;
        this.adress = adress;
    }

    public Employee(String name, int salary, String adress, int numberOfDepartment) {
        this.name = name;
        this.salary = salary;
        this.adress = adress;
        this.numberOfDepartment = numberOfDepartment;
    }

    public Employee(String name, int salary, String adress, String mobilephonenumber, int numberOfDepartment) {
        this.name = name;
        this.salary = salary;
        this.adress = adress;
        this.mobilephonenumber = mobilephonenumber;
        this.numberOfDepartment = numberOfDepartment;
    }

    public Employee(String name, int salary, String adress, String mobilephonenumber, int numberOfDepartment, String currentThread) {
        this.name = name;
        this.salary = salary;
        this.adress = adress;
        this.mobilephonenumber = mobilephonenumber;
        this.numberOfDepartment = numberOfDepartment;
        this.currentThread = currentThread;
    }

    public String getName() {
        return name;
    }

    int getSalary() {
        return salary;
    }

    void setReadyToTransfer() {
        isReadyToTransfer = true;
    }

    public boolean isReadyToTransfer() {
        return isReadyToTransfer;
    }

    public int getNumberOfDepartment() {
        return numberOfDepartment;
    }

    public int getCountOfAvaliableDepartments() {
        return countOfAvaliableDepartments;
    }

    public void setCountOfAvaliableDepartments(int countOfAvaliableDepartments) {
        this.countOfAvaliableDepartments = countOfAvaliableDepartments;
    }

    public String getReportString() {
        return reportString;
    }

    public void setReportString(String reportString) {
        this.reportString = reportString;
    }

    public String getCurrentThread() {
        return currentThread;
    }

    public void setCurrentThread(String currentThread) {
        this.currentThread = currentThread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getSalary() != employee.getSalary()) return false;
        return getName().equals(employee.getName());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSalary();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", adress='" + adress + '\'' +
                ", mobilephonenumber='" + mobilephonenumber + '\'' +
                ", numberOfDepartment=" + numberOfDepartment +
                ", currentThread='" + currentThread + '\'' +
                '}';
    }
}
