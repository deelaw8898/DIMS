import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Sets up doctor's schedule and does the tasks that closely depends on the doctor's schedule
 */
public class Schedule {

    /**
     * Float representation of starting hour of doctor's shift
     * this variable has only two valid decimal values .0 or .5
     * 9.0 means the time is 9:00
     * 9.5 means the time is 9:30
     * The class has functions to do these conversions which helps in the calculations related to schedule
     */
    private float WorkingHourStart;
    /**
     * Float representation of ending hour of doctor's shift
     * this variable has only two valid decimal values .0 or .5
     * 9.0 means the time is 9:00
     * 9.5 means the time is 9:30
     * The class has functions to do these conversions which helps in the calculations related to schedule
     */
    private float WorkingHourEnd;
    /**
     * represents the time slots in the entire schedule of the day with an interval of 30 min
     * each entry of this array represents a time slot of 30 min, and the boolean value represents its availability
     * true means the slot is taken (unavailable) and false means the slot is empty (doctor is available at that time)
     */
    ArrayList<Boolean> TimeLine;
    /**
     * Shows which patient has reserved the timeslot, by making a tree of index of Timeline attribute linked with
     * the patient that has reserved the timeslot at that index
     */
    TreeMap<Integer, Patient> TimeIndexToPatient;


    /**
     * Initializes Attributes to their valid values
     */
    public Schedule() {
        WorkingHourStart = -1;  //Default Value set such that it throws an exception if used without setting it first
        WorkingHourEnd = -1;
        TimeIndexToPatient = new TreeMap<>();
        initializeTimeLine();   //| set correct values of WorkingHourStart and WorkingHourEnd and initializes the TimeLine Array
        //| Doing it like this avoids the overwriting of attributes values
        //| when this class is inherited by other classes

    }

    /**
     * Checks if the values of starting and ending time of the dentist's shift has already been set to a valid value,
     * if not then calls setDocSchedule method to do that. Furthermore, this method fills boolean values in
     * the Timeline array
     */
    private void initializeTimeLine() {
        if (WorkingHourStart == -1 && WorkingHourEnd == -1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }

        TimeLine = new ArrayList<>();
        int timeSlots = (int) ((WorkingHourEnd - WorkingHourStart) * 2);  //Calculate total time slots
        for (int i = 0; i < timeSlots; i++) {
            TimeLine.add(false);        //initialize time slots
        }


    }

    /**
     * Sets the values of WorkingHourStart and WorkingHourEnd attributes by prompting a GUI panel
     */
    private void setDocSchedule() {
        //-----------------panel settings--------------------------------------
        String[] hours = {"9", "10", "11", "12", "13", "14", "15", "16", "17"}; //possible valid values of hours
        String[] min = {"00", "30"};     //possible valid values for minutes
        JComboBox<String> hourStart = new JComboBox<>(hours);
        JComboBox<String> minStart = new JComboBox<>(min);
        JComboBox<String> hourEnd = new JComboBox<>(hours);
        hourEnd.setSelectedIndex(8);    //default value selected for ending hours
        JComboBox<String> minEnd = new JComboBox<>(min);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Starting Time (hrs):"));
        myPanel.add(hourStart);

        myPanel.add(new JLabel("Starting Time (min):"));
        myPanel.add(minStart);

        myPanel.add(new JLabel("Ending Time (hrs):"));
        myPanel.add(hourEnd);

        myPanel.add(new JLabel("Ending Time (min):"));
        myPanel.add(minEnd);

        boolean flag = true;
        while (flag)     //keep asking for the input unless a valid input is given
        {
            JOptionPane.showConfirmDialog(null, myPanel,
                    "Shift Timing", JOptionPane.OK_CANCEL_OPTION);

            //--------------------setting up WorkingHourStart--------------------------------
            String HourInput = Objects.requireNonNull(hourStart.getSelectedItem()).toString();
            String minInput = Objects.requireNonNull(minStart.getSelectedItem()).toString();
            //Converts the input to float convention of this class.
            WorkingHourStart = timeStringToFloat(HourInput, minInput);

            //--------------------setting up WorkingHourEnd--------------------------------
            HourInput = Objects.requireNonNull(hourEnd.getSelectedItem()).toString();
            minInput = Objects.requireNonNull(minEnd.getSelectedItem()).toString();
            //Converts the input to float convention of this class.
            WorkingHourEnd = timeStringToFloat(HourInput, minInput);

            //-------------Ensure the ending time is after starting time--------------------
            if (WorkingHourEnd < WorkingHourStart) {
                JOptionPane.showMessageDialog(null, "End time can not be before starting time!!!",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } else flag = false;  //only change flag if the input is valid


        }
    }

    /**
     * Converts given time to the float convention used in this class for the ease of calculations
     *
     * @param hours Hours
     * @param min   Minutes (either 00 or 30)
     * @return a float value converted from the time given in the input
     */
    private float timeStringToFloat(String hours, String min) {
        if (!(min.equals("00") || min.equals("30"))) {
            throw new RuntimeException("*** Minutes can only be 00 or 30 ***");
        }
        if (min.equals("00")) {
            return Float.parseFloat(hours);
        } else {

            float temp = Float.parseFloat(hours);
            temp += 0.5;
            return temp;
        }

    }

    /**
     * Given the float notation time the method returns the index of TimeLine array representing that timeslot
     *
     * @param time time in the float notation of this class (9:00 = 9.0); (9:30 = 9.5)
     * @return index of TimeLine array representing the timeslot given as input
     */
    private int timeToIndex(float time) {
        if (WorkingHourStart == -1 && WorkingHourEnd == -1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }
        return (int) ((time - WorkingHourStart) * 2);
    }

    /**
     * Checks if a timeslot is available
     *
     * @param hours   Hours, has to be in the range of doctor's schedule
     * @param minutes minutes, has to be either 00 or 30
     * @return true if the timeslot is available, false otherwise
     */
    public boolean isAvailable(String hours, String minutes) {
        if (WorkingHourStart == -1 && WorkingHourEnd == -1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();   //initialize the value first
        }
        float time = timeStringToFloat(hours, minutes);
        if (time > (WorkingHourEnd - 0.5) || time < WorkingHourStart)    //check if the value is in the range of doc schedule
        {
            throw new IndexOutOfBoundsException("The time is not in the range of working schedule");
        } else {
            int index = timeToIndex(time);
            return !(TimeLine.get(index));  //if value of timeline index is false it means it is available
        }

    }

    //Feature is not implemented in the final version of the project, but the method is kept in the code to be used
    //in future iterations/update of this project.

    /**
     * Checks if a timeslot range is available.
     *
     * @param startHour starting value of hours of the timeslot range that is to be checked for availability
     * @param startMin  starting value of minutes of the timeslot range that is to be checked for availability
     * @param endHour   Ending value of hours of the timeslot range that is to be checked for availability
     * @param endMin    Ending value of minutes of the timeslot range that is to be checked for availability
     * @return True if the timeslot range is available, false otherwise
     */
    @SuppressWarnings("unused") //method is not used in the final version of the project, but is kept in the code to be used in future iterations/update of this project.
    public boolean isAvailable(String startHour, String startMin, String endHour, String endMin) {
        if (WorkingHourStart == -1 && WorkingHourEnd == -1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }
        float startTime = timeStringToFloat(startHour, startMin);
        float endTime = timeStringToFloat(endHour, endMin);
        if (startTime > (WorkingHourEnd - 0.5) || startTime < WorkingHourStart || endTime > WorkingHourEnd) {
            throw new IndexOutOfBoundsException("The time is not in the range of working schedule");
        }
        if (startTime > endTime) {
            throw new RuntimeException("End time can not be before starting time!!!");
        }

        int StartIndex = (int) (startTime - WorkingHourStart) * 2;
        int EndIndex = (int) (endTime - WorkingHourStart - 0.5) * 2;


        for (int i = StartIndex; i <= EndIndex; i++) {
            if (TimeLine.get(i)) {    //if any index in the given range is true i.e., booked, Return false showing that the slot is not available
                return false;
            }
        }
        return true;

    }

    /**
     * gives a print-friendly string of doctor's schedule
     *
     * @return a print-friendly string of the doctor's schedule
     */
    public String viewSchedule() {
        if (WorkingHourStart == -1 && WorkingHourEnd == -1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }

        StringBuilder schedule = new StringBuilder();
        int StartIndex = (int) (WorkingHourStart);
        int EndIndex = (int) (WorkingHourEnd);
        int index = 0;

        //loop for each timeslot value
        for (int i = StartIndex; i < EndIndex; i++) {
            schedule.append(i);
            schedule.append(":00-");
            schedule.append(i);
            schedule.append(":30\t");

            //if the timeslot is booked
            if (TimeLine.get(index)) {
                schedule.append("Booked for ").append(TimeIndexToPatient.get(index).getHealthCardNumber()).append('\n');
            } else {
                schedule.append("Available\n");
            }
            index++;

            schedule.append(i);
            schedule.append(":30-");
            schedule.append(i + 1);
            schedule.append(":00\t");
            if (TimeLine.get(index)) {
                schedule.append("Booked for ").append(TimeIndexToPatient.get(index).getHealthCardNumber()).append('\n');
            } else {
                schedule.append("Available\n");
            }
            index++;

        }
        return schedule.toString();
    }

    /**
     * Sets an appointment of a patient on a given time
     *
     * @param patient A Patient object whose appointment is to be set
     * @param hour    hour value of the appointment time
     * @param min     min value of the appointment time (either 00 or 30)
     */
    public void setAppointment(Patient patient, String hour, String min) {

        if (isAvailable(hour, min)) {
            float time = timeStringToFloat(hour, min);
            int index = timeToIndex(time);
            TimeLine.set(index, true);
            TimeIndexToPatient.put(index, patient);

        } else JOptionPane.showMessageDialog(null, "Timeslot Is Already Booked",
                "Appointment Conflict", JOptionPane.ERROR_MESSAGE);


    }

    /**
     * Deletes an appointment on a given time
     *
     * @param hourValue hour value of the time at which the appointment has to be deleted
     * @param minValue  minutes value of the time at which the appointment has to be deleted (either 00 or 30)
     * @return true if the process is a success, false otherwise
     */

    public boolean deleteAppointment(String hourValue, String minValue) {
        int index = timeToIndex(timeStringToFloat(hourValue, minValue));
        if (TimeLine.get(index)) //if the time slot is booked
        {
            TimeLine.set(index, false);  //free the time slot
            TimeIndexToPatient.remove(index);   //remove the index to patient relation
            return true;
        } else return false;

    }


}
