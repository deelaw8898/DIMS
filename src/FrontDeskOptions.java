import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class provides a GUI for all the features used by front desk
 */
public class FrontDeskOptions {
    private JButton RegisterNewPatient;
    private JButton SetAppointment;
    private JButton DeleteAppointment;
    private JButton ViewSchedule;
    private JButton Checkout;
    private JButton FaxNumList;
    private JButton isPatient;
    private JButton ViewPatient;
    private JButton logOutButton;
    private JPanel frontDeskOptionsPanel;

    /**
     * The constructor makes the GUI panel that shows all the front desk options as buttons and upon clicking any button calls appropriate method form FrontDesk Class
     */
    public FrontDeskOptions() {
        FrontDesk frontDesk = new FrontDesk();

        //------------Panel Settings--------------------------------
        JDialog jDialog = new JDialog();
        jDialog.setTitle("Front Desk Options");
        jDialog.setContentPane(frontDeskOptionsPanel);
        jDialog.setMinimumSize(new Dimension(420, 420));
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        //-----------------------------------------------------------

        //---------Action Listeners of all the buttons in the form--------

        //Registers a patient by calling registerPatient() method of FrontDesk Class
        RegisterNewPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.registerPatient();
            }
        });
        /*
          Sets appointment of a patient by calling setAppointment() method of FrontDesk Class
         */
        SetAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.setAppointment();
            }
        });
        /*
          Delete appointment of a patient by calling deleteAppointment() method of FrontDesk Class
         */
        DeleteAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.deleteAppointment();
            }
        });
        /*
          View schedule of dentist by calling viewDentistSchedule() method of FrontDesk Class
         */
        ViewSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.viewDentistSchedule();
            }
        });
        /*
          View checkout panel by calling checkout() method of FrontDesk Class
         */
        Checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.checkout();
            }
        });
        /*
          View affiliated pharmacies' address and fax numbers by calling faxNumbers() method of FrontDesk Class
         */
        FaxNumList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frontDesk.faxNumbers();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error while reading the file", "Task Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Checks whether a patient is registered in the system prompting to get a health card number and checking it with isPatient method of FrontDesk Class.
        isPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JTextField input = new JTextField(11);
                JLabel label = new JLabel("Enter Health Card Number");
                panel.add(label);
                panel.add(input);
                int result = JOptionPane.showConfirmDialog(null, panel, "Check Patient Registration Status", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    boolean isPatient = frontDesk.isPatient(input.getText());
                    if (isPatient) {
                        JOptionPane.showMessageDialog(null, "Patient is registered in the system", "Patient Found", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient not found in the system", "Patient Not Found", JOptionPane.PLAIN_MESSAGE);
                    }
                }

            }
        });
        // View information of patient registered in system by prompting to get a health card number and checking it with getPatient method of FrontDesk Class.
        ViewPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JTextField input = new JTextField(11);
                JLabel label = new JLabel("Enter Health Card Number");
                panel.add(label);
                panel.add(input);
                int result = JOptionPane.showConfirmDialog(null, panel, "View Patient", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Patient patient = frontDesk.getPatient(input.getText());

                    if (patient != null) {
                        JTextArea patientInfo = new JTextArea(patient.toString(), 20, 50);
                        patientInfo.setEditable(false);
                        JOptionPane.showMessageDialog(null, patientInfo,"Patient Information",JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient not found in the system", "Patient Not Found", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });

        jDialog.setVisible(true);
    }


}
