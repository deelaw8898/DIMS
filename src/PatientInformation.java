import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class PatientInformation extends FrontDesk {
    private boolean flag;
    private JTextField FirstNameValue;
    private JTextField SurnameValue;
    private JTextField HealthCardNumValue;
    private JTextField TitleValue;
    private JTextField EmailValue;
    private JTextField DateOfBirthValue;
    private JTextField AddressValue;
    private JTextField PostalCodeValue;
    private JTextField ContactCellValue;
    private JTextField ContactWorkValue;
    private JTextField ContactHomeValue;
    private JTextField EmergencyContactNameValue;
    private JTextField EmergencyContactNumValue;
    private JTextField OccupationValue;
    private JTextField EmployerValue;
    private JPanel registerPanel;
    private JButton nextButton;
    private JTextField ReferredByValue;

    static String MedicalInfo;


    public PatientInformation() {

    }

    public void PatientRegistrationForm(JFrame parent) {
        JDialog jDialog = new JDialog(parent);
        jDialog.setTitle("Create a new account");
        jDialog.setContentPane(registerPanel);
        jDialog.setMinimumSize(new Dimension(700, 474));
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMandatoryFieldsFilled() || true) //Change this line after testing
                {
                    saveAndNext(jDialog);
                    jDialog.setVisible(false);
                }
                else isMandatoryFieldsFilled();

            }
        });
        jDialog.setVisible(true);
        register();
    }

    private boolean isMandatoryFieldsFilled() {
        flag = true;
        if(FirstNameValue.getText().equals(""))
        {
            FirstNameValue.setBackground(Color.red);
            flag = false;
        }
        if(SurnameValue.getText().equals(""))
        {
            SurnameValue.setBackground(Color.red);
            flag = false;
        }
        if(HealthCardNumValue.getText().equals(""))
        {
            HealthCardNumValue.setBackground(Color.red);
            flag = false;
        }
        if(AddressValue.getText().equals(""))
        {
            AddressValue.setBackground(Color.red);
            flag = false;
        }
        if(PostalCodeValue.getText().equals(""))
        {
            PostalCodeValue.setBackground(Color.red);
            flag = false;
        }
        if(ContactCellValue.getText().equals(""))
        {
            ContactCellValue.setBackground(Color.red);
            flag = false;
        }
        if(EmergencyContactNameValue.getText().equals(""))
        {
            EmergencyContactNameValue.setBackground(Color.red);
            flag = false;
        }
        if(EmergencyContactNumValue.getText().equals(""))
        {
            EmergencyContactNumValue.setBackground(Color.red);
            flag = false;
        }
        return flag;
    }

    private void register() {
        if (flag) {
            String healthCardNum = "123-456-789";
            Patient newPatient = new Patient(TitleValue.getText(),
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
                    OccupationValue.getText(),
                    EmployerValue.getText(),
                    ReferredByValue.getText());

            addPatientToList(healthCardNum, newPatient);
            addPatientToFile(newPatient);
        }
    }

    private void addPatientToFile(Patient newPatient) {
        StringBuilder builder = new StringBuilder();
        String info = newPatient.toString();
        builder.append(info).append("\n\n\n").append(MedicalInfo);
        String fileName = "PatientRecord\\" + newPatient.getHealthCardNumber() + ".txt";
        try
        {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(builder.toString());
            myWriter.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error While Writing The Patient's File", "Task Failed", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }

    private void saveAndNext(JDialog jDialog) {
        MedicalInformation medInfo = new MedicalInformation();
        flag = medInfo.MedicalInformationForm(null);
        jDialog.dispose();


    }


}


