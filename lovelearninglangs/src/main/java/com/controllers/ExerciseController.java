package com.controllers;

import com.model.Exercise;
import com.model.Language;
import com.model.LikeLearningLangs;
import com.model.Question;
import com.model.FillInTheBlank;
import com.model.Matching;
import com.model.MultipleChoice;
import com.model.trueOrFalse;
import com.model.User;

import java.util.ArrayList;
import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ExerciseController {

    //Instance variables
    CourseController cc = CourseController.getInstance();
    private Exercise exercise;
    private ArrayList<Question> questions;
    private Double progress;
    private int progressLabelValue;
    private int score = 0;
    private int scoreLabelValue;
    private static ExerciseController instance;
    //private com.controllers.FillInTheBlank fitb;
    //private com.controllers.Matching mtch;
    private com.controllers.MultipleChoiceController mc;
    private com.controllers.TrueFalseController tof;



    //Constructor
    public ExerciseController() {
        this.exercise = new Exercise(cc.getLanguage(), cc.getProgress());
        this.progress = cc.getProgress();
    }

    public static ExerciseController getInstance() {
        if (instance == null) {
            instance = new ExerciseController();
        }
        return instance;
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
     * @throws IOException 
     */
        public void startExercise(int level) throws IOException {
        questions = exercise.generateQuestions();
        for (int i = 0; i < 10; i++) {
            Question question = questions.get(i);
            if(question instanceof trueOrFalse) {
                App.setRoot("trueorfalse");
                FXMLLoader loader = new FXMLLoader(App.class.getResource("trueorfalse.fxml"));
                Parent root = loader.load();
                tof = loader.getController();
                App.scene.setRoot(root);
                tof.setQuestion((trueOrFalse) question, i, questions.size());
            } else if (question instanceof MultipleChoice) {
                App.setRoot("multiplechoice");
                FXMLLoader loader = new FXMLLoader(App.class.getResource("multiplechoice.fxml"));
                Parent root = loader.load();
                tof = loader.getController();
                App.scene.setRoot(root);
                mc.setQuestion((MultipleChoice) question, i, questions.size());
            /*
            } else if (question instanceof FillInTheBlank) {
                // fitb = new com.controllers.FillInTheBlankController();
                // fitb.setQuestion((FillInTheBlank) question, i, questions.size());
                App.setRoot("fillintheblank");
            } else if (question instanceof Matching) {
                mtch = (Matching) question;
                App.launch("matching"); 
            */
            }
        }
        exerciseComplete();
    }

    public void exerciseComplete() throws IOException {
        double accuracy = (double) score / 10.0 * 100.0;
        if (accuracy >= 70.0) {
            System.out.println("Score above 70%. Updating progress...");
            User currentUser = cc.getUser();
            Language currentLanguage = cc.getLanguage();
            Double currentProgress = currentUser.getLangProgress(currentLanguage);
            if (currentProgress == null) {
                currentProgress = 1.0; // Just in case.
            }
            currentUser.getProgress().put(currentLanguage, currentProgress + 1.0);
            LikeLearningLangs.getInstance().saveProgress();
        } else {
            System.out.println("Score below 70%. Progress not updated.");
        }
        App.setRoot("course");
    }

    public void setProgressLabelValue(int x) {
        progressLabelValue = x;
    }

    public int getProgressLabelValue() {
        return progressLabelValue;
    }

    public void setScoreLabelValue(int x) {
        scoreLabelValue = x;
    }

    public int getScoreLabelValue() {
        return progressLabelValue;
    }

    public void setExerciseScore(int x) {
        score = x;
    }

    public int getExerciseScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}