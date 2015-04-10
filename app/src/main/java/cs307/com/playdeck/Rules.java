package cs307.com.playdeck;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by markpundmann on 2/16/15.
 */
public class Rules {

    private boolean canStackPlayedCards;
    private boolean rotateDealers;
    private boolean discardPileExists;
    private boolean reshuffleAfterTurn;
    private int maxCardsHand;
    private Deck deck;
    private boolean mainPileFacingUp;
    private boolean canTradeWithOthers;
    private boolean userCardsVisible;

    public Rules(boolean canStackPlayedCards, boolean rotateDealers, boolean discardPileExists,
                 boolean reshuffleAfterTurn, int maxCardsHand, Deck deck, boolean mainPileFacingUp,
                 boolean canTradeWithOthers, boolean userCardsVisible) {
        this.canStackPlayedCards = canStackPlayedCards;
        this.rotateDealers = rotateDealers;
        this.discardPileExists = discardPileExists;
        this.reshuffleAfterTurn = reshuffleAfterTurn;
        this.maxCardsHand = maxCardsHand;
        this.deck = deck;
        this.mainPileFacingUp = mainPileFacingUp;
        this.canTradeWithOthers = canTradeWithOthers;
        this.userCardsVisible = userCardsVisible;
    }

    public boolean isUserCardsVisible() {
        return userCardsVisible;
    }

    public boolean isCanStackPlayedCards() {
        return canStackPlayedCards;
    }

    public boolean isRotateDealers() {
        return rotateDealers;
    }

    public boolean isDiscardPileExists() {
        return discardPileExists;
    }

    public boolean isReshuffleAfterTurn() {
        return reshuffleAfterTurn;
    }

    public int getMaxCardsHand() {
        return maxCardsHand;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isMainPileFacingUp() {
        return mainPileFacingUp;
    }

    public boolean isCanTradeWithOthers() {
        return canTradeWithOthers;
    }


    public void saveRules(Context context, String gameName) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(gameName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String[] getListGames(Context context) {
        return context.fileList();
    }

    public static Rules getRules(Context context, String gameName) {
        Rules rules = null;
        try {
            FileInputStream fileInputStream = context.openFileInput(gameName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            rules = (Rules) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rules;
    }
}
