package com.example.iem.oldtam.manager;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.iem.oldtam.model.Chanson;
import com.example.iem.oldtam.tools.Notify;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONException;

import java.util.ArrayList;

public class MqttManager {

    private static final int QOS =  2;
    private static final String ADRESSE = "172.24.1.1";
    private static final String PORT = "1894";
    private JsonManager jsonManager;
    private MqttClient androidClient;
    public Context context;
    private Topic topic;
    private WifiManager wifiManager;
    private String clientID;
    private static MqttManager instance;

    public MqttManager(Context context){
        this.topic = Topic.INIT_TS1;
        this.context = context;
        this.jsonManager = new JsonManager();
        this.wifiManager = (WifiManager) this.context.getSystemService(Context.WIFI_SERVICE);
    }

    public static MqttManager getInstance(Context context) {
        if(instance == null) {
            instance = new MqttManager(context);
        }
        return instance;
    }

    public void setTopic(Topic topic){
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }

    public boolean isConnected(){
        if(androidClient.isConnected()){
            return true;
        }else{
            return false;
        }
    }

    public void connect() throws MqttException {
//        clientID = ;

        try {
            androidClient = new MqttClient("tcp://172.24.1.1:1896", MqttClient.generateClientId(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            androidClient.connect(options);
        }
        catch (MqttException e) {
            e.printStackTrace();
        }

//        androidClient = new MqttAndroidClient(context,"tcp://"+ADRESSE+":"+PORT, MqttClient.generateClientId());
//        IMqttToken token = androidClient.connect();
//        token.setActionCallback(new IMqttActionListener() {
//            @Override
//            public void onSuccess(IMqttToken asyncActionToken) {
//                Notify.toast(context,"Connexion au réseau réussi ", Toast.LENGTH_LONG);
//                subscribe(topic.toString());
//            }
//
//            @Override
//            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                Log.i("TAG", "onFailure: ");
//            }
//        });
//        androidClient.setCallback(new MqttCallbackHandler());

    }

    public void disconnect() {
        if (androidClient == null) {
            return;
        }
        try {
            androidClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

//        try {
//            IMqttToken disconToken = androidClient.disconnect();
//            disconToken.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    // Nous nous sommes correctement déconnecté
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken,
//                                      Throwable exception) {
//                    // Quelque chose c'est mal passé, mais on est probablement déconnecté malgré tout
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
    }

    public void sendChanson(Chanson chanson){
        MqttMessage message = new MqttMessage();
        message.setPayload(jsonManager.encodeChansonToJsonArray(chanson).getBytes());
        try {
            androidClient.publish(topic.toString(),message);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void sendChansons(ArrayList<Chanson> arrayList){
        MqttMessage message = new MqttMessage();
        ArrayList arrayListToSend = new ArrayList();
        arrayListToSend = jsonManager.getListToSend(arrayList);
        try {
            message.setPayload(jsonManager.encodeChansonsToJsonArray(arrayList).getBytes());
            androidClient.publish(Topic.VOTE_TS2.toString(),message);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    private void subscribe(final String topic){
        try {
            androidClient.subscribe(topic, QOS);
        } catch (MqttException e) {
            e.printStackTrace();
        }
//        try {
//            IMqttToken subToken = androidClient.subscribe(topic, QOS);
//            subToken.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    // On a bien souscrit au topic
//                    System.out.println("onSuccess subscribe topic " + topic);
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken,
//                                      Throwable exception) {
//                    // La souscription n'a pas pu se faire, peut être que l'utilisateur n'a pas
//                    // l'autorisation de souscrire à ce topic
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void testMSQ(View v){
        Chanson chanson =  new Chanson("1","lolo","ihuojp","ihbunoj","ftyguhijokpl");
        sendChanson(chanson);
    }

    public void wifiState(){
        if (!wifiManager.isWifiEnabled()){
            Notify.toast(this.context,"Vous etes pas connecté", Toast.LENGTH_SHORT);
            wifiManager.setWifiEnabled(true);
        }else {
            Notify.toast(this.context, "Vous etes conecté", Toast.LENGTH_SHORT);
        }
    }

    public void sendInit(ArrayList<Chanson> listData) {
        MqttMessage message = new MqttMessage();
        try {
            message.setPayload(jsonManager.encodeChansonsToJsonArray(listData).getBytes());
            androidClient.publish(Topic.INIT_TS1.toString(),message);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
