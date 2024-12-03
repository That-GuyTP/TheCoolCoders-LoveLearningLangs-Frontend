package com.controllers;

import com.model.Exercise;
import com.model.Language;
import com.model.Question;
import com.model.FillInTheBlank;
import com.model.Matching;
import com.model.MultipleChoice;
import com.model.trueOrFalse;

import java.util.ArrayList;
import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;

public class ExerciseController {

    //Instance variables
    private Exercise exercise;
    private ArrayList<Question> questions;
    private Double currentProgress;
    private com.model.FillInTheBlank fitb;
    private com.model.Matching mtch;
    private com.model.MultipleChoice mc;
    private com.model.trueOrFalse tof;

    //Constructor
    public ExerciseController(Language language, Double progress) {
        exercise = new Exercise(language, progress);
        currentProgress = progress;
    }

    public ArrayList<Question> generateQuestions() {
        questions = exercise.generateQuestions();
        return questions;
    }

    public double startExercise() {
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if(question instanceof trueOrFalse) {
                tof.setProgressLevel(currentProgress);
                tof.setQuestion(question.getQuestion());
                tof.setAnswer(question.getAnswer());
                App.launch("trueorfalse");
            } else if (question instanceof FillInTheBlank) {
                fitb = (FillInTheBlank) question;
                App.launch("fillintheblank");
            } else if (question instanceof MultipleChoice) {
                mc = (MultipleChoice) question;
                App.launch("multiplechoice");
            } else if (question instanceof Matching) {
                mtch = (Matching) question;
                App.launch("matching");
            }
        }
        return exercise.calcAccuracy();
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}