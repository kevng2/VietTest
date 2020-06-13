package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.viewpager.widget.PagerAdapter.POSITION_NONE;

public class FlashcardPagerActivity extends AppCompatActivity {
    private static final String TAG = "FlashcardPagerActivity";
    private ViewPager2 mViewPager;
    private List<Vocabulary> mVocabList;
    private TextView mFlashcardPosition;
    private FlashcardAdapter mFlashcardAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_pager);
        invalidateOptionsMenu();
        Log.d(TAG, "onCreate: ");
        Toolbar toolbar = findViewById(R.id.flashcard_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.activity_flashcard_pager);

        mVocabList = (ArrayList<Vocabulary>)
                getIntent().getSerializableExtra(VocabListFragment.VOCAB_LIST);

        mFlashcardPosition = findViewById(R.id.flashcard_position);
        mViewPager = findViewById(R.id.flashcard_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFlashcardAdapter = new FlashcardAdapter(this);
        mViewPager.setAdapter(mFlashcardAdapter);
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mFlashcardPosition.setText(getString(R.string.flashcard_position_fraction,
                        position + 1, mVocabList.size()));
            }
        });
    }

    public class FlashcardAdapter extends FragmentStateAdapter {
        public FlashcardAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.shuffle) {
            Collections.shuffle(mVocabList);
            //mFlashcardAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
