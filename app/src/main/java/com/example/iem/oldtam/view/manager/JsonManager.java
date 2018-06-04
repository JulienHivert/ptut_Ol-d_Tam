package com.example.iem.oldtam.view.manager;

import com.example.iem.oldtam.view.Model.Chanson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    private static JsonManager instance;
    private Topic channel;
    private JSONArray jsonArray;

    public static JsonManager getInstance() {
        if(instance == null) {
            instance = new JsonManager();
        }

        return instance;
    }

    public void setTopic(Topic topic){
        this.channel = topic;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setData(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    //TODO
    public Chanson decodeChanson(){
        Chanson track = new Chanson();
        return track;
    }


    //TODO Chanson to JSONArray
    public JSONArray encodeChansonToJsonArray(Chanson track) throws JSONException {
//        JsonArray jsonArray2 = new JsonArray();
//        jsonArray2.add

        ArrayList<Chanson> arrayList = new ArrayList<Chanson>();
        arrayList.add(track);
        Gson gson = new Gson();

        String listString = gson.toJson(
                arrayList,
                new TypeToken<ArrayList<Chanson>>() {}.getType());

        JSONArray jsonArray = new JSONArray(listString);

        return jsonArray;
    }


    //Arraylist<Chanson> to JSONArray
    public JSONArray encodeChansonsToJsonArray(ArrayList<Chanson> arrayList) throws JSONException {
        Gson gson = new GsonBuilder().create();



        return jsonArray;
    }

    //TODO JSONArray to ArrayList<Chanson>
    public ArrayList<Chanson> decodeChansons(JSONArray jsonArray){
        ArrayList<Chanson> trackList = new ArrayList<Chanson>();
        return trackList;
    }
    //TODO
    private void encodeChansonToJsonObject(Chanson track) throws JSONException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(track);
        JSONObject jsonObject = new JSONObject(jsonString);
    }
}
