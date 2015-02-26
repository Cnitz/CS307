import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Deck {
	
	private String[] types;
	private String[] values;
			
	private ArrayList<Card> cards = new ArrayList<Card>();
	private int num_cards;
	
	Deck(String file) throws FileNotFoundException, ImproperDeckFormatException{
		File f = new File(file);
		load_deck(f);
	}
	
	public void shuffle(){
		
		
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
		for(int i = 0; i < types.length; i++){
			for(int k = 0; k < values.length; k++){
				cards.add(new Card(values[k], types[i]));
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
	}
}
