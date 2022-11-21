import java.security.PublicKey;

public class Patient extends Person {
    private String Occupation;
    private String Employer;
    private String RefferedBy;
    private Dentist AssignedDentist;

    public Patient(String title, String firstName, String surName, String healthCardNumber, String email, String dateOfBirth, String address, String postalCode, String contactHome, String contactCell, String contactWork, String emergencyContactName, String emergencyContactNumber, String occupation, String employer, String refferedBy) {
        super(title, firstName, surName, healthCardNumber, email, dateOfBirth, address, postalCode, contactHome, contactCell, contactWork, emergencyContactName, emergencyContactNumber);
        Occupation = occupation;
        Employer = employer;
        RefferedBy = refferedBy;
    }
}
