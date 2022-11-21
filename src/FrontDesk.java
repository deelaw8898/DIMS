import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.TreeMap;

public class FrontDesk extends Employee {
    private static final Schedule DocSchedule = new Schedule();

    public TreeMap<String, Patient> PatientList;    //healthCardNum --> Patient

    public FrontDesk() {
        PatientList = new TreeMap<>();
    }

    public void registerPatient() {

        PatientInformation patient = new PatientInformation();
        patient.PatientRegistrationForm(null);

    }

    public void setAppointment() {
        String[] hours = {"9", "10", "11", "12", "13", "14", "15", "16", "17"};
        String[] min = {"00", "30"};
        JTextField healthCardNum = new JTextField(11);

        JComboBox<String> hourDropDown = new JComboBox<>(hours);
        JComboBox<String> minDropDown = new JComboBox<>(min);


        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Patient's Health Card Number"));
        myPanel.add(healthCardNum);

        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Hours:"));
        myPanel.add(hourDropDown);


        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Minutes:"));
        myPanel.add(minDropDown);


        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Set Appointment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String healthCardNumValue = Objects.requireNonNull(healthCardNum.getText());
            String HourValue = Objects.requireNonNull(hourDropDown.getSelectedItem()).toString();
            String MinValue = Objects.requireNonNull(minDropDown.getSelectedItem()).toString();

            if (isPatient(healthCardNumValue)) {
                DocSchedule.setAppointment(PatientList.get(healthCardNumValue), HourValue, MinValue);
            } else {
                JOptionPane.showMessageDialog(null, "Patient is not registered",
                        "ERROR: Unregistered Patient", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void deleteAppointment() {
        JPanel optionPanel = new JPanel();
        JCheckBox delByHealthCardNum = new JCheckBox("Delete Appointment By Health Card Number");
        JCheckBox delByTime = new JCheckBox("Delete Appointment By Time");

        ButtonGroup group = new ButtonGroup();
        group.add(delByHealthCardNum);
        group.add(delByTime);

        optionPanel.add(delByTime);
        optionPanel.add(delByHealthCardNum);

        int selection = JOptionPane.showConfirmDialog(null, optionPanel,
                "Delete Appointment", JOptionPane.OK_CANCEL_OPTION);

        if (selection == JOptionPane.OK_OPTION) {
            if (delByHealthCardNum.isSelected()) {
                JPanel subPanel = new JPanel();
                JLabel label = new JLabel("Enter the health card number");
                JTextField healthCardNum = new JTextField(11);
                subPanel.add(label);
                subPanel.add(healthCardNum);
                int subPanelResult = JOptionPane.showConfirmDialog(null, optionPanel, "Delete by Health Card Number", JOptionPane.OK_CANCEL_OPTION);
                if (subPanelResult == JOptionPane.OK_OPTION) {
                    System.out.println("Feature Not Implemented Yet");
                    /*
                    boolean isDeleted = DocSchedule.deleteAppointment(healthCardNum.getText());
                    if(!isDeleted)
                    {
                        JOptionPane.showMessageDialog(null,"No Appointment found","Task Failed",JOptionPane.ERROR_MESSAGE);
                    }

                     */
                }
            }
            if (delByTime.isSelected()) {
                String[] hours = {"9", "10", "11", "12", "13", "14", "15", "16", "17"};
                String[] min = {"00", "30"};
                JComboBox<String> hourDropDown = new JComboBox<>(hours);
                JComboBox<String> minDropDown = new JComboBox<>(min);

                JPanel subPanel = new JPanel();
                subPanel.add(Box.createHorizontalStrut(15)); // a spacer
                subPanel.add(new JLabel("Hours:"));
                subPanel.add(hourDropDown);

                subPanel.add(Box.createHorizontalStrut(15)); // a spacer
                subPanel.add(new JLabel("Minutes:"));
                subPanel.add(minDropDown);

                int subPanelResult = JOptionPane.showConfirmDialog(null, subPanel,
                        "Delete Appointment", JOptionPane.OK_CANCEL_OPTION);
                if (subPanelResult == JOptionPane.OK_OPTION) {
                    String HourValue = Objects.requireNonNull(hourDropDown.getSelectedItem()).toString();
                    String MinValue = Objects.requireNonNull(minDropDown.getSelectedItem()).toString();
                    boolean isDeleted = DocSchedule.deleteAppointment(HourValue, MinValue);
                    if (!isDeleted) {
                        JOptionPane.showMessageDialog(null, "No Appointment found", "Task Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public void viewDentistSchedule() {
        JTextArea text = new JTextArea(DocSchedule.viewSchedule());
        text.setEditable(false);
        JOptionPane.showMessageDialog(null, text);
    }

    public void checkout() {
        Payment payment = new Payment();
        payment.getPaymentForm();

    }

    public void faxNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("PharmaciesInfo.csv"));
        String line = "";

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

        JOptionPane.showMessageDialog(null, panel);

    }

    public boolean isPatient(String healthCardNum) {
        return PatientList.containsKey(healthCardNum);
    }

    public void viewOptions() {
        System.out.println("""
                1) Register New Patient
                2) Set Appointment
                3) Delete Appointment
                4) View Dentist's Schedule
                5) Checkout
                6) Fax Numbers List of Pharmacies
                7) Add Hours
                0) Quit Program""");
        boolean flag = false;

    }

    public void addPatientToList(String HealthCardNumber, Patient patient) {
        PatientList.put(HealthCardNumber, patient);
    }


    public Patient getPatient(String healthCardNumber) {
        return PatientList.getOrDefault(healthCardNumber, null);
    }


    public static void main(String[] args) {
        FrontDesk fd = new FrontDesk();
        try {
            fd.faxNumbers();
        } catch (IOException e) {
            throw new RuntimeException("Error while reading faxNumbers file");
        }
        //fd.checkout();
        fd.registerPatient();
        Patient patient = new Patient("Mr", "waleed", "Ahmad", "123-456-789", "walee8898@gmail.com", "18/Nov/1999", "Konoha", "1H2 3J4", "090078601", "", "", "Kenpachi", "000000", "Soul Reaper", "Soul Society", "captain Yamamoto");
        fd.addPatientToList("123", patient);
        fd.setAppointment();
        fd.viewDentistSchedule();
        fd.deleteAppointment();
        System.out.println(fd.DocSchedule.viewSchedule());
    }

}
