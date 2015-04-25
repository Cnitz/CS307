package cs307.com.playdeck;

import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;
import android.widget.TextView;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 4/25/2015.
 */
public class PeerLister implements WifiP2pManager.PeerListListener {
    @Override
    public void onPeersAvailable(WifiP2pDeviceList peerList) {
        if(peerList != null){
        }
    }

}
