package com.example.iem.oldtam.view.manager;

import org.json.JSONObject;

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
}
