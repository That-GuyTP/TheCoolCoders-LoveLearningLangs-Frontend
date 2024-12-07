package com.model;

import java.util.ArrayList;

public class Words {
    
    private static Words instance;
    private static ArrayList<Word> words;

    private Words() {
        words = DataLoader.getWords(); // Assuming DataLoader loads words
        System.out.println("Words loaded: " + words.size());
        for (Word word : words) {
            System.out.println(word);
        }
    }

    public static Words getInstance() {
        if (instance == null) {
            instance = new Words();
        }
        return instance;
    }

    public static ArrayList<Word> getWords(Double progress) {
        if (instance == null) {
            System.out.println("Words instance not initialized. Initializing now...");
            getInstance(); // Ensures singleton is initialized
        }
        ArrayList<Word> filteredWords = new ArrayList<>();
        Double tolerance = 0.5;
        for (Word word : words) {
            //System.out.println("Word ID: " + word.getId() + " | User Progress: " + progress); DEBUG
            if (Math.abs(word.getId() - progress) < tolerance) {
                //System.out.println("Adding word: " + word.getWord()); DEBUG
                filteredWords.add(word);
            }
        }
        return filteredWords;
    }

}
