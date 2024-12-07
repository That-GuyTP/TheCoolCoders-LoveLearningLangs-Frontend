/**
 * @author Alex Ervin
 */
package com.model;


import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class User {
    private static User currentUser;
    private Users users;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private HashMap<Language, Double> progress;
    private String password;
    private UUID id;
    public static Scanner kb = new Scanner(System.in);

    /**
     * Default Constructor that initalizes progress as a hashmap for later use.
     */
    public User() {
        this.progress = new HashMap<>();
    }

    /**
     * Parameter constructor that is used to set all of the values associated with a user to the parameters inputed.
     * 
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param progress
     * @param email
     * @param id
     */
    public User(String username, String password, String firstName, String lastName, HashMap<Language, Double> progress, String email, UUID id){
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.progress = new HashMap<>(); // Added by Aashish Jayapuram
        this.password = password;
        this.id = id;
    }

    /**
     * setCurrentUser method that takes in a user object and sets all the values in user to the parameter's values.
     * @param user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * addLanguage method that will be used to allow a user to add a language to their language list.
     * @param language
     */
    public void addLanguage(Language language){
        progress.putIfAbsent(language, 1.0);
    }

    /**
     * removeLanguage method that will be used to allow a user to remove a language from their language list.
     * @param language
     */
    public void removeLanguage(Language language){
        progress.remove(language);
        users.updateUser(currentUser);
    }

    /**
     * viewAccount method that shows all associated values of the user object currently. Mostly used for debugging.
     */
    public void viewAccount(){
        System.out.println("User name: " + this.getUsername());
        System.out.println("Name: " + this.getFirstName() + "" + this.getLastName());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Courses enrolled:");
        this.getProgress();
    }

    /**
     * setProgress method that is used to set the progress a user has gained from exercises to a new value;
     * @param progressHashMap
     */
    public void setProgress(HashMap<Language, Double> progressHashMap){
        this.progress.putAll(progressHashMap);
    }

    /**
     * getProgress method that returns the HashMap of the user's languages and their doubles.
     * 
     * @return HashMap progress
     */
    public HashMap<Language, Double> getProgress(){
        return this.progress;
    }

    public Double getLangProgress(Language lang) {
        return progress.get(lang);
    }

    /**
     * printProgress method that prints the user's progress to the console/returns it as a string.
     */
    public void printProgress() {
        if (progress.isEmpty()) {
            System.out.println("No courses found");
            return;
        }
        for(Language language : progress.keySet()) {
            Double progressValue = progress.get(language);
            System.out.println(language.name() + " - Progress: " + progressValue);
        }
    }

    //Get/Set Methods
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
            return this.username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName; 
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName; 
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
       return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
     }

    public void setUUID(UUID id) {
        this.id = id;
    }

    public UUID getUUID() {
       return this.id;
    }


     
}
