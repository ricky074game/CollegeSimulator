import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CollegeSimulator {
    private static List<String> collegeList = Arrays.asList(
        "Massachusetts Institute of Technology (MIT)", "Harvard University", "Stanford University",
            "University of California Berkeley (UCB)", "University of Chicago", "University of Pennsylvania",
            "Cornell University", "California Institute of Technology (Caltech)", "Yale University",
            "Princeton University", "Columbia University", "Johns Hopkins University",
            "University of California, Los Angeles (UCLA)", "University of Michigan-Ann Arbor",
            "New York University (NYU)", "Northwestern University", "Carnegie Mellon University",
            "Duke University", "University of Texas at Austin", "University of California-San Diego (UCSD)",
            "University of Washington", "University of Illinois at Urbana-Champaign", "Brown University",
            "Pennsylvania State University", "Boston University", "Georgia Institute of Technology (Georgia Tech)",
            "Purdue University", "University of Wisconsin-Madison", "University of Southern California",
            "University of California, Davis (UCD)", "University of North Carolina--Chapel Hill",
            "Texas A&M University", "Michigan State University", "Rice University", "Ohio State University",
            "Washington University in St. Louis", "University of California, Santa Barbara (UCSB)",
            "University of Florida", "University of Maryland, College Park", "Arizona State University",
            "University of Minnesota, Twin Cities", "Emory University", "University of Pittsburgh",
            "University of Rochester", "Dartmouth College", "University of Massachusetts, Amherst",
            "Case Western Reserve University", "University of Virginia", "Vanderbilt University",
            "University of Colorado at Boulder", "University of California-Irvine (UCI)",
            "North Carolina State University", "University of Miami", "University of Arizona",
            "Georgetown University", "Rutgers - The State University of New Jersey-New Brunswick",
            "Virginia Polytechnic Institute (Virginia Tech)", "University of Notre Dame",
            "Indiana University Bloomington", "University of Illinois, Chicago (UIC)",
            "University of California, Santa Cruz (UCSC)", "George Washington University", "Yeshiva University",
            "Northeastern University", "Tufts University", "University of Hawai'i at Manoa",
            "Stony Brook University", "Iowa State University", "University of California, Riverside (UCR)",
            "Colorado State University", "University of Kansas", "Washington State University", "University of Utah",
            "University of Connecticut", "University of Tennessee, Knoxville", "Florida State University",
            "Colorado School of Mines", "Illinois Institute of Technology", "Missouri University of Science and Technology",
            "University at Buffalo SUNY", "University of Iowa", "Rensselaer Polytechnic Institute",
            "University of Delaware", "Oregon State University", "University of Georgia", "University of Texas Dallas",
            "City University of New York", "Lehigh University", "University of Nebraska - Lincoln",
            "Florida International University", "University of South Florida", "University of South Carolina",
            "University of Missouri, Columbia", "University of Central Florida", "Tulane University", "Drexel University",
            "Stevens Institute of Technology", "Boston College"
    );

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the College Application Simulator!");
        System.out.println("Choose an option:");
        System.out.println("(a) Completely simulate the application");
        System.out.println("(b) Go through the application questions and simulate answers");
        System.out.println("(c) Manually enter everything");

        System.out.println();
        String option = input.next().toLowerCase();
        System.out.println();

        switch (option) {
            case "a":
                simulateCompleteApplication(input, random);
                break;
            case "b":
                simulateApplicationQuestions(input, random);
                break;
            case "c":
                manuallyEnterInformation(input);
                break;
            default:
                System.out.println("Invalid option. Please restart the program and choose a valid option.");
        }
    }

    private static void simulateCompleteApplication(Scanner input, Random random) {
        String name = input("Enter your name: ");
        double GPA = statsSimulator.simulateGPA(random);
        double essayStrength = statsSimulator.simulateEssayStrength(input);
        double extracurriculars = statsSimulator.simulateExtracurriculars(input);
        int SAT = statsSimulator.simulateSAT(input, random);
        int ACT = statsSimulator.simulateACT(input, random);
    
        printProfile(name, GPA, essayStrength, extracurriculars, SAT, ACT);
    
        System.out.println();
        ArrayList<Integer> colleges = collegeApplications(input, GPA, SAT, ACT, extracurriculars, essayStrength);
        System.out.println();

        simulateInterviewAndPrintResults(colleges, random, GPA, SAT, ACT, extracurriculars, essayStrength);
        System.out.println();

        makeCollegeDecisions(colleges, name);
    }    

    private static void simulateInterviewAndPrintResults(ArrayList<Integer> colleges, Random random,
                                                     double GPA, int SAT, int ACT,
                                                     double extracurriculars, double essayStrength) {
    System.out.println("Simulating interview strength for each school:");
    
    // Separate lists for interview strengths and admission chances
    List<String> interviewStrengthList = new ArrayList<>();
    List<String> admissionChanceList = new ArrayList<>();

    for (Integer college : colleges) {
        double interviewStrength = simulateInterviewStrength(random);
        interviewStrengthList.add(getCollegeById(college) + ": Interview Strength - " + interviewStrength);

        double admissionChance = collegeChances.chances(college, GPA, SAT, ACT, extracurriculars, essayStrength, interviewStrength);
        String formattedChance = String.format("%.2f", admissionChance);
        admissionChanceList.add(getCollegeById(college) + ": Admission Chance - " + formattedChance + "% || (" + collegeChances.getType(admissionChance) + ")");
    }

    // Print interview strengths
    System.out.println("Interview Strengths:");
    for (String strength : interviewStrengthList) {
        System.out.println(strength);
    }

    // Print admission chances
    System.out.println("\nAdmission Chances:");
    for (String chance : admissionChanceList) {
        System.out.println(chance);
    }

    System.out.println();
}

    private static void simulateApplicationQuestions(Scanner input, Random random) {
        String name = input("Enter your name: ");
        double GPA = statsSimulator.simulateGPA(random);
        double essayStrength = statsSimulator.simulateEssayStrength(input);
        double extracurriculars = statsSimulator.simulateExtracurriculars(input);
        int SAT = statsSimulator.simulateSAT(input, random);
        int ACT = statsSimulator.simulateACT(input, random);
    
        printProfile(name, GPA, essayStrength, extracurriculars, SAT, ACT);
    
        ArrayList<Integer> colleges = collegeApplications(input, GPA, SAT, ACT, extracurriculars, essayStrength);
        System.out.println();
    
        simulateInterviewAndPrintResults(colleges, random, GPA, SAT, ACT, extracurriculars, essayStrength);
        System.out.println();
    
        makeCollegeDecisions(colleges, name);
    
        System.out.println("Thank you for using the College Application Simulator!");
    }
    
    private static void manuallyEnterInformation(Scanner input) {
        String name = input("Enter your name: ");
        double GPA = inputDoubleRange("Enter your GPA (between 0.00 and 100.00): ", 0.00, 100.00);
        double essayStrength = inputDoubleRange("Enter your essay strength (out of 10): ", 0, 10);
        double extracurriculars = inputDoubleRange("Enter your extracurricular activities strength (out of 10): ", 0, 10);
    
        int SAT;
        do {
            System.out.println();
            SAT = inputInt("Enter your SAT score (0 if not applicable): ");
            if (SAT % 10 != 0 || (SAT > 0 && SAT < 400) || SAT > 1600) {
                System.out.println("That SAT is not possible!");
            }
        } while (SAT % 10 != 0 || (SAT > 0 && SAT < 400) || SAT > 1600);
    
        int ACT;
        do {
            System.out.println();
            ACT = inputInt("Enter your ACT score (0 if not applicable): ");
            if (ACT < 0 || ACT > 36) {
                System.out.println("That ACT is not possible!");
            }
        } while (ACT < 0 || ACT > 36);
    
        ArrayList<Integer> colleges = collegeApplications(input, GPA, SAT, ACT, extracurriculars, essayStrength);
        printProfile(name, GPA, essayStrength, extracurriculars, SAT, ACT);
    
        System.out.println();
        simulateInterviewAndPrintResults(colleges, new Random(), GPA, SAT, ACT, extracurriculars, essayStrength);
        System.out.println();

        makeCollegeDecisions(colleges, name);
        System.out.println("Thank you for using the College Application Simulator!");
    }
    
    private static double simulateInterviewStrength(Random random) {
        return Math.round((Math.max(random.nextDouble() * 10.0, random.nextDouble() * 10.0)) * 100.0) / 100.0;  // Simulate interview strength between 0.0 and 10.0
    }

    private static double inputDoubleRange(String prompt, double min, double max) {
        double value;
        do {
            System.out.print(prompt);
            value = inputDouble(prompt);
            if (value < min || value > max) {
                System.out.println("Value must be between " + min + " and " + max + "!");
            } else {
                break;
            }
        } while (true);
        return value;
    }

    private static double inputDouble(String prompt) {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(new Scanner(System.in).next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }

    private static String input(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.next();
    }

    private static int inputInt(String prompt) {
        int value = 0;
        boolean isValidInput = false;
    
        while (!isValidInput) {
            try {
                value = Integer.parseInt(input(prompt));
                isValidInput = true; // Break out of the loop if parsing is successful
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    
        return value;
    }
    

    private static void printProfile(String name, double GPA, double essayStrength, double extracurriculars, int SAT, int ACT) {
        System.out.println("STUDENT PROFILE");
        System.out.println("Name: " + name);
        System.out.println("GPA: " + GPA);
        System.out.println("Essay Strength: " + essayStrength);
        System.out.println("Extracurriculars: " + extracurriculars);
        System.out.println("SAT: " + (SAT != 0 ? SAT : "N/A"));
        System.out.println("ACT: " + (ACT != 0 ? ACT : "N/A"));
        System.out.println();
    }

    private static ArrayList<Integer> collegeApplications(Scanner input, double GPA, int SAT, int ACT, double extracurriculars, double essayStrength) {
        System.out.println();
        ArrayList<Integer> colleges = new ArrayList<>();
        int collegesLeft = 12;
        String option = ""; 

        while (collegesLeft > 0) {
            if (collegesLeft == 12) {
                System.out.println();
                System.out.println("Choose an option for the regular decision applications process:");
                System.out.println("a) Manually enter preferences");
                System.out.println("b) Select random colleges");
                System.out.println("c) Choose recommended colleges");
                
                System.out.print("Enter your option (a, b, or c): ");
                System.out.println();
                option = input.next().toLowerCase();
            }
        if (option.equals("a")) {
            System.out.println("What school do you want to apply to? You can apply to " + collegesLeft + " more colleges. Type 'help' to find out colleges you can apply to, and type 'done' when you are done.");
            String userInput = input.next();

            if (userInput.equalsIgnoreCase("help")) {
                System.out.println();
                System.out.println("List of Colleges:");
                listColleges();
                System.out.println();
                continue;
            }

            if (userInput.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int collegeId = Integer.parseInt(userInput);
                if (isValidCollegeId(collegeId) && !colleges.contains(collegeId)) {
                    colleges.add(collegeId);
                    collegesLeft--;
                    System.out.println("You have applied to: " + getCollegeById(collegeId));
                    System.out.println();
                } else {
                    System.out.println("Invalid college ID or you have already applied to this college. Please choose a different college.");
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid college ID, type 'help' for assistance, or type 'done' to finish.");
                System.out.println();
            }

            printAppliedColleges(colleges);
        }
    else if (option.equals("b")) {
        ArrayList<Integer> coll = collegeApplicationSimulation(new Random());
        for (int college : coll) {
            colleges.add(college);
            collegesLeft--;
        }
        break;
    }
    else if (option.equals("c")) {
        ArrayList<Integer> rec = statsSimulator.recommendedColleges(GPA, SAT, ACT, essayStrength, extracurriculars); // HOW TO GET stats here (gpa, extracurriculars, etc));
        for (int college : rec) {
            colleges.add(college);
            collegesLeft--;
        }
    }
    else {
        System.out.println("Invalid option. Please restart the program and choose a valid option.");
    }

    }
    return colleges;
}

    private static ArrayList<Integer> collegeApplicationSimulation(Random random) {
        System.out.println();
        ArrayList<Integer> colleges = new ArrayList<>();
        int collegesLeft = 12;

        while (collegesLeft > 0) {
            int randomCollegeId = random.nextInt(collegeList.size()) + 1;
            if (!colleges.contains(randomCollegeId)) {
                colleges.add(randomCollegeId);
                collegesLeft--;
            }
    }

        return colleges;
    }

    private static void listColleges() {
        for (int i = 0; i < collegeList.size(); i++) {
            System.out.println((i + 1) + ". " + collegeList.get(i));
        }
    }

    private static String getCollegeById(int collegeId) {
        if (isValidCollegeId(collegeId)) {
            return collegeList.get(collegeId - 1);
        } else {
            return "N/A";
        }
    }

    private static boolean isValidCollegeId(int collegeId) {
        return collegeId >= 1 && collegeId <= collegeList.size();
    }

    private static void printAppliedColleges(ArrayList<Integer> colleges) {
        System.out.println("Colleges you have applied to: ");
        for (Integer college : colleges) {
            System.out.println(getCollegeById(college));
        }
        System.out.println();
    }


    private static String collegeAdmission(double chances) {
        Random random = new Random();
        
        // Add a bit of randomness (+- 1-2%)
        double randomFactor = random.nextDouble() * 12 - 6;
        double modifiedChances = chances + randomFactor;

        // Check if the modified chances are greater than a random value between 0 and 100
        double yourFate = random.nextDouble() * 100;
        if (modifiedChances > yourFate) {
            return "Admitted";
        }
        else if (modifiedChances + 10 > yourFate) {
            return "Waitlisted";
        }
        else {
            return "Rejected";
        }
    }

    private static void makeCollegeDecisions(ArrayList<Integer> colleges, String name) {
        int[] collegeArray = new int[colleges.size()];

        for (int i = 0; i < colleges.size(); i++) {
            collegeArray[i] = colleges.get(i);
        }

        collegeDecision(collegeArray, name);
    }

    private static void collegeDecision(int[] colleges, String name) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> acceptedColleges = new ArrayList<>();
        ArrayList<String> waitlistColleges = new ArrayList<>();
    
        for (int college : colleges) {
            String collegeName = getCollegeById(college);
    
            System.out.println("Admissions decision for " + collegeName + " is in for " + name + ". Would you like to see the decision? (yes/no)");
    
            String proceed = scanner.next().toLowerCase();
    
            if (proceed.equals("no")) {
                System.out.println("No action taken for " + collegeName + ". Moving on to the next college...");
                System.out.println();
                continue;
            }
    
            String result = collegeAdmission(college);

            if (result.equals("Admitted")) {
            System.out.println("Congratulations! You have been admitted to " + collegeName + "!");
            acceptedColleges.add(collegeName);
            } else if (result.equals("Waitlisted")) {
            System.out.println("Your application to " + collegeName + " is on the waitlist. There's a possibility of admission if spots become available.");
            waitlistColleges.add(collegeName);
        } else {
            // Simulate rejection
            System.out.println("We regret to inform you that your application to " + collegeName + " has been unsuccessful.");
}

System.out.println();

        }
    
        System.out.println();
        System.out.println("Accepted Colleges:");
        for (String college : acceptedColleges) {
            System.out.println(college);
        }


        System.out.println();
        System.out.println("Waitlisted Colleges:");
        for (String college : waitlistColleges) {
            System.out.println(college);
        }

        if (waitlistColleges.size() > 0) {
        ArrayList<String> combinedColleges = Waitlist.waitlistResults(waitlistColleges);
        acceptedColleges.addAll(combinedColleges);
        }

        System.out.println();
        System.out.println("Accepted Colleges:");
        for (String college : acceptedColleges) {
            System.out.println(college);
        }

        int score = calculateScore(acceptedColleges);
        double avCAP = calculateRawScore(acceptedColleges, score);

        System.out.println();
        System.out.println("Your College Admission Points: " + score);
        System.out.println("Your Average College Admission Points: " + avCAP);
        
        System.out.println();
        
        int myCollegeID = 0;
    boolean validInput = false;

    while (!validInput) {
    System.out.println("Which college would you like to attend? Pick the position of the college on this list: " + acceptedColleges);
    System.out.println();

    // Check if the input is an integer
    if (scanner.hasNextInt()) {
        myCollegeID = scanner.nextInt();

        // Check if the input is within the bounds of the accepted colleges list
        if (myCollegeID >= 1 && myCollegeID <= acceptedColleges.size()) {
            validInput = true;  // Set the flag to exit the loop
        } else {
            System.out.println("Invalid input. Please enter a valid position from the list.");
        }
    } else {
        System.out.println("Invalid input. Please enter a valid integer position from the list.");
        scanner.next();  // Consume invalid input to avoid an infinite loop
    }
}

System.out.println("You are now enrolled in " + acceptedColleges.get(myCollegeID - 1) + "! Congratulations!");
System.out.println();

        
    }

    private static int getCollegeIdByName(String collegeName) {
    
        List<String> collegeList = Arrays.asList(
            "Massachusetts Institute of Technology (MIT)", "Harvard University", "Stanford University",
            "University of California Berkeley (UCB)", "University of Chicago", "University of Pennsylvania",
            "Cornell University", "California Institute of Technology (Caltech)", "Yale University",
            "Princeton University", "Columbia University", "Johns Hopkins University",
            "University of California, Los Angeles (UCLA)", "University of Michigan-Ann Arbor",
            "New York University (NYU)", "Northwestern University", "Carnegie Mellon University",
            "Duke University", "University of Texas at Austin", "University of California-San Diego (UCSD)",
            "University of Washington", "University of Illinois at Urbana-Champaign", "Brown University",
            "Pennsylvania State University", "Boston University", "Georgia Institute of Technology (Georgia Tech)",
            "Purdue University", "University of Wisconsin-Madison", "University of Southern California",
            "University of California, Davis (UCD)", "University of North Carolina--Chapel Hill",
            "Texas A&M University", "Michigan State University", "Rice University", "Ohio State University",
            "Washington University in St. Louis", "University of California, Santa Barbara (UCSB)",
            "University of Florida", "University of Maryland, College Park", "Arizona State University",
            "University of Minnesota, Twin Cities", "Emory University", "University of Pittsburgh",
            "University of Rochester", "Dartmouth College", "University of Massachusetts, Amherst",
            "Case Western Reserve University", "University of Virginia", "Vanderbilt University",
            "University of Colorado at Boulder", "University of California-Irvine (UCI)",
            "North Carolina State University", "University of Miami", "University of Arizona",
            "Georgetown University", "Rutgers - The State University of New Jersey-New Brunswick",
            "Virginia Polytechnic Institute (Virginia Tech)", "University of Notre Dame",
            "Indiana University Bloomington", "University of Illinois, Chicago (UIC)",
            "University of California, Santa Cruz (UCSC)", "George Washington University", "Yeshiva University",
            "Northeastern University", "Tufts University", "University of Hawai'i at Manoa",
            "Stony Brook University", "Iowa State University", "University of California, Riverside (UCR)",
            "Colorado State University", "University of Kansas", "Washington State University", "University of Utah",
            "University of Connecticut", "University of Tennessee, Knoxville", "Florida State University",
            "Colorado School of Mines", "Illinois Institute of Technology", "Missouri University of Science and Technology",
            "University at Buffalo SUNY", "University of Iowa", "Rensselaer Polytechnic Institute",
            "University of Delaware", "Oregon State University", "University of Georgia", "University of Texas Dallas",
            "City University of New York", "Lehigh University", "University of Nebraska - Lincoln",
            "Florida International University", "University of South Florida", "University of South Carolina",
            "University of Missouri, Columbia", "University of Central Florida", "Tulane University", "Drexel University",
            "Stevens Institute of Technology", "Boston College"
        );
    
        int index = collegeList.indexOf(collegeName);
        if (index != -1) {
            // College name found, return the corresponding college ID (adding 1)
            return index + 1;
        } else {
            // College name not found or invalid
            return -1;
        }
    }
    
    public static int calculateScore(ArrayList<String> acceptedColleges) {
        int score = 0;
        for (String college : acceptedColleges) {
            if (getCollegeIdByName(college) < 4) {
                score += 50;
            }
            else if (getCollegeIdByName(college) < 4) {
                score += 50;
            }
            else if (getCollegeIdByName(college) < 11) {
                score += 40;
            }
            else if (getCollegeIdByName(college) < 20) {
                score += 30;
            }
            else if (getCollegeIdByName(college) < 34) {
                score += 23;
            }
            else if (getCollegeIdByName(college) < 48) {
                score += 18;
            }
            else if (getCollegeIdByName(college) < 66) {
                score += 13;
            }
            else if (getCollegeIdByName(college) < 81) {
                score += 9;
            }
            else {
                score+= 5;
            }

        }

        return score;
    }

    public static double calculateRawScore(ArrayList<String> acceptedColleges, int score) {
        double rawScore = ((score / 12.0) / 100) * 100;
        return Math.round(rawScore * 100.0) / 100.0;
    }

}    