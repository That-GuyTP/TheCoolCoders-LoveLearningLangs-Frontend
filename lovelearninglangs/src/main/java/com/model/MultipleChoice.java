package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MultipleChoice implements Question {
    private Random rand = new Random();
    private Language lang;
    private Double progress;
    private ArrayList<String> choices;
    private int correctAnswerIndex; // Correct Answer Index in ArrayList
    private ArrayList<Phrase> phrases;
    private Phrase correctPhrase;
    private ArrayList<Word> words;
    private Word correctWord;
    

    /**
     * This is the constructor for the MultipleChoice class
     */
    public MultipleChoice(Language lang, Double progress) {
        this.lang = lang;
        this.progress = progress;
        this.phrases = DataLoader.getPhrases();
        this.words = DataLoader.getWords();
        this.choices = new ArrayList<>();
        generateQuestion();
    }

    /**
     * This method displays the question to the user
     * 
     * Note: Currently displaying question to console until a UI is implemented
     */
    public void displayQuestion() {
        if (correctWord != null) {
            System.out.println("Which is the correct translation for the word: " + correctWord.getWord());
        } else if (correctPhrase != null) {
            System.out.println("Which is the correct translation for the phrase: " + correctPhrase.getPhrase());
        }

        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }
    }

    private void generateQuestion() {
        boolean isWord = rand.nextBoolean();
        if (isWord) {
            correctWord = getRandomWord();
            if (correctWord != null) {
                generateChoicesFromWords(correctWord);
            }
        } else {
            correctPhrase = getRandomPhrase();
            if (correctPhrase != null) {
                generateChoicesFromPhrases(correctPhrase);
            }
        }
    }

    private Word getRandomWord() {
        ArrayList<Word> filteredWords = new ArrayList<>();
        for (Word word : words) {
            // Progress comparison using the ones place of the word's ID (UUID based in the real system)
            if (Math.floor(word.getId() % 10) == this.progress.intValue()) {
                filteredWords.add(word);
            }
        }

        // Ensure there are words to choose from
        if (filteredWords.isEmpty()) {
            return null;  // Handle case where no words match the progress level
        }

        return filteredWords.get(rand.nextInt(filteredWords.size()));
    }

    private Phrase getRandomPhrase() {
        ArrayList<Phrase> filteredPhrases = new ArrayList<>();
        for (Phrase phrase : phrases) {
            // Progress comparison using the ones place of the phrase's ID
            if (Math.floor(phrase.getId() % 10) == Math.floor(progress % 10)) {
                filteredPhrases.add(phrase);
            }
        }

        // Ensure there are phrases to choose from
        if (filteredPhrases.isEmpty()) {
            System.out.println("No phrases found for the given progress.");
            return null;  // Handle case where no phrases match the progress level
        }
        return filteredPhrases.get(rand.nextInt(filteredPhrases.size()));
    }

    private void generateChoicesFromWords(Word correctWord) {
        choices.add(correctWord.getTranslation(lang)); // Add correct answer
        while (choices.size() < 4) {
            Word randomWord = getRandomWord();
            if (randomWord != null && !choices.contains(randomWord.getTranslation(lang))) {
                choices.add(randomWord.getTranslation(lang));
            }
        }
        shuffleChoices();
        correctAnswerIndex = choices.indexOf(correctWord.getTranslation(lang));
    }

    private void generateChoicesFromPhrases(Phrase correctPhrase) {
        choices.add(correctPhrase.getTranslatedPhrase(lang));  // Add correct answer
        while (choices.size() < 4) {
            Phrase randomPhrase = getRandomPhrase();
            if (randomPhrase != null && !choices.contains(randomPhrase.getTranslatedPhrase(lang))) {
                choices.add(randomPhrase.getTranslatedPhrase(lang));  // Add incorrect option
            }
        }
        shuffleChoices();
        correctAnswerIndex = choices.indexOf(correctPhrase.getTranslatedPhrase(lang));
    }

    /**
     * This method shuffles the choices so that the correct answer is not always in the same spot
     */
    public void shuffleChoices() {
        Collections.shuffle(choices);
    }

    public String getQuestion() {
        if (correctWord != null) {
            getChoices();
            return "What is the correct translation for the word: " + correctWord.getWord() + "?";
        } else if (correctPhrase != null) {
            getChoices();
            return "What is the correct translation for the phrase: " + correctPhrase.getPhrase() + "?";
        }
        return null;
    }

    public String getAnswer() {
        if (correctWord != null) {
            return correctWord.getTranslation(lang);  // Correct word translation
        } else if (correctPhrase != null) {
            return correctPhrase.getTranslatedPhrase(lang);  // Correct phrase
        }
        return null;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public boolean checkAnswer(int userAnswer) {
        System.out.println("In model MultipleChoice.java the correct answer for this question is set to: " + correctAnswerIndex);
        return userAnswer == correctAnswerIndex;
    }


    
}