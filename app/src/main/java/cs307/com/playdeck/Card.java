package cs307.com.playdeck;

public class Card {

	private String value;
	private String type;
	
	Card(String value, String type){
		this.type = type;
		this.value = value;
	}
	
	
	//~~~~~testing function~~~~~~
	public String view_card(){
		if(value == null || type == null){
			System.out.println("Card value does not exist. Sorry 'bout ya.");
			return null;
	}
		return value+" of "+type;
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public String get_value(){
		if(value == null){
				System.out.println("Card value does not exist. Sorry 'bout ya.");
				return null;
		}
		
		return value;
	}

	public String get_type(){
		if(type == null){
			System.out.println("Card type does not exist. Sorry 'bout ya.");
			return null;
		}
		return type;
	}
	
	
}
