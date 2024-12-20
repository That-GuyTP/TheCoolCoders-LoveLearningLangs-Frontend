package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.application.App;
import com.model.Course;
import com.model.Language;
import com.model.LikeLearningLangs;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class CourseController implements Initializable {

    //Instance Variables
    private Language language;
    public LikeLearningLangs lll = LikeLearningLangs.getInstance();
    private static CourseController instance;
    public Course c = new Course();
    public User currentuser = new User();
    @FXML
    private Label userProgressLabel;

    //Default Constructor
    public CourseController() {
        c = new Course(lll.getCurrentUser());
    }

    public static CourseController getInstance() {
        if (instance == null) {
            instance = new CourseController();
        }
        return instance;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        c = new Course(lll.getCurrentUser());
        CourseController cc = CourseController.getInstance();
        userProgressLabel.setText(cc.getLanguage() + ": " + lll.getCurrentUser().getLangProgress(cc.getLanguage()));

    }

    //Select a Language
    public void selectLangauge(String languageInput) {
        try {
            language = Language.valueOf(languageInput.toUpperCase());
            // System.out.println("Language successfully set to: " + language); debug
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid language input.");
        }
    }

    //Get Course Value
    public void getCourse() throws IOException {
        lll.getCourse(language);
    }

    //Get Selected Language
    public Language getLanguage() {
        if (language == null) {
            System.out.println("Error: No language selected.");
        }
        return language;
    }

    //Get Current User
    public User getUser() {
        return lll.getCurrentUser();
    }

    //Get Language Progress
    public Double getProgress() {
        CourseController cc = CourseController.getInstance();
        Double prog = lll.getCurrentUser().getLangProgress(cc.getLanguage());
        if (prog == null || prog < 1.0) {
            prog = 1.0;
        }
        return prog;
    }

    //Switch to Home
    @FXML
    private void switchToUserHome() throws IOException {
        //System.out.println("You clicked the icon. Switching to user home"); debug
        App.setRoot("userhome");
    }

    //Switch to Learn
    @FXML
    private void switchToLearn() throws IOException {
        CourseController cc = CourseController.getInstance();
        // System.out.println("You've clicked the learn button. Switching to learn page."); debug
        LearnController lc = new LearnController();
        lc.setLanguage(cc.getLanguage());
        // System.out.println("Set lc's language to " + cc.getLanguage()); debug
        App.setRoot("learn");
    }

    //Switch to Level 1
    @FXML
    private void switchToLevel1() throws IOException {
        ExerciseController ec = ExerciseController.getInstance();
        ec.startExercise(1);
    }

    //Switch to Level 2
    @FXML
    private void switchToLevel2() throws IOException {
        if (lll.getCurrentUser().getLangProgress(language) <= 2.0) {
            System.out.println("You're not ready for this course yet. Try exercising some more.");
        } else {
            ExerciseController ec = ExerciseController.getInstance();
            ec.startExercise(2);
        }
    }

    //Switch to Level 3
    @FXML
    private void switchToLevel3() throws IOException {
        if (getProgress() < 3.0) {
            System.out.println("You're not ready for this course yet. Try exercising some more.");
        } else {
            ExerciseController ec = ExerciseController.getInstance();
            ec.startExercise(3);
        }
    }

    //Exit App
    @FXML
    private void exitApp() throws IOException {
        App.close();
    }
}
