package com.example.iem.oldtam.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.view.Model.Chanson;
//import com.example.iem.oldtam.view.adapter.MenuPagerAdapter;
//import com.example.iem.oldtam.view.manager.ChansonResponse;
import com.example.iem.oldtam.view.manager.JsonManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewPager();
        test();
    }

    private void initializeViewPager() {

        tabLayout = findViewById(R.id.main_tabLayout);
        if (tabLayout != null) {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);

            viewPager = findViewById(R.id.main_viewPager);
//            viewPager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager(),this));
//            viewPager.setClipToPadding(false);
            viewPager.setPageMargin(12);

            tabLayout.setupWithViewPager(viewPager);
        }

        setupTabLayoutIcon();
    }

    private void setupTabLayoutIcon() {
        int[] imageResId = {
                R.drawable.ic_mic_primary_light_24dp,
                R.drawable.ic_library_music_primary_light_24dp,
                R.drawable.ic_settings_remote_primary_light_24dp};

        for (int i = 0; i < imageResId.length; i++) {
            if (tabLayout.getTabAt(i) != null) {
                tabLayout.getTabAt(i).setIcon(imageResId[i]);
            }
        }
    }

    public void test(){
        Chanson track = new Chanson("0","Etienne de Crécy","Cut the trap", "Super Discount", "Yo Yo Yo");
        Chanson track2 = new Chanson("1","Etienne de Crécy","Cut the trap", "Super Discount", "Yo Yo Yo");
        JsonManager jsonManager = new JsonManager();

        //Création d'une ArrayList avec 2 chansons, transformation en JsonArray
        ArrayList<Chanson> chansonArrayList = new ArrayList<Chanson>();
        chansonArrayList.add(track);
        chansonArrayList.add(track2);


        String jsonObject = jsonManager.encodeChansonToJsonArray(track);
        String jsonArray = null;
        try {
            jsonArray = jsonManager.encodeChansonsToJsonArray(chansonArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Chanson> arrayList = new ArrayList<Chanson>();
        Chanson chanson1 = new Chanson();

        Log.d("HELLO", jsonObject);
        Log.d("HELLO", jsonArray);

        chanson1 = jsonManager.decodeChanson(jsonObject);
        arrayList = jsonManager.decodeChansons(jsonArray);
    }
}
