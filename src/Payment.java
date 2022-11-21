import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class Payment {
    private JCheckBox scalingAndPolishingCheckBox;
    private JCheckBox dentalCheckUpByCheckBox;
    private JTextField AdditionalCharges;
    private JButton OKButton;
    private JCheckBox DentalFillingCheckBox;
    private JCheckBox XRayCheckBox;
    private JCheckBox RemovalSimpleCheckBox;
    private JCheckBox RemovalComplexCheckBox;
    private JCheckBox CrownCheckBox;
    private JLabel Total;
    private JButton addAdditionalChargesButton;
    private JPanel PaymentPanel;
    private float totalCost = 0;


    public void getPaymentForm() {
        JFrame parent = null;
        JDialog jDialog = new JDialog(parent);
        jDialog.setTitle("Payment Panel");
        jDialog.setContentPane(PaymentPanel);
        jDialog.setMinimumSize(new Dimension(400, 500));
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        checkBoxActionListeners();

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jDialog.dispose();
                System.out.println(totalCost);
            }
        });
        addAdditionalCharges();

        jDialog.setVisible(true);
    }


    private void checkBoxActionListeners() {
        scalingAndPolishingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 200;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        DentalFillingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 500;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        dentalCheckUpByCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 69.68;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        XRayCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 51.51;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        RemovalSimpleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 193;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        RemovalComplexCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 278;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        CrownCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 700;
                Total.setText(String.format("%.2f", totalCost));
            }
        });

    }


    private void addAdditionalCharges() {
        addAdditionalChargesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumberFormat floatFormat = NumberFormat.getNumberInstance();

                NumberFormatter numberFormatter = new NumberFormatter(floatFormat);
                numberFormatter.setAllowsInvalid(false); //restricts the input to be a number
                numberFormatter.setMinimum(0); //Sets Minimum Value

                JFormattedTextField field = new JFormattedTextField(numberFormatter);
                JLabel label = new JLabel("Additional Cost $");
                JOptionPane.showMessageDialog(null, field, "Additional Cost", JOptionPane.PLAIN_MESSAGE);
                totalCost += Float.parseFloat(field.getValue().toString());
                Total.setText(String.format("%.2f", totalCost));


            }
        });
    }
}
