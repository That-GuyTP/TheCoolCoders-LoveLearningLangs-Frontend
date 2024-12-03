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
    Random random = new Random();
    Double progress = 0.0;
    String english = "";
    String translation = "";
    int temp = 0;
    private ArrayList<Phrase> phrases;

    //Default Const
    public LearnController() {
        lll = LikeLearningLangs.getInstance();
        progress = 0.0;
    }

    //Get Progress
    public Double getProgress() {
        return this.progress;
    }

    //Get Review Phrases
    public HashMap<String, String> getReviewPhrases() {
        phrases = Phrases.getPhrases(Math.floor(cc.getProgress()));
        HashMap<String, String> reviewPhrases = new HashMap<>();
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

    //Pick a random phrase
    public void setPhrase() {
        int index = random.nextInt(phrases.size() + 1);
        boolean pause = false;
        while (pause != true) {
            if (temp != index) {
                english = phrases.get(index).getPhrase();
                translation = phrases.get(index).getTranslatedPhrase(cc.getLanguage());
            } else {
                pause = true;
            }
        }
    }

    @FXML
    private Text learnText;

    @FXML
    private void setText() throws IOException {
        learnText.setText((english + " is pronounced, \"" + translation));
    }

    @FXML
    private void playSound() throws IOException {
        System.out.println("You clicked the playASound button!");
        Narrator.playSound(learnText.toString());
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}
