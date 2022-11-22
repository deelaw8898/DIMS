import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    public FrontDeskOptions() {
        FrontDesk frontDesk = new FrontDesk();
        JDialog jDialog = new JDialog();
        jDialog.setTitle("Front Desk Options");
        jDialog.setContentPane(frontDeskOptionsPanel);
        jDialog.setMinimumSize(new Dimension(420, 420));
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);


        RegisterNewPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.registerPatient();
            }
        });
        SetAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.setAppointment();
            }
        });
        DeleteAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.deleteAppointment();
            }
        });
        ViewSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.viewDentistSchedule();
            }
        });
        Checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontDesk.checkout();
            }
        });
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
        isPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JPanel panel = new JPanel();
                JTextField input = new JTextField(11);
                JLabel label = new JLabel("Enter Health Card Number");
                panel.add(label);
                panel.add(input);
                int result = JOptionPane.showConfirmDialog(null,panel,"Check Patient Registration Status", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION){
                    boolean isPatient = frontDesk.isPatient(input.getText());
                    if(isPatient)
                    {
                        JOptionPane.showMessageDialog(null, "Patient is registered in the system", "Patient Found", JOptionPane.PLAIN_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Patient not found in the system", "Patient Not Found", JOptionPane.PLAIN_MESSAGE);
                    }
                }

            }
        });
        ViewPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JTextField input = new JTextField(11);
                JLabel label = new JLabel("Enter Health Card Number");
                panel.add(label);
                panel.add(input);
                int result = JOptionPane.showConfirmDialog(null,panel,"View Patient", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION){
                    Patient patient = frontDesk.getPatient(input.getText());

                    if(patient != null)
                    {
                        JTextArea patientInfo = new JTextArea(patient.toString(),20,50);
                        patientInfo.setEditable(false);
                        JOptionPane.showMessageDialog(null, patientInfo);
                    }
                    else
                    {
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
