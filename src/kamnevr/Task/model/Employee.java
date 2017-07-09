package kamnevr.Task;

/**
 * Created by rkamnev on 15.05.2017.
 */
public class Employee {
    boolean isReadyToTransfer;
    private String name;
    private int salary;

    public Employee() {
    }

    public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
    }

    String getName() {
        return name;
    }

    int getSalary() {
        return salary;
    }

    void setReadyToTransfer() {
            isReadyToTransfer = true;
    }
}
