package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    private TextView mFlashcardPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_pager);
        Toolbar toolbar = findViewById(R.id.flashcard_toolbar);
        setSupportActionBar(toolbar);
        mVocabList = (ArrayList<Vocabulary>)
                getIntent().getSerializableExtra(VocabListFragment.VOCAB_LIST);
        mFlashcardPosition = findViewById(R.id.flashcard_position);
        mViewPager = findViewById(R.id.flashcard_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStateAdapter(fragmentManager, getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                mFlashcardPosition.setText(getString(R.string.flashcard_position_fraction,
                        position + 1, mVocabList.size()));
                return FlashcardFragment.newInstance(mVocabList.get(position));
            }

            @Override
            public int getItemCount() {
                return mVocabList.size();
            }
        });

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mFlashcardPosition.setText(getString(R.string.flashcard_position_fraction,
                        position + 1, mVocabList.size()));
            }
        });
    }
}
