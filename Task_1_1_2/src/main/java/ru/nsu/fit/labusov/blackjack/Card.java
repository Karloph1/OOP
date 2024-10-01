package ru.nsu.fit.labusov.blackjack;

import java.util.HashMap;
import java.util.Map;

/**
 * Single card class.
 */
public class Card {
    private final CardSuit suit;
    private final int value;
    private int blackJackValue;

    private static final Map<Integer, String> valueNames;

    static {
        valueNames = new HashMap<>();
        valueNames.put(2, "Двойка");
        valueNames.put(3, "Тройка");
        valueNames.put(4, "Четверка");
        valueNames.put(5, "Пятерка");
        valueNames.put(6, "Шестерка");
        valueNames.put(7, "Семерка");
        valueNames.put(8, "Восьмерка");
        valueNames.put(9, "Девятка");
        valueNames.put(10, "Десятка");
        valueNames.put(11, "Валет");
        valueNames.put(12, "Дама");
        valueNames.put(13, "Король");
        valueNames.put(14, "Туз");
    }

    /**
     * Constructor single card method.
     */
    public Card(CardSuit numberSuit, int value, int blackJackValue) {
        suit = numberSuit;
        this.value = value;
        this.blackJackValue = blackJackValue;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    /**
     * Get BlackJack value method.
     */
    public int getBlackJackValue() {
        int cardWeight = value;

        if (value == 14) {
            cardWeight = blackJackValue;
        } else if (value > 10) {
            cardWeight = 10;
        }

        return cardWeight;
    }


    public void setBlackJackValue(int value) {
        blackJackValue = value;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String suitName = getSuit().toString(value);
        String cardName = valueNames.get(value);

        if (value <= 10 || value == 14) {
            str.append(cardName);
            str.append(" ");
            str.append(suitName);
            str.append(" (").append(getBlackJackValue()).append(")");
        } else {
            str.append(suitName);
            str.append(" ");
            str.append(cardName);
            str.append(" (").append(getBlackJackValue()).append(")");
        }

        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Card card = (Card) obj;

        return this.suit == card.suit && this.value == card.value
                && this.blackJackValue == card.blackJackValue;
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int hash = 1;
        hash = prime * hash + Integer.hashCode(suit.ordinal());
        hash = prime * hash + Integer.hashCode(value);
        hash = prime * hash + Integer.hashCode(blackJackValue);

        return hash;
    }
}
