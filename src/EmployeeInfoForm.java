import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class EmployeeInfoForm extends HR {
    private JPanel registerPanel;
    private JTextField FirstNameValue;
    private JTextField SurnameValue;
    private JTextField HealthCardNumValue;
    private JTextField EmailValue;
    private JTextField DateOfBirthValue;
    private JTextField AddressValue;
    private JTextField TitleValue;
    private JTextField PostalCodeValue;
    private JTextField ContactCellValue;
    private JTextField ContactWorkValue;
    private JTextField ContactHomeValue;
    private JTextField EmergencyContactNameValue;
    private JTextField EmergencyContactNumValue;
    private JTextField DepartmentValue;
    private JTextField BranchCodeValue;
    private JButton nextButton;
    private JTextField PayValue;
    private JTextField EmployeeIdValue;

    public void EmployeeRegistrationForm()
    {

    }
    public void registerEmployee() {
        JFrame parent=null;
        JDialog jDialog = new JDialog(parent);
        jDialog.setTitle("Create a new employee");
        jDialog.setContentPane(registerPanel);
        jDialog.setMinimumSize(new Dimension(700, 474));
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });
        jDialog.setVisible(true);
        setUserNamePassword();
        storeEmployeeObjectInParentClass(jDialog);
    }

    private void setUserNamePassword() {
        boolean flag = true;
        while(flag)
        {
            JPanel panel = new JPanel();
            JLabel userNameLabel = new JLabel("User Name");
            String username = EmployeeIdValue.getText() + '@' + DepartmentValue.getText();
            JTextField UserNametextField = new JTextField(username);

            JLabel PasswordLabel = new JLabel("Enter Password");
            JPasswordField passwordField = new JPasswordField(11);
            JLabel ConfirmPasswordLabel = new JLabel("Confirm Password");
            JPasswordField ConfirmPasswordField = new JPasswordField(11);

            panel.add(userNameLabel);
            panel.add(UserNametextField);
            panel.add(PasswordLabel);
            panel.add(passwordField);
            panel.add(ConfirmPasswordLabel);
            panel.add(ConfirmPasswordField);
            int status = JOptionPane.showConfirmDialog(null, panel, "Set Password", JOptionPane.OK_CANCEL_OPTION);
            if(status==JOptionPane.OK_OPTION)
            {
                if(Arrays.equals(passwordField.getPassword(), ConfirmPasswordField.getPassword()))
                {
                    flag = false;
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
                }
                else {
                    JOptionPane.showMessageDialog(null,"Password Did Not Match","Password Mismatch",JOptionPane.ERROR_MESSAGE);
                }
            }
        }




    }

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


        putEmployeeInList("123", employee);
        putEmployeeInFile(employee, "EmployeeRecord.txt");

    }

    private void putEmployeeInFile(Employee employee, String fileName) {
        String info = getEmployeeInfo(employee);
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            out.println(info);
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error While Writing The Employee File", "Task Failed", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        EmployeeInfoForm eif = new EmployeeInfoForm();
        eif.setUserNamePassword();
    }

}
