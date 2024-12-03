package com.controllers;
import java.io.IOException;
import java.util.List;

import com.application.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TrueOrFalse {

    @FXML
    private Label trueOrFalseQ;

    @FXML
    private Button trueButton;

    @FXML
    private Button falseButton;

    @FXML
    private Label progressLabel;

    @FXML
    private Label scoreLabel;

    private int correctAnswer;
    private int progress = 0;
    private int current = 0;
    private int total = 10;

    public void TrueOrFalse(){
        
    }
     public void setQuestion(String question, List<String> options, int correctIndex) {
        trueOrFalseQ.setText(question);
        correctAnswer = correctIndex;
        current++;

        trueButton.setText("True");
        falseButton.setText("False");

        trueButton.setOnAction(event -> checkAnswer(0));
        falseButton.setOnAction(event -> checkAnswer(1));
    }  
    
    private void checkAnswer(int selectedIndex) {
        if (selectedIndex == correctAnswer) {
            System.out.println("Correct!");
            progress++;
        } else {
            System.out.println("Incorrect!");
        }
        setProgressLabel();
    }

    public void setProgressLabel() {
        progressLabel.setText("Question " + current + " of " + total);
        setScoreLabel();
    }

    public void setScoreLabel() {
        scoreLabel.setText("Score: " + progress);
    }

    

    
}
