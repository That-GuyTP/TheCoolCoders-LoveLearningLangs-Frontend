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
    private double progress = 0;
    private int current = 0;
    private int total = 10;

    public void TrueOrFalse(){
        
    }
     public void setQuestion(String question, List<String> options, int correctIndex,double progress) {
        trueOrFalseQ.setText(question);
        correctAnswer = correctIndex;
        current++;

        trueButton.setText("True");
        falseButton.setText("False");

        setProgress(progress);
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

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public double getProgress() {
        return progress;
    }

    public int getCurrent() {
        return current;
    }

    public int getTotal() {
        return total;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }


    
}
