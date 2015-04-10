package cs307.com.playdeck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CreateGame extends ActionBarActivity {
    ArrayList<Card> cardSaved;

    public static final String[] types = {"Spades", "Hearts", "Diamonds", "Clubs"};
    public static final String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "Jack", "Queen", "King"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        Card[] cards = new Card[52];
        int i = 0;
        for (String type : types) {
            for (String value : values) {
                cards[i++] = (new Card(value, type));
            }
        }
        i = 0;
        cardSaved = new ArrayList<Card>();
        for(Card card : cards) {
            cardSaved.add(cards[i++]);
        }


        ListView lv = (ListView) findViewById(R.id.listView3);
        CardAdapter adapter = new CardAdapter(this, cards);
        lv.setAdapter(adapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_game, menu);
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


    public void goBack(View view) {
        Intent intent = new Intent(CreateGame.this, ChooseGame.class);
        startActivity(intent);
    }

    public void saveGame(View view) {
        CheckBox canStackPlayedCards = (CheckBox) findViewById(R.id.canStackPlayedCards);
        CheckBox rotateDealers = (CheckBox) findViewById(R.id.rotateDealers);
        CheckBox reshuffleAfterTurn = (CheckBox) findViewById(R.id.reshuffleAfterTurn);
        CheckBox mainPileFacingUp = (CheckBox) findViewById(R.id.mainPileFacingUp);
        EditText maxCardsHand = (EditText) findViewById(R.id.maxCardsHand);
        EditText gameName = (EditText) findViewById(R.id.game_name);
        Deck deck = new Deck(cardSaved);

        Rules rules = new Rules(canStackPlayedCards.isChecked(), rotateDealers.isChecked(), false,
                reshuffleAfterTurn.isChecked(), Integer.parseInt(maxCardsHand.getText().toString()),
                deck, mainPileFacingUp.isChecked(), false, false);
        rules.saveRules(this, gameName.getText().toString());
        goBack(view);

        /*     public Rules(boolean canStackPlayedCards, boolean rotateDealers, boolean discardPileExists,
                 boolean reshuffleAfterTurn, int maxCardsHand, boolean mainPileFacingUp,
                 boolean canTradeWithOthers, boolean userCardsVisible) { */

    }


    private class CardAdapter extends ArrayAdapter<Card> {
        Card[] cards = null;
        Context context;
        public CardAdapter(Context context, Card[] cards) {
            super(context,R.layout.row, cards);
            this.context = context;
            this.cards = cards;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.row, parent, false);
            TextView name = (TextView) convertView.findViewById(R.id.textView1);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
            name.setText(cards[position].view_card());
            if(cardSaved.contains(cards[position])) {
                cb.setChecked(true);
            }
            else {
                cb.setChecked(false);
            }

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        if(!cardSaved.contains(cards[position])) {
                            cardSaved.add(cards[position]);
     //                       Log.w("Verbose", cards[position].view_card() + " was added to deck, deck size is now " +
       //                     cardSaved.size());
                        }
                    }
                    else {
                        if(cardSaved.contains(cards[position])) {
                            cardSaved.remove(cards[position]);
      //                      Log.w("Verbose", cards[position].view_card() + " was removed from deck, deck size is now " +
        //                            cardSaved.size());
                        }
                    }
                }
            });

//                cb.setChecked(false);
            return convertView;

        }


    }
}