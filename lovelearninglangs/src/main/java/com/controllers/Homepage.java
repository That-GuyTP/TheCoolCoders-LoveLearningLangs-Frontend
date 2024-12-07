package com.controllers;

import java.io.IOException;

import com.application.App;
import com.model.LikeLearningLangs;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Homepage{

    User currentUser = new User();
    LikeLearningLangs lll = new LikeLearningLangs();
    

    public Homepage() {
        lll = LikeLearningLangs.getInstance();
        currentUser = lll.getCurrentUser();
    }

    
    
    @FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void switchToCourse() throws IOException {
        System.out.println("Switching to Course!");
        App.setRoot("course");
    }

    @FXML
    private void displayUserInfo() throws IOException {
        System.out.println("This is the current user's information: ");
        currentUser.viewAccount();
    }

    @FXML
    void exitApp(ActionEvent event) {
        App.close();
    }

}
