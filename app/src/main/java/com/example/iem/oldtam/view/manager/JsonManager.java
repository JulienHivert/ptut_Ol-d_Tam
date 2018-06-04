package com.example.iem.oldtam.view.manager;

import com.example.iem.oldtam.view.Model.Chanson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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


    //[String]JsonArray to ArrayList<Chanson>
    public ArrayList<Chanson> decodeChansons(String response){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Chanson> arrayList = gson.fromJson(response, new TypeToken<ArrayList<Chanson>>(){}.getType());

        return arrayList;
    }

    //[String]JsonArray to Chanson
    public Chanson decodeChanson(String response){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Chanson chanson = gson.fromJson(response, Chanson.class);
        System.out.println(chanson);
        return chanson;
    }


    //Chanson to [String]JsonObject
    public String encodeChansonToJsonArray(Chanson track){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", track.getId());
        jsonObject.addProperty("artiste",track.getArtiste());
        jsonObject.addProperty("titre", track.getTitre());
        jsonObject.addProperty("album", track.getAlbum());
        jsonObject.addProperty("paroles", track.getParoles());
        return jsonObject.toString();
    }


    //Arraylist<Chanson> to JsonArray
    public String encodeChansonsToJsonArray(ArrayList<Chanson> arrayList) throws JSONException {
        JsonArray jsonArray = new JsonArray();
        for(int i=0;i<arrayList.size();i++){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", arrayList.get(i).getId());
            jsonObject.addProperty("artiste", arrayList.get(i).getArtiste());
            jsonObject.addProperty("titre", arrayList.get(i).getTitre());
            jsonObject.addProperty("album", arrayList.get(i).getAlbum());
            jsonObject.addProperty("paroles", arrayList.get(i).getParoles());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
