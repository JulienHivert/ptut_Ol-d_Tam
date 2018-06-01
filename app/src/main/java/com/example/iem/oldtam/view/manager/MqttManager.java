package com.example.iem.oldtam.view.manager;

public class MqttManager {

    private static MqttManager instance;

    public static MqttManager getInstance() {
        if(instance == null) {
            instance = new MqttManager();
        }

        return instance;
    }

}
