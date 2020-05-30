package com.kevinnguyen.android.viettest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_chapter_recycler_view, container, false);
        mRecyclerView = view.findViewById(R.id.chapter_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ChapterAdapter());
        return view;
    }

    private class ChapterHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ChapterHolder";
        private Button mChapterButton;
        private ImageView mImageView ;

        public ChapterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.chapter_fragment, parent, false));
            mChapterButton = itemView.findViewById(R.id.chapter_button);
            mImageView = itemView.findViewById(R.id.chapter_image);
            mChapterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), VocabListActivity.class);
                    startActivity(intent);
                }
            });
        }

        public void bindChapter(String text, int chapter) {
            mChapterButton.setText(text);
            String chapterStr = "chapter" + (chapter + 1);
            int drawable = Objects.requireNonNull(getActivity()).getResources().getIdentifier(chapterStr, "drawable",
                    getActivity().getPackageName());
            Log.d(TAG, "bindChapter: " + drawable);
            Drawable image = getActivity().getResources().getDrawable(drawable);
            Glide.with(getActivity())
                    .load(image)
                    .into(mImageView);
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
            holder.bindChapter("Chapter " + mChapter.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mChapter.size();
        }
    }
}
