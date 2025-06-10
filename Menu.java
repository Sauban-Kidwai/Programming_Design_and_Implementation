import java.util.*;


/*********************************************************
 * Author: Sauban Kidwai                                 *
 * Date: 17 May 2023                                     *
 * Purpose: A menu class which is run for the program to *
 * begin                                                 *
 *********************************************************/


public class Menu {
    public static void main(String[] args) {
        int selection = 0;
        boolean programQuit = false;
        Scanner sc = new Scanner(System.in);
        UserInput userinput = new UserInput();

        while (!programQuit) {
            selection = userinput.IntroUserInput();

            while (!programQuit && (selection != 15 && selection != 6)) {
                if (selection == 1) {
                    userinput.AllOfCanada();
                    selection = 6;
                } else if (selection >= 2 && selection <= 14) {
                    userinput.OtherInput(selection);
                } else {
                    System.out.println("Wrong Choice. Please Try again.");
                }

                if (selection != 6) {
                    selection = userinput.OtherInput(selection);
                } else {
                    selection = userinput.IntroUserInput();
                }
            }

            if (selection == 15) {
                programQuit = true; // Set programQuit to true to exit the outer loop
                System.out.println("Exiting Program.....");
            }
        }

        sc.close();
    }

}