import javax.swing.*;
import java.io.*;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Contains methods to do all the tasks related to front desk and methods to help implement the tasks performed by the front desk employees
 */
public class FrontDesk {
    /**
     * Schedule class sets up dentist's working hours and so it's object is needed to set up valid appointments
     */
    private static final Schedule DocSchedule = new Schedule();

    /**
     * Maps each patient to their health card number
     */
    public static TreeMap<String, Patient> PatientList;

    /**
     * Initializes PatientList and import patient's stored in the file to store them in the treemap
     */
    public FrontDesk() {
        PatientList = new TreeMap<>();
        importPatients();
    }

    /**
     * Reads the patient record file which has all the values of patient's constructor stored separated by a comma ','
     * This function reads those values and creates patients with those values and stores them in the PatientList treemap.
     * This approach is used instead of object serialization because appending serialized objects to a file causes errors.
     */
    private void importPatients() {

        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader("PatientRecord.txt"));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Missing PatientRecord.txt file in program file",
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
            Patient newPatient = new Patient(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10], info[11], info[12], info[13], info[14], info[15]);
            addPatientToList(newPatient.getHealthCardNumber(), newPatient);
        }
    }

    /**
     * Registers a new patient by calling PatientRegistrationForm method of PatientInformation class
     */
    public void registerPatient() {

        PatientInformation patient = new PatientInformation();
        patient.PatientRegistrationForm(null);

    }

    /**
     * Asks health card number of the patient and the time of appointment.
     * Based on the input calls the setAppointment method of Schedule class
     */
    public void setAppointment() {
        JPanel myPanel = new JPanel();      //Create new panel

        //-------------Get health card number----------------
        JTextField healthCardNum = new JTextField(11);
        myPanel.add(new JLabel("Patient's Health Card Number"));
        myPanel.add(healthCardNum);

        //--------------Get time of appointment-----------------------------
        String[] hours = {"9", "10", "11", "12", "13", "14", "15", "16", "17"};
        String[] min = {"00", "30"};
        JComboBox<String> hourDropDown = new JComboBox<>(hours);
        JComboBox<String> minDropDown = new JComboBox<>(min);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Hours:"));
        myPanel.add(hourDropDown);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Minutes:"));
        myPanel.add(minDropDown);
        //---------------------------------------------------------------------

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Set Appointment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String healthCardNumValue = Objects.requireNonNull(healthCardNum.getText());
            String HourValue = Objects.requireNonNull(hourDropDown.getSelectedItem()).toString();
            String MinValue = Objects.requireNonNull(minDropDown.getSelectedItem()).toString();

            boolean flag = isPatient(healthCardNumValue);   //checks if the patient is registered

            if (flag) {
                DocSchedule.setAppointment(PatientList.get(healthCardNumValue), HourValue, MinValue);
            } else {
                JOptionPane.showMessageDialog(null, "Patient is not registered",
                        "ERROR: Unregistered Patient", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    /**
     * Deletes appointment on the given time
     */
    public void deleteAppointment() {
        //creating dropdowns
        JComboBox<String> hourDropDown = new JComboBox<>(new String[]{"9", "10", "11", "12", "13", "14", "15", "16", "17"});
        JComboBox<String> minDropDown = new JComboBox<>(new String[]{"00", "30"});

        //adding hours dropdown to panel
        JPanel panel = new JPanel();
        panel.add(Box.createHorizontalStrut(15)); // a spacer
        panel.add(new JLabel("Hours:"));
        panel.add(hourDropDown);

        //adding min dropdown to panel
        panel.add(Box.createHorizontalStrut(15)); // a spacer
        panel.add(new JLabel("Minutes:"));
        panel.add(minDropDown);

        int panelResult = JOptionPane.showConfirmDialog(null, panel,
                "Delete Appointment", JOptionPane.OK_CANCEL_OPTION);
        if (panelResult == JOptionPane.OK_OPTION) {
            String HourValue = Objects.requireNonNull(hourDropDown.getSelectedItem()).toString();
            String MinValue = Objects.requireNonNull(minDropDown.getSelectedItem()).toString();
            boolean isDeleted = DocSchedule.deleteAppointment(HourValue, MinValue);  //function returns true if the appointment is deleted successfully
            if (!isDeleted) {
                JOptionPane.showMessageDialog(null, "No Appointment found", "Task Failed", JOptionPane.ERROR_MESSAGE);
            }
        }


    }

    /**
     * Generates a dialog box containing doctor's schedule (from viewSchedule method of Schedule Class) in a non-editable text box.
     */
    public void viewDentistSchedule() {
        JTextArea text = new JTextArea(DocSchedule.viewSchedule());
        text.setEditable(false);
        JOptionPane.showMessageDialog(null, text,"Dentist's Schedule",JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * opens the checkout panel from Payment class
     */
    public void checkout() {
        Payment payment = new Payment();
        payment.getPaymentForm();
    }

    /**
     * Reads the csv of affiliated pharmacies of pattern "name,address,fax number". The function then preview their information in a dialog box.
     * @throws IOException if the process of file reading fails.
     */
    public void faxNumbers() throws IOException {
        //-----------------------reading the file---------------------------------------
        BufferedReader reader = new BufferedReader(new FileReader("PharmaciesInfo.csv"));
        String line;
        StringBuilder name = new StringBuilder();
        StringBuilder address = new StringBuilder();
        StringBuilder number = new StringBuilder();

        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException("File not found");
            }
            String[] Pharmacy = line.split(",");
            name.append(Pharmacy[0]).append("\n");
            address.append(Pharmacy[1]).append("\n");
            number.append(Pharmacy[2]).append("\n");

        }
        reader.close();

        //---------------------------Viewing info in the dialog box -------------------------------
        JTextArea NameTextArea = new JTextArea(name.toString());
        JTextArea AddressTextArea = new JTextArea(address.toString());
        JTextArea NumberTextArea = new JTextArea(number.toString());

        NameTextArea.setEditable(false);
        AddressTextArea.setEditable(false);
        NumberTextArea.setEditable(false);

        JPanel panel = new JPanel();

        panel.add(NameTextArea);
        panel.add(AddressTextArea);
        panel.add(NumberTextArea);

        JOptionPane.showMessageDialog(null, panel,"List of Affiliated Pharmacies",JOptionPane.PLAIN_MESSAGE);

    }

    /**
     * Checks if a patient is registered in the system or not
     *
     * @param healthCardNum Health card number of the patient whose status is to be checked
     * @return boolean true if the patient is registered in the system, false otherwise
     */
    public boolean isPatient(String healthCardNum) {
        return PatientList.containsKey(healthCardNum);
    }

    /**
     * Adds patient to the treeMap PatientList
     *
     * @param patient          The patient object to be added to the treeMap.
     * @param HealthCardNumber Health Card Number of the patient to be added to the treeMap.
     */
    public void addPatientToList(String HealthCardNumber, Patient patient) {
        PatientList.put(HealthCardNumber, patient);
    }

    /**
     * Returns a patient object of the given health card number if the patient is registered in the system
     *
     * @param healthCardNumber health card number of the patient.
     * @return Patient object of the patient with the health card number provided in parameter if the patient is registered,
     * returns null otherwise.
     */
    public Patient getPatient(String healthCardNumber) {
        return PatientList.getOrDefault(healthCardNumber, null);
    }

}
