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
        startExercise();
    }

    /**
     * calls the generateQuestions method from our exercise model and creates a list of questions based off that.
     * 
     * @return arrayList<Question> questions
     */
    public ArrayList<Question> generateQuestions() {
        questions = exercise.generateQuestions();
        return questions;
    }

    /**
     * startExercise is what will run when the exercise controller starts. It calls the creation of the questions, compares the types of each question, and changes to
     * a question type based off the type. It also sets the values of each questions' question string, correct answer, and a progress value.
     * 
     * @return double Arruracy
     */
    public double startExercise() {
        generateQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if(question instanceof trueOrFalse) {
                tof.setQuestion(question.getQuestion(), null, i);
                if (question.getAnswer() == "true") {
                    tof.setCorrectAnswer(0);
                } else if (question.getAnswer() == "false") {
                    tof.setCorrectAnswer(1);
                }
                tof.setProgress(currentProgress.intValue());
                App.setRoot("trueorfalse");
            } else if (question instanceof FillInTheBlank) {
                fitb.setQuestion(null, null);
                fitb.setCorrectAnswer(question.getAnswer());
                fitb.setProgress(currentProgress.intValue());
                //fitb.FillInTheBlank(question.getQuestion(), question.getAnswer(), currentProgress.intValue()) - Based of current para const for fitb
                App.setRoot("fillintheblank");
            } else if (question instanceof MultipleChoice) {
                mc.setQuestion(question.getQuestion());
                mc.setCorrectAnswer(question.getAnswer());
                mc.setProgress(currentProgress.intValue());
                //mc.MultipleChoice(question.getQuestion(), question.getAnswer(), currentProgress.intValue()) - Based of current para const for mc
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