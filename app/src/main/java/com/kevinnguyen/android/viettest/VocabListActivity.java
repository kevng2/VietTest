package com.kevinnguyen.android.viettest;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;

public class VocabListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        String chapterTitle = getIntent().getStringExtra(ChapterFragment.CHAPTER_TITLE);
        Bundle args = new Bundle();
        args.putString(ChapterFragment.CHAPTER_TITLE, chapterTitle);
        VocabListFragment vocabListFragment = VocabListFragment.newInstance();
        vocabListFragment.setArguments(args);
        return vocabListFragment;
    }
}
