import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HR_Options {
    private JButton createNewEmployeeButton;
    private JButton viewPayOfEmployeeButton;
    private JButton changePayOfEmployeeButton;
    private JButton transferEmployeeButton;
    private JButton removeEmployeeButton;
    private JButton button8;
    private JPanel HrOptionsPanel;
    private JButton viewEmployeeButton;
    private HR hr;

    private static String getEmployeeIdDialogue(String title) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter the Employee ID");
        JTextField textField = new JTextField(11);
        panel.add(label);
        panel.add(textField);
        int status = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION);
        return textField.getText();
    }

    public HR_Options() {
        hr = new HR();
        JDialog jDialog = new JDialog();
        jDialog.setTitle("Front Desk Options");
        jDialog.setContentPane(HrOptionsPanel);
        jDialog.setMinimumSize(new Dimension(420, 420));
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);


        createNewEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hr.createEmployee();
            }
        });
        viewPayOfEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = getEmployeeIdDialogue("View Pay of Employee");
                String pay = hr.getPay(employeeId);
                if(pay == null)
                {
                    JOptionPane.showMessageDialog(null, "Employee not found in the system", "Employee Not Found", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JTextArea text = new JTextArea(pay);
                    text.setEditable(false);
                    JOptionPane.showMessageDialog(null, text);
                }
            }
        });
        changePayOfEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JLabel labelId = new JLabel("Enter the Employee ID");
                JLabel labelPay = new JLabel("Enter the New Pay");
                JTextField employeeId = new JTextField(11);
                JTextField newPay = new JTextField(11);
                panel.add(labelId);
                panel.add(employeeId);
                panel.add(labelPay);
                panel.add(newPay);

                int status = JOptionPane.showConfirmDialog(null, panel, "Change Employee Pay", JOptionPane.OK_CANCEL_OPTION);
                if(status == JOptionPane.OK_OPTION)
                {
                    String id = employeeId.getText();
                    String pay = newPay.getText();
                    hr.changePay(id, pay);
                }

            }
        });
        transferEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel panel = new JPanel();
                JLabel label = new JLabel("Enter the Employee ID");
                JTextField textField = new JTextField(11);
                JLabel label2 = new JLabel("Enter the Destination Branch Code");
                JTextField textField2 = new JTextField(11);
                panel.add(label);
                panel.add(textField);
                panel.add(label2);
                panel.add(textField2);
                int status = JOptionPane.showConfirmDialog(null, panel, "Transfer Employee", JOptionPane.OK_CANCEL_OPTION);
                hr.setBranchCode(textField.getText(), textField2.getText());
            }
        });
        removeEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = getEmployeeIdDialogue("Remove Employee");
                hr.removeEmployee(employeeId);
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });

        viewEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = getEmployeeIdDialogue("View Employee");
                String info = hr.viewEmployee(employeeId);
                if(!info.equals(""))
                {
                    JTextArea text = new JTextArea(info);
                    text.setEditable(false);
                    JOptionPane.showMessageDialog(null, text);
                }else {
                    JOptionPane.showMessageDialog(null, "Employee not found in the system", "Employee Not Found", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        jDialog.setVisible(true);
    }

    public static void main(String args[])
    {
        HR_Options hr = new HR_Options();
    }
}
