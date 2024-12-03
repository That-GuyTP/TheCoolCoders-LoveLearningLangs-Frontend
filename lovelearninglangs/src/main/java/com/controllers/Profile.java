package com.controllers;

import java.io.IOException;

import com.application.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Profile {

    public Register rInfo = new Register();

    @FXML
    private Label userFirstName;

    @FXML
    private Label userUsername;

    @FXML
    private void signOut(ActionEvent event) throws IOException {
        App.setRoot("startup");
    }

    @FXML
    private void updateProfile(MouseEvent event) throws IOException {

    }

    @FXML
    private void showName(MouseEvent event) throws IOException {
        // Change label text to user's name but not replacing it entirely
        // userFirstName.setText(rInfo.getName()); // Supposed method for getting the name that was used to login (from the Register or Login class)
        // System.out.println(rInfo.getName());
        // Supposed to be a "hover over and reveal" type of label
        // Problem 1: Name is null, but profile page is viewable
    }

    @FXML
    private void showUsername(MouseEvent event) throws IOException {

    }

    @FXML
    private void returnToHome(MouseEvent event) throws IOException {
        App.setRoot("homepage");
    }

}
