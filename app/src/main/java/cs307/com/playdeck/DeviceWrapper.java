package cs307.com.playdeck;

import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by msi on 4/27/2015.
 */
public class DeviceWrapper implements Serializable {
    WifiP2pDevice device;
    String name;

    public DeviceWrapper(WifiP2pDevice d, String n){
        device = d;
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }

}
