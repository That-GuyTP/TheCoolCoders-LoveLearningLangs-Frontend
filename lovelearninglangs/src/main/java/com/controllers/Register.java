package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;

public class Register {
    
    @FXML
    private void switchToLoginSubmit() throws IOException {
        System.out.println("You clicked the register button!");
        App.setRoot("register");
    }
}
