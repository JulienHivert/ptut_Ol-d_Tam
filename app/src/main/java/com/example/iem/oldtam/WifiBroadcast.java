package com.example.iem.oldtam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;

public class WifiBroadcast  extends BroadcastReceiver {

        private WifiP2pManager wifiP2pManager;
        private WifiP2pManager.Channel  channel;
        private MainActivity mainActivity;


    public WifiBroadcast(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, MainActivity mainActivity) {
        this.wifiP2pManager = wifiP2pManager;
        this.channel = channel;
        this.mainActivity = mainActivity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)){
                // Verifie si le wifi est bien activé and notify l'activity
            }else  if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)){
                // get la liste de tout les current peers
            }else  if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)){
                // Nouvelle (de)connexion
            }else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)){
                // Respond to this device's wifi state changing
            }
    }
}
