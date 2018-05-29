package com.example.iem.oldtam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "WIFI State";
    private WifiP2pManager wifiP2pManager;
    private WifiP2pDevice wifiP2pDevice;
    private WifiP2pConfig wifiP2pConfig;
    private WifiP2pManager.Channel channel;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private List<WifiP2pDevice> peers =  new ArrayList<WifiP2pDevice>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = wifiP2pManager.initialize(this, getMainLooper(),null);
        broadcastReceiver =  new WifiBroadcast(wifiP2pManager, channel, this);

        intentFilter =  new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        discoverPeers();




    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver( broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }



    private void discoverPeers(){

        wifiP2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess: ");

            }

            @Override
            public void onFailure(int reason) {
                Log.i(TAG, "onFailure: ");
            }
        });


    }

    private WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peersList) {
            List<WifiP2pDevice> refreshedPeers = (List<WifiP2pDevice>) peersList.getDeviceList();
            if (!refreshedPeers.equals(peers)){
                peers.clear();
                peers.addAll(refreshedPeers);
            }
            if (peers.size() == 0){
                Log.d(TAG, "onPeersAvailable: NO device FOUND");
            }
        }

    };

    private void bienroz(){

        wifiP2pDevice = peers.get(0);
        wifiP2pConfig = new WifiP2pConfig();
        wifiP2pConfig.deviceAddress = wifiP2pDevice.deviceAddress;
        wifiP2pConfig.wps.setup = WpsInfo.PBC;



        wifiP2pManager.connect(channel, wifiP2pConfig, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(MainActivity.this, "connxion : ECHEC",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
