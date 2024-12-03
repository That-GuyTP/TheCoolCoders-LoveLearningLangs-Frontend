package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.application.App;
import com.model.Language;
import com.model.LikeLearningLangs;
import com.model.User;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


public class AddLanguageController implements Initializable {

    private LikeLearningLangs lll;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lll = LikeLearningLangs.getInstance();
        if (!lll.isLoggedIn) {
            try {
                switchToLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        currentUser = lll.getCurrentUser();
    }
   
    private void displayAddableLanguages(){
        for(Language language : Language.values()){
            if(currentUser.getProgress().containsKey(language)){
                continue;
            }
            HBox hBox = new HBox();
            Image image = new Image(getClass().getResourceAsStream("/images/" + language.label + ".jpg"));
            ImageView languageImage = new ImageView(image);
            languageImage.setFitHeight(50);
            languageImage.setFitWidth(70);
            languageImage.setPreserveRatio(true);
            hBox.getChildren().add(languageImage);

            Button languageButton = new Button(language.toString());
            languageButton.setFont(new Font(18));
            languageButton.setPrefHeight(50);
            hBox.getChildren().add(languageButton);
            languageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    lll.addLanguage(language);
                    try {
                        switchToUserhome();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }

    @FXML
    public void switchToLogin() throws IOException{
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

