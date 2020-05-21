package com.kevinnguyen.android.viettest;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VietTestActivity extends AppCompatActivity {
    private static final String TAG = "VietTestActivity";
    TextToSpeech mTextToSpeech;
    Locale mVietLocale;
    WordList mWordList;
    List<Vocabulary> mVocabularyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVietLocale = new Locale("vi_VN");

        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    int result = mTextToSpeech.setLanguage(mVietLocale);
                    Log.d(TAG, "onInit: Success!");
                }
            }
        });

        mWordList = new WordList();
        mVocabularyList = mWordList.readCSV(this);

        for(Vocabulary x : mVocabularyList) {
            Log.d(TAG, " " + x);
        }
    }

    private void speak() {
        String text = "đụ má";
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
