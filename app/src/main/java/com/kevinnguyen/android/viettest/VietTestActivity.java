package com.kevinnguyen.android.viettest;
import androidx.fragment.app.Fragment;

public class VietTestActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ChapterFragment.newInstance();
    }
}
