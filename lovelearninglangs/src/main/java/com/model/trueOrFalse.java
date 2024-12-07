package com.model;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * @author Joey LaCroix
 * This class is used to generate a true or false question for the user to answer.
   * The question can be based on a word or a phrase.
   * The user will be asked if the translation of the word or phrase is true or false.
   * The user will be given two choices, true or false.
 */
public class trueOrFalse implements Question {
    
  /**
   * @param kb
   * This is the scanner that will be used to get the user's input
   * @param rand
   * This is the random number generator that will be used to generate random numbers
   * @param language
   * This is the language that the user is learning
   * @param progress
   * This is the progress of the user
   * @param correctAnswerIndex
   * This is the index of the correct answer in the choices array
   * @param userAnswer
   * This is the user's answer to the question
   * @param choices
   * This is the list of choices that the user can choose from
   * @param phrases
   * This is the list of phrases that the user can be asked about
   * @param words
   * This is the list of words that the user can be asked about
   * @param phrase
   * This is the phrase that the user will be asked about
   * @param phraseTranslation
   * This is the translation of the phrase that the user will be asked about
   * @param word
   * This is the word that the user will be asked about
   * @param wordTranslation
   * This is the translation of the word that the user will be asked about
   * @param answer
   * true or false answer
   *  
   */

    private Scanner kb = new Scanner(System.in);
    private Random rand = new Random();
    private Language language;
    private Double progress;
    private int correctAnswerIndex;
    private String userAnswer;
    private ArrayList<String> choices;
    private ArrayList<Phrase> phrases;
    private ArrayList<Word> words;
    private Phrase phrase;
    private String phraseTranslation;
    private String wordTranslation;
    private Word word;
    private boolean answer;
    private int correctAnswer = 0;

    /**
     * This is the constructor for the trueOrFalse class
     */
    public trueOrFalse(Language language, Double progress){
        this.language = language;
        this.progress = progress;
        this.phrases = DataLoader.getPhrases();
        this.words = DataLoader.getWords();
        this.choices = new ArrayList<>();
        choices.add("True");
        choices.add("False");
        generateQuestion();
    }

    /**
     * This method displays the question to the user
     * 
     */
    public void displayQuestion(){
        System.out.println("True or False: ");
        if(phrase != null)
        {
            System.out.println("The phrase is: " + phrase);
            System.out.println("The translation is: " + phraseTranslation);
            
        } 
        else if(word != null)
        {
            System.out.println("The word is: " + word);
            System.out.println("The translation is: " + wordTranslation);
        }

        System.out.println("True or False.");
    }

    /**
     * This method generates the question for the user
     * 
     */
    public void generateQuestion(){
        boolean isWord = rand.nextBoolean();
        if (isWord) {
            word = getRandomWord();
            if(word != null){
                wordTranslation = getWordTranslation();
                if (wordTranslation == word.getTranslation(language)) {
                    correctAnswer = 1;
                } else {
                    correctAnswer = 2;
                }
            }
        } else {
            phrase = getRandomPhrase();
            if(phrase != null){
                phraseTranslation = getPhraseTranslation();
                if (phraseTranslation == phrase.getTranslatedPhrase(language)) {
                    correctAnswer = 1;
                } else {
                    correctAnswer = 2;
                }
            }
        }
    }

    public String getWordTranslation(){
        Word randomWord = getRandomWord();
        return randomWord.getTranslation(language);
    }

    public String getPhraseTranslation(){
        Phrase randomPhrase = getRandomPhrase();
        return randomPhrase.getTranslatedPhrase(language);

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
            if (Math.floor(phrase.getId() % 10) == this.progress.intValue()) {
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

    public ArrayList<String> getChoices(){
        return choices;
    }

    public String getQuestion() {
        if (word!= null) {
            return "Is " + "'" + this.wordTranslation + "'" +  " the translation for the word " + word.getWord() + "?";
        } else if (phrase!= null) {
            return "Does '" + this.phrase.getTranslatedPhrase(language) +  "' mean '" + phrase.getPhrase() + "'?";
        }
        return null;
    }

    public String getAnswer() {
        return "" + correctAnswerIndex;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctAnswer;
    }
    



}
