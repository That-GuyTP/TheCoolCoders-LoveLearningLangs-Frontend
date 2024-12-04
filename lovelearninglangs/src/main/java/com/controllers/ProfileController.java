package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.application.App;
import com.model.LikeLearningLangs;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfileController implements Initializable{

    @FXML
    private Label fullNameLabel, usernameLabel;

    @FXML
    private TextField userFirstName, userLastName, userUsername, userEmail;

    @FXML
    private PasswordField userPassword;

    @FXML
    private 

    private User currentUser;
    private LikeLearningLangs lll;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lll = LikeLearningLangs.getInstance();
        if (!lll.isLoggedIn) {
            try {
                switchToStartup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        currentUser = lll.getCurrentUser();
    }

    @FXML
    private void switchToStartup() throws IOException {
        App.setRoot("startup");
    }

    

}
