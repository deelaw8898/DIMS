import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * This class acts as the starting point of our dentistry management system.
 * Asks user to log in with a password and upon authentication gives access to relevant department
 */
public class LogIn {

    private String Username;
    private String Password;
    /**
     * The tree makes an association of a username with its password which will be used for authentication purposes
     */
    private static final TreeMap<String, String> UserNameTree = new TreeMap<>();

    /**
     * Reads the Username and password record stored in the files and stores that information in the TreeMap UserNameTree.
     */
    public LogIn() {
        makeRequiredFiles();

        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader("usernames.txt"));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Missing username.txt file in program file",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);

        }
        String line;

        while (true) {
            try {
                //noinspection ConstantConditions
                if ((line = read.readLine()) == null) break;
            } catch (AssertionError | IOException e) {
                throw new RuntimeException("File not found");
            }
            if (line.equals(""))
                break;
            String[] info = line.split("\\|");  //username and passwords in the file are separated by a pipe character.
            String username = info[0];
            String pass = info[1];
            UserNameTree.put(username, pass);
        }


    }

    /**
     * Creates files and folders required for normal working of the program (if they are not present already)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored") //This is to suppress the warning of createNewFile() and mkdir() method
    private void makeRequiredFiles() {
        File PatientRecordDirectory = new File("PatientRecord");
        File EmployeeRecord = new File("EmployeeRecord.txt");
        File PatientRecord = new File("PatientRecord.txt");
        File usernames = new File("usernames.txt");
        File PharmaciesInfo = new File("PharmaciesInfo.csv");

        try {
            EmployeeRecord.createNewFile();
            PatientRecord.createNewFile();
            usernames.createNewFile();
            PharmaciesInfo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!PatientRecordDirectory.isDirectory()) {
            PatientRecordDirectory.mkdir();
        }
    }

    /**
     * Crosschecks the credentials (username and password) provided by the user with the credentials stored in the TreeMap
     * to verify user's validity.
     * This class defines a superuser too with the username of admin@"department" with a password of 0000
     * this is done for maintenance purposes.
     */
    public void authentication() {
        if ((Username.equals("admin@hr") | Username.equals("admin@frontdesk") | Username.equals("admin@dentist"))
                && Password.equals("[0, 0, 0, 0]"))//Super User
            call();

        else if (UserNameTree.containsKey(Username)) {
            if (UserNameTree.get(Username).equals(Password)) {
                call();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Setter function for the Username attribute
     */
    public void setUsername(String username) {
        Username = username;
    }

    /**
     * Setter function of the Password attribute
     * @param password password to be set
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     * Depending upon the values of class attribute Username, calls appropriate department option class.
     * _____________________________________________
     *  username format        - Class Called
     * --------------------------------------------
     *  "employeeId"@frontdesk - FrontDeskOptions
     *  "employeeId"@hr        - HR_Options
     *  "employeeId"@dentist   - DentistOptions
     * ----------------------------------------------
     */
    public void call() {
        String[] department = Username.split("@");
        switch (department[1]) {
            case "frontdesk" -> new FrontDeskOptions();
            case "hr" -> new HR_Options();
            case "dentist" -> new DentistOptions();
        }
    }


    /**
     * Prompts user to enter their credentials, then calls functions to verify and store those credentials in the class attributes,
     */
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
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
            if (status == JOptionPane.OK_OPTION) {
                login.setUsername(username.getText());
                login.setPassword(Arrays.toString(password.getPassword()));
                login.authentication();
            }
            if (status == JOptionPane.OK_CANCEL_OPTION | status == JOptionPane.CLOSED_OPTION) {
                flag = false;
            }

        }


    }
}
