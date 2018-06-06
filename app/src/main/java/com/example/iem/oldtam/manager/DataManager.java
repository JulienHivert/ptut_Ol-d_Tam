package com.example.iem.oldtam.manager;

import android.content.Context;

import com.example.iem.oldtam.model.Chanson;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<Chanson> listData;

    private static DataManager instance;

    public static DataManager getInstance() {
        if(instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public DataManager(){
        this.listData = new ArrayList<Chanson>();
        this.listData.add(new Chanson("0","Etienne de Crecy","Hashtag my ass","Super Dicsount 3","Toudoudou Toudoudou"));
    }

    public ArrayList<Chanson> getListData() {
        return listData;
    }

    public void setListData(ArrayList<Chanson> listData) {
        this.listData = listData;
    }

    public void addChanson(String artiste, String titre, String album, String paroles) {
        String id;
        if(listData.size()<=0){
            id = "0";
        }else{
            id = String.valueOf(listData.size() + 1);
        }
        Chanson chanson = new Chanson(id, artiste, titre, album, paroles);
        listData.add(chanson);
    }
}
