import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    public int id;
    public String name, city, state, category;
    public Integer managerId;
    public double salary;
    public LocalDate doj;
    public List<Employee> reportees = new ArrayList<>();

    public Employee(int id, String name, String city, String state, String category,
                    Integer managerId, double salary, LocalDate doj) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.category = category;
        this.managerId = managerId;
        this.salary = salary;
        this.doj = doj;
    }

    public String toString() {
        return id + " - " + name;
    }
}
