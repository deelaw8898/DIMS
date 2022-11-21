import javax.swing.*;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HR extends Employee {
    private static TreeMap<String, Employee> EmployeeList;

    public HR() {
        super();
        EmployeeList = new TreeMap<>();
        importEmployees();
    }

    private void importEmployees() {
        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader("EmployeeRecord.txt"));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Missing EmployeeRecord.txt file in program file",
                    "PatientRecord File Not Found", JOptionPane.ERROR_MESSAGE);
            ;
        }
        String line = "";

        while (true) {
            try {
                if ((line = read.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException("File not found");
            }
            if(line.equals(""))
                break;
            String[] info = line.split(",");
            Employee newEmployee = new Employee(info[0],info[1],info[2],info[3],info[4],info[5],info[6],info[7],info[8],info[9],info[10],info[11],info[12],info[13],info[14],info[15],info[16]);
            putEmployeeInList(newEmployee.getEmployeeId(), newEmployee);
        }
    }

    public void putEmployeeInList(String employeeId, Employee employee)
    {
        EmployeeList.put(employeeId,employee);
    }

    public void createEmployee()
    {
        EmployeeInfoForm eif = new EmployeeInfoForm();
        eif.registerEmployee();
    }

    public void removeEmployee(String employeeID) {
        if(EmployeeList.containsKey(employeeID))
        {
            EmployeeList.remove(employeeID);
            exportEmployee("EmployeeRecord.txt");
        }


    }

    private void exportEmployee(String fileName) {
        Set set = EmployeeList.entrySet();
        for (Object o : set) {
            Map.Entry mapEntry = (Map.Entry) o;
            String info = getEmployeeInfo((Employee) mapEntry.getValue());

            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                out.println(info);
                out.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error While Writing The Employee File", "Task Failed", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    public String getEmployeeInfo(Employee employee) {
        StringBuilder builder = new StringBuilder();
        if (employee.getTitle().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getTitle()).append(',');

        if (employee.getFirstName().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getFirstName()).append(',');

        if (employee.getSurName().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getSurName()).append(',');

        if (employee.getHealthCardNumber().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getHealthCardNumber()).append(',');

        if (employee.getEmail().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getEmail()).append(',');

        if (employee.getDateOfBirth().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getDateOfBirth()).append(',');

        if (employee.getAddress().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getAddress()).append(',');

        if (employee.getPostalCode().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getPostalCode()).append(',');

        if (employee.getContactHome().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getContactHome()).append(',');

        if (employee.getContactCell().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getContactCell()).append(',');

        if (employee.getContactWork().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getContactWork()).append(',');

        if (employee.getEmergencyContactName().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getEmergencyContactName()).append(',');

        if (employee.getEmergencyContactNumber().equals(""))
            builder.append('-').append(',');
        else builder.append(getEmergencyContactNumber()).append(',');

        if (employee.getDepartment().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getDepartment()).append(',');

        if (employee.getBranchCode().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getBranchCode()).append(',');

        if (employee.getEmployeeId().equals(""))
            builder.append('-').append(',');
        else builder.append(employee.getEmployeeId()).append(',');

        if (employee.getPay().equals(""))
            builder.append('-');
        else builder.append(employee.getPay());

        return builder.toString();
    }

    public String getPay(String employeeId)
    {
        if(EmployeeList.containsKey(employeeId))
        {
            return EmployeeList.get(employeeId).getPay();
        }
        else return null;

    }

    public void setBranchCode(String employeeId, String branchCode)
    {
        if(EmployeeList.containsKey(employeeId))
        {
            EmployeeList.get(employeeId).setBranchCode(branchCode);
        }

    }
    public String getPaySheet() {
        StringBuilder builder = new StringBuilder();

        Set set = EmployeeList.entrySet();
        for (Object o : set) {
            Map.Entry mapEntry = (Map.Entry) o;
            String EmployeeId = ((Employee) mapEntry.getValue()).getEmployeeId();
            String pay = ((Employee) mapEntry.getValue()).getPay();
            builder.append(EmployeeId).append("\t$").append(pay).append('\n');
        }
        return builder.toString();
    }

    public void changePay(String employeeId, String pay) {
        EmployeeList.get(employeeId).setPay(pay);
    }

    public String viewEmployee(String employeeId)
    {
        return EmployeeList.get(employeeId).toString();
    }

    public static void main(String[] args)
    {
        HR hr = new HR();
        System.out.println(hr.EmployeeList.get("123").toString());
    }
}
