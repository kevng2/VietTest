package com.kevinnguyen.android.viettest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class TypingActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        Bundle args = new Bundle();
        args.putSerializable(VocabListFragment.VOCAB_LIST,
                getIntent().getSerializableExtra(VocabListFragment.VOCAB_LIST));
        TypingFragment typingFragment = new TypingFragment();
        typingFragment.setArguments(args);
        return typingFragment;
    }
}
