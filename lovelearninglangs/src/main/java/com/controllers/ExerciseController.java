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
    private Double progress;
    CourseController cc = CourseController.getInstance();
    private static ExerciseController instance;
    private com.controllers.FillInTheBlank fitb;
    //private com.controllers.Matching mtch;
    private com.controllers.MultipleChoice mc;
    private com.controllers.TrueOrFalse tof;



    //Constructor
    private ExerciseController() {
        exercise = new Exercise(cc.getLanguage(), cc.getProgress());
        progress = cc.getProgress();
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
        public double startExercise() throws IOException {
        generateQuestions();
        for (int i = 0; i < 10; i++) {
            Question question = questions.get(i);
            if(question instanceof trueOrFalse) {
                App.setRoot("trueorfalse");
            } else if (question instanceof FillInTheBlank) {
                App.setRoot("fillintheblank");
            } else if (question instanceof MultipleChoice) {
                App.setRoot("multiplechoice");
                mc = new MultipleChoice();
                mc.setQuestion(question.getQuestion(), 
                              ((MultipleChoice) question).getChoices(),
                              ((MultipleChoice) question).getCorrectAnswerIndex(), 
                              progress,
                              cc.getLanguage());
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