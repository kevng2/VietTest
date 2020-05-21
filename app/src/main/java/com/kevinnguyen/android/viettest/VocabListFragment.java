package com.kevinnguyen.android.viettest;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VocabListFragment extends Fragment {
    private RecyclerView mVocabRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vocab_fragment, container, false);
        mVocabRecyclerView = view.findViewById(R.id.viet_recycler_view);
        mVocabRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
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
        }

        public void bindData(Vocabulary vocabulary) {

        }
    }
}
