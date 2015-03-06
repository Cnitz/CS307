package cs307.com.playdeck;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class gamePage extends ActionBarActivity {
    GameClient GClient;
    GameServer GServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        boolean isHost = getIntent().getBooleanExtra("isHost",false);
        if(isHost){
            GClient = new GameClient(true);
            try {
                GServer = new GameServer(true,GClient);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_page, menu);
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
    public void exitGame(View view){
        Intent intent = new Intent(gamePage.this, MainActivity.class);
        startActivity(intent);
    }
}
