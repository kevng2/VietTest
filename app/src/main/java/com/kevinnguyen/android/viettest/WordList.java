package com.kevinnguyen.android.viettest;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WordList {
    private static final String TAG = "WordList";
    List<Vocabulary> wordList = new ArrayList<>();

    public List<Vocabulary> readCSV(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.chapter_1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                wordList.add(new Vocabulary(words[0], words[1]));
            }
        }
        catch(IOException ioe) {
            Log.e(TAG, "Could not read file ", ioe);
        }
        return wordList;
    }
}
