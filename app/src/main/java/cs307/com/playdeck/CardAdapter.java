package cs307.com.playdeck;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.lang.reflect.Field;
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
        int drawableId = 0;
        try {
            Class res = R.drawable.class;
            Field field = res.getField(type);
            drawableId = field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }

        cardViewHolder.card.setImageResource(drawableId);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card, viewGroup, false);

        return new CardViewHolder(itemView);
    }


    public void addCard(Card c)
    {
        cards.add(c);
    }



    public static class CardViewHolder extends RecyclerView.ViewHolder {

        protected ImageButton card;
        private boolean isClicked = false;

        public CardViewHolder(View v) {
            super(v);
            card =  (ImageButton) v.findViewById(R.id.card_image);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO add ability to place cards or remove

                }
            });
        }
    }
}
