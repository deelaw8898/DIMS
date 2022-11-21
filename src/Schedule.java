import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class Schedule {

    private float WorkingHourStart;
    private float WorkingHourEnd;
    ArrayList<Boolean> TimeLine;
    TreeMap<Integer, Patient> TimeIndexToPatient;



    public Schedule() {
        WorkingHourStart = -1;  //Default Value set such that it throws an exception if used without setting it first
        WorkingHourEnd = -1;    //Value not set in the constructor to avoid multiple GUI setters everytime the class is inherited
        //setDocSchedule();
        TimeIndexToPatient = new TreeMap<>();
        initializeTimeLine();

    }

    private void initializeTimeLine() {
        if(WorkingHourStart == -1 && WorkingHourEnd ==-1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }

        TimeLine = new ArrayList<>();
        int timeSlots = (int) ((WorkingHourEnd - WorkingHourStart)*2);  //Calculate total time slots
        for(int i=0; i<timeSlots; i++)
        {
            TimeLine.add(false);        //initialize time slots
        }


    }

    private void setDocSchedule() {
        String[] hours = { "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        String[] min = {"00","30"};
        JComboBox<String> hourStart = new JComboBox<>(hours);
        JComboBox<String> minStart = new JComboBox<>(min);
        JComboBox<String> hourEnd = new JComboBox<>(hours);
        hourEnd.setSelectedIndex(8);
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
        while(flag)
        {
            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Shift Timing", JOptionPane.OK_CANCEL_OPTION);


            String HourInput = Objects.requireNonNull(hourStart.getSelectedItem()).toString();
            String minInput = Objects.requireNonNull(minStart.getSelectedItem()).toString();

            WorkingHourStart = timeStringToFloat(HourInput, minInput);

            HourInput = Objects.requireNonNull(hourEnd.getSelectedItem()).toString();
            minInput = Objects.requireNonNull(minEnd.getSelectedItem()).toString();

            WorkingHourEnd = timeStringToFloat(HourInput, minInput);
            if(WorkingHourEnd<WorkingHourStart)
            {
                JOptionPane.showMessageDialog(null, "End time can not be before starting time!!!",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
            else flag = false;


        }
    }

    private float timeStringToFloat(String hours, String min)
    {
        if( !( min.equals("00") || min.equals("30") ) )
        {
            throw new RuntimeException("*** Minutes can only be 00 or 30 ***");
        }
        if(min.equals("00")){
            return Float.parseFloat(hours);
        }
        else{

            float temp = Float.parseFloat(hours);
            temp += 0.5;
            return temp;
        }

    }

    private int timeToIndex(float time)
    {
        if(WorkingHourStart == -1 && WorkingHourEnd ==-1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }
        int index = (int) ((time - WorkingHourStart)*2);
        return index;
    }
    public boolean isAvailable(String hours, String minutes)
    {
        if(WorkingHourStart == -1 && WorkingHourEnd ==-1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }
        float time = timeStringToFloat(hours,minutes);
        if(time > (WorkingHourEnd - 0.5) || time < WorkingHourStart)
        {
            throw new IndexOutOfBoundsException("The time is not in the range of working schedule");
        }
        else {
            int index = timeToIndex(time);
            return !(TimeLine.get(index));  //if value of timeline index is false it means it is available
        }

    }

    public boolean isAvailable(String startHour, String startMin, String endHour, String endMin) {
        if(WorkingHourStart == -1 && WorkingHourEnd ==-1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }
        float startTime = timeStringToFloat(startHour,startMin);
        float endTime = timeStringToFloat(endHour,endMin);
        if(startTime > (WorkingHourEnd - 0.5) || startTime < WorkingHourStart || endTime > WorkingHourEnd)
        {
            throw new IndexOutOfBoundsException("The time is not in the range of working schedule");
        }
        if(startTime>endTime)
        {
            throw new RuntimeException("End time can not be before starting time!!!");
        }

        int StartIndex = (int) (startTime - WorkingHourStart)*2;
        int EndIndex = (int) (endTime - WorkingHourStart - 0.5)*2;


        for(int i=StartIndex; i<= EndIndex; i++)
        {
            if(TimeLine.get(i)){    //if any index in the given range is true i.e., booked, Return false showing that the slot is not available
                return false;
            }
        }
        return true;

    }

    public String viewSchedule(){
        if(WorkingHourStart == -1 && WorkingHourEnd ==-1)   //Means that the values are not yet initialized Correctly
        {
            setDocSchedule();
        }
        StringBuilder schedule = new StringBuilder();
        int StartIndex = (int) (WorkingHourStart);
        int EndIndex = (int) (WorkingHourEnd);
        int index = 0;


        for(int i=StartIndex; i< EndIndex; i++)
        {
            schedule.append(i);
            schedule.append(":00-");
            schedule.append(i);
            schedule.append(":30\t");

            if(TimeLine.get(index))
            {
                schedule.append("Booked for ").append(TimeIndexToPatient.get(index).getHealthCardNumber()).append('\n');
                index++;
            }
            else
            {
                schedule.append("Available\n");
                index++;
            }

            schedule.append(i);
            schedule.append(":30-");
            schedule.append(i + 1);
            schedule.append(":00\t");
            if(TimeLine.get(index))
            {
                schedule.append("Booked for ").append(TimeIndexToPatient.get(index).getHealthCardNumber()).append('\n');
                index++;
            }
            else
            {
                schedule.append("Available\n");
                index ++;
            }

        }
        return schedule.toString();
    }

    public void setAppointment(Patient patient, String hour, String min)
    {
        boolean flag = true;
        while(flag)
        {
            if(isAvailable(hour,min))
            {
                float time = timeStringToFloat(hour,min);
                int index = timeToIndex(time);
                TimeLine.set(index,true);
                TimeIndexToPatient.put(index,patient);
                flag = false;

            }
            else JOptionPane.showMessageDialog(null, "Timeslot Is Already Booked",
                    "Appointment Conflict", JOptionPane.ERROR_MESSAGE);

        }

    }

    public boolean deleteAppointment(String hourValue, String minValue) {
        int index = timeToIndex(timeStringToFloat(hourValue,minValue));
        if(TimeLine.get(index)) //if the time slot is booked
        {
            TimeLine.set(index,false);  //free the time slot
            TimeIndexToPatient.remove(index);   //remove the index to patient relation
            return true;
        }
        else return false;

    }



    public static void main(String [] args)
    {
        Schedule schedule = new Schedule();
        System.out.println(schedule.TimeLine);
        System.out.println(schedule.viewSchedule());
        System.out.println("--------------------------");
        Patient patient = new Patient("Mr","waleed","Ahmad","123-456-789","walee8898@gmail.com","18/Nov/1999","Konoha","1H2 3J4","090078601","","","Kenpachi","000000","Soul Reaper","Soul Society","Captain Yamamoto");
        schedule.setAppointment(patient, "9","30");
        System.out.println(schedule.TimeLine);
        System.out.println(schedule.viewSchedule());

    }

/*
    public boolean deleteAppointment(String healthCardNum) {
        Patient patient = getPatient(healthCardNum);
        if((patient != null) && TimeIndexToPatient.containsValue(patient))
        {
            for(int i =0; i< TimeLine.size(); i++)  //check all timeslots
            {
                if(TimeIndexToPatient.get(i).getHealthCardNumber().equals(healthCardNum))
                {
                    TimeLine.set(i,false);  //free the scheduled appointment
                    TimeIndexToPatient.remove(i);   //remove the timeslot to patient relation
                }
            }
            return true;    //indicating the deletion process was a success
        }
        else return false;  //indicating the process failed or no appointment found
    }

 */


}
