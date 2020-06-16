package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TypingFragment extends Fragment {
    private static final String TAG = "TypingFragment";
    private TextView mVocab;
    private EditText mTypingViet;
    private List<Vocabulary> mVocabList;
    private Random mRandom;
    private String mRandomWord;

    public static TypingFragment newInstance() {
        return new TypingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVocabList = (ArrayList<Vocabulary>) getArguments().getSerializable(VocabListFragment.VOCAB_LIST);
        mRandom = new Random();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.typing_fragment, container, false);
        mVocab = v.findViewById(R.id.typing_vocab);

        int number = mRandom.nextInt(mVocabList.size());
        mRandomWord = mVocabList.get(number).getWord();
        mVocab.setText(mRandomWord);

        mTypingViet = v.findViewById(R.id.typing_edit);
        mTypingViet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(mRandomWord)) {
                    Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                    int n = mRandom.nextInt(mVocabList.size());
                    mRandomWord = mVocabList.get(n).getWord();
                    mVocab.setText(mRandomWord);
                    Log.d(TAG, "onTextChanged: " + mVocab.getText());
                    mTypingViet.getText().clear();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }
}
