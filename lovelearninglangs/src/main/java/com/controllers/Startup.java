package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;
import com.narration.*;

public class Startup {

    /* When you click the login button from the startup.fxml, it will
    1. Print to the terminal "You clicked in the login button"
    2. Wait while it plays through the Narrator
    3. And then switch to login.fxml
    */
    
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void switchToRegister() throws IOException {
        System.out.println("You clicked the register button!");
        App.setRoot("register");
    }

    @FXML
    private void playASound() throws IOException {
        System.out.println("You clicked the login button!");
        Narrator.playSound("Hola World! Vosotros los Cool Coders! Hola Grader! Como estas?");
    }
}
