package com.kevinnguyen.android.viettest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChapterFragment extends Fragment {
    private RecyclerView mRecyclerView;
    public static final String CHAPTER_TITLE = "requestChapterNumber";

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

    private class ChapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "ChapterHolder";
        private ImageView mImageView;
        private TextView mChapterTitle;
        private CardView mCardView;
        private String mTitleText;

        public ChapterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.chapter_fragment, parent, false));
            mImageView = itemView.findViewById(R.id.chapter_image);
            mChapterTitle = itemView.findViewById(R.id.chapter_title);
            mCardView = itemView.findViewById(R.id.cardView);
            itemView.setOnClickListener(this);
        }

        public void bindChapter(String text, int chapter) {
            YoYo.with(Techniques.FadeIn)
                    .playOn(mCardView);
            int stringResource = getActivity().getResources().getIdentifier(text, "string", getActivity().getPackageName());
            mChapterTitle.setText(getActivity().getResources().getString(stringResource));
            String chapterStr = "chapter" + (chapter + 1);
            int drawable = Objects.requireNonNull(getActivity()).getResources()
                    .getIdentifier(chapterStr, "drawable", getActivity().getPackageName());
            Drawable image = getActivity().getResources().getDrawable(drawable);
            Glide.with(getActivity())
                    .load(image)
                    .into(mImageView);
        }

        @Override
        public void onClick(View v) {
            YoYo.YoYoString animation = YoYo.with(Techniques.Pulse)
                    .duration(200)
                    .playOn(mCardView);

            // Delay the the switch to a new activity until the animation is done
            itemView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "Running run()");
                    Intent intent = new Intent(getActivity(), VocabListActivity.class);
                    intent.putExtra(CHAPTER_TITLE, mChapterTitle.getText().toString());
                    startActivity(intent);
                }
            }, 200);
        }
    }

    private class ChapterAdapter extends RecyclerView.Adapter<ChapterHolder> {
        private static final String TAG = "ChapterAdapter";
        List<String> mChapter;
        private CardView mCardView;

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
            holder.bindChapter("chapter" + mChapter.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mChapter.size();
        }
    }
}
