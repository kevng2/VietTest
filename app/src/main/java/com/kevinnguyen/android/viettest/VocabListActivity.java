package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class VocabListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        String chapterTitle = getIntent().getStringExtra(ChapterFragment.CHAPTER_TITLE);
        int chapterNumber = getIntent().getIntExtra(ChapterFragment.CHAPTER_NUMBER, 0);
        Bundle args = new Bundle();
        args.putString(ChapterFragment.CHAPTER_TITLE, chapterTitle);
        args.putInt(ChapterFragment.CHAPTER_NUMBER, chapterNumber);
        VocabListFragment vocabListFragment = VocabListFragment.newInstance();
        vocabListFragment.setArguments(args);
        return vocabListFragment;
    }
}
