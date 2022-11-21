import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<Integer> question = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    public void question(String input){
        System.out.println(input);
        boolean valid = true;
        while (valid)
        {
            int choice = in.nextInt();
            if( !(choice == 0 || choice == 1 || choice ==2) )
            {
                System.out.println("Invalid Choice! Select Again...");
            }
            else{
                question.add(choice);
                valid = false;
            }
        }
    }


    public static void main(String[] args) {

        Main main = new Main();
        main.question("Do you have any allergies? \n" +
                "1) Yes " +
                "2) No" +
                "3) Maybe");

    }
}