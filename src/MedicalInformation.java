import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Collects the medical history of the patient using a GUI form
 */
public class MedicalInformation extends PatientInformation {
    private boolean flag;

    //---------Text boxes and checkBoxes to collect information----------------------------
    private JPanel MedicalInformation;
    private JButton nextButton;
    private JLabel Question1;
    private JCheckBox no1;
    private JCheckBox maybe1;
    private JLabel Question2;
    private JCheckBox yes2;
    private JCheckBox no2;
    private JCheckBox maybe2;
    private JLabel Question3;
    private JCheckBox yes3;
    private JCheckBox no3;
    private JCheckBox maybe3;
    private JLabel Question4;
    private JCheckBox yes4;
    private JCheckBox no4;
    private JCheckBox maybe4;
    private JLabel Question5;
    private JCheckBox yes5;
    private JCheckBox no5;
    private JCheckBox maybe5;
    private JLabel Question6;
    private JCheckBox yes6;
    private JCheckBox no6;
    private JCheckBox maybe6;
    private JLabel Question7;
    private JCheckBox yes7;
    private JCheckBox no7;
    private JCheckBox maybe7;
    private JCheckBox yes1;
    private JTextArea TextBox1;
    private JTextArea TextBox2;
    private JTextArea TextBox3;
    private JTextArea TextBox4;
    private JTextArea TextBox5;
    private JPanel MedicalInfoPanel;


    /**
     * Views the GUI form to collect the information and calls methods to process and store that info in parent class
     * @return returns true if the process is a success, false otherwise
     */
    public boolean MedicalInformationForm(JFrame parent) {

        JDialog jDialog = new JDialog(parent);

        //------------------panel settings---------------------------
        jDialog.setTitle("Patient Medical Information");
        jDialog.setContentPane(MedicalInformation);
        jDialog.setMinimumSize(new Dimension(700, 800));
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);


        /**Calls method to process and save collected data and then proceeds to terms and condition panel*/
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMedInfo();
                jDialog.setVisible(false);
                flag = next(jDialog);

            }
        });
        jDialog.setVisible(true);
        return flag;
    }

    /**
     * Processes the collected information, builds a string of that and stores it in the parent class
     */
    private void saveMedInfo() {

        StringBuilder builder = new StringBuilder();    //String builder to hold all the processed info

        builder.append(Question1.getText()).append("\n");
        if (yes1.isSelected())
            builder.append(">>>Yes\n");
        if (no1.isSelected())
            builder.append(">>>No\n");
        if (maybe1.isSelected())
            builder.append(">>>Maybe\n");
        builder.append(">>>").append(TextBox1.getText()).append('\n');

        builder.append(Question2.getText()).append("\n");
        if (yes2.isSelected())
            builder.append(">>>Yes\n");
        if (no2.isSelected())
            builder.append(">>>No\n");
        if (maybe2.isSelected())
            builder.append(">>>Maybe\n");
        builder.append(">>>").append(TextBox2.getText()).append('\n');

        builder.append(Question3.getText()).append("\n");
        if (yes3.isSelected())
            builder.append(">>>Yes\n");
        if (no3.isSelected())
            builder.append(">>>No\n");
        if (maybe3.isSelected())
            builder.append(">>>Maybe\n");
        builder.append(">>>").append(TextBox3.getText()).append('\n');

        builder.append(Question4.getText()).append("\n");
        if (yes4.isSelected())
            builder.append(">>>Yes\n");
        if (no4.isSelected())
            builder.append(">>>No\n");
        if (maybe4.isSelected())
            builder.append(">>>Maybe\n");
        builder.append(">>>").append(TextBox4.getText()).append('\n');

        builder.append(Question5.getText()).append("\n");
        if (yes5.isSelected())
            builder.append(">>>Yes\n");
        if (no5.isSelected())
            builder.append(">>>No\n");
        if (maybe5.isSelected())
            builder.append(">>>Maybe\n");
        builder.append(">>>").append(TextBox5.getText()).append('\n');

        builder.append(Question6.getText()).append("\n");
        if (yes6.isSelected())
            builder.append(">>>Yes\n");
        if (no6.isSelected())
            builder.append(">>>No\n");
        if (maybe6.isSelected())
            builder.append(">>>Maybe\n");

        builder.append(Question7.getText()).append("\n");
        if (yes7.isSelected())
            builder.append(">>>Yes\n");
        if (no7.isSelected())
            builder.append(">>>No\n");
        if (maybe7.isSelected())
            builder.append(">>>Maybe\n");

        PatientInformation.MedicalInfo = builder.toString();
    }


    /**
     * Calls TermsAndConditions object and returns true if it gets a true value terms and conditions implying the success of the registration process
     */
    private boolean next(JDialog jDialog) {
        TermsAndConditions termsAndConditions = new TermsAndConditions();
        boolean flag = termsAndConditions.TermsAndConditionsFrom(null);
        jDialog.dispose();
        return flag;
    }


}
