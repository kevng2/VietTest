package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class FlashcardPagerActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_flashcard_pager);

        mViewPager = findViewById(R.id.flashcard_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStateAdapter(fragmentManager, getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return FlashcardFragment.newInstance();
            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }
}
