package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import com.application.App;
import com.model.LikeLearningLangs;
import com.controllers.CourseController;
import com.model.Phrase;
import com.model.Phrases;
import com.narration.*;

public class LearnController {
    
    //Instance Variables
    LikeLearningLangs lll = new LikeLearningLangs();
    CourseController cc = new CourseController();
    Double progress = 0.0;
    private ArrayList<Phrase> phrases;

    public LearnController() {
        lll = LikeLearningLangs.getInstance();
        progress = 0.0;
    }

    public void setProgress(Double x) {
        this.progress = x;
    }

    public Double getProgress() {
        return this.progress;
    }

    public HashMap<String, String> getReviewPhrases() {
        phrases = Phrases.getPhrases(Math.floor(cc.getProgress()));
        HashMap<String, String> reviewPhrases = new HashMap<>();
        Random random = new Random();

        if (phrases.isEmpty()) {
            System.out.println("No Phrases available for review.");
            return reviewPhrases;
        }
        for(int i=0; i<phrases.size(); i++){
            Phrase reviewPhrase = phrases.get(i);
            reviewPhrases.putIfAbsent(reviewPhrase.getPhrase(), reviewPhrase.getTranslatedPhrase(cc.getLanguage()));
        }
        return reviewPhrases;
    }

    @FXML
    private Text learnText;

    @FXML
    private void setText() throws IOException {
        int temp = 0;
    }

    @FXML
    private void playSound() throws IOException {
        System.out.println("You clicked the playASound button!");
        Narrator.playSound("Hola World! Vosotros los Cool Coders! Hola Grader! Como estas?");
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}
