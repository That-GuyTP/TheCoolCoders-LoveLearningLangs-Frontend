package com.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import com.application.App;
import com.controllers.CourseController;

public class LanguageSelector {
    @FXML
    private TextArea languageButton;

    CourseController cc = new CourseController();

    @FXML
    private void selectLanguage() throws IOException {
        String inputText = languageButton.getText().trim();
        cc.selectLangauge(inputText);
        App.setRoot("course");
    }
}
