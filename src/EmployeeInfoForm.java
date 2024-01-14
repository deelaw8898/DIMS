import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Collects all the essential information to create an employee using GUI tools
 */
public class EmployeeInfoForm extends HR {
    /**
     * Panel that contains all the text-fields to collect data
     */
    private JPanel registerPanel;
    /**
     * Title of the employee (Mr., Ms., Dr. etc)
     */
    private JTextField TitleValue;
    /**
     * First name of the employee
     */
    private JTextField FirstNameValue;
    /**
     * Surname of the employee
     */
    private JTextField SurnameValue;
    /**
     * Health card number of the employee
     */
    private JTextField HealthCardNumValue;
    /**
     * Email id of the employee
     */
    private JTextField EmailValue;
    /**
     * Date of birth of the employee
     */
    private JTextField DateOfBirthValue;
    /**
     * Address of the employee
     */
    private JTextField AddressValue;
    /**
     * Postal code of the employee
     */
    private JTextField PostalCodeValue;
    /**
     * Cellphone number of the employee
     */
    private JTextField ContactCellValue;
    /**
     * Work-phone number of the employee
     */
    private JTextField ContactWorkValue;
    /**
     * Home phone number of the employee
     */
    private JTextField ContactHomeValue;
    /**
     * Name of the person to be contacted in the case of emergency related to the employee
     */
    private JTextField EmergencyContactNameValue;
    /**
     * Contact number of the person to be contacted in the case of emergency related to the employee
     */
    private JTextField EmergencyContactNumValue;
    /**
     * Department of the employee (Dentist, Front-desk, HR)
     */
    private JTextField DepartmentValue;
    /**
     * Branch code of the clinic where the employee works
     */
    private JTextField BranchCodeValue;
    /**
     * Salary of the employee
     */
    private JTextField PayValue;
    /**
     * Unique ID assigned to an employee for identification purposes
     */
    private JTextField EmployeeIdValue;
    /**
     * Next button
     */
    private JButton nextButton;

    /**
     * Views the GUI form to register an employee
     */
    public void registerEmployee() {
        //--------------panel settings------------------------
        JFrame parent = null;
        JDialog jDialog = new JDialog(parent);
        jDialog.setTitle("Create a new employee");
        jDialog.setContentPane(registerPanel);
        jDialog.setMinimumSize(new Dimension(700, 474));
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        //--------------------------------------------------------

        /*
          Disposes the panel if next is clicked*/
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = isMandatoryFieldsFilled();
                boolean alreadyExist = containsEmployee(EmployeeIdValue.getText());
                if (valid) {
                    if (alreadyExist) {
                        JOptionPane.showMessageDialog(null, "An employee with this employee ID already exists", "Employee ID already exist", JOptionPane.ERROR_MESSAGE);
                        EmployeeIdValue.setBackground(Color.red);
                        valid = false;
                    }
                    if (valid)   //if process is still valid, set password and check for it's success
                    {
                        valid = setUserNamePassword();
                    }

                    if (valid)  //if password is set successfully i.e., process is still valid
                    {
                        storeEmployeeObjectInParentClass(jDialog);  //stores all the collected information in the HR class
                        jDialog.dispose();
                    }
                }
                //else isMandatoryFieldsFilled();


            }
        });

        jDialog.setVisible(true);

    }

    private boolean isMandatoryFieldsFilled() {
        boolean flag = true;
        if (FirstNameValue.getText().equals("")) {
            FirstNameValue.setBackground(Color.red);
            flag = false;
        }
        if (SurnameValue.getText().equals("")) {
            SurnameValue.setBackground(Color.red);
            flag = false;
        }
        if (HealthCardNumValue.getText().equals("")) {
            HealthCardNumValue.setBackground(Color.red);
            flag = false;
        }
        if (AddressValue.getText().equals("")) {
            AddressValue.setBackground(Color.red);
            flag = false;
        }
        if (PostalCodeValue.getText().equals("")) {
            PostalCodeValue.setBackground(Color.red);
            flag = false;
        }
        if (ContactCellValue.getText().equals("")) {
            ContactCellValue.setBackground(Color.red);
            flag = false;
        }
        if (EmergencyContactNameValue.getText().equals("")) {
            EmergencyContactNameValue.setBackground(Color.red);
            flag = false;
        }
        if (EmergencyContactNumValue.getText().equals("")) {
            EmergencyContactNumValue.setBackground(Color.red);
            flag = false;
        }
        if (EmployeeIdValue.getText().equals("")) {
            EmployeeIdValue.setBackground(Color.red);
            flag = false;
        }
        if (EmployeeIdValue.getText().equals("")) {
            EmployeeIdValue.setBackground(Color.red);
            flag = false;
        }
        if (DepartmentValue.getText().equals("")) {
            DepartmentValue.setBackground(Color.red);
            flag = false;
        }
        if (!(DepartmentValue.getText().equals("hr") | DepartmentValue.getText().equals("frontdesk") | DepartmentValue.getText().equals("dentist"))) {
            JOptionPane.showMessageDialog(null, "Invalid department");
            DepartmentValue.setBackground(Color.red);
            flag = false;
        }

        if (BranchCodeValue.getText().equals("")) {
            BranchCodeValue.setBackground(Color.red);
            flag = false;
        }
        if (PayValue.getText().equals("")) {
            PayValue.setBackground(Color.red);
            flag = false;
        }

        return flag;
    }

    /**
     * Prompts user to set up a password and saves that password in the file
     *
     * @return true if the password is set successfully, false otherwise
     */
    private boolean setUserNamePassword() {
        while (true) //keep prompting until password matches with the "confirm password" field
        {
            //--------------creating panel elements------------------------
            JPanel panel = new JPanel();
            JLabel userNameLabel = new JLabel("User Name");
            String username = EmployeeIdValue.getText() + '@' + DepartmentValue.getText();  //generates the username based on the employee ID in the format of "employeeId@department"
            JTextField UserNameTextField = new JTextField(username);

            JLabel PasswordLabel = new JLabel("Enter Password");
            JPasswordField passwordField = new JPasswordField(11);
            JLabel ConfirmPasswordLabel = new JLabel("Confirm Password");
            JPasswordField ConfirmPasswordField = new JPasswordField(11);

            //------------------add elements to panel--------------------------------
            panel.add(userNameLabel);
            panel.add(UserNameTextField);
            panel.add(PasswordLabel);
            panel.add(passwordField);
            panel.add(ConfirmPasswordLabel);
            panel.add(ConfirmPasswordField);
            int status = JOptionPane.showConfirmDialog(null, panel, "Set Password", JOptionPane.OK_CANCEL_OPTION);

            if (status == JOptionPane.OK_OPTION)    //if ok is pressed, set the password
            {
                //if password matches with ConfirmPassword field then store the password to a file and break the loop
                if (Arrays.equals(passwordField.getPassword(), ConfirmPasswordField.getPassword())) {
                    if (passwordField.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(null, "Password Field is Empty", "Invalid Password", JOptionPane.ERROR_MESSAGE);

                    } else {
                        String entry = username + '|' + (Arrays.toString(passwordField.getPassword()));
                        //write username and password to file
                        try {
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("usernames.txt", true)));
                            out.println(entry);
                            out.close();
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Error While Writing The usernames.txt File", "Task Failed", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                        return true;    //password set successfully
                    }

                } else //if password fields don't match
                {
                    JOptionPane.showMessageDialog(null, "Password Did Not Match", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                }

            } else if (status == JOptionPane.OK_CANCEL_OPTION | status == JOptionPane.CLOSED_OPTION) {
                return false;

            }
        }
    }

    /**
     * Creates an Employee object and stores it in the HR's EmployeeList then updates the record file stored on the drive
     */
    private void storeEmployeeObjectInParentClass(JDialog jDialog) {
        Employee employee = new Employee(TitleValue.getText(),
                FirstNameValue.getText(),
                SurnameValue.getText(),
                HealthCardNumValue.getText(),
                EmailValue.getText(),
                DateOfBirthValue.getText(),
                AddressValue.getText(),
                PostalCodeValue.getText(),
                ContactHomeValue.getText(),
                ContactCellValue.getText(),
                ContactWorkValue.getText(),
                EmergencyContactNameValue.getText(),
                EmergencyContactNumValue.getText(),
                DepartmentValue.getText(),
                BranchCodeValue.getText(),
                EmployeeIdValue.getText(),
                PayValue.getText());


        putEmployeeInList(employee.getEmployeeId(), employee);
        putEmployeeInFile(employee, "EmployeeRecord.txt");

    }

    /**
     * Given an employee object and a filename this method stores the constructor values of that employee, required to recreate the object, to the file
     *
     * @param employee an Employee object whose information is to be stored in the file.
     *                 The information is stored in a pattern "ctrVal1,ctrVal2,......"
     *                 where ctrVal are the constructor values required to recreate the object
     * @param fileName Name of the .txt file where the information is to be stored.
     *                 The method is designed while keeping the .txt file in consideration,
     *                 putting filenames with other extensions might cause unexpected behaviour.
     *                 The extension has to be included in the filename parameter
     */
    private void putEmployeeInFile(Employee employee, String fileName) {
        String info = getEmployeeConstructorValuesAsCsvString(employee);
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
