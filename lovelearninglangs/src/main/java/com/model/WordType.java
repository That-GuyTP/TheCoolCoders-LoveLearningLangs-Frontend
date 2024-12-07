package com.model;
public enum WordType {

    ARTICLE("ARTICLE"),
    NOUN("NOUN"),
    PROPER_NOUN("PROPER_NOUN"),
    VERB("VERB"),
    ADJECTIVE("ADJECTIVE"),
    ADVERB("ADVERB"),
    PREPOSITION("PREPOSITION"),
    CONJUNCTION("CONJUNCTION"),
    INTERJECTION("INTERJECTION"),
    PRONOUN("PRONOUN"),
    DETERMINER("DETERMINER"),
    NUMERAL("NUMERAL"),
    SUFFIX("SUFFIX");

    public final String label;

    private WordType(String label){
        this.label = label;
    }


}
