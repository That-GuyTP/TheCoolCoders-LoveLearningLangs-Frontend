package com.model;

import java.util.ArrayList;
import java.util.Random;

public class FillInTheBlank implements Question {
    private String question;
    private String answer;
    private ArrayList<Phrase> phrases;
    private Phrase selectedPhrase;
    private Random rand = new Random();

    // Parameter Constructor
    public FillInTheBlank(Language language, double progress) {
        this.phrases = DataLoader.getPhrases();
        selectedPhrase = selectRandomPhrase(progress);
        if (selectedPhrase == null) {
            throw new IllegalArgumentException("No phrases available for the given progress: " + progress);
        }
        this.question = generateQuestion(selectedPhrase, language);
        this.rand = new Random();
    }

    private Phrase selectRandomPhrase(double progress) {
        ArrayList<Phrase> filteredPhrases = new ArrayList<>();
        for (Phrase phrase : phrases) {
            if (Math.floor(phrase.getId()) == Math.floor(progress)) {
                filteredPhrases.add(phrase);
            }
        }
        if (filteredPhrases.isEmpty()) {
            System.out.println("No phrases found for the given progress: " + progress);
            return null;
        }
        Phrase selectedPhrase = filteredPhrases.get(rand.nextInt(filteredPhrases.size()));
        System.out.println("Selected phrase for progress " + progress + ": " + selectedPhrase.getPhrase());
        return selectedPhrase;
    }
    
    private String generateQuestion(Phrase phrase, Language language) {
        ArrayList<Phrase> phrases = DataLoader.getPhrases();
        Phrase selectedPhrase = phrases.get(rand.nextInt(phrases.size()));

        //Translate Words
        ArrayList<String> translatedWords = new ArrayList<>();
        for (Word word : selectedPhrase.getWordList()) {
            String translatedWord = word.getTranslation(language); // Hypothetical method similar to MultipleChoice
            translatedWords.add(translatedWord);
        }

        //Add them to a print, replace one with "_"
        int blankIndex = rand.nextInt(translatedWords.size());
        this.answer = translatedWords.get(blankIndex);  // Store the answer
        translatedWords.set(blankIndex, "__");
        StringBuilder questionBuilder = new StringBuilder();
        for (String word : translatedWords) {
            questionBuilder.append(word).append(" ");
        }

        return questionBuilder.toString().trim();
    }

    // Getter for the question
    public String getQuestion() {
        return "Fill in the Blank: " + this.question;
    }

    // Getter for the answer
    public String getAnswer() {
        return this.answer;
    }

    public boolean checkAnswer(String userAnswer){
        System.out.println("In model FillInTheBlank.java the correct answer for this question is set to: " + this.answer);
        return userAnswer.trim().toLowerCase().equalsIgnoreCase(this.answer);
    }
}
