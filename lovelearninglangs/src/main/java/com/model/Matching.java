package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author Joey LaCroix
 * 
 */


public class Matching implements Question {

/**
 * @param words
 * This is the list of words that the user will be matching
 * @param wordPairs
 * This is the list of word pairs that the user will be matching
 *  @param userChoice1
 * This is the first choice that the user picks
 * @param userChoice2
 * This is the second choice that the user picks
 * @param progress
 * This is the progress of the user
 * @param language
 * This is the language that the user is learning
 * @param difficulty
 *  This is the difficulty of the question
 */
    private ArrayList<Phrase> phrases;
    private HashMap<String, String> wordDictionary;
    private HashMap<String, String> wordPairs;
    private String userChoice1;
    private String userChoice2;
    private double progress;
    private Language language;

    /**
     * This is the constructor for the Matching class
     * @param words
     * This is the list of words that the user will be matching
     * @param language
     * This is the language that the user is learning
     */
    public Matching(Language language, Double progress) {
        this.phrases = Phrases.getPhrases(Math.floor(progress));
        this.language = language;
        this.progress = progress;
        wordPairs = new HashMap<>();
        getPairs();
        //randomizePairs();
    }

    /**
     * Gets random words from random phrases of the difficulty and sets them to their respective places in the matching pairs. This will not be randomized.
     */
    public void getPairs(){
        Random random = new Random();
        for(int i=0;i<4;i++){
            Phrase randPhrase = phrases.get(random.nextInt(phrases.size()));
            Word targetWord = randPhrase.getWordList().get(random.nextInt(randPhrase.getWordList().size()));
            wordPairs.putIfAbsent(targetWord.getWord(), targetWord.getTranslation(this.language));
            wordDictionary.putIfAbsent(targetWord.getWord(), targetWord.getTranslation(this.language));
        }
    }


    /**
     * 
     * This method displays the question to the user
     * 
     * Note: Currently displaying question to console until a UI is implemented
     */
    public void displayQuestion() {
        for (Map.Entry<String, String> entry : wordPairs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key);
        }
    }

    /**
     * This is the toString method that prints out the different word pairs that are needed to be matched
     */
    public String toString() {
        String returnString = new String();
        int count = 0;
        for (Map.Entry<String, String> entry : wordPairs.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();
            returnString += count + " " + key + " : " + count + " " + value + "\n";
        }
        return returnString;
    }

    /**
     * This method shuffles the word pairs so that the correct answer is not always in the same spot
     */
    /*private void randomizePairs() {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        
        for (Word word : words) {
            keys.add(word.getWord());
            values.add(word.getTranslation(language));
        }
        
        Collections.shuffle(keys);
        Collections.shuffle(values);
        
        for (int i = 0; i < keys.size(); i++) {
            wordPairs.put(keys.get(i), values.get(i));
        }
    }*/

    /**
     * This method checks if the user's answer is correct
     * @return boolean
     * True if the user's answer is correct, false if it is incorrect
     */
    /*public boolean isCorrect(String userAnswer) {
        String[] pairs = userInput.split(" ");
        boolean allCorrect = true;

        for (String pair : pairs) {
            String[] match = pair.split("\\."); // Split at the dot
            if (match.length != 2) {
                System.out.println("Invalid format for pair: " + pair);
                allCorrect = false;
                continue;
            }
        }

    }*/

    
    /**
     * This method gets the feedback for the user based on the validity of their answer
     * @return String
     * The feedback for the user
     */
    
    /* public String getFeedback() {
        if (!isCorrect()) 
        {
            return "Correct! /n Great Job!";
        } 
        /else 
        {
            return "Incorrect! /n Dont give up!";
        }
        
    }
    */

    /**
     * This method gets the users answer to the question
     * @return String
     * The users answer to the question
     * 
     */
    public String getAnswer() {
        for (Map.Entry<String, String> entry : wordPairs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key);
        }
        return " : " + userChoice2;
    }

    @Override
    public String getQuestion() {
        return this.toString() + "\nEnter your matches(1.2 3.1 ...)";
    }

}
