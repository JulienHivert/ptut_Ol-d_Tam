package com.example.iem.oldtam.view.activity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.iem.oldtam.R;
import com.example.iem.oldtam.view.Model.Chanson;
import com.example.iem.oldtam.view.fragment.InProgressFragment;
import com.example.iem.oldtam.view.fragment.MusicListFragment;
import com.example.iem.oldtam.view.fragment.PollFragment;
import com.example.iem.oldtam.view.manager.JsonManager;
import com.example.iem.oldtam.view.manager.Topic;
import com.example.iem.oldtam.view.tools.Notify;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private WifiManager wifiManager;
    private MqttAndroidClient androidClient = null;
    private int QOS =  0;
    private final String topic =Topic.INIT_ST0.toString();
    private final String ADRESSE = "172.24.1.1";
    private final String PORT = "1896";

    private Button btn;
    JsonManager jsonManager = new JsonManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        btn = findViewById(R.id.btn);
        initializeNav();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_admin){
            //TODO Lancer le mode admin
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeNav() {
        BottomNavigationView navigation = findViewById(R.id.menu_nav);

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_progress:
                        showFragment(InProgressFragment.newInstance());
                        return true;
                    case R.id.navigation_list:
                        showFragment(MusicListFragment.newInstance());
                        return true;
                    case R.id.navigation_poll:
                        showFragment(PollFragment.newInstance());
                        return true;
                }
                return false;
            }

        };

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_list);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.menu_frame, fragment)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        disconnect();
    }

    @Override
    protected void onResume() {
        try {
            connect(ADRESSE, PORT);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    private void connect(String adress, String port) throws MqttException {
        String clientID = MqttClient.generateClientId();
        androidClient = new MqttAndroidClient(getApplicationContext(),"tcp://"+adress+":"+port, clientID);
        IMqttToken token = androidClient.connect();
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Notify.toast(getApplicationContext(),"Connexion au réseau réussi ", Toast.LENGTH_LONG);
                subscribe(topic);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("TAG", "onFailure: ");
            }
        });
            androidClient.setCallback(new MqttCallbackHandler());
    }

    public void disconnect() {
        if (androidClient == null) {
            return;
        }
        try {
            IMqttToken disconToken = androidClient.disconnect();
            disconToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // Nous nous sommes correctement déconnecté
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // Quelque chose c'est mal passé, mais on est probablement déconnecté malgré tout
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void sendChanson(Chanson chanson){
        MqttMessage message = new MqttMessage();
        message.setPayload(jsonManager.encodeChansonToJsonArray(chanson).getBytes());
        try {
            androidClient.publish(topic,message);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    private void subscribe(final String topic){
        try {
            IMqttToken subToken = androidClient.subscribe(topic, QOS);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // On a bien souscrit au topic
                    System.out.println("onSuccess subscribe topic " + topic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // La souscription n'a pas pu se faire, peut être que l'utilisateur n'a pas
                    // l'autorisation de souscrire à ce topic
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void testMSQ(View v){
        Chanson chanson =  new Chanson("1","lolo","ihuojp","ihbunoj","ftyguhijokpl");
        sendChanson(chanson);
    }

}
