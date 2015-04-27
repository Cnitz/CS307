package cs307.com.playdeck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;

/**
 * Created by msi on 4/22/2015.
 */
public class GameBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private Channel mChannel;
    private CreateGameLobby g;
    private findGamePage f;
    private PeerListListener p;

    GameBroadcastReceiver(WifiP2pManager manager, Channel channel, CreateGameLobby game, PeerListListener list){
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.g = game;
        this.p = list;
    }

    GameBroadcastReceiver(WifiP2pManager manager, Channel channel, findGamePage game,PeerListListener list){
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.f = game;
        this.p = list;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Log.v("Playdeck","Wifi is enabled\n");
            } else {
                // Wi-Fi P2P is not enabled
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            if (mManager != null) {
                Log.v("Playdeck","Request Peers called!\n");
                mManager.requestPeers(mChannel, this.p);
            }
            //Log.d(WiFiDirectActivity.TAG, "P2P peers changed");
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
        }
    }
}