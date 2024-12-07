
package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Word that contains values such as the plain text string, tranlsation for languages, part of speech, word type, 
 * and a unique UUID used to reference it.
 */
public class Word {
    private static ArrayList<Word> wordList = new ArrayList<>();
    private String word;
    private HashMap<Language, String> translations;
    private WordType partOfSpeech;
    private String pronounciation;
    private String gender;
    private UUID uuid;
    private Double id;
    private static Word aWord;
    

    /**
     * Creates new word
     * @param word plaintext String in english for the word
     * @param translations Hashmap of translations for each language. EX: "spanish : hola"
     * @param wordType Type of word. Can be noun, pronoun, adjective, etc
     * @param gender Gender of the word. For languages where gender matters for certain words
     * @param uuid Unique UUID for the word
     */
    public Word(String word, HashMap<Language, String> translations, WordType wordType, String gender, Double id, UUID uuid) {
        //this.wordList = new ArrayList<>();
        this.word = word;
        this.translations = translations;
        this.partOfSpeech = wordType;
        this.gender = gender;
        this.id = id;
        this.uuid = uuid;
    }

    /**
     * Used for DataWriter
     * @return instance of Word
     */
    public static Word getInstance() {
        if(aWord == null){
            aWord = new Word(null, null, null, null, null, null);
            
        }
            return aWord;
        
    }

    /**
     * Gets UUID of word
     * @return unique UUID
     */
    public Double getId() {
        return id;
    }

    /**
     * Sets the UUID
     * @param id UUID to be changed too
     */
    public void setId(Double id) {
        this.id = id;
    }
    
    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
    /**
     * Sets the plaintext version of the word
     * @param word String the word will now become
     */
    public void setWord(String word) {
        this.word = word;
    }

  
    /**
     * Gets the plaintext version of the Word
    * @return the Word as a String
    */
    // made a little change to the get word by Aashish Jayapuram
    public String getWord() {
        return word;
    }  

    /**
     * Sets the word type from a String
     * @param partOfSpeech String that will be changed to part of speech
     */
    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = WordType.valueOf(partOfSpeech);
    }

    /**
     * Gets the Words part of speech/wordType
     * @return the Words wordType
     */
    public WordType getPartOfSpeech() {
        return partOfSpeech;
    }

    /**
     * Sets the gender of the word
     * @param gender gender that the word will be changed too
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the words gender
     * @return gender of the word
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the Hashmap of word translations
     * @param translations Hashmap that will replace the words current translation set
     */
    public void setWordTranslation(HashMap<Language, String> translations) {
        this.translations = translations;
    }

    /**
     * Returns the current Words translation set. 
     * @return Hashmap of Lanugages and the translations in those languages
     */
    public HashMap<Language, String> getWordTranslations() {
        return translations;
    }

    /**
     * Gets a words translation in a given language
     * @param language language to get the word in
     * @return Word in the chosen language (EX: Hello -> Hola)
     */
    public String getTranslation(Language language){
        System.out.println("In Word.java getTranslation - Language: " + language);
        if (translations.containsKey(language)) {
            return translations.get(language);
        } else {
            System.out.println("No translation found for language: " + language);
            return null;
        }
    }

    /**
     * Sets the pronunciation of the word as a String
     * @param pronounciation pronunciation to be change too
     */
    public void setPronounciation(String pronounciation) {
        this.pronounciation = pronounciation;
    }

    /**
     * Gets the words pronunciation 
     * @return the words pronunciation
     */
    public String getPronounciation() {
        return pronounciation;
    }

    /**
     * Returns the Word as a String with all relevant information
     */
    public String toString() {
        return "Word = " + this.word
             + "Part Of Speech = " + this.partOfSpeech
             + "Gender = " + this.gender
             + "Translations = " + this.translations
             + "Pronounciation = " + this.pronounciation;
    }

    public static void addWord(Word word) {
        for (Word existingWord : wordList) {
            if (existingWord.equals(word)) 
                return;
        }
        
        wordList.add(word);
        DataWriter.saveWords();

    }

    public static ArrayList<Word> getWords() {
        return wordList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Word word = (Word) obj;
        return this.word.equals(word.word) && this.translations.equals(word.translations);
    }

}
