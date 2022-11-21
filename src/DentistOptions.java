import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DentistOptions extends Dentist {
    private JButton viewPatientHistoryButton;
    private JButton addNotesToPatientButton;
    private JButton viewAppointmentScheduleButton;
    private JButton logOutButton;
    private JPanel DentistOptionPanel;

    public DentistOptions() {
        JDialog jDialog = new JDialog();
        jDialog.setTitle("Dentist's Menu");
        jDialog.setContentPane(DentistOptionPanel);
        jDialog.setMinimumSize(new Dimension(420, 420));
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);

        viewPatientHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField healthCardNum = getHealthCardNumDialogue("Patient's Information");


                JTextArea history = null;
                try {
                    history = new JTextArea(viewPatientHistory(healthCardNum.getText()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "No Patient Found", "Task Failed", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                assert history != null;
                history.setEditable(false);
                JPanel panel = new JPanel();
                panel.add(history);
                JOptionPane.showMessageDialog(null, panel);
            }
        });
        addNotesToPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField healthCardNum = getHealthCardNumDialogue("Add Note To Patient's File");
                String fileName = "PatientRecord\\"+healthCardNum.getText()+".txt";
                File tmpDir = new File(fileName);
                if(tmpDir.exists())
                {
                    JTextArea DocNote = new JTextArea(10,20);
                    JPanel panel = new JPanel();
                    panel.add(DocNote);

                    int action = JOptionPane.showConfirmDialog(null, panel, "Add Note to Patient's File", JOptionPane.OK_CANCEL_OPTION);
                    if(action == JOptionPane.OK_OPTION)
                    {
                        addDocNote(healthCardNum.getText(), DocNote.getText());
                    }
                }
                else JOptionPane.showMessageDialog(null, "No Patient Found", "Task Failed", JOptionPane.ERROR_MESSAGE);

            }
        });
        viewAppointmentScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDentistSchedule();
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

    private static JTextField getHealthCardNumDialogue(String title) {
        JPanel healthCardInputPanel = new JPanel();
        JLabel label = new JLabel("Enter the health card number");
        JTextField healthCardNum = new JTextField(11);
        healthCardInputPanel.add(label);
        healthCardInputPanel.add(healthCardNum);
        int status = JOptionPane.showConfirmDialog(null, healthCardInputPanel, title, JOptionPane.OK_CANCEL_OPTION);
        return healthCardNum;
    }

    public static void main(String[] args) {
        DentistOptions dopt = new DentistOptions();
    }
}
