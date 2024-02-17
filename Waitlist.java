import java.util.ArrayList;
import java.util.Scanner;

public class Waitlist {
    public static ArrayList<String> waitlistResults(ArrayList<String> colleges) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        ArrayList<String> acceptedColleges = new ArrayList<>();

        System.out.println("The waitlist results are in. Type anything to proceed");
        input.next();  // Consuming user input, assuming any input is valid

        System.out.println();
        for (String college : colleges) {
            System.out.println("Results are in for " + college + " on the waitlist. Ready to proceed?");
            input.next();  // Consuming user input, assuming any input is valid

            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            if (waitlistAdmitted(CollegeSimulator.getCollegeIdByName(college))) {
                System.out.println("Congratulations! You have been accepted off the waitlist for " + college + "!");
                acceptedColleges.add(college);
            } else {
                System.out.println("Unfortunately, you have been rejected from the waitlist for " + college + ".");
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            System.out.println();
          }

        input.close();
        return acceptedColleges;
    }

    public static boolean waitlistAdmitted(int college) {
        double chances = 0;
        if (college < 4) {
            chances = 4 + Math.random() * 4 - 2;    
        }
        else if (college < 10) {
             chances = 6 + Math.random() * 6 - 3;    
        }
        else if (college < 17) {
             chances = 8 + Math.random() * 8 - 4;    
        }
        else if (college < 25) {
             chances = 10 + Math.random() * 8 - 4;    
        }
        else if (college < 34) {
             chances = 12.5 + Math.random() * 8 - 4;    
        }
        else if (college < 45) {
             chances = 15 + Math.random() * 10 - 5;    
        }
        else if (college < 60) {
             chances = 18 + Math.random() * 12 - 6;    
        }
        else if (college < 80) {
             chances = 24 + Math.random() * 14 - 7;    
        }
        else {
             chances = 30 + Math.random() * 16 - 8;
        }
        return chances > Math.min(Math.random() * 100, Math.random() * 100);
    }
}
