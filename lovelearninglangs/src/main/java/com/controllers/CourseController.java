package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import com.model.Language;
import com.model.LikeLearningLangs;
import com.controllers.LanguageSelector;

public class CourseController {

    private String langaugeChoice;
    private Language language;

    public LikeLearningLangs langs = LikeLearningLangs.getInstance();
    public void selectLangauge(Language languageInput) {
        Language language = languageInput;

    }

    public void getCourse() throws IOException {
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
