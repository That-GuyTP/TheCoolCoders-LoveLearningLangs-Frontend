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
    private com.controllers.FillInTheBlank fitb;
    //private com.controllers.Matching mtch;
    private com.controllers.MultipleChoice mc;
    private com.controllers.TrueOrFalse tof;

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
                tof.setQuestion(question.getQuestion(), null, i);
                tof.setCorrectAnswer(question.getAnswer());
                tof.setProgress(currentProgress.intValue());
                App.setRoot("trueorfalse");
            } else if (question instanceof FillInTheBlank) {
                fitb.setQuestion(question.getQuestion());
                fitb.setCorrectAnswer(question.getAnswer());
                fitb.setProgress(currentProgress.intValue());
                App.setRoot("fillintheblank");
            } else if (question instanceof MultipleChoice) {
                mc.setQuestion(question.getQuestion());
                mc.setCorrectAnswer(question.getAnswer());
                mc.setProgress(currentProgress.intValue());
                App.setRoot("multiplechoice");
            /*} else if (question instanceof Matching) {
                mtch = (Matching) question;
                App.launch("matching"); */
            }
        }
        return exercise.calcAccuracy();
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}