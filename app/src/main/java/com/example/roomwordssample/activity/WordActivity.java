package com.example.roomwordssample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomwordssample.R;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class WordActivity extends AppCompatActivity {

    private EditText mEditWordView;
    private int idIntent;
    private Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        mEditWordView = findViewById(R.id.edit_word);

        btn_delete = findViewById(R.id.button_delete);
        btn_delete.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        String wordIntent = intent.getStringExtra("word");
        idIntent = intent.getIntExtra("id", -1);
        if (wordIntent != null) {
            mEditWordView.setText(wordIntent);
            btn_delete.setVisibility(View.VISIBLE);
        }

    }

    public void SaveClick(View view) {
        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(mEditWordView.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String word = mEditWordView.getText().toString();
            replyIntent.putExtra("action", "update");
            replyIntent.putExtra("word", word);
            replyIntent.putExtra("id", idIntent);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

    public void DeleteClick(View view) {
        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(mEditWordView.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String word = mEditWordView.getText().toString();
            replyIntent.putExtra("action", "delete");
            replyIntent.putExtra("word", word);
            replyIntent.putExtra("id", idIntent);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }
}