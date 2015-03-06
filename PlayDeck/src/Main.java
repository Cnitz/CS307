import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, ImproperDeckFormatException {
		

		Deck d = new Deck("Basic Deck");
		d.print_suits_and_types();
		d.shuffle();
	
		d.factory_reset();
		for(int i = 0; i < 52; i++){
			if(d.curr_size() != 0)
			d.draw(578);
		}
		
	}

}
