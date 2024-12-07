package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import com.application.App;
import com.model.LikeLearningLangs;
//import com.controllers.CourseController;
import com.model.Phrase;
import com.model.Phrases;
import com.narration.*;
import com.model.Language;

public class LearnController {
    
    //Instance Variables
    LikeLearningLangs lll = new LikeLearningLangs();
    CourseController cc = CourseController.getInstance();
    Random random = new Random();
    Language language = cc.getLanguage();
    Double progress = cc.getProgress();
    String english = "";
    String translation = "";
    int temp = 0;
    private ArrayList<Phrase> phrases;

    //Default Const
    public LearnController() {
        lll = LikeLearningLangs.getInstance();
        language = cc.getLanguage();
        if (language == null) { // DEBUG
            System.out.println("Error: Language is not set in CourseController.");
        }
        progress = cc.getProgress();
    }

    //Get Progress
    public Double getProgress() {
        return this.progress;
    }

    //Get Review Phrases
    public HashMap<String, String> getReviewPhrases() {
        phrases = Phrases.getPhrases(Math.floor(cc.getProgress()));
        System.out.println(phrases); // DEBUG
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
        if (phrases.isEmpty()) {
            System.out.println("No phrases available.");
            return;
        }
        int index;
        do {
            index = random.nextInt(phrases.size());
        } while (index == temp);
        temp = index;
    
        Phrase selectedPhrase = phrases.get(index);
        english = selectedPhrase.getPhrase();
        translation = selectedPhrase.getTranslatedPhrase(cc.getLanguage());
        System.out.println("Selected phrase: " + english + " with translation: " + translation); // Debug
    }

    //Set Language Backup
    public void setLanguage(Language language) {
        this.language = language;
        System.out.println("Language set in LearnController: " + language);
    }

    @FXML
    private Text learnText;

    @FXML
    private void setText() throws IOException {
        learnText.setText("\"" + english + "\" is pronounced, \"" + translation + "\"");
    }

    @FXML
    private void playSound() throws IOException {
        System.out.println("You clicked the playASound button!");
        Narrator.playSound(learnText.toString());
    }

    @FXML
    private void generateNewText() throws IOException {
        System.out.println("In LearnController.java - Current Language: " + language);
        getReviewPhrases();
        setPhrase();
        learnText.setText("\"" + english + "\" is pronounced, \"" + translation + "\"");
        System.out.println("Displayed phrase: \"" + english + "\" translated as \"" + translation + "\"");
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("course");
    }

}
