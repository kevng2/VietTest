package com.kevinnguyen.android.viettest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import java.util.Locale;

public class VietTestActivity extends AppCompatActivity {
    ImageButton mSoundButton;
    TextToSpeech mTextToSpeech;
    private static final String TAG = "VietTestActivity";
    Locale mVietLocale;

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

        mSoundButton = findViewById(R.id.sound_icon);
        mSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    private void speak() {
        String text = "trời ơi";
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
