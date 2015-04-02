package cs307.com.playdeck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Deck {
	
	private String[] types;
	private String[] values;
			
	private ArrayList<Card> cards;
	private ArrayList<Card> removed;
	
	/*
	 * Basic constructor that takes in the string of a file path leading to the deck file.
	 * Parses the information from the file and stores it in the deck class and 
	 * builds the deck based on that information.
	 */
	Deck(String file) throws FileNotFoundException, ImproperDeckFormatException{
		File f = new File(file);
		load_deck(f);
		build_deck();
		removed = new ArrayList<Card>();
	}
	
	//TODO: devise a system for when someone draws a card from a deck that has no cards in it.
	public Card draw(){
		if(curr_size() <= 0 || cards == null) return null;
		Card ret = cards.get(0);
		removed.add(cards.remove(0));
		return ret;
	}
		
	public Card[] draw(int num_of_cards){
		Card[] c = new Card[num_of_cards];
		for(int i = 0; i < num_of_cards; i++){
			if(curr_size() <= 0) break;
			c[i] = draw();
		}
		return c;
	}
	
	public void shuffle(){
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
		
	/*
	 * Builds a deck from the information stored in types and values. 
	 * It assign each type to each value one. 
	 * 	- e.g. if there are 4 types and 13 values there will be 52 cards and each value will have 4 different 
	 * 	 types and each type will have 13 values.
	 * Can only build a deck once per deck.
	 * Sets the num_cards (number of cards in the Deck) to types*values.
	 */
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
	
	public int curr_size(){
		if(cards == null) return -1;
		return cards.size();
	}
	
	/*
	 * Resets the deck to a "pre-drawn" state
	 * Restores all cards in removed to cards and shuffles the deck.
	 */
	public void reset(){
		while (!removed.isEmpty()){
			cards.add(removed.remove(0));
		}
		shuffle();
	}
	
	/*
	 * Resets the deck to its initial "new-deck" order
	 */
	public void factory_reset(){
		cards.clear();
		removed.clear();
		 for(int i = 0; i < types.length; i++){
			 for(int k = 0; k < values.length; k++){
				 cards.add(new Card(values[k], types[i]));
			 }
		 }
	}
	
	public void add_card_to_deck(){
		//TODO: implement or decide to implement
	}

	
	//~~~~~~~~~~~~~~~~~~~~Testing Functions~~~~~~~~~~~~~~~~~~~~~~~~~~
	
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
	}
	
	//~~~~~~~~~~~~~~~~End Testing Functions~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
}
