package com.kevinnguyen.android.viettest;
import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TextToSpeechUtil {
    private TextToSpeech mTextToSpeech;

    public TextToSpeechUtil(Context context) {
        mTextToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(new Locale("vi_VN"));
                }
            }
        });
    }

    public void speak(String word) {
        mTextToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void shutdown() {
        mTextToSpeech.shutdown();
    }

    public TextToSpeech getTextToSpeech() {
        return mTextToSpeech;
    }
}
