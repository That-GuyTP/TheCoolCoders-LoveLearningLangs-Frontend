package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import com.model.Course;

public class CourseController {

    @FXML
    private TextArea langaugeChoice;

    public Course userCourse = new Course();

    @FXML 
    private void selectLangauge() throws IOException {
        String language = langaugeChoice.getText();
        
    }
    
    @FXML 
    private void switchToExercise() throws IOException {
        System.out.println("This is the exercise button");
        App.setRoot(null /* INSERT EXERCISE PATH HERE */);
    }

    @FXML
    private void exitApp() throws IOException {
        App.close();
    }
}
