package cs307.com.playdeck;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CreateGameLobby extends ActionBarActivity {

    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    GameBroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;
    private List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    private List<DeviceWrapper> wrappers = new ArrayList<DeviceWrapper>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game_lobby);
        Bundle extras = getIntent().getExtras();
        String game_name = extras.getString("game_name");


        TextView textView = new TextView(this);

        textView = (TextView)findViewById(R.id.game_name);
        textView.setText(game_name);
        Intent i = getIntent();
        int b = i.getIntExtra("isHost",0);
        if(b < 0){
            DeviceWrapper hostDeviceWrapper =(DeviceWrapper) i.getSerializableExtra("HostData");
            WifiP2pDevice host = hostDeviceWrapper.device;

            mIntentFilter = new IntentFilter();
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

            mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
            mChannel = mManager.initialize(this, getMainLooper(), null);

            final ListView listview = (ListView) findViewById(R.id.listView4);
            final ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, wrappers);
            listview.setAdapter(adapter);
            wrappers.add(hostDeviceWrapper);
        }
        else {

            mIntentFilter = new IntentFilter();
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

            mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
            mChannel = mManager.initialize(this, getMainLooper(), null);

            final ListView listview = (ListView) findViewById(R.id.listView4);
            final ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, wrappers);
            listview.setAdapter(adapter);
            WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
                @Override
                public void onPeersAvailable(WifiP2pDeviceList peerList) {

                    // Out with the old, in with the new.
                    peers.clear();
                    peers.addAll(peerList.getDeviceList());
                    wrappers.clear();
                    for(WifiP2pDevice dev : peers){
                        wrappers.add(new DeviceWrapper(dev,dev.deviceName));

                    }
                    // If an AdapterView is backed by this data, notify it
                    // of the change.  For instance, if you have a ListView of available
                    // peers, trigger an update.
                    adapter.notifyDataSetChanged();
              /*  if (peers.size() == 0) {
                    //Log.d(WiFiDirectActivity.TAG, "No devices found");
                    return;
                }*/
                }
            };
            Log.v("Playdeck", "Right before CGL GBR call\n");
            mReceiver = new GameBroadcastReceiver(mManager, mChannel, this, peerListListener);

            mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    // Code for when the discovery initiation is successful goes here.
                    // No services have actually been discovered yet, so this method
                    // can often be left blank.  Code for peer discovery goes in the
                    // onReceive method, detailed below.
                }

                @Override
                public void onFailure(int reasonCode) {
                    // Code for when the discovery initiation fails goes here.
                    // Alert the user that something went wrong.
                }
            });
            Log.v("Playdeck", "After CGL discover peers call\n");

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_game_lobby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goBack(View view){
        Intent intent = new Intent(CreateGameLobby.this, ChooseGame.class);
        startActivity(intent);
    }

    public void startGame(View view){
        Intent intent = new Intent(CreateGameLobby.this, gamePage.class);
        startActivity(intent);
        /*     TODO   Use to send game name to start game, and then get the rules from the game name
        TextView textView = new TextView(this);
        textView = (TextView)findViewById(R.id.game_name);
                intent.putExtra("game_name", textView.getText());
         */
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

}
