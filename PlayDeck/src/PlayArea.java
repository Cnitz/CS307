import java.util.ArrayList;

public class PlayArea {
	Deck deck;
	Rules rules;
	ArrayList<Card> area_space;
	ArrayList<Player> players;
	
	PlayArea(Deck deck, Rules rules, ArrayList<Player> players){
		this.deck = deck;
		this.rules = rules;
		this.players = players;
	}
	
	public ArrayList<Card> view_play_area(){
		return area_space;
	}
	
	public void deal(Deck d, Player p){
		if(!d.isEmpty())
			p.add_card_to_hand(d.draw());
		else{
			System.out.println("Deck is Empty");
		}
	}
	
	
	public void deal(Deck d, Player p, int numCards){
		for(int i = 0; i < numCards; i++){
		if(!d.isEmpty())
			p.add_card_to_hand(d.draw());
		else{
			System.out.println("Deck is Empty");
		}
		}
	}
	
	public void deal_round_robin(Deck d, ArrayList<Player> ps, int numCards){
		for(int i = 0; i < numCards; i++){
			for(int j = 0; j < ps.size(); j++){
				deal(d, ps.get(j));
			}
		}
	}
	
	public void deal_person_by_person(Deck d, ArrayList<Player> ps, int numCards){
		for(int i = 0; i < ps.size(); i++){
			for(int j = 0; j < numCards; j++){
				deal(d, ps.get(i));
			}
		}
	}

}
