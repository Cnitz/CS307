package cs307.com.playdeck;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.LinearLayout;

public class Card {

    private String value;
    private String type;

    Card(String value, String type) {
        this.type = type;
        this.value = value;
    }


    //~~~~~testing function~~~~~~
    public String view_card() {
        if (value == null || type == null) {
            System.out.println("Card value does not exist. Sorry 'bout ya.");
            return null;
        }
        return value + " of " + type;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public String get_value() {
        if (value == null) {
            System.out.println("Card value does not exist. Sorry 'bout ya.");
            return null;
        }

        return value;
    }

    public String get_type() {
        if (type == null) {
            System.out.println("Card type does not exist. Sorry 'bout ya.");
            return null;
        }
        return type;
    }

    public String getImageName() {
        String img = new String();
        char t = type.charAt(0);
        try {
            int v = Integer.parseInt(value);
            img = "/drawable/" + t + Integer.toString(v);
        } catch (NumberFormatException e) {
            img = "/drawable/" + t + value.charAt(0);
        }
        img = img.toLowerCase();
        return img;
    }

    public Button createButton(ActionBarActivity screen)
    {
        Button b = new Button(screen);
        b.setBackground(Drawable.createFromPath(this.getImageName()));
        return b;
    }
	
}
