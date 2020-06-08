package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;

public class FlashcardFragment extends Fragment {
    private TextView mVocabWord;
    private CardView mVocabCard;
    private Vocabulary mVocab;
    private TextToSpeechUtil mTextToSpeech;
    private ImageButton mSoundButton;
    private boolean onWord = true;
    public static final String VOCABULARY_WORD = "vocabWord";

    public static FlashcardFragment newInstance(Vocabulary vocabulary) {
        Bundle args = new Bundle();
        args.putSerializable(VOCABULARY_WORD, vocabulary);
        FlashcardFragment flashcardFragment = new FlashcardFragment();
        flashcardFragment.setArguments(args);
        return flashcardFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextToSpeech = new TextToSpeechUtil(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTextToSpeech.shutdown();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.flashcard_fragment, container, false);
        mVocabCard = v.findViewById(R.id.vocab_card);
        mVocabWord = v.findViewById(R.id.flashcard_vocab);
        mVocab = (Vocabulary) getArguments().getSerializable(VOCABULARY_WORD);
        mVocabWord.setText(mVocab.getWord());
        mVocabCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FlipInY)
                        .playOn(v);
                if(onWord) {
                    mVocabWord.setText(mVocab.getTranslation());
                    onWord = false;
                }
                else {
                    mVocabWord.setText(mVocab.getWord());
                    onWord = true;
                }
            }
        });

        mSoundButton = v.findViewById(R.id.flashcard_sound_button);
        mSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onWord) {
                    mTextToSpeech.getTextToSpeech().setLanguage(new Locale("vi_VN"));
                    mTextToSpeech.speak(mVocab.getWord());
                }
                else {
                    mTextToSpeech.getTextToSpeech().setLanguage(new Locale("en"));
                    mTextToSpeech.speak(mVocab.getTranslation());
                }
            }
        });
        return v;
    }
}
