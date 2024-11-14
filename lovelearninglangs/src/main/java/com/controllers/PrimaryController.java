package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;
import com.narration.*;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("You clicked the primary button!");
        Narrator.playSound("Hallo Thomas! Wie gehts?");
        App.setRoot("secondary");
    }
}
