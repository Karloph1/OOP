package nsu.fit.labusov;

import java.util.ArrayList;
import java.util.Random;

/**
 * Dealer class.
 */
public class Dealer {
    private ArrayList<CardsDeck> decks;
    private final ArrayList<Card> playerCards;
    private final ArrayList<Card> dealerCards;

    /**
     * Constructor dealer method.
     */
    public Dealer(int decksAmount) {
        decks = new ArrayList<>(decksAmount);
        playerCards = new ArrayList<>();
        dealerCards = new ArrayList<>();

        for (int i = 0; i < decksAmount; i++) {
            decks.add(new CardsDeck());
        }
    }

    /**
     * Draw card method.
     */
    public void drawCard(boolean isPlayerTurn) {
        Random random = new Random();

        int chosenDeck = random.nextInt(decks.size());
        int chosenCard = random.nextInt(decks.get(chosenDeck).getDeckLength());

        Card drownCard = decks.get(chosenDeck).getCard(chosenCard);

        if (isPlayerTurn) {
            playerCards.add(drownCard);
        } else {
            dealerCards.add(drownCard);
        }
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public ArrayList<Card> getDealerCards() {
        return dealerCards;
    }

    /**
     * Start new round method.
     */
    public void makeNewRound() {
        playerCards.clear();
        dealerCards.clear();

        int deckAmount = decks.size();

        decks = new ArrayList<>(deckAmount);

        for (int i = 0; i < deckAmount; i++) {
            decks.add(new CardsDeck());
        }
    }
}
