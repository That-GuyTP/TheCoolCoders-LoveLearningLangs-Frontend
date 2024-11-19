package com.controllers;

import java.io.IOException;
import com.application.App;
import com.model.LikeLearningLangs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Homepage {
    
    @FXML
    private void switchToProfile() throws IOException {
        System.out.println("Pretend this switches to the profile page");
        App.setRoot(null/* INSERT PROFILE FXML NAME HERE */);
    }

    @FXML
    private void switchToCourse() throws IOException {
        System.out.println("Switching to Course!");
        App.setRoot(null/* INSERT COURSE FXML NAME HERE */);
    }

    @FXML
    void exitApp(ActionEvent event) {
        App.close();
    }

}
