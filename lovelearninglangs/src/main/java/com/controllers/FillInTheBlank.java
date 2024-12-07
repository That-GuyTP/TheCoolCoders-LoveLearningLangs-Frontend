package com.controllers;
import java.io.IOException;
import java.util.List;

import com.application.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FillInTheBlank {
    
    @FXML
    private Label fillInTheBlankQ;

    @FXML
    private TextField fillInField;

    @FXML
    private Button submitButton;

    @FXML
    private Label progressLabel;

    @FXML
    private Label scoreLabel;

    private String correctAnswer;
    private double progress = 0;
    private int current = 0;
    private int total = 10;

    public void FillInTheBlank(){
        
    }
     public void setQuestion(String question, String correctAnswer,double progress) {
        fillInTheBlankQ.setText(question);
        this.correctAnswer = correctAnswer;
        current++;
        setProgress(progress);
        submitButton.setOnAction(event -> checkAnswer());
    }

    private void checkAnswer() {
        if (fillInField.getText().equals(correctAnswer)) {
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
        scoreLabel.setText("Score: " + progress + "/" + total);
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

    public void setCorrectAnswer(String correctAnswer) {
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}
