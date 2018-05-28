package com.example.iem.oldtam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WifiBroadcast  extends BroadcastReceiver {


        private static final String TAG = "WIFI State";
        private WifiP2pManager wifiP2pManager;
        private WifiP2pManager.Channel  channel;
        private MainActivity mainActivity;
        private WifiP2pManager.PeerListListener peerListListener;



        public WifiBroadcast(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, MainActivity mainActivity) {
            this.wifiP2pManager = wifiP2pManager;
            this.channel = channel;
            this.mainActivity = mainActivity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)){
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                    if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED){
                        Log.i(TAG, "WIFI is enabled");
                    }else {
                        Log.i(TAG, "WIFI  is not enabled");
                    }

                // Verifie si le wifi est bien activé and notify l'activity
            }else  if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)){
                    if (wifiP2pManager != null){
                        wifiP2pManager.requestPeers(channel, peerListListener);
                    }
                    Log.d(TAG, "onReceive: P2P  peer changed ! ");


            }else  if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)){
                // Nouvelle (de)connexion
            }else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)){
                // Respond to this device's wifi state changing
            }

    }
}
