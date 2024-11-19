package com.controllers;

import java.io.IOException;

import com.application.App;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import com.model.LikeLearningLangs;


public class Register {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;

    @FXML
    private void switchToHomePage() throws IOException {
        System.out.println("This is the submit button! Creating a new account");
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        LikeLearningLangs langs = LikeLearningLangs.getInstance();
        if(langs.doesAccountExist(username)){
            showError("Invalid Username or Password");
            
        }
        else{
            langs.registerUser(username, password, firstName, lastName, email);
            App.setRoot("login");
        }
    }



    private void showError(String errorMessage){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }



}
