package com.controllers;

import java.io.IOException;

import com.application.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Profile {

    @FXML
    private void back() throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    private Button saveButton;

    @FXML
    private Button signOutButton;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userFirstName;

    @FXML
    private Label userFullNameLabel;

    @FXML
    private TextField userLastName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private TextField userUsername;

    @FXML
    private Label userUsernameLabel;

    @FXML
    private void signOut(ActionEvent event) throws IOException {
        App.setRoot("startup");
    }

    @FXML
    private void updateProfile(MouseEvent event) throws IOException {

    }

    @FXML
    private void returnToHome(MouseEvent event) throws IOException {
        App.setRoot("homepage");
    }

    private void setTextFields() {
        // Would update the text fields like first name, last name, etc.
        // Ex.: userUsername
    }

}
