package com.kevinnguyen.android.viettest;

import androidx.fragment.app.Fragment;

public class VocabListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return VocabListFragment.newInstance();
    }
}
