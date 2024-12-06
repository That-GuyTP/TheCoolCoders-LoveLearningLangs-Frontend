package com.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.application.App;
import com.model.Language;
import com.model.LikeLearningLangs;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddLanguageController implements Initializable {

    private LikeLearningLangs lll;
    private User currentUser;

    @FXML
    private VBox addableLanguages;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lll = LikeLearningLangs.getInstance();
        currentUser = lll.getCurrentUser();
        displayAddableLanguages();
    }

    private void displayAddableLanguages() {
        for (Language language : Language.values()) {
            if (currentUser.getProgress().containsKey(language)) {
                continue;
            }
            HBox hBox = new HBox();
            System.out.println();
            InputStream imageStream = getClass().getResourceAsStream("src\\main\\resources\\com\\application\\images\\language_flags\\english.png" + language.label.toLowerCase() + ".png");
            if (imageStream == null) {
                System.out.println("Image not found for language: " + language.label);
                imageStream = getClass().getResourceAsStream("/images/language_flags/default.png");
            }
            Image image = new Image(imageStream);
            ImageView languageImage = new ImageView(image);
            languageImage.setFitHeight(50);
            languageImage.setFitWidth(70);
            languageImage.setPreserveRatio(true);
            hBox.getChildren().add(languageImage);

            Button languageButton = new Button(language.toString());
            languageButton.setFont(new Font(18));
            languageButton.setPrefHeight(50);
            hBox.getChildren().add(languageButton);
            languageButton.setOnAction(event -> {
                try {
                    lll.addLanguage(language);
                    switchToUserhome();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            addableLanguages.getChildren().add(hBox);
        }
    }

    @FXML
    public void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void switchToUserhome() throws IOException {
        App.setRoot("userhome");
    }
}
