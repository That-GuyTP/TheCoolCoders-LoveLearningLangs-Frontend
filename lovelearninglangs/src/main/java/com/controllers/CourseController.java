package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import com.model.Language;
import com.model.LikeLearningLangs;

public class CourseController {

    @FXML
    private TextArea langaugeChoice;

    public LikeLearningLangs langs = new LikeLearningLangs();

    @FXML 
    private void selectLangauge() throws IOException {
        String inputText = langaugeChoice.getText().trim();
        System.out.println(inputText); // DEBUG
        Language language = Language.valueOf(inputText.toUpperCase());
        System.out.println(language); // DEBUG
        langs.getCourse(language);
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
