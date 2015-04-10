import java.io.FileNotFoundException;
import java.util.ArrayList;




public class Main {

	public static void main(String[] args) throws FileNotFoundException, ImproperDeckFormatException {
		
		
		ArrayList<Player> ps = new ArrayList<Player>();

		Deck d = new Deck("Basic Deck");
		//d.print_suits_and_types();
		//d.shuffle();
		
		Rules r  = new Rules(d);
		
		Card a = new Card("Ace", "Spades");
		Card b = new Card("King", "Diamonds");
		
		r.set_trump("Spades");
		r.set_ace_high(true);
		r.set_lead_suit("Diamonds");
		Card c =r.compare(a, b);
		/*
		System.out.println(a.view_card());
		System.out.println(b.view_card());
		System.out.println(c.view_card());
		System.out.println("~~~~~~~~~~~~");
		*/
		r.set_ace_high(false);
		c =r.compare(a, b);
	
		/*
		System.out.println(a.view_card());
		System.out.println(b.view_card());
		System.out.println(c.view_card());
		*/
		
		Player p1 = new Player("Jim", 1);
		Player p2 = new Player("Tim", 2);
		Player p3 = new Player("Kim", 3);
		
		ps.add(p1);
		ps.add(p2);
		ps.add(p3);
		
		PlayArea pa = new PlayArea(d, r, ps);
		
		pa.deal_person_by_person(d, ps, 5);
		p1.view_hand();
		p2.view_hand();
		p3.view_hand();
		
		
			
	}
	
	

}
