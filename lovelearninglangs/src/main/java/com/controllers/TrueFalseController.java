package com.controllers;
import java.io.IOException;

import com.application.App;
import com.model.trueOrFalse;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TrueFalseController {

    @FXML
    private Label questionLabel;

    @FXML
    private Button trueButton;

    @FXML
    private Button falseButton;

    @FXML
    private Label progressLabel;

    @FXML
    private Label scoreLabel;

    private trueOrFalse currentQuestion;
    private int currentQuestionIndex;
    private int correctAnswers;
    private int totalQuestions;
    private ExerciseController ec;

    public TrueFalseController() {
        ec = ExerciseController.getInstance();
    }

    public void setQuestion(trueOrFalse question, int currentIndex, int total) {
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

        trueButton.setText("True");
        falseButton.setText("False");

        trueButton.setOnAction(event -> {
            System.out.println("True button clicked"); // Debug
            checkAnswer(true);
        });
        falseButton.setOnAction(event -> {
            System.out.println("False button clicked"); // Debug
            checkAnswer(false);
        });

        updateProgressLabel();
    }

    private void checkAnswer(boolean selectedAnswer) {
        if (currentQuestion == null) {
            System.out.println("Error: No question is set!");
            return;
        }
        System.out.println("Current question is set to: " + currentQuestion.toString());
        System.out.println("This is the selected answer " + selectedAnswer);
        boolean isCorrect = currentQuestion.checkAnswer(selectedAnswer ? 1 : 0);
        if (isCorrect) {
            correctAnswers++;
            System.out.println("Correct!");
            ec.incrementScore();
        } else {
            System.out.println("Incorrect! The correct answer was: " + currentQuestion.getAnswer());
        }
        // Notify the ExerciseController and move to the next question
        try {
            ec.loadNextQuestion();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void updateProgressLabel() {
        progressLabel.setText("Question " + (currentQuestionIndex + 1) + "/" + totalQuestions);
        scoreLabel.setText("Score: " + correctAnswers);
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }
}
