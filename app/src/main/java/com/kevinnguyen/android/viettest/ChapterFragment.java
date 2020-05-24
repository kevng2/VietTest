package com.kevinnguyen.android.viettest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChapterFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public static Fragment newInstance() {
        return new ChapterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_chapter_recycler_view, container, false);
        mRecyclerView = view.findViewById(R.id.chapter_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ChapterAdapter());
        return view;
    }

    private class ChapterHolder extends RecyclerView.ViewHolder {
        private Button mChapterButton;

        public ChapterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.chapter_fragment, parent, false));
            mChapterButton = itemView.findViewById(R.id.chapter_button);
            mChapterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), VocabListActivity.class);
                    startActivity(intent);
                }
            });
        }

        public void bindChapter(String text) {
            mChapterButton.setText(text);
        }
    }

    private class ChapterAdapter extends RecyclerView.Adapter<ChapterHolder> {
        private static final String TAG = "ChapterAdapter";
        List<String> mChapter;

        public ChapterAdapter() {
            mChapter = new ArrayList<>(10);
            for(int i = 0; i < 10; i++) {
                mChapter.add(String.valueOf(i + 1));
                Log.d(TAG, "ChapterAdapter: " + i);
            }
        }

        @NonNull
        @Override
        public ChapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ChapterHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ChapterHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder: " + position);
            holder.bindChapter("Chapter " + mChapter.get(position));
        }

        @Override
        public int getItemCount() {
            return mChapter.size();
        }
    }
}
