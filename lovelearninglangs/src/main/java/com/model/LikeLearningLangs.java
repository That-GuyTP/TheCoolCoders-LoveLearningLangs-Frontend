package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class LikeLearningLangs {
    
    private static LikeLearningLangs instance;

    private User currentUser;
    private static Users users;
    private ArrayList<Phrase> phrases;
    private ArrayList<Word> words;
    private Language currentLanguage;

    private Course course;
    public boolean isLoggedIn;
    public static final Scanner kb = new Scanner(System.in);

    public LikeLearningLangs() {
        users = Users.getInstance();
        this.currentUser = new User();
        Phrases.getInstance();
        course = new Course();
    }

    public static LikeLearningLangs getInstance(){
        if(instance == null){
            instance = new LikeLearningLangs();
        }
        return instance;
    }

    /**
     * calls login method from User.java
     */
    public void login() {
        User temp = new User(null, null, null, null, null, null, null); // Added the arguments for the constructor by Aashish jayapuram
        boolean b = users.checkUsers(temp);
        if (b == true) {
            this.currentUser = temp;
        } else {
            System.out.println("Login failed. Please try again");
        }
    }

    public boolean login(String username, String password){
        for(User user : Users.getUsers()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                this.currentUser = user;
                isLoggedIn = true;
                return true;
            }
        }
        return false;
        
    }

    /**
     * calls register method from User.java
     */
    public void registerUser(String username, String password, String firstname, String lastname, String email) {
        UUID userId = UUID.randomUUID(); //UUID
        HashMap<Language, Double> emptyProgress = new HashMap<>();
        User newUser = new User(username, password, firstname, lastname, emptyProgress, email, userId);
        users.addUser(newUser);
        DataWriter.saveUsers();
        System.out.println("Success! You have created an account.");
    }

    /**
     * signOut method that can be used to let the user sign out when they are done using the app. It calls the signOut method from the User.java file.
     */
    public void signOut() {
        DataWriter.savePhrase();
        DataWriter.saveUsers();
        DataWriter.saveWords();
        currentUser = null;
    }

    public boolean doesAccountExist(String username){
        for(User user: Users.getUsers()){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }


    /**
     * calls viewAccount method from User.java
     */
    public void viewAccount() {
        if (currentUser != null) {
            currentUser.viewAccount();
        } else {
            System.out.println("Error retrieving data");
        }
    }

    /**
     * Calls printProgress method from User.java
     */
    public void getCourses() {
        currentUser.printProgress();
    }

    /**
     * Calls selectCourse method from course.java class. This will begin the process of starting an exercise.
     * 
     * @param language
     * @param progress
     */
    public void getExercise() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        Exercise newExercise = course.getExercise(currentUser);
        Double progress = newExercise.startExercise();
        updateProgress(progress);
        saveProgress();
    }

    public void getCourse(Language language){
        this.currentLanguage = language;
        course = new Course(currentLanguage, currentUser.getProgress().get(currentLanguage));
        phrases = Phrases.getPhrases(Math.floor(currentUser.getProgress().get(currentLanguage)));
    }

    /**
     * Add a language for the user.
     * @param language
     */
    public void addLanguage(Language language) {
        currentUser.addLanguage(language);
        updateUser(currentUser);
    }

    /**
     * WIP method that will be used to save the user's info
     * @param language
     */
    public void saveProgress() {
        users.updateUser(currentUser);
    }

    //New Method - Alex
    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public HashMap<String,String> getReviewPhrases(){
        phrases = Phrases.getPhrases(Math.floor(currentUser.getProgress().get(currentLanguage)));
        HashMap<String, String> reviewPhrases = new HashMap<>();
        Random random = new Random();

        if (phrases.isEmpty()) {
            System.out.println("No Phrases available for review.");
            return reviewPhrases;
        }
        for(int i=0;i<phrases.size();i++){
            Phrase reviewPhrase = phrases.get(i);
            reviewPhrases.putIfAbsent(reviewPhrase.getPhrase(), reviewPhrase.getTranslatedPhrase(currentLanguage));
        }
        return reviewPhrases;
    }

    public void updateProgress(Double progress){
        //Users.getInstance().updateUser(currentUser);
        this.currentUser.getProgress().put(currentLanguage, progress);
    }

    public void updateUser(User user){
        users.updateUser(user);
    }
}
