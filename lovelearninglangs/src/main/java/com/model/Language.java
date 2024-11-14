package com.model;

public enum Language {
    ENGLISH("english"),
    SPANISH("spanish"),
    GREEK("greek"),
    FRENCH("french"),
    GERMAN("german");
    //More languages could be added

    public final String label;

    private Language(String label){
        this.label = label;
    }

    public String getName() {
        return label;
    }
}
