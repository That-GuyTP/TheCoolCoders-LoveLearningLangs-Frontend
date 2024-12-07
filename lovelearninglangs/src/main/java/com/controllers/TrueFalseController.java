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
    private ExerciseController exerciseController;

    public void TrueFalseController() {
        exerciseController = ExerciseController.getInstance();
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

        trueButton.setOnAction(event -> checkAnswer(true));
        falseButton.setOnAction(event -> checkAnswer(false));

        updateProgressLabel();
    }

    private void checkAnswer(boolean selectedAnswer) {
        boolean isCorrect = currentQuestion.checkAnswer(selectedAnswer ? 1 : 0);
        if (isCorrect) {
            correctAnswers++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect! The correct answer was: " + currentQuestion.getAnswer());
        }
        if (currentQuestionIndex + 1 < totalQuestions) {
            try {
                App.setRoot("exercise"); 
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
        progressLabel.setText("Question " + (currentQuestionIndex + 1) + "/" + totalQuestions);
        scoreLabel.setText("Score: " + correctAnswers);
    }

    private void back() throws IOException {
        App.setRoot("course");
    }
}
