import java.util.ArrayList;

public class Player {

	private ArrayList<Card> hand;
	private String name;
	private int playerid = -1;
	
	Player(String name, int playerid){
		this.name = name;
		this.playerid = playerid;		
	}
	
	public void draw(){
		//TODO: implement		
	}
	
	public String get_name(){
		if(name != null) return name;
		return null;
	}
	
	public int get_id(){
		if(playerid < 0) return -1;
		return playerid;
	}
	
	public void add_card_to_hand(Card c){
		if ( c != null)
			hand.add(c);
		else 
			System.out.println("Trying to add card to hand, but the card don't exist. Whoops.");
			
	}
	
	public void set_name(String name){
		if (this.name != null || name == null) return;
		this.name = name;
	}
	
	public void set_id(int id){
		if(id < 0) return;
		this.playerid = id;
	}

}
