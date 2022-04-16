package com.example.roomwordssample.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roomwordssample.R;
import com.example.roomwordssample.adapter.WordListAdapter;
import com.example.roomwordssample.entity.Word;
import com.example.roomwordssample.viewmodel.WordViewModel;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel = new WordViewModel(getApplication());
        mWordViewModel.getAllWords().observe(this, words -> {
            adapter.setWords(words);
        });

    }

    ActivityResultLauncher mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Word word = new Word(data.getStringExtra("word"));
                    mWordViewModel.insert(word);
                }
            });

    public void addNewWord(View view) {
        Intent intent = new Intent(this, WordActivity.class);
        mGetContent.launch(intent);
    }
}