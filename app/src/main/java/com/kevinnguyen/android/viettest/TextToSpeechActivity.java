package com.kevinnguyen.android.viettest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {
    private static final String TAG = "TextToSpeechActivity";
    private Spinner mVoiceSpinner;
    private EditText mVietWord;
    private ImageButton mImageButton;
    private TextToSpeechUtil mTextToSpeech;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        //mTextToSpeech = new TextToSpeechUtil(this);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTTS.setLanguage(new Locale("vi_VN"));
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
                            mTextToSpeech.speak(mVietWord.getText().toString());
                        }
                    });

                    ArrayList<Voice> voices = new ArrayList<>(mTTS.getVoices());
                    ArrayAdapter<Voice> adapter = new ArrayAdapter<>(TextToSpeechActivity.this, android.R.layout.simple_spinner_dropdown_item, voices);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mVoiceSpinner = findViewById(R.id.voice_spinner);
                    mVoiceSpinner.setAdapter(adapter);
                }
            }
        });
    }
}