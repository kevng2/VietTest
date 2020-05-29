package com.kevinnguyen.android.viettest;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class VocabListFragment extends Fragment {
    private RecyclerView mVocabRecyclerView;
    private TextToSpeech mTextToSpeech;

    public static Fragment newInstance() {
        return new VocabListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(new Locale("vi_VN"));
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_vocab_recycler_view, container, false);
        mVocabRecyclerView = view.findViewById(R.id.viet_recycler_view);
        mVocabRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mVocabRecyclerView.setAdapter(new VocabAdapter(FileUtil.readCSV(getActivity())));
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

            mSoundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    speak();
                }
            });
        }

        public void bindData(Vocabulary vocabulary) {
            mVocabText.setText(vocabulary.getWord());
            mTranslation.setText(vocabulary.getTranslation());
            mSoundButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_sound));
        }

        public void speak() {
            String toSpeak = mVocabText.getText().toString();
            mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
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
