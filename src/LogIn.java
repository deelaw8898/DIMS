import java.util.Scanner;

public class LogIn {
    private String Username;
    private String Password;

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void call()
            //for now the function is creating new objects. the ideal plan is to check for the object's presence in file and if present load that object
    {
        if(Username.equals("frontdesk"))
        {
            FrontDesk frontDesk = new FrontDesk();
        } else if (Username.equals("hr")) {
            HR hr = new HR();
        } else if (Username.equals("Dentist")) {
            Dentist dentist = new Dentist();
        }
    }

    public static void main(String[] args)
    {
        LogIn login = new LogIn();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter UserName:   ");
        login.setUsername(in.next());
        System.out.println("Enter Password: ");
        login.setPassword(in.next());

    }
}
