package com.example.iem.oldtam.view.manager;

import com.example.iem.oldtam.view.Model.Chanson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class ChansonResponse {

    public List<Chanson> listChansons;

    public ChansonResponse () {
        listChansons = new ArrayList<Chanson>();
    }

    public ChansonResponse parseJSON (String response) {
        Gson gson = new GsonBuilder().create();
        ChansonResponse chansonResponse = gson.fromJson(response, ChansonResponse.class);
        return chansonResponse;
    }
}
