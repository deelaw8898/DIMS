/**
 * This class is a representation of an employee working at the firm
 */
public class Employee extends Person{
    /**
     * Department of the employee, (A dentist, A front desk employee or an HR)
     */
    private String Department;
    /**
     * Branch code of the clinic
     */
    private String BranchCode;
    /**
     * A unique ID assigned to every employee for identification purposes.
     */
    private String EmployeeId;
    /**
     * Salary of the employee
     */
    private String Pay;

    /**
     * Constructor sets the values of all the attributes
     */
    public Employee(String title, String firstName, String surName, String healthCardNumber, String email, String dateOfBirth, String address, String postalCode, String contactHome, String contactCell, String contactWork, String emergencyContactName, String emergencyContactNumber, String department, String branchCode, String employeeId, String pay) {
        super(title, firstName, surName, healthCardNumber, email, dateOfBirth, address, postalCode, contactHome, contactCell, contactWork, emergencyContactName, emergencyContactNumber);
        Department = department;
        BranchCode = branchCode;
        Pay = pay;
        EmployeeId = employeeId;
    }
    /**
     * an empty constructor
     */
    public Employee() {

    }

    /**
     * Setter function for Branch Code
     * @param branchCode New branch code value
     */
    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    /**
     * Setter function for Pay
     * @param pay New salary/pay of the employee
     */
    public void setPay(String pay) {
        Pay = pay;
    }

    /**
     * Getter function for the Department
     * @return Department of the employee*/
    public String getDepartment() {
        return Department;
    }

    /**
     * Getter function for the branch code
     * @return Branch code of the clinic at which the employee works
     */
    public String getBranchCode() {
        return BranchCode;
    }

    /**
     * Getter function for employee ID
     * @return Employee ID of the employee
     */
    public String getEmployeeId() {
        return EmployeeId;
    }

    /**
     * Getter function for the pay
     * @return pay of the employee
     */
    public String getPay() {
        return Pay;
    }

    /**
     * returns a string containing information of the employee in a print-friendly manner
     * @return A string containing information of the employee
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nDepartment\t\t" + Department +
                "\nBranchCode\t\t" + BranchCode +
                "\nEmployeeId\t\t" + EmployeeId +
                "\nSalary\t\t" + Pay;
    }
}
