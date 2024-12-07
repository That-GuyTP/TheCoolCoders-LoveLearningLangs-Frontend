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
        if (currentQuestion == null) { // DEBUG
            System.out.println("Error: There is no question to display!");
            return;
        }
        if (submitButton == null) { // Debug
            System.out.println("Error: submitButton is not initialized!");
            return;
        }
        if (answerField == null) { // Debug
            System.out.println("Error: answerField is not initialized!"); 
            return;
        }
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
        submitButton.setOnAction(event -> {
            checkAnswer();
            progressLabel.setText("Question " + (currentQuestionIndex + 1) + "/" + totalQuestions);
            scoreLabel.setText("Score: " + correctAnswers);
        });
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        if (userAnswer.isEmpty()) { // Debug
            System.out.println("Error: No answer provided!");
            return;
        }
        boolean isCorrect = currentQuestion.getAnswer().equalsIgnoreCase(userAnswer);
        
        if (isCorrect) {
            correctAnswers++;
            System.out.println("Correct!");
            ec.incrementScore();
            //ec.setExerciseScore(Integer.parseInt(userAnswer));
        } else {
            System.out.println("Incorrect! The correct answer was: " + currentQuestion.getAnswer());
        }
        progressLabel.setText("Question " + (currentQuestionIndex + 1) + "/" + totalQuestions);
        scoreLabel.setText("Score: " + correctAnswers);
        
        // Notify the ExerciseController and move to the next question
        try {
            System.out.println("Loading ec.loadNextQuestion: ");
            ec.loadNextQuestion();
        } catch (IOException e) {
            System.out.println("Error! couldn't process ec.loadNextQuestion: ");
            e.printStackTrace();
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
