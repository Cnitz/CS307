package cs307.com.playdeck;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by markpundmann on 4/10/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cards;

    public CardAdapter(List<Card> cards) {
        this.cards = cards;
    }


    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        Card card = cards.get(i);
        String type = card.get_type().substring(0, 1).toLowerCase();
        String tempValue = card.get_value();
        String value;
        if(tempValue.equals("Ace")) {
            value = "a";
        }
        else if(tempValue.equals("Jack")) {
            value = "j";
        }
        else if(tempValue.equals("Queen")) {
            value = "q";
        }
        else if(tempValue.equals("King")) {
            value = "k";
        }
        else {
            value = tempValue;
        }
        type = type.concat(value);
        // TODO fix cards being updated
        int resource = R.drawable.c10;
        int getResource = Resources.getSystem().getIdentifier(type, "drawable", gamePage.PACKAGE_NAME);
        cardViewHolder.card.setImageResource(
                Resources.getSystem().getIdentifier(type, "drawable", gamePage.PACKAGE_NAME));
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card, viewGroup, false);

        return new CardViewHolder(itemView);
    }






    public static class CardViewHolder extends RecyclerView.ViewHolder {

        protected ImageView card;

        public CardViewHolder(View v) {
            super(v);
            card =  (ImageView) v.findViewById(R.id.card_image);
        }
    }
}
