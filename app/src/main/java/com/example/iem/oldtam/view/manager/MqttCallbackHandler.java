package com.example.iem.oldtam.view.manager;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class MqttCallbackHandler implements MqttCallbackExtended {

    private Context context;
    private String clientHandle;

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {

    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String message_str = new String(message.getPayload(), "UTF-8");
        Log.i("TAG", "messageArrived: "+topic+" " + message_str);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
