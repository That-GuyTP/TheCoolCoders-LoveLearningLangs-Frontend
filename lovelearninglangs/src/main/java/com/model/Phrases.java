package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Phrases {
    private static Phrases aPhrases;
    private  static ArrayList<Phrase> phrases;
    //private ArrayList<Word> words;

    /**
     * Instance of Phrases. This is basically the resevoir of Phrases and words that make them up. Loads words and phrases from
     * DataLoader. For each phrase, look for the Words UUID's and add the correct words to the phrase's wordList. 
     */
    private Phrases(){
        aPhrases = this;
        phrases = DataLoader.getPhrases();
        
        
        /* FOR CREATING WORD-BASED PHRASES (WIP)
        words = DataLoader.getWords();
        for(Phrase phrase: phrases){
            ArrayList<Word> phraseWords = new ArrayList<>();
            for(UUID id: phrase.getWordUUIDs()){
                for(Word word: words){
                    if(word.getId().equals(id)){
                        phraseWords.add(word);
                    }
                }
                phrase.setWordList(phraseWords);
            }
             
        }*/
    }

    /**
     * Singleton way to obtain an instance of phrases. 
     * @return instance of Phrases or creates an instance to return. 
     */
    public static Phrases getInstance(){
        if (aPhrases == null) {
            aPhrases = new Phrases();
        }
        return aPhrases;
    }

    public ArrayList<Phrase> getPhrases() {
        return phrases;
    }

    /**
     * Based on the inputted difficulty double, returns all of the phrases that are off that difficulty
     * in the form of a list of Phrases.
     * @param phraseId Phrase id/phrase difficulty. Can be either 1.0, 2.0, 3.0 currently
     * @return List of Phrases that have the correct id. 
     */
    public static ArrayList<Phrase> getPhrases(Double id){
        Double tolerance = 0.0001;
        ArrayList<Phrase> returnPhrases = new ArrayList<>();
        for(Phrase phrase: phrases){
            System.out.println("Checking phrase with ID: " + phrase.getId());
            if(Math.abs(phrase.getId() - id) < tolerance){ // Checks for tolerances
                System.out.println("Adding phrase: " + phrase.getPhrase()); // Debug
                returnPhrases.add(phrase);
            }
        }
        return returnPhrases;
    }
    
    public void addPhrase(Phrase phrase) {
        for (Phrase existingPhrase : phrases) {
            if (existingPhrase.equals(phrase)) {
                return;
            }
        }

        phrases.add(phrase);
        DataWriter.savePhrase();
    }
}
