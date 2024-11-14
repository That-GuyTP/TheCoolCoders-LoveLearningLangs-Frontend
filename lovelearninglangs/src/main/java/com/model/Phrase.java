package com.model;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;

public class Phrase {
    private double id; // Added by Aashish Jayapuram
    private String phrase;
    private ArrayList<UUID> wordUUIDList;
    private Word word;
    private ArrayList<Word> wordList;
    private static ArrayList<Phrase> phraseList = new ArrayList<>();;// Added by Aashish Jayapuram
    private static Phrase aPhrase;
    private ArrayList<Phrase> phrases = new ArrayList<>();
    private HashMap<Language, String> translations;

    // CONSTRUCTORS
    public Phrase() {
        this.translations = new HashMap<>();
        wordList = filterWordsByUUID(wordUUIDList);
        if (wordList == null) {
            wordList = new ArrayList<>();
            System.out.println("DEBUG WARNING WORDLIST IS NULL!");
        }
        
    }

    public Phrase(double id, String phrase, ArrayList<UUID> wordUUIDList) {
        this.id = id;
        this.phrase = phrase;
        this.wordUUIDList = wordUUIDList;
      
        this.translations = new HashMap<>();
        wordList = filterWordsByUUID(wordUUIDList);
    }

    // PHRASE
    public Double getId() {
        return this.id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getPhrase() {
        return this.phrase;
    }

    // Added by Aashish Jayapuram
    public static ArrayList<Phrase> getPhraseList() {
        if (phraseList == null) {
            phraseList = new ArrayList<>();
        }

        return phraseList;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    // Get the list of UUIDs for the phrase
    public ArrayList<UUID> getwordUUIDLists() {
        return this.wordUUIDList;
    }

    public void setwordUUIDLists(ArrayList<UUID> wordUUIDList) {
        this.wordUUIDList = wordUUIDList;
    }

    // Translate the UUIDs into word objects
    public void translateUUIDs(HashMap<UUID, Word> wordMap) {
        for (UUID wordUUID : wordUUIDList) {
            if (wordMap.containsKey(wordUUID)) {
                wordList.add(wordMap.get(wordUUID));
            } else {
                System.out.println("Word with UUID " + wordUUID + " not found.");
            }
        }
    }

    public ArrayList<Word> getWordList() {
        return this.wordList;
    }

    public void setWordList(ArrayList<Word> wordList) {
        this.wordList = wordList;
    }

    // WORD

    // Translate the words by lanuage and return the phrase.
    public String getTranslatedPhrase(Language language) {
        if (wordList == null) {
            return "Error: wordList is null";
        }
        StringBuilder translatedPhrase = new StringBuilder();
        boolean isFirstWord = true;
        for (Word word : wordList) {
            String translation = word.getTranslation(language);
            if (translation != null) {
                if (isFirstWord) {
                    translatedPhrase.append((translation)).append(" ");
                    isFirstWord = false;
                } else {
                    translatedPhrase.append(translation.toLowerCase()).append(" ");
                }
            } else {
                System.out.println("Translation not found");
            }
        }
        return translatedPhrase.toString().trim(); // Remove trailing space
    }

    public void setTranslation(HashMap<Language, String> translationMap) {
        this.translations.putAll(translationMap);
    }

    public static Phrase getInstance() {
        if (aPhrase == null) {
            aPhrase = new Phrase(0, null, null);

        }
        return aPhrase;

    }

    // public void getWord() {

    // }

    // public void getPartOFSpeech() {

    // }

    public void addPhrase(Phrase phrase) {
        for (Phrase existingPhrase : phraseList) {
            if (existingPhrase.equals(phrase)) {
                return;
            }
        }

        phraseList.add(phrase);
        DataWriter.savePhrase();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Phrase phrase = (Phrase) obj;
        return Double.compare(phrase.id, id) == 0 && this.phrase.equals(phrase.phrase);
    }

    private ArrayList<Word> filterWordsByUUID(ArrayList<UUID> wordUUIDList) {
        ArrayList<Word> filteredWords = new ArrayList<>();
        HashMap<UUID, Word> wordMap = new HashMap<>();
        
        // Create a map of UUID to Word for quick access
        for (Word word : DataLoader.getWords()) {
            wordMap.put(word.getUUID(), word);
        }
        
        // Add words to filteredWords if their UUID matches
        for (UUID uuid : wordUUIDList) {
            if (wordMap.containsKey(uuid)) {
                filteredWords.add(wordMap.get(uuid));
            } else {
                System.out.println("Word with UUID " + uuid + " not found.");
            }
        }
        return filteredWords;
    }

}
