package com.example.iem.oldtam.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.manager.DataManager;
import com.example.iem.oldtam.manager.MqttManager;
import com.example.iem.oldtam.model.Chanson;

import org.eclipse.paho.client.mqttv3.MqttException;


public class AdminMusicDetailActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView artistTextView;
    ImageView partitionsImageView;
    DataManager dataManager;
    MqttManager mqttManager;
    private int actualID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_music_detail);

        initViews();
        initData();


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_admin){
            try {
                mqttManager.connect();
                if(mqttManager.isConnected()){
                    Log.d("CONNECTION", "Connected to network");
                    Chanson chanson = dataManager.getListData().get(actualID);
                    mqttManager.sendChanson(chanson);
                }else{
                    Log.d("CONNECTION", "Not connected");
                }


            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
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

    public void initData(){
        dataManager = DataManager.getInstance();
        mqttManager = new MqttManager(getApplicationContext());
        Intent intent = getIntent();
        String id_temp = intent.getStringExtra("id");
        actualID = Integer.parseInt(id_temp);
        this.titleTextView.setText(this.dataManager.getListData().get(actualID).getTitre());
        this.artistTextView.setText(this.dataManager.getListData().get(actualID).getArtiste());
    }
}
