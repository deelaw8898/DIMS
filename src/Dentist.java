import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains methods to do all the tasks related to Dentist and methods to help implement the tasks performed by the dentists
 */
public class Dentist extends FrontDesk {

    /**
     * View patient's history provided at the time of registration and any doctor's notes added in the future
     *
     * @param healthCardNum health card number of the patient
     * @return a string containing patient's history
     * @throws IOException if any error occurs with file handling
     */
    public String viewPatientHistory(String healthCardNum) throws IOException {
        StringBuilder builder = new StringBuilder();
        String filename = "PatientRecord\\" + healthCardNum + ".txt";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = "";
        while (!((line = reader.readLine()) == null)) {
            builder.append(line).append('\n');
        }
        return builder.toString();
    }

    /**
     * Adds a note in the patient's file with the date stamp at which the note was added
     *
     * @param healthCardNum health card number of the patient whose file is to be updated
     * @param note          a String containing the note that is to be added in the file
     */
    public void addDocNote(String healthCardNum, String note) {
        StringBuilder builder = new StringBuilder();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime date = LocalDateTime.now();

        builder.append("\n\nDoctor's Note @ ").append(dtf.format(date)).append("\n\n").append(note);
        String fileName = "PatientRecord\\" + healthCardNum + ".txt";

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            out.println(builder.toString());
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error While Writing The Patient's File", "Task Failed", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }


}
