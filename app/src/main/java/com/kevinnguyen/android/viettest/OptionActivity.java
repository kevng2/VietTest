package com.kevinnguyen.android.viettest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionActivity extends AppCompatActivity {
    private CardView mCardView;
    private CardView mTextToSpeechOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        mCardView = findViewById(R.id.chapter_option_card);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionActivity.this,
                        VietTestActivity.class);
                startActivity(intent);
            }
        });

        mTextToSpeechOption = findViewById(R.id.text_to_speech_option_card);
        mTextToSpeechOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionActivity.this,
                        TextToSpeechActivity.class);
                startActivity(intent);
            }
        });
    }
}