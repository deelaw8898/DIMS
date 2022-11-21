//TODO THE INFO COLLECTED IN THIS CLASS IS NOT YET SAVED ANYWHERE. THE PLAN IS TO MAKE A TREEMAP LINKING PATIENT TO THEIR INFO

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicalInformation extends PatientInformation {
    private boolean flag;

    //--------------------------------------
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

    public boolean MedicalInformationForm(JFrame parent) {

        JDialog jDialog = new JDialog(parent);

        jDialog.setTitle("Patient Medical Information");
        jDialog.setContentPane(MedicalInformation);
        jDialog.setMinimumSize(new Dimension(700, 800));
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);

        //todo: create terms and conditions

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMedInfo();
                flag = next(jDialog);
                jDialog.setVisible(false);
            }
        });
        jDialog.setVisible(true);
        return flag;
    }

    private void saveMedInfo() {

        StringBuilder builder = new StringBuilder();
        builder.append(Question1.getText()).append("\n >> ");
        if(yes1.isSelected())
            builder.append("Yes\n");
        if(no1.isSelected())
            builder.append("No\n");
        if(maybe1.isSelected())
            builder.append("Maybe\n");
        builder.append(TextBox1.getText()).append('\n');

        builder.append(Question2.getText()).append("\n >> ");
        if(yes2.isSelected())
            builder.append("Yes\n");
        if(no2.isSelected())
            builder.append("No\n");
        if(maybe2.isSelected())
            builder.append("Maybe\n");
        builder.append(TextBox2.getText()).append('\n');

        builder.append(Question3.getText()).append("\n >> ");
        if(yes3.isSelected())
            builder.append("Yes\n");
        if(no3.isSelected())
            builder.append("No\n");
        if(maybe3.isSelected())
            builder.append("Maybe\n");
        builder.append(TextBox3.getText()).append('\n');

        builder.append(Question4.getText()).append("\n >> ");
        if(yes4.isSelected())
            builder.append("Yes\n");
        if(no4.isSelected())
            builder.append("No\n");
        if(maybe4.isSelected())
            builder.append("Maybe\n");
        builder.append(TextBox4.getText()).append('\n');

        builder.append(Question5.getText()).append("\n >> ");
        if(yes5.isSelected())
            builder.append("Yes\n");
        if(no5.isSelected())
            builder.append("No\n");
        if(maybe5.isSelected())
            builder.append("Maybe\n");
        builder.append(TextBox5.getText()).append('\n');

        builder.append(Question6.getText()).append("\n >> ");
        if(yes6.isSelected())
            builder.append("Yes\n");
        if(no6.isSelected())
            builder.append("No\n");
        if(maybe6.isSelected())
            builder.append("Maybe\n");

        builder.append(Question7.getText()).append("\n >> ");
        if(yes7.isSelected())
            builder.append("Yes\n");
        if(no7.isSelected())
            builder.append("No\n");
        if(maybe7.isSelected())
            builder.append("Maybe\n");

        MedicalInfo = builder.toString();
    }


    private boolean next(JDialog jDialog) {
        TermsAndConditions termsAndConditions = new TermsAndConditions();
        boolean flag = termsAndConditions.TermsAndConditionsFrom(null);
        jDialog.dispose();
        return true;
    }

    private void createUIComponents() {

    }


}
