package cs307.com.playdeck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class gamePage extends ActionBarActivity {
    GameClient GClient;
    GameServer GServer;
    public static String PACKAGE_NAME;

    Deck mainDeck;
    ArrayList<Card> hand;
    ArrayList<Card> played;
    ImageButton deckbutton;
    ImageButton chipbutton;

    String game_name;
    Rules rules;
    CardAdapter ca;

    int pot;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mainDeck = new Deck();
        hand = new ArrayList<Card>();
        played = new ArrayList<Card>();
        //rules = Rules.getRules(gamePage.this, game_name);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        PACKAGE_NAME = getApplicationContext().getPackageName();
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

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardsInHand);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recList.setLayoutManager(llm);

        // TODO test card list
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card("10", "Clubs"));
        cards.add(new Card("9", "Clubs"));
        cards.add(new Card("8", "Clubs"));
        cards.add(new Card("7", "Clubs"));
        cards.add(new Card("6", "Clubs"));
        cards.add(new Card("5", "Clubs"));
        cards.add(new Card("4", "Clubs"));



        ca = new CardAdapter(hand);
        recList.setAdapter(ca);

        // TODO  these are the cards at the top left
        RecyclerView recList2 = (RecyclerView) findViewById(R.id.cardsInPlay);
        recList2.setHasFixedSize(true);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recList2.setLayoutManager(llm2);
        CardAdapter ca2 = new CardAdapter(played);
        recList2.setAdapter(ca2);


        deckbutton = (ImageButton)findViewById(R.id.game_deck);
        //Deck Button Context Menu
        deckbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popupMenu = new PopupMenu(gamePage.this, deckbutton);
                //Inflating the Popup using xml file
                popupMenu.getMenuInflater().inflate(R.menu.menu_deck, popupMenu.getMenu());
                //registering popup with OnMenuItemClickListener
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.shuffle)
                        {
                            mainDeck.shuffle();
                        }
                        if (item.getItemId() == R.id.deal)
                        {
                            ca.addCard(mainDeck.draw());
                        }
                        return true;
                    }
                });
                popupMenu.show();//showing popup menu
            }
        });

        chipbutton = (ImageButton)findViewById(R.id.game_chips);
        //Chip Button Context Menu
        chipbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu popupMenu = new PopupMenu(gamePage.this, chipbutton);
                popupMenu.getMenuInflater().inflate(R.menu.menu_chips, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        //Expand Bet Menu
                        if (item.getItemId() == R.id.addchip)
                        {
                            PopupMenu addMenu = new PopupMenu(gamePage.this, (ImageButton) findViewById(R.id.addchip));
                            addMenu.getMenuInflater().inflate(R.menu.menu_chips_bet, addMenu.getMenu());
                            addMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                public boolean onMenuItemClick(MenuItem item)
                                {
                                    if (item.getItemId() == R.id.bet_confirm)
                                    {
                                        EditText bamount = (EditText) findViewById(R.id.bet_amount);
                                        pot += Integer.parseInt(bamount.getText().toString());
                                    }
                                    return true;
                                }
                            });
                            addMenu.show();//showing popup menu
                        }
                        //End Bet Menu

                        //Expand Withdraw Menu
                        if (item.getItemId() == R.id.takechip)
                        {
                            PopupMenu addMenu = new PopupMenu(gamePage.this, (ImageButton) findViewById(R.id.addchip));
                            addMenu.getMenuInflater().inflate(R.menu.menu_chips_bet, addMenu.getMenu());
                            addMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                            {
                                public boolean onMenuItemClick(MenuItem item)
                                {
                                    if (item.getItemId() == R.id.bet_confirm)
                                    {
                                        EditText bamount = (EditText) findViewById(R.id.bet_amount);
                                        pot -= Integer.parseInt(bamount.getText().toString());
                                    }
                                    return true;
                                }
                            });
                            addMenu.show();//showing popup menu
                        }
                        return true;
                    }
                });
                popupMenu.show();//showing popup menu
            }
        });
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
        //LinearLayout played = (LinearLayout)findViewById(R.id.hand_layout);
        //played.addView(view);
    }
    public void add_to_playedview(View view)
    {
        //LinearLayout played = (LinearLayout)findViewById(R.id.played_layout);
        //played.addView(view);
    }



}
