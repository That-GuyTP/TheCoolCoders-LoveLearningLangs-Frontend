package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.application.App;
import com.model.LikeLearningLangs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextField;

public class Login implements Initializable {

    private LikeLearningLangs lll;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lll = LikeLearningLangs.getInstance();
    }

    @FXML
    private void login() throws IOException {
        System.out.println("You've clicked the submit button");
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (checkLogin(username, password)) {
            System.out.println("Inputs accepted. Logging you in");
            App.setRoot("userhome");
        } else {
            showError("Invalid Username or Password");
        }
    }

    private boolean checkLogin(String username, String password) {
        return lll.login(username, password);
    }

    private void showError(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @FXML
    public void handleEnterPressed(KeyEvent event) throws IOException{
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("startup");
    }
}
