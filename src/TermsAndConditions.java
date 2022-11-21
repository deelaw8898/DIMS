//TODO CANCEL BUTTON IS NOT CONFIGURED YET
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TermsAndConditions {
    private boolean flag;
    private JTextArea TextArea;
    private JPanel panel1;
    private JButton iAgreeButton;
    private JButton cancelButton;

    public TermsAndConditions() {

    }

    public boolean TermsAndConditionsFrom(JFrame parent) {
        JDialog jDialog = new JDialog(parent);
        jDialog.setTitle("Patient Medical Information");
        jDialog.setContentPane(panel1);
        jDialog.setMinimumSize(new Dimension(700, 700));
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);
        TextArea.append("""
                >>> Insurance Coverage:
                • It is your responsibility to be aware of your insurance coverage, policy provisions, exclusions and
                  limitations as well as authorization requirements. This information is furnished by the insurance carrier.\s
                  Please contact your insurance carrier with any questions regarding your coverage.
                • We make every effort to verify that your insurance is valid at the time of your visit.\s
                  However, please understand that if your coverage has been terminated or suspended at the time of visit,\s
                  you will be financially responsible for payment of services rendered.
                • If your insurance changes, it is your responsibility to notify the office prior to your next visit so we\s
                  can make the appropriate changes to help vou to receive your maximum benefits.
                • Verification of benefits with your insurance carrier is not a guarantee of payment for claims submitted\s
                  by our billing company.
                                
                >>> Office Policies:
                • As a patient of Campus Dentist you accept financial responsibility for services provided to you.
                  Deductibles:
                • Deductibles are the patient's responsibility. The deductible is determined by the contract that\s
                  you have with your insurance company. Our office does not know and is not responsible for kndwing\s
                  how much each patient's deductible is or how much has been met at the time of your visit.
                                
                >>> Referrals:
                • It is your responsibility to verify coverage with your insurance carrier if you require a referral
                  Insurance Request:
                • It is your responsibility to comply with any request from your insurance carrier for further information.\s
                  Your inability to provide the requested information will result in a denial of your insurance claim and you\s
                  will be responsible for the outstanding amount.
                • You agree to cooperate with our billing company if they request your assistance in appealing your claim to\s
                  your insurance carrier.
                  Insurance Payments issued and sent to you:
                • If insurance payments are sent to you, it is your responsibility to forward the payment to our office upon\s
                  receipt with a copy of your "Explanation of Benefits" (EOB) received.
                                
                >>> Collection Accounts:
                • In the event that your account is forwarded to our collection agency you are responsible for payment collection\s
                  agency fees if applicable. We emphasize that as a dental care provider that our relationship is with you, the patient,\s
                  and not with the insurance company. We will assist you in understanding your insurance policy and coverage.
                  have read and understand my financial responsibility and agree to the above.""");

        iAgreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register(jDialog);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });
        jDialog.setVisible(true);
        return flag;
    }


    public void register(JDialog jDialog) {
        flag = true;
        jDialog.dispose();


    }


}
