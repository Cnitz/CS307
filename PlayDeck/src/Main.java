import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, ImproperDeckFormatException {
		

		Deck d = new Deck("Basic Deck");
		d.print_suits_and_types();
		d.shuffle();
		for(int i = 0; i < 53; i++){
		System.out.println(d.draw().view_card());

		}
		
	}

}
