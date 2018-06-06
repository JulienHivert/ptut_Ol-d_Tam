package com.example.iem.oldtam.view.manager;

import android.util.Log;

import com.example.iem.oldtam.view.model.Chanson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import java.util.ArrayList;

public class JsonManager {
    private static JsonManager instance;
    private Topic channel;

    public static JsonManager getInstance() {
        if(instance == null) {
            instance = new JsonManager();
        }

        return instance;
    }

    public void setTopic(Topic topic){
        this.channel = topic;
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
