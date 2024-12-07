package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;
import com.model.Language;
import com.model.LikeLearningLangs;
import com.model.Course;
import com.model.User;

public class CourseController {

    //Instance Variables
    private Language language;
    public LikeLearningLangs lll = LikeLearningLangs.getInstance();
    public Course c = new Course();
    public User currentuser = new User();

    //Default Constructor
    public CourseController() {
        c = new Course(lll.getCurrentUser());
    }

    //Select a Language
    public void selectLangauge(String languageInput) {
        language = Language.valueOf(languageInput.toUpperCase());
    }

    //Get Course Value
    public void getCourse() throws IOException {
        lll.getCourse(language);
    }

    //Get Selected Language
    public Language getLanguage() {
        return language;
    }

    //Get Current User
    public User getUser() {
        return lll.getCurrentUser();
    }

    //Get Language Progress
    public Double getProgress() {
        currentuser = lll.getCurrentUser();
        Double prog = currentuser.getLangProgress(language);
        if (prog == null || prog < 1.0) {
            prog = 1.0;
        }
        return prog;
    }
    
    //Switch to Home
    @FXML
    private void switchToUserHome() throws IOException {
        System.out.println("You clicked the icon. Switching to user home");
        App.setRoot("userhome");
    }

    //Switch to Learn
    @FXML
    private void switchToLearn() throws IOException {
        System.out.println("You've clicked the learn button. Switching to learn page.");
        App.setRoot("learn");
    }

    //Switch to Level 1
    @FXML 
    private void switchToLevel1() throws IOException {
        System.out.println("This is the level 1 button");
        App.setRoot("exercise"); 
        App.launch("exercise");
    }

    //Switch to Level 2
    @FXML
    private void switchToLevel2() throws IOException {
        System.out.println("This is the level 2 button");
        if (lll.getCurrentUser().getLangProgress(language) < 2.0) {
            System.out.println("You're not ready for this course yet. Try exercising some more.");
        } else {

        }
    }

    //Switch to Level 3
    @FXML
    private void switchToLevel3() throws IOException {
        System.out.println("This is the level 3 button");
        if (lll.getCurrentUser().getLangProgress(language) < 3.0) {
            System.out.println("You're not ready for this course yet. Try exercising some more.");
        } else {
            App.setRoot(null /* INSERT EXERCISE PATH HERE */);      
        }
    }

    //Exit App
    @FXML
    private void exitApp() throws IOException {
        App.close();
    }
}
