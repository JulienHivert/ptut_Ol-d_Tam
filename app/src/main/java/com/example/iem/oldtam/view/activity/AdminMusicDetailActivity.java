package com.example.iem.oldtam.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iem.oldtam.R;

public class AdminMusicDetailActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView artistTextView;
    ImageView partitionsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_music_detail);

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
        titleTextView = findViewById(R.id.detail_title);
        artistTextView = findViewById(R.id.detail_artist);
        partitionsImageView = findViewById(R.id.detail_partitions);
    }
}
