import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Provides with a gui panel for checkout
 */

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


    /**
     * Views the panel
     */
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
                JOptionPane.showMessageDialog(null, "Total bill:" + totalCost, "Total Bill", JOptionPane.PLAIN_MESSAGE);
            }
        });
        addAdditionalCharges(); //pops up anther panel to input any additional amount to be added to the total

        jDialog.setVisible(true);
    }

    /**
     * Updates the total on the panel if the checkbox is selected
     */
    private void checkBoxActionListeners() {
        scalingAndPolishingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 200;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        /*Updates the total on the panel if the checkbox is selected*/
        DentalFillingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 500;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        /*Updates the total on the panel if the checkbox is selected*/
        dentalCheckUpByCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 69.68;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        /*Updates the total on the panel if the checkbox is selected*/
        XRayCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 51.51;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        /*Updates the total on the panel if the checkbox is selected*/
        RemovalSimpleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 193;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        /*Updates the total on the panel if the checkbox is selected*/
        RemovalComplexCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 278;
                Total.setText(String.format("%.2f", totalCost));
            }
        });
        /*Updates the total on the panel if the checkbox is selected*/
        CrownCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost += 700;
                Total.setText(String.format("%.2f", totalCost));
            }
        });

    }


    /**
     * pops up anther panel to input any additional amount to be added to the total
     */
    private void addAdditionalCharges() {
        addAdditionalChargesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //makes sure only float value is input in the text field
                NumberFormat floatFormat = NumberFormat.getNumberInstance();
                NumberFormatter numberFormatter = new NumberFormatter(floatFormat);
                numberFormatter.setAllowsInvalid(false); //restricts the input to be a number
                numberFormatter.setMinimum(0); //Sets Minimum Value
                //------------------------------------------------------------------------

                JFormattedTextField field = new JFormattedTextField(numberFormatter);
                JLabel label = new JLabel("Additional Cost $");
                JOptionPane.showMessageDialog(null, field, "Additional Cost", JOptionPane.PLAIN_MESSAGE);
                totalCost += Float.parseFloat(field.getValue().toString());
                Total.setText(String.format("%.2f", totalCost));


            }
        });
    }
}
