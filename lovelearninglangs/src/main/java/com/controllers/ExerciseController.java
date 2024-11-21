package com.controllers;

import com.model.Exercise;
import com.model.LikeLearningLangs;
import com.model.Course;

import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;

public class ExerciseController {

    //Instance variables
    private Exercise exercise;
    private LikeLearningLangs lll;
    private Course course;

    //Constructor
    public ExerciseController() {
        this.exercise = lll.getExercise();
    }

    public void setLanguage() {

    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}