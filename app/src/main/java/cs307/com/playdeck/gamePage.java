package cs307.com.playdeck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class gamePage extends ActionBarActivity {
    GameClient GClient;
    GameServer GServer;

    Deck mainDeck;
    ArrayList<Card> hand;
    ArrayList<Card> played;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        if (id == R.id.game_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void exitGame(View view){
        Intent intent = new Intent(gamePage.this, MainActivity.class);
        startActivity(intent);
    }

    public void menu_deck(View view)
    {

    }
    public void show_menu(View view)
    {

    }
    public void card_menu(View view)
    {

    }
    public void chips_menu(View view)
    {

    }
    public void sync_played()
    {
        for(Card c : played)
        {
            add_to_playedview(c.createButton(this));
        }
    }
    public void add_to_handview(View view)
    {
        LinearLayout played = (LinearLayout)findViewById(R.id.hand_layout);
        played.addView(view);
    }
    public void add_to_playedview(View view)
    {
        LinearLayout played = (LinearLayout)findViewById(R.id.played_layout);
        played.addView(view);
    }
}
