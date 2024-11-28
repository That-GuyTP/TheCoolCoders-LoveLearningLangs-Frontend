package com.controllers;

import java.io.IOException;
import com.application.App;
import com.model.LikeLearningLangs;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class Login {
    LikeLearningLangs langs = new LikeLearningLangs();
    
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private void login() throws IOException {
        System.out.println("You've clicked the submit button");
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(checkLogin(username, password)){
            System.out.println("Inputs accepted. Logging you in");
            App.setRoot("homepage");
        }
        else {
            showError("Invalid Username or Password");
        }
    }

    private boolean checkLogin(String username, String password){
        langs = LikeLearningLangs.getInstance();
        return langs.login(username, password);
    }

    private void showError(String errorMessage){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("startup");
    }
}