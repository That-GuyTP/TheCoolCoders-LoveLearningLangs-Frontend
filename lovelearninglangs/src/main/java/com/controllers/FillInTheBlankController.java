package com.controllers;

import java.io.IOException;

import com.application.App;
import com.model.FillInTheBlank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FillInTheBlankController {

    @FXML
    private Label questionLabel;

    @FXML
    private TextField answerField;

    @FXML
    private Button submitButton;

    @FXML
    private Label progressLabel;

    @FXML 
    private Label scoreLabel;

    private FillInTheBlank currentQuestion;
    private int currentQuestionIndex;
    private int correctAnswers;
    private int totalQuestions;
    private ExerciseController ec;

    public FillInTheBlankController() {
        ec = ExerciseController.getInstance();
    }

    public void setQuestion(FillInTheBlank question, int currentIndex, int total) {
        this.currentQuestion = question;
        this.currentQuestionIndex = currentIndex;
        this.totalQuestions = total;

        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestion == null) {
            System.out.println("Error: There is no question to display!");
            return;
        }
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());

        submitButton.setOnAction(event -> checkAnswer());
        updateProgressLabel();
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        boolean isCorrect = currentQuestion.checkAnswer(userAnswer);
        
        if (isCorrect) {
            correctAnswers++;
            System.out.println("Correct!");
            ec.incrementScore();
            ec.setExerciseScore(Integer.parseInt(userAnswer));
        } else {
            System.out.println("Incorrect! The correct answer was: " + currentQuestion.getAnswer());
        }

        // Notify the ExerciseController and move to the next question
        if (currentQuestionIndex + 1 < totalQuestions) {
            try {
                App.setRoot("exercise"); // Move to the next question
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ExerciseController.getInstance().exerciseComplete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProgressLabel() {
        progressLabel.setText("Question " + (currentQuestionIndex + 1 ) + "/" + totalQuestions);
        scoreLabel.setText("Score: " + correctAnswers);
    }

    // added by Aashish Jayapuram
    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }
}
