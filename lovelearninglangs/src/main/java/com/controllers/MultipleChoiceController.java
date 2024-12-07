package com.controllers;
import java.io.IOException;
import java.util.List;

import com.application.App;
import com.controllers.ExerciseController;
import com.model.MultipleChoice;
import com.model.Language;
import com.model.Question;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MultipleChoiceController {

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

    private MultipleChoice currentQuestion;
    private int currentQuestionIndex;
    private int correctAnswers;
    private int totalQuestions;
    private ExerciseController ec = new ExerciseController();

    public MultipleChoiceController(){
        ec = ExerciseController.getInstance();
        if (ec == null) { // DEBUG
            System.out.println("Error: ExerciseController instance is null");
        }
    }

    public void setQuestion(MultipleChoice question, int currentIndex, int total) {
        this.currentQuestion = question;
        this.currentQuestionIndex = currentIndex;
        this.totalQuestions = total;
        System.out.println("Loading question " + currentIndex + " of " + totalQuestions);
        displayQuestion();
        
    }

    private void displayQuestion() {
        if (currentQuestion == null) {
            System.out.println("Error: There is no question to display!");
            return;
        }
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
        List<String> options = currentQuestion.getChoices();
        optionA.setText("A. " + options.get(0));
        optionB.setText("B. " + options.get(1));
        optionC.setText("C. " + options.get(2));
        optionD.setText("D. " + options.get(3));

        optionA.setOnAction(event -> checkAnswer(0));
        optionB.setOnAction(event -> checkAnswer(1));
        optionC.setOnAction(event -> checkAnswer(2));
        optionD.setOnAction(event -> checkAnswer(3));

        updateProgressLabel();
    }

    private void checkAnswer(int selectedIndex) {
        System.out.println("You chose index: " + selectedIndex);
        boolean isCorrect = currentQuestion.checkAnswer(selectedIndex);
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
        progressLabel.setText("Question " + (currentQuestionIndex + 1 ) + "/" + totalQuestions);
        scoreLabel.setText("Score: " + correctAnswers);
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}
