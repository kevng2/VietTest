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

public class FileUtil {
    private static final String TAG = "WordList";

    public static List<Vocabulary> readCSV(Context context, int chapterNumber) {
        List<Vocabulary> wordList = new ArrayList<>();
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier("chapter_" + chapterNumber, "raw", packageName);
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                words[0] = words[0].replace("\"", "");
                words[1] = words[1].replace("\"", "");
                wordList.add(new Vocabulary(words[0], words[1]));
            }
        }
        catch(IOException ioe) {
            Log.e(TAG, "Could not read file ", ioe);
        }
        return wordList;
    }
}
