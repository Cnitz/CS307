package cs307.com.playdeck;

import java.util.ArrayList;

public class PlayArea {
	ArrayList<Card> deck;
	ArrayList<Card> discard;
    ArrayList<Card> played;
	ArrayList<Player> players;
	
	PlayArea(Deck deck, ArrayList<Player> players){
		
	}

    public ArrayList<Card> get_played()
    {
        if (played == null)
            played = new ArrayList<Card>();
        return played;
    }

}
