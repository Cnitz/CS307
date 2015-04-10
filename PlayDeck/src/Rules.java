import java.util.ArrayList;
import java.util.Arrays;

public class Rules {
	
	private String trump;
	private boolean isTrump = false;
	private ArrayList<String> rank;
	private int hand_size;
	private int draw_amount;
	private int discard_amount;
	private boolean AceHigh = true;
	private int trump_offset;
	private int lead_suit_offset;
	private String lead_suit;
	private boolean isLead = false;
	
	
	Rules(Deck deck){		
		rank = new ArrayList<String>(Arrays.asList(deck.get_values()));
		set_trump_offset();
		set_lead_suit_offset();
	}
	
	public void set_hand_size(int size){
		this.hand_size = size;
	}
	
	public void set_draw_amount(int n){
		this.draw_amount = n;
	}
	
	public void set_discard_amount(int n){
		this.discard_amount = n;
	}
	
	public void set_ace_high(boolean ace_high){
		if(!rank.contains("Ace")) return;
		this.AceHigh = ace_high;
		if(ace_high){
			rank.remove("Ace");
			rank.add("Ace");
		}
		else{
			rank.remove("Ace");
			rank.add(0, "Ace");
		}
		
	}
	
	public void set_trump_offset(){
		this.trump_offset = 2*rank.size();
	}
	
	public void set_lead_suit_offset(){
		this.lead_suit_offset = rank.size();
	}
	
	public void set_trump(String trump){
		this.trump = trump;
		isTrump = true;
	}
	
	public void set_lead_suit(String suit){
		this.lead_suit = suit;
		this.isLead = true;
	}
	
	
	public String get_trump(){
		return trump != null ? trump : "No Trump";
	}
	
	public boolean isTrump(String type){
		if(get_trump().equals(type)) return true;
		return false;
	}
	
	public int get_rank(String value){
		return rank.indexOf(value);
	}
	
	public String get_lead_suit(){
		return lead_suit;
	}
	
	public boolean isLead(String suit){
		if(get_lead_suit().equals(suit)) return true;
		return false;
	}
	
	public int evaluate(Card c){
		int value = 0;	
		
		if(isLead && isLead(c.get_type())) value += lead_suit_offset;
		
		if(isTrump && isTrump(c.get_type())) value += trump_offset;
		value += get_rank(c.get_value());
		return value;
	}
	
	//compares only on trump and value
	Card compare(Card a, Card b){
		if(evaluate(a) > evaluate(b))  return a;
		else return b;
	}
	

}
