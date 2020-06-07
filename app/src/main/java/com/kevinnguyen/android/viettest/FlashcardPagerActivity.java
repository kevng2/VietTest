package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class FlashcardPagerActivity extends AppCompatActivity {
    private static final String TAG = "FlashcardPagerActivity";
    private ViewPager2 mViewPager;
    private List<Vocabulary> mVocabList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_pager);

        mVocabList = (ArrayList<Vocabulary>) getIntent().getSerializableExtra(VocabListFragment.VOCAB_LIST);

        mViewPager = findViewById(R.id.flashcard_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStateAdapter(fragmentManager, getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return FlashcardFragment.newInstance(mVocabList.get(position));
            }

            @Override
            public int getItemCount() {
                return mVocabList.size();
            }
        });
    }
}
