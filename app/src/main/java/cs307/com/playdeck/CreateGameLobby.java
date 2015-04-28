package cs307.com.playdeck;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class CreateGameLobby extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game_lobby);
        Bundle extras = getIntent().getExtras();
        String game_name = extras.getString("game_name");


        TextView textView = new TextView(this);

        textView = (TextView)findViewById(R.id.game_name);
        textView.setText(game_name);
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
        intent.putExtra("game_name", ((TextView)findViewById(R.id.game_name)).getText().toString());
        startActivity(intent);
        /*     TODO   Use to send game name to start game, and then get the rules from the game name
        TextView textView = new TextView(this);
        textView = (TextView)findViewById(R.id.game_name);
                intent.putExtra("game_name", textView.getText());
         */
    }


}
