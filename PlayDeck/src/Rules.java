import java.util.ArrayList;

public class Rules {
	
	private String trump;
	private boolean isTrump = false;
	private ArrayList<String> rank;
	private int hand_size;
	private int draw_amount;
	private int discard_amount;
	private boolean AceHigh = true;
	private int trump_offset;
	
	
	public void set_trump(String trump){
		this.trump = trump;
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
	
	public int evaluate(Card c){
		int value = 0;
		
		if(isTrump && isTrump(c.get_type())) value =+ trump_offset;
		value =+ get_rank(c.get_value());
		return value;
	}
	
	//compares only on trump and value
	Card compare(Card a, Card b){
		if(evaluate(a) > evaluate(b))  return a;
		else return b;
	}
	
	

}
