import java.io.*;
import java.util.*;


/*********************************************************
 * Author: Sauban Kidwai                                 *
 * Date: 17 May 2023                                     *
 * Purpose: An UserInput class containing all the inputs *
 * required. Also contains the method to write to        *
 * terminal and file simultaneously                      *
 *********************************************************/

public class UserInput {

    private PrintWriter filePrintWriter;
    private PrintStream originalOut;

    /*********************************************************
     * IMPORT: NONE                                          *
     * EXPORT: NONE                                          *
     *                                                       *
     * ASSERT: The constructor that sets the file write part *
     *********************************************************/ 

    public UserInput() {
        try {
            filePrintWriter = new PrintWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            System.out.println("An error occurred while creating the output file.");
            e.printStackTrace();
        }

        originalOut = System.out; // Backup the original System.out
    }

    /*********************************************************
     * IMPORT: String text                                   *
     * EXPORT: none                                          *
     *                                                       *
     * ASSERT: This method lets any print statements         *
     * be outputted to a file as well as the terminal        *
     *********************************************************/ 

    public void printToConsoleAndFile(String text) {
        originalOut.println(text); // Print to console
        filePrintWriter.println(text); // Print to file
        filePrintWriter.flush(); // Flush the PrintWriter to ensure the text is written immediately
    }

    /*********************************************************
     * IMPORT: none                                          *
     * EXPORT: Integer choice                                *
     *                                                       *
     * ASSERT: When called, the method will print out the    *
     * intro text to the user prompting them to make a       *
     * choice while making sure no error occurs              *
     *********************************************************/

    public int IntroUserInput() {

        String output = ""; // Store the output in a string
        Scanner sc = new Scanner(System.in);

        Project myProject = new Project();
        myProject.readDataFromFile("First_Nation_Infrastructure_Investment.csv");

        int count = myProject.totalProjects();

        int choice = 0;

        try {
            output += "\nWelcome to the Investments in Indigenous community infrastructure Program. There are a total of "
                    + count + " projects throughout Canada. Please make a selection from the Menu below.\n\n";
            output += "\n> 1) All of Canada";
            output += "\n> 2) Alberta";
            output += "\n> 3) British Columbia";
            output += "\n> 4) Manitoba";
            output += "\n> 5) New Brunswick";
            output += "\n> 6) Newfoundland and Labrador";
            output += "\n> 7) Nova Scotia";
            output += "\n> 8) Ontario";
            output += "\n> 9) Prince Edward Island";
            output += "\n> 10) Quebec";
            output += "\n> 11) Saskatchewan";
            output += "\n> 12) Northwest Territories";
            output += "\n> 13) Nunavut";
            output += "\n> 14) Yukon";
            output += "\n> 15) Exit Program";
            output += "\n> Answer: ";

            printToConsoleAndFile(output);

            choice = sc.nextInt();

        } catch (Exception e) {
            output += "Wrong Choice please try again";

            sc.close();
        }

        return choice;

    }

    /*********************************************************
     * IMPORT: none                                          *
     * EXPORT: none                                          *
     *                                                       *
     * ASSERT: When called, method will display the          *
     * statistics for Canada                                 *
     *********************************************************/ 

    public void AllOfCanada() {

        String output = ""; // Store the output in a string

        Project myProject = new Project();
        myProject.readDataFromFile(
                "First_Nation_Infrastructure_Investment.csv");

        int count = myProject.totalProjects();
        int totOngoing = myProject.totalOngoing();
        int totComp = myProject.totalComp();
        double compPercentage = myProject.compPercentage();

        output += "\nYou have selected All of Canada\n";

        output += "\n> The total number of projects in Canada: " + count;
        output += "\n> The total number of Ongoing projects: " + totOngoing;
        output += "\n> The total number of projects Completed: " + totComp;
        output += "\n> The percentage of Completed Projects: " + compPercentage + " %\n";

        printToConsoleAndFile(output);
    }

    /*********************************************************
     * IMPORT: Integer selection                             *
     * EXPORT: Integer choice                                *
     *                                                       *
     * ASSERT: When called, the method will display          *
     * statistics based off user selection and those         *
     * statistics are called from the projetc class.         *
     *********************************************************/ 

    public int OtherInput(int selection) {

        String output = ""; // Store the output in a string
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        String[] towns = { "All of Canada", "Alberta", "British Columbia", "Manitoba", "New Brunswick",
                "Newfoundland and Labrador", "Nova Scotia", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan",
                "Northwest Territories", "Nunavut", "Yukon" };

        String townname;
        townname = towns[selection - 1];

        try {

            output += "\nPlease make a choice from the statistics below for " + townname + "\n";

            output += "\n> 1) Number of projects";
            output += "\n> 2) Percentage of all projects located in this province/territory";
            output += "\n> 3) Total number and percentage of Ongoing projects";
            output += "\n> 4) Total number and percentage of Completed projects";
            output += "\n> 5) All of the above statistics";
            output += "\n> 6) Return to main menu";
            output += "\n> Answer: ";
            printToConsoleAndFile(output);

            choice = sc.nextInt();

            Project myProject = new Project();
            myProject.readDataFromFile("First_Nation_Infrastructure_Investment.csv");

            if (choice == 1) {
                String outputwo = ""; // Store the output in a string

                int count = myProject.countProjectsInProvince(townname);
                outputwo += "\n\nNumber of projects in " + townname + " is " + count + "\n";

                printToConsoleAndFile(outputwo);

            } else if (choice == 2) {
                String outputwo = ""; // Store the output in a string

                double percentage = myProject.projectPercentage(townname);

                outputwo += "\n\nPercentage of all projects located in " + townname + " is " + percentage + " %\n";

                printToConsoleAndFile(outputwo);

            } else if (choice == 3) {
                String outputwo = ""; // Store the output in a string

                double ongPercentage = myProject.ongoingPercentage(townname);

                int ongNum = myProject.ongoingNumber(townname);

                outputwo += "\n\nTotal number and percentage of Ongoing projects in " + townname + " is " + ongNum
                        + " and " + ongPercentage + " %\n";

                printToConsoleAndFile(outputwo);


            } else if (choice == 4) {
                String outputwo = ""; // Store the output in a string

                double compPercentage = myProject.completedPercentage(townname);

                int compNum = myProject.completedNumber(townname);

                outputwo += "\n\nTotal number and percentage of Completed projects in " + townname + " is " + compNum
                        + " and " + compPercentage + " %\n";

                printToConsoleAndFile(outputwo);

            } else if (choice == 5) {
                String outputwo = ""; // Store the output in a string

                int count = myProject.countProjectsInProvince(townname);
                double percentage = myProject.projectPercentage(townname);
                double ongPercentage = myProject.ongoingPercentage(townname);
                int ongNum = myProject.ongoingNumber(townname);
                double compPercentage = myProject.completedPercentage(townname);
                int compNum = myProject.completedNumber(townname);

                outputwo += "\n\nNumber of projects in " + townname + " is " + count + "\n";

                outputwo += "\n\nPercentage of all projects located in " + townname + " is " + percentage + "\n";

                outputwo += "\n\nTotal number and percentage of Ongoing projects in " + townname + " is " + ongNum
                        + " and " + ongPercentage + " %\n";

                outputwo += "\n\nTotal number and percentage of Completed projects in " + townname + " is " + compNum
                        + " and " + compPercentage + " %\n";

                printToConsoleAndFile(outputwo);


            } else {
                choice = 6;
            }

        } catch (Exception e) {

            output += "Wrong Choice please try again";

            sc.close();
        }

        return choice;

    }

}
