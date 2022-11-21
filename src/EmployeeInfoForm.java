import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void EmployeeRegistrationForm(JFrame parent) {
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
        register(jDialog);
    }

    private void register(JDialog jDialog) {
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

    }

}
