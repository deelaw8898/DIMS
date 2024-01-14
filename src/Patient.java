/**
 * Represents a patient
 */
public class Patient extends Person {
    /**
     * Occupation of the patient. Doctors need to know this to make better analysis of patient's health
     */
    private final String Occupation;
    /**
     * Employer of the patient. This information would help to process insurance claims
     */
    private final String Employer;
    /**
     * If a patient is referred by another clinic or a doctor that information would be stored in this attribute
     */
    private final String ReferredBy;

    /**
     * sets values of all the attributes of patient
     */
    public Patient(String title, String firstName, String surName, String healthCardNumber, String email, String dateOfBirth, String address, String postalCode, String contactHome, String contactCell, String contactWork, String emergencyContactName, String emergencyContactNumber, String occupation, String employer, String referredBy) {
        super(title, firstName, surName, healthCardNumber, email, dateOfBirth, address, postalCode, contactHome, contactCell, contactWork, emergencyContactName, emergencyContactNumber);
        Occupation = occupation;
        Employer = employer;
        ReferredBy = referredBy;
    }

    /**
     * Returns a string containing patient's information in a print-friendly manner
     * @return a string containing patient's information in a print-friendly manner*/
    @Override
    public String toString() {
        return super.toString() +
                "\nOccupation\t\t" + Occupation +
                "\nEmployer\t\t" + Employer +
                "\nReferred By\t\t" + ReferredBy;
    }
}
