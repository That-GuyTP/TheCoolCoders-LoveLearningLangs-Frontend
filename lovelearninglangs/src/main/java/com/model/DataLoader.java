/**
 * @author Alex Ervin
 */
package com.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    private static DataLoader dataLoader;

    public static void main(String[] args) {
        ArrayList<Phrase> phrases = DataLoader.getPhrases();
        for (Phrase phrase : phrases) {
            System.out.println(phrase.getPhrase());
            System.out.println(phrase.getTranslatedPhrase(Language.SPANISH));
        }
    }

    private DataLoader() {
        dataLoader = this;
    }

    public static DataLoader getInstance() {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            InputStream in = Files.newInputStream(Paths.get(USERS_JSON_FILE));
            InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);
                String username = (String) userJSON.get(USER);
                String email = (String) userJSON.get(USER_EMAIL);
                String firstName = (String) userJSON.get(USER_FIRST_NAME);
                String lastName = (String) userJSON.get(USER_LAST_NAME);
                JSONObject progressJSON = (JSONObject) userJSON.get(USER_PROGRESS);
                HashMap<Language, Double> progress = new HashMap<Language, Double>();
                if (progressJSON != null) {
                    for (Object key : progressJSON.keySet()) {
                        Language language = Language.valueOf((String) key);
                        Double progression = (Double) progressJSON.get(key);
                        progress.put(language, progression);
                    }
                }
                String password = (String) userJSON.get(USER_PASSWORD);
                String uuidStr = (String) userJSON.get(USER_UUID);
                UUID id = null;
                try {
                    id = UUID.fromString(uuidStr);
                } catch (IllegalArgumentException e) {
                    System.err.printf("Invalid UUID format");
                    continue;
                }

                // //Debugging Section
                // System.out.println(id);
                // System.out.println(firstName + " " + lastName);

                users.add(new User(username, password, firstName, lastName, progress, email, id));
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }

    public static ArrayList<Word> getWords() {
        // Only Reading in WORDS from json file
        ArrayList<Word> words = new ArrayList<>();
        try {
            InputStream in = Files.newInputStream(Paths.get(WORDS_JSON_FILE));
            InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            JSONArray wordsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < wordsJSON.size(); i++) {
                JSONObject wordJSON = (JSONObject) wordsJSON.get(i);
                UUID uuid = UUID.fromString((String) wordJSON.get(WORD_UUID));
                Double id = Double.parseDouble((String) wordJSON.get(WORD_ID));
                String word = (String) wordJSON.get(WORD_WORD);
                WordType partOfSpeech = WordType.valueOf((String) wordJSON.get(WORD_PARTOFSPEECH));
                String gender = (String) wordJSON.get(WORD_GENDER);
                HashMap<Language, String> translations = new HashMap<Language, String>();
                Object languageObject = wordJSON.get(WORD_LANGUAGE);
                if (languageObject instanceof JSONObject) {
                    JSONObject translationsObject = (JSONObject) languageObject;
                    for (Object key : translationsObject.keySet()) {
                        Language language = Language.valueOf((String) key);
                        String translation = (String) translationsObject.get(key);
                        translations.put(language, translation);
                    }
                } else if (languageObject instanceof JSONArray) {
                    JSONArray translationsArray = (JSONArray) languageObject;
                    for (int j = 0; j < translationsArray.size(); j++) {
                        JSONObject translationsObject = (JSONObject) translationsArray.get(j);
                        for (Object key : translationsObject.keySet()) {
                            Language language = Language.valueOf((String) key);
                            String translation = (String) translationsObject.get(key);
                            translations.put(language, translation);
                        }
                    }
                } else {
                    System.err.println("Unexpected language format: " + languageObject);
                }
                words.add(new Word(word, translations, partOfSpeech, gender, id, uuid));
            }
            return words;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Word>();

    }

    public static ArrayList<Phrase> getPhrases() {
        ArrayList<Phrase> phrases = new ArrayList<>();
        try {
            InputStream in = Files.newInputStream(Paths.get(PHRASES_JSON_FILE));
            InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            JSONArray phrasesJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < phrasesJSON.size(); i++) {
                JSONObject phraseJSON = (JSONObject) phrasesJSON.get(i);
                String idString = (String) phraseJSON.get(PHRASE_ID);
                Double id = Double.parseDouble(idString);
                String phrase = (String) phraseJSON.get(PHRASE_PHRASE);

                ArrayList<UUID> wordUUIDs = new ArrayList<>();
                String wordArrayStr = (String) phraseJSON.get(PHRASE_WORDS);
                wordArrayStr = wordArrayStr.substring(1, wordArrayStr.length() - 1); // Remove brackets
                String[] wordArray = wordArrayStr.split(", ");

                for (String wordIDStr : wordArray) {
                    UUID wordID = UUID.fromString(wordIDStr);
                    wordUUIDs.add(wordID);
                }

                phrases.add(new Phrase(id, phrase, wordUUIDs));
            }
            return phrases;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Phrase>();
    }
}
