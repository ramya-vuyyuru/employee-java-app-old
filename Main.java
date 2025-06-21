import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        List<Employee> list = Arrays.asList(
            new Employee(123, "Ravi", "Hyderabad", "Telangana", "employee", 456, 45000, LocalDate.parse("4-Jun-2023", formatter)),
            new Employee(456, "Shivam", "Bangalore", "Karnataka", "manager", 789, 75000, LocalDate.parse("5-Jul-2022", formatter)),
            new Employee(789, "Rama", "Chennai", "Tamil Nadu", "Director", null, 150000, LocalDate.parse("25-Oct-2022", formatter)),
            new Employee(1011, "Krishna", "Hyderabad", "Telangana", "employee", 456, 50000, LocalDate.parse("7-Mar-2021", formatter)),
            new Employee(1213, "Sreekanth", "Mumbai", "Maharashtra", "employee", 789, 60000, LocalDate.parse("8-Aug-2019", formatter)),
            new Employee(1415, "Manoj", "Mangalore", "Karnataka", "employee", 456, 95000, LocalDate.parse("9-Jun-2018", formatter))
        );

        // Gratuity Eligibility
        System.out.println("Gratuity Eligible (> 60 months):");
        list.stream()
            .filter(e -> ChronoUnit.MONTHS.between(e.doj, LocalDate.now()) > 60)
            .forEach(System.out::println);

        // Salary > Manager's Salary
        System.out.println("\nEmployees with salary higher than their manager:");
        Map<Integer, Employee> map = list.stream().collect(Collectors.toMap(e -> e.id, e -> e));
        list.stream()
            .filter(e -> e.managerId != null && map.containsKey(e.managerId)
                    && e.salary > map.get(e.managerId).salary)
            .forEach(System.out::println);

        // Build Hierarchy
        System.out.println("\nEmployee Hierarchy:");
        for (Employee e : list) {
            if (e.managerId != null && map.containsKey(e.managerId)) {
                map.get(e.managerId).reportees.add(e);
            }
        }

        Employee root = map.get(789); // Director
        printTree(root, 0);
    }

    static void printTree(Employee e, int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println(e.name + " (" + e.category + ")");
        for (Employee reportee : e.reportees) {
            printTree(reportee, level + 1);
        }
    }
}
