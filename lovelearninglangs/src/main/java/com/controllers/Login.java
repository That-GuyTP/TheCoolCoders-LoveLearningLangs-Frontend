package com.controllers;

import java.io.IOException;
import com.application.App;
import com.model.LikeLearningLangs;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Login {

    /* When you click the LoginSubmit button it will:
     1. Print to the console, "This is the submit button! Taking you back to homepage page!"
     2. Switch back to the homepage page.

     Alex, I believe this is where you will input the logic for checking a user's information is correct and confirming them logging in so that the app switches to the homepage.
     */
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;
    LikeLearningLangs langs = new LikeLearningLangs();

    @FXML
    private void switchToHomePage() throws IOException {
        login();
    }

    private void login() throws IOException {
        System.out.println("You've clicked the submit button");
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        //DEBUG
        System.out.println("usernameField: " + usernameField);
        System.out.println("passwordField: " + passwordField);
        System.out.println("username: " + username);
        System.out.println("password: " + password);

        if(checkLogin(username, password)){
            System.out.println("Inputs accepted. Logging you in");
            App.setRoot("homepage");
        }
        else {
            showError("Invalid Username or Password");
        }
    }

    private boolean checkLogin(String username, String password){
        return "tap".equals(username) && "tp3".equals(password);
        //return langs.login(username, password);
    }
    

    private void showError(String errorMessage){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @FXML
    private void exitApp() throws IOException {
        App.close();
    }
}