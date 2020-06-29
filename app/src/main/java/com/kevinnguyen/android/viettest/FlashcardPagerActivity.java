package com.kevinnguyen.android.viettest;
import android.content.Intent;
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
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Log.d(TAG, "onCreate: Creating Stuff");
        mVocabList = (ArrayList<Vocabulary>)
                getIntent().getSerializableExtra(VocabListFragment.VOCAB_LIST);
        mFlashcardPosition = findViewById(R.id.flashcard_position);
        mViewPager = findViewById(R.id.flashcard_view_pager);
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

        Toolbar toolbar = findViewById(R.id.flashcard_toolbar);
        toolbar.inflateMenu(R.menu.activity_flashcard_pager);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Log.d(TAG, "onCreateOptionsMenu: Here is the menu!");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_flashcard_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: Up Button Clicked");
        if(item.getItemId() == R.id.shuffle) {
            Collections.shuffle(mVocabList);

            // restarts the activity
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
