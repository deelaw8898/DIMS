import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Collects patient's information required to make a Patient object, using a GUI form
 */
public class PatientInformation extends FrontDesk {
    /**
     * Indicates whether the process of registration is successful or not
     */
    private boolean flag;
    /**
     * First name of the patient
     */
    private JTextField FirstNameValue;
    /**
     * Last name of the patient
     */
    private JTextField SurnameValue;
    /**
     * Health card number of the patient
     */
    private JTextField HealthCardNumValue;
    /**
     * Title of the patient (Mr, Ms. Dr. ets)
     */
    private JTextField TitleValue;
    /**
     * Email Id of the patient
     */
    private JTextField EmailValue;
    /**
     * Date of birth of the patient
     */
    private JTextField DateOfBirthValue;
    /**
     * Address of the patient
     */
    private JTextField AddressValue;
    /**
     * Postal code of the patient
     */
    private JTextField PostalCodeValue;
    /**
     * Cellphone number of the patient
     */
    private JTextField ContactCellValue;
    /**
     * Word-phone number of the patient
     */
    private JTextField ContactWorkValue;
    /**
     * Home-phone number of the patient
     */
    private JTextField ContactHomeValue;
    /**
     * Name of the person to be contacted in the case of an emergency regarding the patient
     */
    private JTextField EmergencyContactNameValue;
    /**
     * Contact Number of the person to be contacted in the case of an emergency regarding the patient
     */
    private JTextField EmergencyContactNumValue;
    /**
     * Occupation of the patient
     */
    private JTextField OccupationValue;
    /**
     * Employer of the patient
     */
    private JTextField EmployerValue;
    /**
     * Name of the doctor/clinic that referred the patient
     */
    private JTextField ReferredByValue;
    /**
     * Panel containing all the fields
     */
    private JPanel registerPanel;
    private JButton nextButton;

    /**
     * Medical history of the patient
     */
    static String MedicalInfo;


    /**Default Constructor*/
    public PatientInformation() {

    }

    /**
     * views a registration form and calls appropriate method to complete the registration processes
     */
    public void PatientRegistrationForm(JFrame parent) {
        //------------panel settings-------------------------
        JDialog jDialog = new JDialog(parent);
        jDialog.setTitle("Create a new account");
        jDialog.setContentPane(registerPanel);
        jDialog.setMinimumSize(new Dimension(700, 474));
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        //---------------------------------------------------------
        /**Checks if all the mandatory fields are filled, saves the collected information and calls MedicalInformation class to collect further information */
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean alreadyExist = isPatient(HealthCardNumValue.getText());
                if(alreadyExist)
                    JOptionPane.showMessageDialog(null,"A Patient with this health card number already exist","Patient already exist",JOptionPane.ERROR_MESSAGE);
                if (isMandatoryFieldsFilled() && !alreadyExist) {
                    saveAndNext(jDialog);   //calls Medical information class to collect further information
                    jDialog.setVisible(false);  //hides the current panel
                }

            }
        });
        jDialog.setVisible(true);
        register(); //registers a patient
    }

    /**
     * Checks if the Mandatory fields are filled, if not the function changes the color of the unfilled feilds to red
     */
    private boolean isMandatoryFieldsFilled() {
        flag = true;
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
        return flag;
    }

    /**
     * Calls MedicalInformationForm method of MedicalInformation class to collect further information about patient's medical history
     */
    private void saveAndNext(JDialog jDialog) {
        MedicalInformation medInfo = new MedicalInformation();
        flag = medInfo.MedicalInformationForm(null);    //flag is set to true if the MedicalInformationForm collects patient's info successfully
        jDialog.dispose();


    }

    private void register() {
        if (flag) //if other processes required for registration have been completed
        {
            String healthCardNum = HealthCardNumValue.getText();

            //makes new patient
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

            addPatientToList(healthCardNum, newPatient);    //add patient to the list in FrontDesk
            addPatientInfoToFile(newPatient);   //adds patient's medical information to a file
            addPatientObjectToFile("PatientRecord.txt");    //add patient's information required to recreate the patient object, to the file
        }
    }

    /**
     * Stores MedicalHistory of the patient in the file with the filename of patient's health card number with .txt extension
     *
     * @param newPatient a Patient object that is to be added to the file
     */
    private void addPatientInfoToFile(Patient newPatient) {
        StringBuilder builder = new StringBuilder();
        String info = newPatient.toString();
        builder.append(info).append("\n\n\n").append(MedicalInfo);
        String fileName = "PatientRecord\\" + newPatient.getHealthCardNumber() + ".txt";
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(builder.toString());
            myWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error While Writing The Patient's File", "Task Failed", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }

    /**
     * Stores the constructor values needed to recreate the Patient, to a file.
     * By doing so this function essentially stores the employee object to the file.
     *
     * @param fileName the name of the file in which this data is to be stored
     */
    private void addPatientObjectToFile(String fileName) {

        String info = getPatientInfoString();   //get information of patient in a comma separated format with each entry being the input value for Patient constructor to recreate the Patient

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            out.println(info);
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error While Writing The Patient's File", "Task Failed", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    /**
     * Formats the information of patient in a comma separated format with each entry being the input value for Patient constructor to recreate the Patient
     */
    private String getPatientInfoString() {
        StringBuilder builder = new StringBuilder();
        if (TitleValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(TitleValue.getText()).append('|');

        if (FirstNameValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(FirstNameValue.getText()).append('|');

        if (SurnameValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(SurnameValue.getText()).append('|');

        if (HealthCardNumValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(HealthCardNumValue.getText()).append('|');

        if (EmailValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(EmailValue.getText()).append('|');

        if (DateOfBirthValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(DateOfBirthValue.getText()).append('|');

        if (AddressValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(AddressValue.getText()).append('|');

        if (PostalCodeValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(PostalCodeValue.getText()).append('|');

        if (ContactHomeValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(ContactHomeValue.getText()).append('|');

        if (ContactCellValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(ContactCellValue.getText()).append('|');

        if (ContactWorkValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(ContactWorkValue.getText()).append('|');

        if (EmergencyContactNameValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(EmergencyContactNameValue.getText()).append('|');

        if (EmergencyContactNumValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(EmergencyContactNumValue.getText()).append('|');

        if (OccupationValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(OccupationValue.getText()).append('|');

        if (EmployerValue.getText().equals(""))
            builder.append('-').append('|');
        else builder.append(EmployerValue.getText()).append('|');

        if (ReferredByValue.getText().equals(""))
            builder.append('-');
        else builder.append(ReferredByValue.getText());
        return builder.toString();
    }


}


