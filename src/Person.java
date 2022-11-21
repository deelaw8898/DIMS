import java.io.Serializable;

public class Person implements Serializable {
    private String Title;
    private String FirstName;
    private String SurName;
    private String HealthCardNumber;
    private String Email;
    private String DateOfBirth;
    private String Address;
    private String PostalCode;
    private String ContactHome;
    private String ContactCell;
    private String ContactWork;
    private String EmergencyContactName;
    private String EmergencyContactNumber;


    public Person(){}
    public Person(String title, String firstName, String surName, String healthCardNumber, String email, String dateOfBirth, String address, String postalCode, String contactHome, String contactCell, String contactWork, String emergencyContactName, String emergencyContactNumber) {
        Title = title;
        FirstName = firstName;
        SurName = surName;
        HealthCardNumber = healthCardNumber;
        Email = email;
        DateOfBirth = dateOfBirth;
        Address = address;
        PostalCode = postalCode;
        ContactHome = contactHome;
        ContactCell = contactCell;
        ContactWork = contactWork;
        EmergencyContactName = emergencyContactName;
        EmergencyContactNumber = emergencyContactNumber;

    }

    @Override
    public String toString() {
        return "Title=\t\t" + Title +
                "\nFirstName=\t\t" + FirstName +
                "\nSurName=\t\t" + SurName +
                "\nHealthCardNumber=\t" + HealthCardNumber +
                "\nEmail=\t\t" + Email + '\'' +
                "\nDateOfBirth=\t\t" + DateOfBirth +
                "\nAddress=\t\t" + Address + '\'' +
                "\nPostalCode=\t\t" + PostalCode +
                "\nContactHome=\t\t" + ContactHome +
                "\nContactCell=\t\t" + ContactCell +
                "\nContactWork=\t\t" + ContactWork +
                "\nEmergencyContactName=\t" + EmergencyContactName +
                "\nEmergencyContactNumber=\t" + EmergencyContactNumber;
    }

    public String getTitle() {
        return Title;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getSurName() {
        return SurName;
    }

    public String getHealthCardNumber() {
        return HealthCardNumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public String getAddress() {
        return Address;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public String getContactHome() {
        return ContactHome;
    }

    public String getContactCell() {
        return ContactCell;
    }

    public String getContactWork() {
        return ContactWork;
    }

    public String getEmergencyContactName() {
        return EmergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return EmergencyContactNumber;
    }
}
