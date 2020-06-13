package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;

public class FlashcardFragment extends Fragment {
    private static final String TAG = "FlashcardFragment";
    private TextView mVocabWord;
    private CardView mVocabCard;
    private Vocabulary mVocab;
    private Toolbar toolbar;
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
        setHasOptionsMenu(true);
        Log.d(TAG, "onCreate");
        mTextToSpeech = new TextToSpeechUtil(getActivity());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.d(TAG, "onCreateOptionsMenu");
        inflater.inflate(R.menu.activity_flashcard_pager, menu);
        MenuItem menuItem = menu.findItem(R.id.shuffle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.menu.activity_flashcard_pager) {
            Toast.makeText(getActivity(), "Big boi", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
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
        toolbar = v.findViewById(R.id.flashcard_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
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
