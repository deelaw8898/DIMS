import java.io.Serializable;

public class Patient extends Person implements Serializable {
    private String Occupation;
    private String Employer;
    private String ReferredBy;
    private Dentist AssignedDentist;

    public Patient(String title, String firstName, String surName, String healthCardNumber, String email, String dateOfBirth, String address, String postalCode, String contactHome, String contactCell, String contactWork, String emergencyContactName, String emergencyContactNumber, String occupation, String employer, String referredBy) {
        super(title, firstName, surName, healthCardNumber, email, dateOfBirth, address, postalCode, contactHome, contactCell, contactWork, emergencyContactName, emergencyContactNumber);
        Occupation = occupation;
        Employer = employer;
        ReferredBy = referredBy;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nOccupation=\t\t" + Occupation +
                "\nEmployer=\t\t" + Employer +
                "\nReferred By=\t\t" + ReferredBy +
                '}';
    }
}
