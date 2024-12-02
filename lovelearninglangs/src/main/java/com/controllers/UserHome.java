package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.application.App;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.model.*;
import java.util.HashMap;

public class UserHome implements Initializable{

    private LikeLearningLangs lll;
    private User currentUser;
    
    @FXML private Label welcomeLabel;
    @FXML private VBox userLanguageList;

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
            userLanguages.add(language);
            HBox hBox = new HBox();
            Image image = new Image(getClass().getResourceAsStream("/images/" + language.label + ".jpg"));
            ImageView languageImage = new ImageView(image);
            languageImage.setFitHeight(50);
            languageImage.setPreserveRatio(true);
            hBox.getChildren().add(languageImage);

            userLanguageList.getChildren().add(hBox);

            Button languageButton = new Button(language.toString());
            hBox.getChildren().add(languageImage);
            languageButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    lll.getCourse(language);
                    try {
                        switchToCourse();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
            });
        }
    }

    @FXML
    private void switchToCourse() throws IOException{
        App.setRoot("course");
    }

    @FXML
    private void switchToAddLanguage() throws IOException{
        App.setRoot("addlanguage");
    }
    
    @FXML
    private void switchToProfile() throws IOException{
        App.setRoot("profile");
    }
}
