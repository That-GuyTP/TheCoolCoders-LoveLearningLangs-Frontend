/**
 * @author Aashish Jayapuram
 */

package com.model;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    private static DataWriter dataWriter;

    private DataWriter() {
        dataWriter = this;
    }

    public static void main(String[] args) {
        DataWriter.saveAll();
    }

    @SuppressWarnings("unchecked")
    public static void saveUsers() {
        Users users = Users.getInstance();
        ArrayList<User> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (BufferedWriter writer = Files.newBufferedWriter(getUsersFilePath(), StandardCharsets.UTF_8)) {
            writer.write(jsonUsers.toJSONString());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER, user.getUsername());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PROGRESS, user.getProgress());
        userDetails.put(USER_PROGRESS_LANGUAGE, user.getProgress());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_UUID, user.getUUID().toString());
        return userDetails;

    }

    @SuppressWarnings("unchecked")
    public static void saveWords() {
        ArrayList<Word> existingWord = DataLoader.getWords();
        JSONArray jsonWord = new JSONArray();

        for (Word word : existingWord) {
            jsonWord.add(getWordJSON(word));
        }

        ArrayList<Word> wordList = Word.getWords();
        for (Word word : wordList) {
            jsonWord.add(getWordJSON(word));
        }

        try (BufferedWriter writer = Files.newBufferedWriter(getWordsFilePath(), StandardCharsets.UTF_8)) {
            writer.write(jsonWord.toJSONString());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static JSONObject getWordJSON(Word word) {
        JSONObject wordDetails = new JSONObject();
        wordDetails.put(WORD_ID, word.getId().toString());
        wordDetails.put(WORD_UUID, word.getUUID().toString());
        wordDetails.put(WORD_WORD, word.getWord());
        wordDetails.put(WORD_PARTOFSPEECH, word.getPartOfSpeech().toString());
        wordDetails.put(WORD_GENDER, word.getGender());

        JSONObject translations = new JSONObject();
        for (Language lang : word.getWordTranslations().keySet()) {
            translations.put(lang.toString(), word.getTranslation(lang));
        }
        wordDetails.put(WORD_LANGUAGE, translations);

        return wordDetails;
    }

    @SuppressWarnings("unchecked")
    public static void savePhrase() {
        ArrayList<Phrase> existingPhrases = DataLoader.getPhrases();
        JSONArray jsonPhrases = new JSONArray();

        for (Phrase phrase : existingPhrases) {
            jsonPhrases.add(getPhraseJSON(phrase));
        }

        try (BufferedWriter writer = Files.newBufferedWriter(getPhrasesFilePath(), StandardCharsets.UTF_8)) {
            writer.write(jsonPhrases.toJSONString());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public static JSONObject getPhraseJSON(Phrase phrase) {
        JSONObject phraseDetails = new JSONObject();
        phraseDetails.put(PHRASE_ID, phrase.getId().toString());
        phraseDetails.put(PHRASE_PHRASE, phrase.getPhrase());
        phraseDetails.put(PHRASE_WORDS, phrase.getwordUUIDLists().toString());
        return phraseDetails;

    }

    public static void saveAll() {
        savePhrase();
        saveUsers();
        saveWords();
    }
}
