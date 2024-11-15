package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;

public class LoginSubmit {

    /* When you click the LoginSubmit button it will:
     1. Print to the console, "This is the submit button! Taking you back to the startup page!"
     2. Switch back to the homepage page.

     Alex, I believe this is where you will input the logic for checking a user's information is correct and confirming them logging in so that the app switches to the homepage.
     */
    @FXML
    private void switchToHomePage() throws IOException {
        System.out.println("This is the submit button! Taking you back to the startup page!");
        App.setRoot("homepage");
    }
}