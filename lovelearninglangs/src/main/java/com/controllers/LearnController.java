package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import com.application.App;
import com.model.Course;
import com.narration.*;

public class LearnController {
    
    @FXML
    private void playSound() throws IOException {
        System.out.println("You clicked the playASound button!");
        Narrator.playSound("Hola World! Vosotros los Cool Coders! Hola Grader! Como estas?");
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}
