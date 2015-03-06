package cs307.com.playdeck;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import java.net.InetAddress;

public class findGamePage extends ActionBarActivity {

     GameClient GClient;
    static int udpPort = 27960;
    static int tcpPort = 27960;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game_page);
        GClient = new GameClient(false);

        new Thread(new Runnable() {
            public void run() {
                InetAddress address = GClient.getClient().discoverHost(udpPort, 5000);
                System.out.println(address);
            }
        }).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_game_page, menu);
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
        Intent intent = new Intent(findGamePage.this, MainActivity.class);
        startActivity(intent);
    }
    public void joinGame(View view){
        Intent intent = new Intent(findGamePage.this, gamePage.class);
        startActivity(intent);
    }

}
