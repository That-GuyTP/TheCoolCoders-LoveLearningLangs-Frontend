package com.model;

public abstract class DataConstants {
    

    //Changed the old File Path "Narration\\speak\\src\\main\\java\\com\\narration\\json\\Users.json."
    //User Details
    protected static final String USERS_JSON_FILE = "resources/data/Users.json";
    protected static final String USER = "username";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_PROGRESS = "courseProg";
    protected static final String USER_PROGRESS_LANGUAGE = "language";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_UUID = "id";

    //Phrases Details
    protected static final String PHRASES_JSON_FILE = "resources/data/Phrases.json";
    protected static final String PHRASE_ID = "id";
    protected static final String PHRASE_PHRASE = "phrase";
    protected static final String PHRASE_WORDS = "phraseWords";

    //Word Details
    protected static final String WORDS_JSON_FILE = "resources/data/Words.json";
    protected static final String WORD_ID = "id";
    protected static final String WORD_UUID = "uuid";
    protected static final String WORD_WORD = "word";
    protected static final String WORD_PARTOFSPEECH = "partOfSpeech";
    protected static final String WORD_GENDER = "gender";
    protected static final String WORD_LANGUAGE = "language";
}
