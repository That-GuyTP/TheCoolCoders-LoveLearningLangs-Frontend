package com.controllers;
import java.io.IOException;
import java.util.HashMap;
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
    HashMap<String, String> easyWords = new HashMap<>();
    HashMap<String, String> mediumWords = new HashMap<>();
    HashMap<String, String> hardWords = new HashMap<>();
    
    public TrueOrFalse() {
        

        easyWords.put("perro", "dog");
        easyWords.put("gato", "cat");
        easyWords.put("correr", "run");
        easyWords.put("bueno", "good");
        easyWords.put("mal", "bad");

        mediumWords.put("rapido", "fast");
        mediumWords.put("nombre", "name");
        mediumWords.put("que", "what");
        mediumWords.put("donde", "where");
        mediumWords.put("como", "how");

        hardWords.put("filosofia", "philosophy");
        hardWords.put("constitucion", "constitution");
        hardWords.put("sostenibilidad", "sustainability");
        hardWords.put("revolucion", "revolution");
        hardWords.put("desafio", "challenge");
    }
<<<<<<< HEAD

       
   
     public void setQuestion(List<String> options, int correctIndex,double progress) {
        String wordVariable = generateRandomWord();
        String wordVariable2 = generateRandomWord2();
      
        
        trueOrFalseQ.setText("Does " + wordVariable + " mean " + wordVariable2 + "?");
=======
     public void setQuestion(String question, List<String> options, int correctIndex) {
        trueOrFalseQ.setText(question);
        correctAnswer = correctIndex;
        current++;
>>>>>>> 56cc8d7d6e68bfe2e58da99c604238f7336e00a8

        trueButton.setText("True");
        falseButton.setText("False");

<<<<<<< HEAD
        setProgress(progress);

=======
>>>>>>> 56cc8d7d6e68bfe2e58da99c604238f7336e00a8
        trueButton.setOnAction(event -> checkAnswer(0));
        falseButton.setOnAction(event -> checkAnswer(1));
    }  
    
    public String generateRandomWord(){
       
        String[] keys = easyWords.keySet().toArray(new String[0]);
        String randomKey = keys[(int) (Math.random() * keys.length)];
        return randomKey;
        
    };

    public String generateRandomWord2(){
        String[] values = easyWords.values().toArray(new String[0]);
        String randomValue = values[(int) (Math.random() * values.length)];
        return randomValue;
    };

    

    public void setProgressLabel() {
        progressLabel.setText("Question " + current + " of " + total);
        setScoreLabel();
    }

    public void setScoreLabel() {
        scoreLabel.setText("Score: " + progress);
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
