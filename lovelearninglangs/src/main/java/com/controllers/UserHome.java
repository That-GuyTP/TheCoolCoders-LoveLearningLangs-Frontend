package com.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.model.Language;
import com.model.LikeLearningLangs;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class UserHome implements Initializable{

    private LikeLearningLangs lll;
    private User currentUser;
    @FXML private Label welcomeLabel;
    @FXML private GridPane userLanguageList;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lll = LikeLearningLangs.getInstance();
        currentUser = lll.getCurrentUser();

        welcomeLabel.setText("Lets get Started, " + currentUser.getFirstName());
        displayUserItems();
    }

    private void displayUserItems(){
        HashMap<Language, Double> userProgress = currentUser.getProgress();
        ArrayList<Language> userLanguages = new ArrayList<>();
        for(Language language: userProgress.keySet()){
            int i = 0;
            userLanguages.add(language);
            HBox hBox = new HBox();
            Image image = new Image(getClass().getResourceAsStream("/images/" + language.label + ".jpg"));
            ImageView languageImage = new ImageView(image);
            languageImage.setFitHeight(50);
            languageImage.setPreserveRatio(true);
            hBox.getChildren().add(languageImage);
            Button languageButton = new Button();
            languageButton.setText(language.label);
            hBox.getChildren().add(languageButton);

            userLanguageList.add(hBox, i, 0);

            /*hBox.getChildren().add(image);*/

            i++;
        }



    }
    
}
