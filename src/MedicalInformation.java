//TODO THE INFO COLLECTED IN THIS CLASS IS NOT YET SAVED ANYWHERE. THE PLAN IS TO MAKE A TREEMAP LINKING PATIENT TO THEIR INFO

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MedicalInformation  {
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
                saveMedInfoToFile();
                flag = next(jDialog);
                jDialog.setVisible(false);
            }
        });
        jDialog.setVisible(true);
        return flag;
    }

    private void saveMedInfoToFile() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("myfile.txt", true)));
            out.println("the text");
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
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
