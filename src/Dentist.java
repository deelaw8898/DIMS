import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dentist extends FrontDesk {

    public String viewPatientHistory(String healthCardNum) throws IOException {
        StringBuilder builder = new StringBuilder();
        String filename = "PatientRecord\\" + healthCardNum + ".txt";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = "";
        while(!((line = reader.readLine()) == null))
        {
            builder.append(line).append('\n');
        }
        return builder.toString();
    }

    public void addDocNote(String healthCardNum, String note)
    {
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


public static void main(String[] args) {
    Dentist dentist = new Dentist();
    dentist.addDocNote("222","You are a dumb fuck");
    try {
        dentist.viewPatientHistory("222");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}
}
