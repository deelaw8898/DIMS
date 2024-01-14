import javax.swing.*;
import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Contains methods to do all the tasks related to HR and methods to help implement the tasks performed by the HR employees
 */
public class HR extends Employee {
    /**
     * Maps all the employees of the firm to their employee ID
     */
    private static TreeMap<String, Employee> EmployeeList;

    /**
     * Initialize the EmployeeList treeMap and also loads all the employees stored in the file
     */
    public HR() {
        super();
        EmployeeList = new TreeMap<>();
        importEmployees();
    }

    /**
     * Reads the EmployeeRecord.txt to get all the constructor values to stored employees,
     * create employees using those parameters while uploading them to the EmployeeList TreeMap.
     * This approach is used instead of storing and retrieving serialized objects to avoid file corruption caused
     * while appending multiple objects to a single file
     */
    private void importEmployees() {
        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader("EmployeeRecord.txt"));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Missing EmployeeRecord.txt file in program file",
                    "PatientRecord File Not Found", JOptionPane.ERROR_MESSAGE);

        }
        String line;

        while (true) {
            try {
                //noinspection ConstantConditions
                if ((line = read.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException("File not found");
            }
            if (line.equals(""))
                break;
            String[] info = line.split("\\|");

            //create new employee
            Employee newEmployee = new Employee(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10], info[11], info[12], info[13], info[14], info[15], info[16]);
            //update the EmployeeList TreeMap
            putEmployeeInList(newEmployee.getEmployeeId(), newEmployee);
        }
    }

    /**
     * Puts an employee to the EmployeeList treemap mapping each employee to their employee ID
     *
     * @param employeeId A unique employee ID assigned to each employee
     * @param employee   an object of Employee class representing an employee at the firm
     */
    public void putEmployeeInList(String employeeId, Employee employee) {
        EmployeeList.put(employeeId, employee);
    }

    /**
     * Calls EmployeeInfoForm to get data of an employee to register them in the system
     */
    public void createEmployee() {
        EmployeeInfoForm eif = new EmployeeInfoForm();
        eif.registerEmployee();
    }

    /**
     * removes an employee from the EmployeeList treemap and also updates the employee record stored in the file accordingly
     * @param employeeID unique id of employee that is to be removed
     * @throws NoSuchFieldException if employee is not found
     */
    public void removeEmployee(String employeeID) throws NoSuchFieldException {
        if (EmployeeList.containsKey(employeeID)) {
            EmployeeList.remove(employeeID);
            exportEmployee("EmployeeRecord.txt");
        } else throw new NoSuchFieldException();


    }

    /**
     * Stores the constructor values needed to recreate the employee to a file.
     * By doing so this function essentially stores the employee object to the file.
     *
     * @param fileName the name of the file in which this data is to be stored
     */
    @SuppressWarnings("rawtypes")
    public void exportEmployee(String fileName) {
        Set<Map.Entry<String, Employee>> set = EmployeeList.entrySet();
        if (set.size() == 0) //if no element's left, rewrite the file with no text
        {
            String info = "";

            try {
                FileWriter out = new FileWriter(fileName, false);
                out.write(info);
                out.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error While Writing The Employee File", "Task Failed", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            for (Object o : set) {
                Map.Entry mapEntry = (Map.Entry) o;
                String info = getEmployeeConstructorValuesAsCsvString((Employee) mapEntry.getValue());
                try {
                    FileWriter out = new FileWriter(fileName, false);
                    out.write(info);
                    out.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error While Writing The Employee File", "Task Failed", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * Converts employee information string with each constructor parameter separated with a comma
     * and substituting an empty field with a '-'
     *
     * @param employee An Employee object whose constructor parameters are to be converted to a string
     * @return A string with constructor parameters required to recreate the Employee object
     */
    String getEmployeeConstructorValuesAsCsvString(Employee employee) {
        StringBuilder builder = new StringBuilder();
        //param 1
        if (employee.getTitle().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getTitle()).append('|');
        //param 2
        if (employee.getFirstName().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getFirstName()).append('|');

        //param 3
        if (employee.getSurName().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getSurName()).append('|');

        //param 4
        if (employee.getHealthCardNumber().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getHealthCardNumber()).append('|');

        //param 5
        if (employee.getEmail().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getEmail()).append('|');

        //param 6
        if (employee.getDateOfBirth().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getDateOfBirth()).append('|');

        //param 7
        if (employee.getAddress().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getAddress()).append('|');

        //param 8
        if (employee.getPostalCode().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getPostalCode()).append('|');

        //param 9
        if (employee.getContactHome().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getContactHome()).append('|');

        //param 10
        if (employee.getContactCell().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getContactCell()).append('|');

        //param 11
        if (employee.getContactWork().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getContactWork()).append('|');

        //param 12
        if (employee.getEmergencyContactName().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getEmergencyContactName()).append('|');

        //param 13
        if (employee.getEmergencyContactNumber().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getEmergencyContactNumber()).append('|');

        //param 14
        if (employee.getDepartment().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getDepartment()).append('|');

        //param 15
        if (employee.getBranchCode().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getBranchCode()).append('|');

        //param 16
        if (employee.getEmployeeId().equals(""))
            builder.append('-').append('|');
        else builder.append(employee.getEmployeeId()).append('|');

        //param 17
        if (employee.getPay().equals(""))
            builder.append('-');
        else builder.append(employee.getPay());

        return builder.toString();
    }

    /**
     * Get pay of the employee
     *
     * @param employeeId Unique employee ID who's pay is enquired
     * @return pay of the employee whose employeeID was provided in the parameters
     * @throws NoSuchFieldException if employee is not found
     */
    public String getPay(String employeeId) throws NoSuchFieldException {
        if (EmployeeList.containsKey(employeeId)) {
            return EmployeeList.get(employeeId).getPay();
        } else throw new NoSuchFieldException("No such employee");

    }

    /**
     * Setter for branch code
     *
     * @param employeeId unique employee ID of the employee whose branch code is to be changed
     * @param branchCode branch code that is to be set
     * @throws NoSuchFieldException if employee is not found
     */
    public void setBranchCode(String employeeId, String branchCode) throws NoSuchFieldException {
        if (EmployeeList.containsKey(employeeId))
            EmployeeList.get(employeeId).setBranchCode(branchCode);
        else throw new NoSuchFieldException("No such employee found");


    }

    /**
     * gets pay sheet of all the employees registered in the firm
     *
     * @return a string with pay of all the employees in the firm
     */
    @SuppressWarnings({"rawtypes", "unused"})
    public String getPaySheet() {
        StringBuilder builder = new StringBuilder();

        Set<Map.Entry<String, Employee>> set = EmployeeList.entrySet();
        for (Object o : set) {
            Map.Entry mapEntry = (Map.Entry) o;
            String EmployeeId = ((Employee) mapEntry.getValue()).getEmployeeId();
            String pay = ((Employee) mapEntry.getValue()).getPay();
            builder.append(EmployeeId).append("\t$").append(pay).append('\n');
        }
        return builder.toString();
    }

    /**
     * Change the pay of an employee
     *
     * @param employeeId unique employee id of the employee whose pay is to be changed.
     * @param pay the updated value of pay
     * @throws NoSuchFieldException if employee is not found
     */
    public void changePay(String employeeId, String pay) throws NoSuchFieldException {
        if(EmployeeList.containsKey(employeeId))
            EmployeeList.get(employeeId).setPay(pay);
        else throw new NoSuchFieldException("No such employee");

    }

    /**
     * Shows information of an employee
     *
     * @param employeeId unique employee id of the employee whose info is to be returned
     * @return a string with printable information of the employee
     * @throws NoSuchFieldException if employee is not found
     */
    public String viewEmployee(String employeeId) throws NoSuchFieldException {
        if (EmployeeList.containsKey(employeeId))
            return EmployeeList.get(employeeId).toString();
        else throw new NoSuchFieldException();
    }

    /**
     * Tells if an employee ID already exist in the system
     *
     * @param employeeId Employee ID in question
     * @return true if the employee already exist in the system, false otherwise
     */
    public boolean containsEmployee(String employeeId) {
        return EmployeeList.containsKey(employeeId);
    }

}
