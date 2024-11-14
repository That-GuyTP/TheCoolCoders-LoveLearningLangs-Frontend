package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class LikeLearningLangsUI {

    // Instance Variables
    private static Scanner kb = new Scanner(System.in);
    private static LikeLearningLangs lll;
    private static boolean quit = false;
    private static int input = 0;

    // MAIN METHOD
    public static void main(String args[]) {
        lll = new LikeLearningLangs();
        welcome();

        //jimSmith1();
        jimSmith2();
        jimSmith3();
    }

    // public static void main(String args[]) {
    // lll = new LikeLearningLangs();
    // welcome();

    // jimSmith1();

    // while (quit != true) {
    // choices();
    // input = kb.nextInt();
    // kb.nextLine();
    // checkInput(input);
    // choiceSwitch(input);
    // }
    // System.exit(0);
    // }

    // HELPER METHODS
    // Implement further sort of possible choices. Aka make first set of choices
    // (login, register, or quit), then after the user has logged into an accout
    // show them (get courses, get exercise, view progress, logout).
    private static void welcome() {
        System.out.println("Welcome to the LoveLearningLangs app! Please choose from one of the options below");
    }

    private static void choices() {
        System.out.println("1: Login"
                + "\n2: Register"
                + "\n3: Get Courses w/ Progress"
                + "\n4: Start Exercise"
                + "\n5: Learn"
                + "\n6: View Account Info"
                + "\n9: Logout");
    }

    private static int checkInput(Integer input) {
        while (input == 0) {
            if (input == 1 || input == 2 || input == 3 || input == 4 || input == 5 || input == 6 || input == 9) {
                return input;
            } else {
                System.out.println("Sorry not a valid choice. Choose again.");
                return 0;
            }
        }
        return input;
    }

    private static void choiceSwitch(int choice) {
        switch (choice) {
            case 1:
                lll.login();
                System.out.println("");
                break;
            case 2:
                register();
                System.out.println("");
                break;
            case 3:
                lll.getCourses();
                System.out.println("");
                break;
            case 4:
                getExercise();
                System.out.println("Not implemented yet");
                break;
            case 5:
                /// lll.learn();
                System.out.println("");
            case 6:
                lll.viewAccount();
                System.out.println("");
                break;
            case 9:
                // lll.signOut();
                System.out.println("Logging out");
                lll.signOut();
                quit = false;
                System.exit(0);
                break;
            default:
                System.out.println("Error has occured try program again");
        }
    }

    /*
    public static void jimSmith1() {
        if (!lll.checkUsers("jSmith1", "password123")) {
            lll.registerUser("jSmith1", "password123", "Jim", "Smith", "jsmith1@gmail.com");
        }
        lll.login("jSmith1", "password123");
        // --- DBUGGING - THOMAS PETERSON
        lll.addLanguage(Language.SPANISH);
        System.out.println("");
        System.out.println("---------------");
        lll.viewAccount();
        lll.getCourses();
        System.out.println("---------------");
        System.out.println("");
    }
         */

    public static void jimSmith2() {
        // Needs to Learn a little
        lll.addLanguage(Language.SPANISH);
        lll.getCourse(Language.SPANISH);
        learn();
        lll.getExercise();
        lll.updateProgress(2.0);
        lll.getCourses();
        learn();
        lll.updateProgress(2.0);
        lll.getExercise();
    }

    public static void jimSmith3() {
        lll.viewAccount();
        lll.updateProgress(3.0);
        lll.getCourses();
        lll.signOut();
    }

    /**
     * Encapsulated all dialogue that involves the user to this class(UI). It will
     * check the username and password first to see if an account exists
     * with the same username/password.
     */
    public static void register() {
        boolean success = false;
        while (success != true) {
            System.out.println("");
            System.out.println("Insert a username: ");// Username
            String username = kb.nextLine();
            System.out.println("Insert a password: "); // Password
            String password = kb.nextLine();
            /*if (lll.checkUsers(username, password)) {
                System.out.println(
                        "Sorry, this username or password seems to be connected to an account. Try logging in.");
            } else {
                System.out.println("Insert first name: "); // First name
                String firstname = kb.nextLine();
                System.out.println("Insert last name: "); // Last name
                String lastname = kb.nextLine();
                System.out.println("Insert email: "); // Email
                String email = kb.nextLine();
                lll.registerUser(username, password, firstname, lastname, email);
                success = true;
            }*/

        }
    }

    public static void getExercise() {
        if (!lll.isLoggedIn) {
            System.out.println("Login to start exercises");
            return;
        }
        System.out.println("Which language would you like to practice?");
        HashMap<Language, Double> languages = lll.getCurrentUser().getProgress();
        for (Language language : languages.keySet()) {
            System.out.println(language);
        }
        String input = kb.nextLine().toUpperCase();
        for (Language lang : languages.keySet()) {
            if (input.equals(lang.toString())) {
                lll.getCourse(Language.valueOf(input));
                lll.getExercise();
            } else if (Language.valueOf(input) != null) {
                addLanguagePrompt();
                return;
            } else {
                System.out.println("Invalid Input. Try Again.");
                getExercise();
            }

        }
    }

    private static void addLanguagePrompt() {
        System.out.println("Language not found. Would you like to add it? (y/n)");
        String response = kb.nextLine().trim().toLowerCase();

        if (response.equals("y")) {
            System.out.println("Enter the langauge you would like to add");
            String input = kb.nextLine().toUpperCase(null);
            lll.addLanguage(Language.valueOf(input));
        } else if (response.equals("n")) {
            System.out.println("Operation canceled.");
        } else {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            addLanguagePrompt(); // Prompt again if the input was invalid
        }
    }

    public static void learn() {
        if (lll.isLoggedIn) {
            HashMap<String, String> reviewPhrases = lll.getReviewPhrases();
            for (Map.Entry<String, String> entry : reviewPhrases.entrySet()) {
                boolean doContinue = true;
                String key = entry.getKey();
                String value = entry.getValue();
                while (doContinue) {
                    System.out.println(key + " is pronounced as " + value);
                    Narrator.playSound(key + " is pronounced as " + value);
                    System.out.println("Play Sound again? (Y to play again, N to continue to next word, Q to quit)");
                    String response = kb.nextLine().trim().toLowerCase();
                    switch (response) {
                        case "y":
                            break;
                        case "n":
                            doContinue = false;
                            break;
                        case "q":
                            return;
                        default:
                            System.out.println("Invalid input. Please enter 'Y', 'N', or 'Q'.");
                    }
                }
            }
        } else {
            System.out.println("Log in to be able to learn");
            return;
        }
    }

}
