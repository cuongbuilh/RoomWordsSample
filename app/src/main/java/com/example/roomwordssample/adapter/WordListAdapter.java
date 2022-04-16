package com.example.roomwordssample.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordssample.R;
import com.example.roomwordssample.activity.MainActivity;
import com.example.roomwordssample.activity.WordActivity;
import com.example.roomwordssample.entity.Word;
import com.example.roomwordssample.viewmodel.WordViewModel;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords;
    private Context context;
    private WordViewModel mWordViewModel;
    private ActivityResultLauncher mGetContent;

    public WordListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mWordViewModel = new WordViewModel(((Activity) context).getApplication());
        mGetContent = ((MainActivity) context).registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        String action = data.getStringExtra("action");
                        Word word = new Word(data.getStringExtra("word"));
                        word.setID(data.getIntExtra("id", -1));

                        if (action.equals("update")) {
                            mWordViewModel.update(word);
                        }
                        else if (action.equals("delete")) {
                            mWordViewModel.delete(word);
                        }
                    }
                });
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
            holder.setData(current);
        } else {
            holder.wordItemView.setText("No Word");
        }
    }

    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private Word word;

        void setData(Word word) {
            this.word = word;
        }
        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);

            wordItemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, WordActivity.class);
                intent.putExtra("word", word.getWord());
                intent.putExtra("id", word.getID());
                mGetContent.launch(intent);
            });
        }
    }
}