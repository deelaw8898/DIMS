/**
 * Represents a person (Can be anyone, a patient or an employee)
 */
public class Person {
    /**
     * Title of the patient (Mr. Mrs. Dr. etc)
     */
    private String Title;
    /**
     * First name of the person
     */
    private String FirstName;
    /**
     * Last name of the person
     */
    private String SurName;
    /**
     * Health card number of the person
     */
    private String HealthCardNumber;
    /**
     * Email of the person
     */
    private String Email;
    /**
     * Date of birth of the person
     */
    private String DateOfBirth;
    /**
     * Address of the person
     */
    private String Address;
    /**
     * Postal code where the person lives
     */
    private String PostalCode;
    /**
     * Home Contact number
     */
    private String ContactHome;
    /**
     * Cell Contact number
     */
    private String ContactCell;
    /**
     * Work Contact number
     */
    private String ContactWork;
    /**
     * Name of the person who is to be contacted in the case of an emergency
     */
    private String EmergencyContactName;
    /**
     * Number of the person who is to be contacted in the case of an emergency
     */
    private String EmergencyContactNumber;


    /**
     * Empty Constructor
     */
    public Person() {
    }

    /**
     * Constructor that sets values for all the attributes
     */
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

    /**
     * toString method for the patient
     *
     * @return a string containing information of the patient in a print-friendly format
     */
    @Override
    public String toString() {
        return "Title\t\t" + Title +
                "\nFirstName\t\t" + FirstName +
                "\nSurName\t\t" + SurName +
                "\nHealthCardNumber\t" + HealthCardNumber +
                "\nEmail\t\t" + Email +
                "\nDateOfBirth\t\t" + DateOfBirth +
                "\nAddress\t\t" + Address +
                "\nPostalCode\t\t" + PostalCode +
                "\nContactHome\t\t" + ContactHome +
                "\nContactCell\t\t" + ContactCell +
                "\nContactWork\t\t" + ContactWork +
                "\nEmergencyContactName\t" + EmergencyContactName +
                "\nEmergencyContactNumber\t" + EmergencyContactNumber;
    }

    /**
     * Getter function for the Title
     *
     * @return title of the person
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Getter function for the FirstName
     *
     * @return First name of the person
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Getter function for the SurName
     *
     * @return Surname of the person
     */
    public String getSurName() {
        return SurName;
    }

    /**
     * Getter function for the HealthCardNumber
     *
     * @return Health card number of the person
     */
    public String getHealthCardNumber() {
        return HealthCardNumber;
    }

    /**
     * Getter function for the Email
     *
     * @return Email ID of the person
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Getter function for the DateOfBirth
     *
     * @return Date of birth of the person
     */
    public String getDateOfBirth() {
        return DateOfBirth;
    }

    /**
     * Getter function for the Address
     *
     * @return Address of the person
     */
    public String getAddress() {
        return Address;
    }

    /**
     * Getter function for the PostalCode
     *
     * @return Postal code of the address of the person
     */
    public String getPostalCode() {
        return PostalCode;
    }

    /**
     * Getter function for the ContactHome
     *
     * @return Home contact number of the person
     */
    public String getContactHome() {
        return ContactHome;
    }

    /**
     * Getter function for the ContactCell
     *
     * @return Cellphone number of the person
     */
    public String getContactCell() {
        return ContactCell;
    }

    /**
     * Getter function for the ContactWork
     *
     * @return Work contact number of the person
     */
    public String getContactWork() {
        return ContactWork;
    }

    /**
     * Getter function for the EmergencyContactName
     *
     * @return Name of the person to be contacted in the case of emergency
     */
    public String getEmergencyContactName() {
        return EmergencyContactName;
    }

    /**
     * Getter function for the EmergencyContactNumber
     *
     * @return Number of the person to be contacted in the case of emergency
     */
    public String getEmergencyContactNumber() {
        return EmergencyContactNumber;
    }
}
