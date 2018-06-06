package com.example.iem.oldtam.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iem.oldtam.R;

public class AdminAddMusicActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText artistEditText;
    EditText albumEditText;
    EditText partitionEditText;
    EditText lyricsEditText;

    Button partitionsImportButton;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);

        initViews();

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initViews() {
        titleEditText = findViewById(R.id.add_title_edit);
        artistEditText = findViewById(R.id.add_artist_edit);
        albumEditText = findViewById(R.id.add_album_edit);
        partitionEditText = findViewById(R.id.add_partitions_edit);
        lyricsEditText = findViewById(R.id.add_lyrics_edit);

        partitionsImportButton = findViewById(R.id.add_partitions_import);
        addButton = findViewById(R.id.add_validate_button);
    }
}
