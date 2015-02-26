import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, ImproperDeckFormatException {
		Card c = new Card("Ace", "Spades");
		System.out.println(c.view_card());
		System.out.println(c.get_value());

		Deck d = new Deck("Basic Deck");
		d.print_suits_and_types();
		
	}

}
