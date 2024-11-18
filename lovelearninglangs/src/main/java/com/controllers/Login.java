package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;
import com.narration.*;

public class Login {

    /* When you click the login button from the startup.fxml, it will
    1. Print to the terminal "You clicked in the login button"
    2. Wait while it plays through the Narrator
    3. And then switch to login.fxml
    */
    
    @FXML
    private void switchToLoginSubmit() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void playASound() throws IOException {
        System.out.println("You clicked the login button!");
        Narrator.playSound("Hello World! We are the Cool Coders! Hallo Grader! Wie gehts?");
    }
}
