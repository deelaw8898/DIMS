import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

public class LogIn {
    private String Username;
    private String Password;
    private static TreeMap<String, String> UserNameTree = new TreeMap<>();
    public LogIn()
    {
        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader("usernames.txt"));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Missing username.txt file in program file",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            ;
        }
        String line = "";

        while (true) {
            try {
                if ((line = read.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException("File not found");
            }
            if(line.equals(""))
                break;
            String[] info = line.split("\\|");
            String username = info[0];
            String pass = info[1];
            UserNameTree.put(username, pass);
        }
    }

    public void authentication()
    {   boolean flag = UserNameTree.containsKey(Username);
        if((Username.equals("admin@hr") | Username.equals("admin@frontdesk") | Username.equals("admin@dentist"))
                && Password.equals("[0, 0, 0, 0]"))//Super User
            call();

        else if(UserNameTree.containsKey(Username))
        {
            if(UserNameTree.get(Username).equals(Password))
            {
                call();
            }
            else{
                JOptionPane.showMessageDialog(null,"Incorrect Username or Password","Login Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Incorrect Username or Password","Login Failed",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void call()
    {
        String[] department = Username.split("@");
        if(department[1].equals("frontdesk"))
        {
            FrontDeskOptions fdo = new FrontDeskOptions();
        } else if (department[1].equals("hr")) {
            HR_Options hro = new HR_Options();
        } else if (department[1].equals("dentist")) {
             DentistOptions dopt = new DentistOptions();
        }
    }


    public static void main(String[] args)
    {
        boolean flag = true;
        while(flag)
        {
            LogIn login = new LogIn();
            JPanel panel = new JPanel();
            JLabel labelUserName = new JLabel("User Name");
            JLabel labelPassword = new JLabel("Password");
            JTextField username = new JTextField(11);
            JPasswordField password = new JPasswordField(11);
            panel.add(labelUserName);
            panel.add(username);
            panel.add(labelPassword);
            panel.add(password);

            int status = JOptionPane.showConfirmDialog(null, panel, "DIMS Login", JOptionPane.OK_CANCEL_OPTION);
            if(status == JOptionPane.OK_OPTION)
            {
                login.setUsername(username.getText());
                login.setPassword(Arrays.toString(password.getPassword()));
                login.authentication();
            }
            if(status == JOptionPane.OK_CANCEL_OPTION | status == JOptionPane.CLOSED_OPTION)
            {
                flag = false;
            }

        }


    }
}
