# Dental Industry Management System (DIMS)

## Overview
DIMS is a POC for Java-based management system designed to streamline administrative tasks in the dentistry industry. It provides features for different roles including front desk, dentists, and HR. Key functionalities include patient registration, appointment scheduling, and employee management.

## Project Report
For a detailed overview of the project, please refer to the [Project Report](https://github.com/deelaw8898/DIMS/blob/main/DIMS-Project%20Report.pdf).

## Installation
To install DIMS, you have two options: using the pre-compiled JAR file or building from source. For a quick setup, download DIMS.jar from the repository and run it using Java (requires Java Runtime Environment). Alternatively, if you prefer to build from source, clone the repository to your local machine, navigate to the source code directory, and compile the Java files.

# Usage

This guide walks you through the basic usage of the Dental Information Management System (DIMS).

## Getting Started

Before you begin, ensure that all dependencies are checked. The system should automatically create necessary files and directories if they don't exist.

### Login

1. Start the application.
2. Enter your assigned username and password. Usernames follow the convention `<Employee ID>@<Department>`. For example, `wa392@frontdesk`.
3. There are three admin accounts: `admin@frontdesk`, `admin@dentist`, and `admin@hr`. Standard password for all three is `0000` by default. These accounts have access to all features and are in place to test the features. 

### Front Desk Operations

After successful login, the front desk interface provides options to manage patient and appointment data. It prompts the front desk employee to enter the schedule for the doctor on duty for that day.

#### Register Patient

1. Go to 'Register New Patient'.
2. Fill out the patient registration form and medical information form.
3. Agree to the terms and conditions to complete registration.

#### Set Appointment

1. Select 'Set Appointment'.
2. Enter patient's Health Card Number and desired time slot.
3. Confirm the appointment if there is no conflict.

#### Other Options

- View doctor's schedule.
- Delete an appointment.
- Check out and process payments.
- List and send prescriptions to affiliated pharmacies.
- Verify if an individual is a registered patient.
- View detailed patient information.

### Dentist's Interface

For dentists, the system provides a tailored interface for patient management.

#### Patient History

1. Choose 'View Patient History'.
2. Enter the patient's Health Card Number to view their medical records.

#### Add Notes

1. Select 'Add Notes to Patient's File'.
2. Enter diagnosis and prescription information.

#### View Schedule

- Use 'View Appointment Schedule' to see your upcoming appointments.

### HR Management

Human Resources can manage employee data within the system.

#### Manage Employees

1. Create new employee records.
2. View and edit existing employee details.
3. Transfer or remove employees from the system.

## Troubleshooting

For a complete list of features and detailed user instructions, please refer to the detailed [Project Report](https://github.com/deelaw8898/DIMS/blob/main/DIMS-Project%20Report.pdf) included in the system documentation.

## Architecture
The Dental Industry Management System (DIMS) is designed using object-oriented programming principles in Java, ensuring a modular, scalable, and maintainable codebase. The architecture is centered around robust class design and encapsulation of data and behavior, providing a clear separation of concerns and ease of use.

## Future Enhancements

While DIMS version-1 serves as a fully functional administrative tool for the dentistry industry, 
there are several enhancements in the pipeline aimed at bolstering the system's capabilities, improving user experience, and reinforcing security. Here are the envisaged enhancements for future releases:

### Architecture Refinement

- **Layer Decoupling:** Refactor the architecture to separate the view layer from the data layer, reducing coupling and improving the maintainability of the system.

### Data Management

- **Cloud Integration:** Transition from local data storage to cloud servers for increased data redundancy, secure backup, and global accessibility.
- **Input Sanitization:** Implement validation to prevent special characters in input fields that could disrupt data integrity, such as pipe characters in form submissions.

### Security Improvements

- **Encryption of Sensitive Data:** Secure the storage of usernames and passwords by implementing encryption and only decrypting at the point of verification to mitigate security risks.
- **Unique Username Enforcement:** Extend the existing validation logic to ensure usernames are unique across the system, preventing duplicate entries.

### User Interface and Experience

- **GUI Enhancement:** Redesign the graphical user interface to enhance usability, ensuring it is intuitive and accessible for all user roles within the system.
- **Extended Functionality in GUI:** Implement additional getters and setters in the HR and Front Desk modules, following the logic of the existing implemented features.

### Communication Features

- **Appointment Reminders:** Incorporate a feature to send automated reminder emails to patients about their upcoming appointments utilizing the Java Mail API.

## Acknowledgements
Designing a software solution for managing dentistry industry was an interesting journey where I
learned a lot about real world applications of software designs.
I would like to express my gratitude and appreciation to all those who gave me the possibility to complete
this project. Especially to my instructor and project supervisor Ehsan Moradi who came up with the
project idea.
Further on I want to thank Campus Dentist staff for providing me with the patient registration forms
being used in the industry. And to Pharmacist Alia Anber for providing me with the detailed insight of
how current management systems works in medical fields
