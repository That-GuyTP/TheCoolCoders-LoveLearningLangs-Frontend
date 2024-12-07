package com.controllers;
import java.io.IOException;
import java.util.List;

import com.application.App;
import com.controllers.ExerciseController;
import com.model.FillInTheBlank;
import com.model.Language;
import com.model.Question;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MultipleChoice {

    @FXML
    private Label questionLabel;

    @FXML
    private Button optionA;

    @FXML
    private Button optionB;

    @FXML
    private Button optionC;

    @FXML
    private Button optionD;

    @FXML
    private Label progressLabel;

    @FXML Label scoreLabel;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private ExerciseController exerciseController;

    public void MultipleChoice(){
        exerciseController = ExerciseController.getInstance();
    }

    public void setQuestion(String question, List<String> options, int correctIndex, double progress, Language language) {
        questionLabel.setText(question);
        correctAnswer = correctIndex;
        current++;

        optionA.setText("A. " + options.get(0));
        optionB.setText("B. " + options.get(1));
        optionC.setText("C. " + options.get(2));
        optionD.setText("D. " + options.get(3));

        optionA.setOnAction(event -> checkAnswer(0));
        optionB.setOnAction(event -> checkAnswer(1));
        optionC.setOnAction(event -> checkAnswer(2));
        optionD.setOnAction(event -> checkAnswer(3));
    }

    private void checkAnswer(int selectedIndex) {
        if (selectedIndex == correctAnswer) {
            System.out.println("Correct!");
            progress++;
        } else {
            System.out.println("Incorrect!");
        }
        setProgress(current,total);
    }

    private void setProgress(int current, int total) {
        progressLabel.setText("Question " + current + " of " + total);
        scoreLabel.setText("Score: " + progress + "/" + current);
        
    }

    private void back() throws IOException {
        App.setRoot("startup");
    }

    public void setProgress(int progress) {
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

    public int getProgress() {
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
