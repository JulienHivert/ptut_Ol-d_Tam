package com.example.iem.oldtam.view.activity;

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

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.view.Model.Chanson;
import com.example.iem.oldtam.view.fragment.InProgressFragment;
import com.example.iem.oldtam.view.fragment.MusicListFragment;
import com.example.iem.oldtam.view.fragment.PollFragment;
import com.example.iem.oldtam.view.manager.JsonManager;
import com.example.iem.oldtam.view.manager.Topic;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private WifiManager wifiManager;
    private MqttAndroidClient androidClient = null;
    private int QOS =  0;
    private final String topic =Topic.INIT_ST0.toString();
    JsonManager jsonManager = new JsonManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tunrnWifi();
        testMSQ();
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

  //  private void tunrnWifi(){
  //      if (!wifiManager.isWifiEnabled()){
  //          wifiManager.setWifiEnabled(true);
  //          connect("127.24.1.1","1896" );
  //          Chanson chanson =  new Chanson("1","lolo","ihuojp","ihbunoj","ftyguhijokpl^");
  //          sendChanson(chanson);
//
  //      }else{
  //          connect("127.24.1.1","1896" );
  //      }
  //  }

    public void connect(String address, String port) {
        String clientId = MqttClient.generateClientId(); // génère un ID
        androidClient = new MqttAndroidClient(getApplicationContext(), "tcp://" + address + ":" + port, clientId);

        try {
            IMqttToken token = androidClient.connect(); // on tente de se connecter
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // Nous sommes connecté
                    System.out.println("On est connecté !");
                    subscribe(topic); // ligne à commenter pour le moment
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Erreur de connexion : temps de connexion trop long ou problème de pare-feu
                    System.err.println("Echec de connection !");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        androidClient.setCallback(new MqttCallbackHandler()); // ligne à commenter pour le moment
    }

    @Override
    protected void onPause() {
        super.onPause();
        disconnect();
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
    private void subscribe(final String topic ){
        try {

            IMqttToken subToken =  androidClient.subscribe(topic, QOS);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("TAG", "onSuccess: "+ topic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("TAG", "onFailure: ");
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void sendChanson(Chanson chanson){
        MqttMessage message1 = new MqttMessage();
        message1.setPayload(jsonManager.encodeChansonToJsonArray(chanson).getBytes());
        try {
            androidClient.publish(topic, message1);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    public void testMSQ(){
        connect("127.24.1.1","1896" );
        Chanson chanson =  new Chanson("1","lolo","ihuojp","ihbunoj","ftyguhijokpl^");
        sendChanson(chanson);

    }
}
