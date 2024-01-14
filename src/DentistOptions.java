import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * This class provides a GUI for all the features used by Dentist
 */
public class DentistOptions extends Dentist {
    private JButton viewPatientHistoryButton;
    private JButton addNotesToPatientButton;
    private JButton viewAppointmentScheduleButton;
    private JButton logOutButton;
    private JPanel DentistOptionPanel;

    /**
     * Shows Dentist's options on GUI panel and calls appropriate functions to carry out tasks selected by user
     */
    public DentistOptions() {
        //------------Panel Settings--------------------------------
        JDialog jDialog = new JDialog();
        jDialog.setTitle("Dentist's Menu");
        jDialog.setContentPane(DentistOptionPanel);
        jDialog.setMinimumSize(new Dimension(420, 420));
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        //-----------------------------------------------------------

        /*
         * view patient's history by calling the viewPatientHistory method of Dentist class and showing the output in a
         * non-editable text field
         */
        viewPatientHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField healthCardNum = getHealthCardNumDialogue("Patient's Information");


                JTextArea history = null;
                try {
                    //noinspection ConstantConditions
                    history = new JTextArea(viewPatientHistory(healthCardNum.getText()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "No Patient Found", "Task Failed", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                assert history != null;
                history.setEditable(false);
                JPanel panel = new JPanel();
                panel.add(history);
                JOptionPane.showMessageDialog(null, panel,"Patinet's History",JOptionPane.PLAIN_MESSAGE);
            }
        });
        /*
         * Adds a note to patient's file by calling the addDocNote method of dentist class
         * It also adds the datestamp at which the note is written
         */
        addNotesToPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField healthCardNum = getHealthCardNumDialogue("Add Note To Patient's File");
                //noinspection ConstantConditions
                String fileName = "PatientRecord\\" + healthCardNum.getText() + ".txt";
                File tmpDir = new File(fileName);
                if (tmpDir.exists()) {
                    JTextArea DocNote = new JTextArea(10, 20);
                    DocNote.setLineWrap(true);
                    DocNote.setWrapStyleWord(true);
                    JPanel panel = new JPanel();
                    panel.add(DocNote);

                    int action = JOptionPane.showConfirmDialog(null, panel, "Add Note to Patient's File", JOptionPane.OK_CANCEL_OPTION);
                    if (action == JOptionPane.OK_OPTION) {
                        addDocNote(healthCardNum.getText(), DocNote.getText());
                    }
                } else
                    JOptionPane.showMessageDialog(null, "No Patient Found", "Task Failed", JOptionPane.ERROR_MESSAGE);

            }
        });
        /*
         * Show's the dentist's schedule by calling viewDentistSchedule method of dentist class
         */
        viewAppointmentScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDentistSchedule();
            }
        });
        /*
         * Logs out the dentist by disposing the option panel
         */
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });
        jDialog.setVisible(true);
    }

    /**
     * Prompts a dialog to enter the health card number and returns JTextField in which the number was entered.
     *
     * @param title The label on the prompt
     * @return A JTextField in which the user entered the health card number
     */
    private static JTextField getHealthCardNumDialogue(String title) {
        JPanel healthCardInputPanel = new JPanel();
        JLabel label = new JLabel("Enter the health card number");
        JTextField healthCardNum = new JTextField(11);
        healthCardInputPanel.add(label);
        healthCardInputPanel.add(healthCardNum);
        int status = JOptionPane.showConfirmDialog(null, healthCardInputPanel, title, JOptionPane.OK_CANCEL_OPTION);
        if (status == JOptionPane.OK_OPTION)
            return healthCardNum;
        else return null;
    }

}
