package com.kevinnguyen.android.viettest;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import java.util.ArrayList;

public class TextToSpeechActivity extends AppCompatActivity {
    private static final String TAG = "TextToSpeechActivity";
    private Spinner mVoiceSpinner;
    private EditText mVietWord;
    private ImageButton mImageButton;
    private TextToSpeechUtil mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        mTextToSpeech = new TextToSpeechUtil(this);
        ArrayList<Voice> voices = new ArrayList<>();
        ArrayAdapter<Voice> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, voices);
        mVoiceSpinner = findViewById(R.id.voice_spinner);
        //mVoiceSpinner.setAdapter(adapter);

        mVietWord = findViewById(R.id.viet_edit_text);
        mVietWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mImageButton = findViewById(R.id.speech_image_button);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Voice x : mTextToSpeech.getTextToSpeech().getVoices()) {
                    Log.d(TAG, x.toString());
                }
                mTextToSpeech.speak(mVietWord.getText().toString());
            }
        });

    }
}