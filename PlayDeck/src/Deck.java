import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Deck {
	
	private String[] types;
	private String[] values;
			
	private ArrayList<Card> cards;
	private int num_cards;
	
	Deck(String file) throws FileNotFoundException, ImproperDeckFormatException{
		File f = new File(file);
		load_deck(f);
		build_deck();
	}
	
	public Card draw(){
		if(num_cards <= 0 || cards == null) return null;
		Card ret = cards.get(0);
		cards.add(cards.remove(0));
		num_cards--;
		return ret;
	}
	
	
	public Card[] draw(int num_of_cards){
		//TODO: Decide whether to return null if < num_of_cards or return an incomplete array
		//		e.g. draw(5) with num_cards = 4. Returns an array of 4 cards or null. 
		return null;
	}
	
	public void shuffle(){
		//TODO: Should this be modified to deal with decks that have had cards drawn from them. 
		//		Meaning you call shuffle after someone has drawn one or many cards.
		Collections.shuffle(cards);
		
	}
	
	/*
	 * This function takes in a file descriptor where deck information is located.
	 * The file should be a csv or Comma Separated Values where the values are on the first line
	 * and the types are on the second line. e.g 
	 * ----------------
	 * "1,2,3,4"
	 * "Red,Blue,Green"
	 * -----------------
	 *  Then stores the read in values and types into the global arrays for the deck 
	 *  Each array can only be initialized once.
	 */
	public void load_deck(File file) throws FileNotFoundException, ImproperDeckFormatException{
		Scanner s = new Scanner(file);
		String value_list;
		String type_list;
		if(s.hasNextLine())
			value_list = s.nextLine();
		else{
			s.close();
			throw new ImproperDeckFormatException("Deck File is in incorrect format");
		}
		if(s.hasNextLine())
			type_list = s.nextLine();
		else{
			s.close();
			throw new ImproperDeckFormatException("Deck File is in incorrect format");
		}		
		s.close();
		if(types == null)
			this.types = type_list.split(",");
		if(values == null)
			this.values = value_list.split(",");
		return;
		
	}
	
	public void add_card_to_deck(){
		//TODO: decide whether to use arrays or Linked Lists
	}
	
	public void build_deck(){
		if(types == null || values == null){
			System.out.println("There is not enough information to build a deck.\n"
					+ "Please check your types and values");
			return;
		}
		if(cards == null){
			cards = new ArrayList<Card>();
			 for(int i = 0; i < types.length; i++){
				 for(int k = 0; k < values.length; k++){
					 cards.add(new Card(values[k], types[i]));
				 }
			 }
		}
		
			
		
	}
	
	/*
	 * Prints out a list of the types and values for testing purposes
	 */
	public void print_suits_and_types(){
		System.out.printf("Types: ");
		for(int i = 0; i < types.length; i++){
			System.out.printf("%s", types[i]);
			if(i == types.length -1) break;
			System.out.printf(" : ");
		}
		System.out.println();
		System.out.printf("Values: ");
		for(int i = 0; i < values.length; i++){
			System.out.printf("%s", values[i]);
			if(i == values.length -1) break;
			System.out.printf(" : ");
		}
		System.out.println();
		this.num_cards = values.length * types.length;
	}
}
