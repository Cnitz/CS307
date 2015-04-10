import java.io.FileNotFoundException;




public class Main {

	public static void main(String[] args) throws FileNotFoundException, ImproperDeckFormatException {
		

		Deck d = new Deck("Basic Deck");
		//d.print_suits_and_types();
		d.shuffle();
		
		Rules r  = new Rules(d);
		
		Card a = new Card("Ace", "Spades");
		Card b = new Card("King", "Diamonds");
		
		//r.set_trump("Spades");
		r.set_ace_high(true);
		
		Card c =r.compare(a, b);
		
		System.out.println(a.view_card());
		System.out.println(b.view_card());
		System.out.println(c.view_card());
		System.out.println("~~~~~~~~~~~~");
		
		r.set_ace_high(false);
		c =r.compare(a, b);
	
		System.out.println(a.view_card());
		System.out.println(b.view_card());
		System.out.println(c.view_card());
		
			
	}
	
	

}
