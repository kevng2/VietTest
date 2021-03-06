package com.kevinnguyen.android.viettest;

import java.io.Serializable;

public class Vocabulary implements Serializable {
    private String mWord;
    private String mTranslation;

    public Vocabulary(String word, String translation) {
        mWord = word;
        mTranslation = translation;
    }

    public String toString() {
        return "Word: " + mWord + "\tTranslation: " + mTranslation + "\n";
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public String getTranslation() {
        return mTranslation;
    }

    public void setTranslation(String translation) {
        mTranslation = translation;
    }
}
