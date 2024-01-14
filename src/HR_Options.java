import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * This class provides a GUI for all the features used by HR
 */
public class HR_Options {
    private JButton createNewEmployeeButton;
    private JButton viewPayOfEmployeeButton;
    private JButton changePayOfEmployeeButton;
    private JButton transferEmployeeButton;
    private JButton removeEmployeeButton;
    private JButton Logout;
    private JPanel HrOptionsPanel;
    private JButton viewEmployeeButton;
    private final HR hr;

    /**
     * Prompts a dialog to enter the employee ID and returns the entered value as a string
     *
     * @param title The label on the prompt
     * @return A string of the value entered in the text field of the prompt
     */
    private static String getEmployeeIdDialogue(String title) {

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter the Employee ID");
        JTextField textField = new JTextField(11);
        panel.add(label);
        panel.add(textField);
        int status = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION);
        if (status == JOptionPane.OK_OPTION)
            return textField.getText();
        else return null;
    }

    /**
     * The constructor views a panel with all the options that could be used by the HR as buttons
     * and calls appropriate methods to carry out the tasks bound to each button
     */
    public HR_Options() {
        hr = new HR();
        //------------Panel Settings--------------------------------
        JDialog jDialog = new JDialog();
        jDialog.setTitle("HR Options");
        jDialog.setContentPane(HrOptionsPanel);
        jDialog.setMinimumSize(new Dimension(420, 420));
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        //---------------------------------------------------------

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
                if (employeeId == null)
                    return;
                try {
                    String pay = hr.getPay(employeeId);
                    JLabel text = new JLabel("Salary of \""+ employeeId+ "\" is $" + pay);
                    //text.setEditable(false);
                    JOptionPane.showMessageDialog(null, text,"Pay of Employee",JOptionPane.PLAIN_MESSAGE);
                } catch (NoSuchFieldException ex) {
                    JOptionPane.showMessageDialog(null, "No such employee found", "Employee not found", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        changePayOfEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                //--------------------------------------------------
                NumberFormat floatFormat = NumberFormat.getNumberInstance();
                NumberFormatter numberFormatter = new NumberFormatter(floatFormat);
                numberFormatter.setAllowsInvalid(false); //restricts the input to be a number
                numberFormatter.setMinimum(0); //Sets Minimum Value
                //---------------------------------------------------


                JLabel labelId = new JLabel("Enter the Employee ID");
                JLabel labelPay = new JLabel("Enter the New Pay");
                JTextField employeeId = new JTextField(11);
                JFormattedTextField newPay = new JFormattedTextField(numberFormatter);
                newPay.setColumns(11);
                //JTextField newPay = new JTextField(11);
                panel.add(labelId);
                panel.add(employeeId);
                panel.add(labelPay);
                panel.add(newPay);

                int status = JOptionPane.showConfirmDialog(null, panel, "Change Employee Pay", JOptionPane.OK_CANCEL_OPTION);
                if (status == JOptionPane.OK_OPTION) {
                    String id = employeeId.getText();
                    String pay = newPay.getText();
                    try {
                        hr.changePay(id, pay);
                    } catch (NoSuchFieldException ex) {
                        JOptionPane.showMessageDialog(null, "No such employee found", "Employee not found", JOptionPane.ERROR_MESSAGE);
                    }
                    hr.exportEmployee("EmployeeRecord.txt");
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
                if (status == JOptionPane.OK_OPTION) {
                    try {
                        hr.setBranchCode(textField.getText(), textField2.getText());
                        hr.exportEmployee("EmployeeRecord.txt");
                    } catch (NoSuchFieldException ex) {
                        JOptionPane.showMessageDialog(null, "No such employee found", "Employee not found", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        /**
         * Removes an employee by calling the removeEmployee method of HR class
         */
        removeEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = getEmployeeIdDialogue("Remove Employee");
                if (employeeId == null)
                    return;
                try {
                    hr.removeEmployee(employeeId);
                } catch (NoSuchFieldException ex) {
                    JOptionPane.showMessageDialog(null, "No such employee found", "Employee not found", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /**
         * View employee information by calling viewEmployee method of HR class
         */
        viewEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = getEmployeeIdDialogue("View Employee");
                if (employeeId == null)
                    return;
                try {
                    String info = hr.viewEmployee(employeeId);
                    if (!info.equals("")) {
                        JTextArea text = new JTextArea(info, 20, 50);
                        text.setEditable(false);
                        JOptionPane.showMessageDialog(null, text,"View Employee Information",JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (NoSuchFieldException ex) {
                    JOptionPane.showMessageDialog(null, "Employee not found in the system", "Employee Not Found", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hr.exportEmployee("EmployeeRecord.txt");    // |save changes of the session in the file
                // |The changes are saved right after mutator methods as well,
                // |this is just a safety step for future updates in case a
                // |mutator method is added and changes are not saved in file
                // |after that due to programmer's ignorance
                jDialog.dispose();
            }
        });
        jDialog.setVisible(true);
    }


}
