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
import javafx.scene.control.TextField;

public class ProfileController implements Initializable{

    @FXML
    private Label fullNameLabel, usernameLabel;

    @FXML
    private TextField userFirstName, userLastName, userUsername, userEmail, userPassword;

    private User currentUser;
    private LikeLearningLangs lll;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lll = LikeLearningLangs.getInstance();
        if (!lll.isLoggedIn) {
            try {
                switchToStartup();
            } catch (IOException e) {
            }
        }
        currentUser = lll.getCurrentUser();
        showUserInfo();
    }

    @FXML
    private void switchToStartup() throws IOException {
        App.setRoot("startup");
    }



    private void showUserInfo(){
        String fullName = currentUser.getFirstName() + " " + currentUser.getLastName();
        fullNameLabel.setText(fullName);
        usernameLabel.setText(currentUser.getUsername());

        userFirstName.setText(currentUser.getFirstName());
        userLastName.setText(currentUser.getLastName());
        userEmail.setText(currentUser.getEmail());
        userUsername.setText(currentUser.getUsername());
        userPassword.setText(currentUser.getPassword());
    }

    @FXML
    @SuppressWarnings("unused")
    private void saveToUser(){
        currentUser.setFirstName(userFirstName.getText());
        currentUser.setLastName(userLastName.getText());
        currentUser.setUsername(userUsername.getText());
        currentUser.setEmail(userEmail.getText());
        currentUser.setPassword(userPassword.getText());
        showUserInfo();

    }

    @FXML
    private void signOut() throws IOException{
        App.setRoot("login");
        lll.setCurrentUser(null);
    }
    

}
