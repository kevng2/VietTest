package com.kevinnguyen.android.viettest;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VocabListFragment extends Fragment {
    private RecyclerView mVocabRecyclerView;
    private TextToSpeechUtil mTextToSpeech;
    private String mTextTitle;
    private TextView mTextViewTitle;
    private List<Vocabulary> mVocab;
    private static final String TAG = "VocabListFragment";
    public static final String VOCAB_LIST = "vocabList";

    public static VocabListFragment newInstance() {
        return new VocabListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mTextTitle = getArguments().getString(ChapterFragment.CHAPTER_TITLE);
        Log.d(TAG, "onCreate: " + mTextTitle);
        mTextToSpeech = new TextToSpeechUtil(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTextToSpeech.shutdown();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: " + mTextTitle);
        View view = inflater.inflate(R.layout.list_vocab_recycler_view, container, false);
        mTextViewTitle = view.findViewById(R.id.chapter_list_title);
        mTextViewTitle.setText(mTextTitle);
        mVocabRecyclerView = view.findViewById(R.id.viet_recycler_view);
        mVocabRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mVocab = FileUtil.readCSV(getActivity());
        mVocabRecyclerView.setAdapter(new VocabAdapter(mVocab));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_chapter_recycler_view, menu);

        MenuItem flashcardItem = menu.findItem(R.id.flashcards);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.flashcards) {
            Intent intent = new Intent(getActivity(), FlashcardPagerActivity.class);
            intent.putExtra(VOCAB_LIST, (ArrayList<Vocabulary>) mVocab);
            startActivity(intent);
            return true;
        }
        else {
            return onOptionsItemSelected(item);
        }
    }

    private class VocabHolder extends RecyclerView.ViewHolder {
        private TextView mVocabText;
        private TextView mTranslation;
        private ImageButton mSoundButton;

        public VocabHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.vocab_fragment, parent, false));
            mVocabText = itemView.findViewById(R.id.vocab_text);
            mTranslation = itemView.findViewById(R.id.translation_text);
            mSoundButton =  itemView.findViewById(R.id.sound_button);

            mSoundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTextToSpeech.speak(mVocabText.getText().toString());
                }
            });
        }

        public void bindData(Vocabulary vocabulary) {
            mVocabText.setText(vocabulary.getWord());
            mTranslation.setText(vocabulary.getTranslation());
            mSoundButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_sound));
        }
    }

    private class VocabAdapter extends RecyclerView.Adapter<VocabHolder> {
        private List<Vocabulary> mVocabList;

        public VocabAdapter(List<Vocabulary> vocab) {
            mVocabList = vocab;
        }

        @NonNull
        @Override
        public VocabHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new VocabHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull VocabHolder holder, int position) {
            holder.bindData(mVocabList.get(position));
        }

        @Override
        public int getItemCount() {
            return mVocabList.size();
        }
    }
}
