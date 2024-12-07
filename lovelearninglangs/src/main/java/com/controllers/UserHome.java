<<<<<<< HEAD
package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.application.App;
import com.model.Language;
import com.model.LikeLearningLangs;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UserHome implements Initializable {

    private LikeLearningLangs lll;
    private User currentUser;

    @FXML
    private Label welcomeLabel;
    @FXML
    private VBox userLanguageList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lll = LikeLearningLangs.getInstance();
        if (!lll.isLoggedIn) {
            try {
                switchToStartup();
            } catch (IOException e) {
            }
        }
        currentUser = lll.getCurrentUser();
        welcomeLabel.setText("Lets get Started, " + currentUser.getFirstName());
        System.out.println(currentUser.viewAccount());
        displayUserItems();
    }

    private void displayUserItems() {
        HashMap<Language, Double> userProgress = currentUser.getProgress();

        userLanguageList.getChildren().clear();
        for (Language language : userProgress.keySet()) {
            System.out.println(language.label);
            HBox hBox = new HBox();
            // Image image = new Image(getClass().getResourceAsStream("/images/language_flags/" + language.label.toLowerCase() + ".png"));
            // ImageView languageImage = new ImageView(image);
            // languageImage.setFitHeight(50);
            // languageImage.setFitWidth(70);
            // languageImage.setPreserveRatio(true);
            // hBox.getChildren().add(languageImage);

            userLanguageList.getChildren().add(hBox);

            Button languageButton = new Button(language.toString());
            languageButton.setFont(new Font(18));
            languageButton.setPrefHeight(50);
            hBox.getChildren().add(languageButton);

            languageButton.setOnAction(event -> {
                try {
                    switchToCourse(language.toString().toUpperCase());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        Button addLanguageButton = new Button("Add Language");
        addLanguageButton.setFont(new Font(18));
        addLanguageButton.setPrefHeight(50);

        userLanguageList.getChildren().add(addLanguageButton);
        addLanguageButton.setOnAction(event -> {
            try {
                // System.out.println("Clicked Add Language button"); debug
                switchToAddLanguage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        HBox addLanguageHBox = new HBox();
        addLanguageHBox.setSpacing(10);
        addLanguageHBox.getChildren().add(addLanguageButton);

        userLanguageList.getChildren().add(addLanguageHBox);
    }

    @FXML
    private void switchToCourse(String langChoice) throws IOException {
        CourseController cc = CourseController.getInstance();
        cc.selectLangauge(langChoice);
        // System.out.println("Opening course. Language chosen is " + langChoice); dcebug
        App.setRoot("course");
    }

    @FXML
    private void switchToAddLanguage() throws IOException {
        App.setRoot("addlanguage");
    }

    @FXML
    @SuppressWarnings("unused")
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void switchToStartup() throws IOException {
        App.setRoot("startup");
    }
}
=======
package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.application.App;
import com.model.Language;
import com.model.LikeLearningLangs;
import com.model.User;
import com.controllers.CourseController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UserHome implements Initializable {

    private LikeLearningLangs lll;
    private User currentUser;

    @FXML
    private Label welcomeLabel;
    @FXML
    private VBox userLanguageList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lll = LikeLearningLangs.getInstance();
        if (!lll.isLoggedIn) {
            try {
                switchToStartup();
            } catch (IOException e) {
            }
        }
        currentUser = lll.getCurrentUser();
        welcomeLabel.setText("Lets get Started, " + currentUser.getFirstName());
        displayUserItems();
    }

    private void displayUserItems() {
        HashMap<Language, Double> userProgress = currentUser.getProgress();

        userLanguageList.getChildren().clear();
        for (Language language : userProgress.keySet()) {
            HBox hBox = new HBox();
            // Image image = new Image(getClass().getResourceAsStream("/images/language_flags/" + language.label.toLowerCase() + ".png"));
            // ImageView languageImage = new ImageView(image);
            // languageImage.setFitHeight(50);
            // languageImage.setFitWidth(70);
            // languageImage.setPreserveRatio(true);
            // hBox.getChildren().add(languageImage);

            userLanguageList.getChildren().add(hBox);

            Button languageButton = new Button(language.toString());
            languageButton.setFont(new Font(18));
            languageButton.setPrefHeight(50);
            hBox.getChildren().add(languageButton);

            languageButton.setOnAction(event -> {
                try {
                    switchToCourse(language.toString().toUpperCase());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        Button addLanguageButton = new Button("Add Language");
        addLanguageButton.setFont(new Font(18));
        addLanguageButton.setPrefHeight(50);

        userLanguageList.getChildren().add(addLanguageButton);
        addLanguageButton.setOnAction(event -> {
            try {
                // System.out.println("Clicked Add Language button"); debug
                switchToAddLanguage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        HBox addLanguageHBox = new HBox();
        addLanguageHBox.setSpacing(10);
        addLanguageHBox.getChildren().add(addLanguageButton);

        userLanguageList.getChildren().add(addLanguageHBox);
    }

    @FXML
    private void switchToCourse(String langChoice) throws IOException {
        CourseController cc = CourseController.getInstance();
        cc.selectLangauge(langChoice);
        // System.out.println("Opening course. Language chosen is " + langChoice); dcebug
        App.setRoot("course");
    }

    @FXML
    private void switchToAddLanguage() throws IOException {
        App.setRoot("addlanguage");
    }

    @FXML
    @SuppressWarnings("unused")
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void switchToStartup() throws IOException {
        App.setRoot("startup");
    }
}
>>>>>>> d0f7f247f977b3c89cefe077c43371fedd99f89c
