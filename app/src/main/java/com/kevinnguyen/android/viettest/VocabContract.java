package com.kevinnguyen.android.viettest;

import android.provider.BaseColumns;

public class VocabContract {
    private VocabContract() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "vocab";

    }
}
