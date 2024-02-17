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

            if (waitlistAdmitted()) {
                System.out.println();
                System.out.println("Congratulations! You have been accepted off the waitlist for " + college + "!");
                acceptedColleges.add(college);
                System.out.println();
            } else {
                System.out.println();
                System.out.println("Unfortunately, you have been rejected from the waitlist for " + college + ".");
                System.out.println();
            }
        }

        return acceptedColleges;
    }

    public static boolean waitlistAdmitted() {
        double chances = 11 + Math.random() * 10 - 2.5;
        return chances > Math.min(Math.random() * 100, Math.random() * 100);
    }
}
