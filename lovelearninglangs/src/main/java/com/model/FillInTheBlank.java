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
        this.question = generateQuestion(selectedPhrase, language);
        this.rand = new Random();
    }

    private Phrase selectRandomPhrase(double progress) {
        ArrayList<Phrase> filteredPhrases = new ArrayList<>();
        for (Phrase phrase : phrases) {
            if ((int)(phrase.getId() % 10) == (int)(progress % 10)) {
                filteredPhrases.add(phrase);
            }
        }
        if (filteredPhrases.isEmpty()) {
            System.out.println("No phrases found for the given progress.");
            return null;
        }
        int randomIndex = (int) (Math.random() * filteredPhrases.size());
        return filteredPhrases.get(randomIndex);
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
        System.out.println("In model trueOrFalse.java the correct answer for this question is set to: " + this.answer);
        return userAnswer.trim().toLowerCase().equalsIgnoreCase(this.answer);
    }
}
