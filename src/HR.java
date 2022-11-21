import java.util.ArrayList;
import java.util.TreeMap;

public class HR extends Employee {
    private static TreeMap<String, Employee> EmployeeList;

    public HR() {
        super();
        EmployeeList = new TreeMap<>();
    }

    public void addEmployee() {
        EmployeeInfoForm eif = new EmployeeInfoForm();
        eif.EmployeeRegistrationForm(null);
        //TODO
    }
    public void putEmployeeInList(String employeeId, Employee employee)
    {
        EmployeeList.put(employeeId,employee);
    }

    public void removeEmployee() {
        //todo
    }

    public void listEmployee() {
        //todo
    }

    public void viewPaySheet() {
        //todo
    }

    public void changePay() {
        //todo
    }

    public void giveBonus() {
        //todo
    }

    public static void main(String[] args)
    {
        HR hr = new HR();
        hr.addEmployee();
        System.out.println(hr.EmployeeList.get("123").toString());
    }
}
