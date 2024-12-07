
package com.model;

import java.util.HashMap;
import java.util.Scanner;

public class Course {

    private Exercise exercise;
    public Double progress;
    public Language language;
    private User user;
    private static Scanner kb = new Scanner(System.in);

    /**
     * Default Constructor
     */
    public Course() {
        this.progress = 0.0;
        this.language = null;
        this.exercise = new Exercise();
    }

    /**
     * Parameter Constructor that takes in a user object so that their info can be used for getting any needed course info.
     * 
     * @param user
     */
    public Course(User user) {
        this.user = user;
    }

    public Course(Language language, Double progress){
        this.language = language;
        this.progress = progress;
    }

    /**
     * MAIN METHOD FOR CLASS
     * This class runs the course file and everything that's tied to it.
     * It first calls selectCourse() to let the user choose their language.
     * getExercise() to then start the exercises.
     * and lastly shows the user their score and updates their progress if they got 60% or more.
     */
    public void runCourse(User user) {
        selectCourse();
        getExercise(user);

        double accuracy = exercise.startExercise();
        System.out.println("Your accuracy was: " + accuracy + "%");

        checkScore(accuracy);
        Users.getInstance().updateUser(user);
    }

    /**
     * getExercise method that calls the "createExercise" method in Exercise.java and sends in the desired language (with it's progress) the user wants to start practicing.
     */
    public Exercise getExercise(User user) {
        exercise = new Exercise(this.language, user.getLangProgress(this.language));
        return exercise;
    }

    /**
     * selectCourse method that prompts the user for which of the languages they are currently practicing they want to practice. It will then set the language and progress variables as needed
     * and send that info to Exercise.java to create the needed questions for.
     */
    public void selectCourse() {
        System.out.println("Which language would you like to practice?");
        HashMap<Language, Double> languages = user.getProgress();
        for (Language language : languages.keySet()) {
            System.out.println(language);
        }
        String input = kb.nextLine();
        for (Language lang : languages.keySet()) {
            if(input.equalsIgnoreCase((lang.toString()))) {
                this.language = lang;
                System.out.println("You've selected: " + lang);
                this.progress = getCourseProg(lang);
                break;
            }
        }
        if (this.language == null) {
            System.out.println("Error! Invalid language selected");
            selectCourse();
        }
    }

    public void checkScore(double accuracy) {
        if (accuracy >= 60.0) {
            gainPoint();
        }
    }

    /**
     * gainPoint method that changes the progress value of a user for exercise.java's use.
     * It creates a hashmap clone of the user object's progress, adds the new score to the value associated with the language, and then sends that hashmap back to the user so it can set the progress map as such.
     * It also includes an else case if the user doesn't have any progress for that language (defaults it to 0.1).
     */
    public void gainPoint() {
        HashMap<Language, Double> userProgress = user.getProgress();
        if (userProgress.containsKey(this.language)) {
            double currentProgress = userProgress.get(this.language);
            userProgress.put(this.language, currentProgress);
        } else {
            userProgress.put(this.language, 0.1);
        }
        user.setProgress(userProgress);
    }

    /**
     * losePoint method that changes the progress value of a user for exercise.java's use.
     */
    public void losePoint() {
        this.progress -= .1;
    }

    /**
     * getCourseProg method that takes in a language, loads the user's hashmap of courses, and then returns the double associated with the language inputed.
     * 
     * @param lang
     * @return Double progress
     */
    public double getCourseProg(Language lang) {
        Double prog = user.getLangProgress(lang);
        return prog;
    }

    public void updateScore(User user, Double accuracy){
        checkScore(accuracy);
        Users.getInstance().updateUser(user);
    }


}
