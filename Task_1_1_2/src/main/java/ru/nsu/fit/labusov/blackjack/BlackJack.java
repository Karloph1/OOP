package ru.nsu.fit.labusov.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * BlackJack class.
 */
public class BlackJack {
    private int currentRound;
    private final int maxRounds;
    private final int decksAmount;
    private boolean isGameOver;
    private int playerScore;
    private int dealerScore;
    private final boolean shouldDealerLose;

    /**
     * Constructor BlackJack method.
     */
    public BlackJack(int maxRounds, int decksAmount, boolean shouldDealerLose) {
        currentRound = 1;
        isGameOver = false;
        playerScore = 0;
        dealerScore = 0;
        this.maxRounds = maxRounds;
        this.decksAmount = decksAmount;
        this.shouldDealerLose = shouldDealerLose;
    }

    private int calculateScore(List<Card> cardsHand) {
        int score = 0;
        int aceCount = 0;

        for (Card card : cardsHand) {
            score += card.getBlackJackValue();

            if (card.getBlackJackValue() == 11) {
                aceCount++;
            }
        }

        while (score > 21 && aceCount != 0) {
            score -= 10;

            for (Card card : cardsHand) {
                if (card.getBlackJackValue() == 11) {
                    card.setBlackJackValue(1);
                }
            }

            aceCount--;
        }

        return score;
    }

    private String printCards(List<Card> cardsHand) {
        StringBuilder string = new StringBuilder("[");

        int cardSum = calculateScore(cardsHand);

        for (Card card : cardsHand) {
            string.append(card).append(", ");
        }

        string.delete(string.length() - 2, string.length());
        string.append("] => ").append(cardSum);

        return String.valueOf(string);
    }

    private String showCard(List<Card> cardsHand) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(cardsHand.get(cardsHand.size() - 1));
        calculateScore(cardsHand);

        StringBuilder str = new StringBuilder(printCards(cards));
        str.delete(0, 1);

        int index = str.indexOf("]");
        str.delete(index, str.length());

        return String.valueOf(str);
    }

    /**
     * Initialising game method.
     */
    public boolean startGame(Scanner scanner) {
        Dealer dealer = new Dealer(decksAmount);

        System.out.println("Welcome to BlackJack!");

        while (playerScore < maxRounds && dealerScore < maxRounds) {
            System.out.println("Round " + currentRound + "\nDealer deals out cards");

            dealer.drawCard(true);
            dealer.drawCard(true);
            dealer.drawCard(false);
            dealer.drawCard(false);

            System.out.println("    Your cards: " + printCards(dealer.getPlayerCards()));

            StringBuilder string = new StringBuilder(printCards(dealer.getDealerCards()));
            int index = string.indexOf(",");
            string.delete(index + 2, string.length());
            string.append("<close card>]");

            System.out.println("    Dealer cards:" + string);
            boolean isPlayerTurn = true;

            if (calculateScore(dealer.getPlayerCards()) == 21
                    || calculateScore(dealer.getDealerCards()) == 21) {
                isGameOver = true;
            }

            while (!isGameOver) {
                System.out.println("\nYour turn\n------");

                while (!isGameOver && isPlayerTurn) {

                    System.out.println("Insert '1' to take a card,"
                            + " and '0' to pass...");
                    int commandNumber = scanner.nextInt();

                    System.out.println(commandNumber);

                    if (commandNumber == 1) {
                        dealer.drawCard(true);

                        String drawnCard = showCard(dealer.getPlayerCards());

                        System.out.println("You get card " + drawnCard);
                        System.out.println("    Your cards: "
                                + printCards(dealer.getPlayerCards()));
                        System.out.println("    Dealer cards:" + string);

                        if (calculateScore(dealer.getPlayerCards()) > 21) {
                            isGameOver = true;
                        }
                    } else if (commandNumber == 0) {
                        isPlayerTurn = false;
                    }
                }

                if (!isGameOver) {
                    String hiddenCard = showCard(dealer.getDealerCards());

                    System.out.println("\nDealer turn\n------\nDealer opens closed card "
                            + hiddenCard);
                    System.out.println("    Your cards: " + printCards(dealer.getPlayerCards()));
                    System.out.println("    Dealer cards:" + printCards(dealer.getDealerCards()));
                }

                while ((calculateScore(dealer.getDealerCards()) < 18 || shouldDealerLose)
                        && !isGameOver) {
                    dealer.drawCard(false);

                    String drawnCard = showCard(dealer.getDealerCards());

                    System.out.println("Dealer opens a card " + drawnCard);
                    System.out.println("    Your cards: " + printCards(dealer.getPlayerCards()));
                    System.out.println("    Dealer cards:" + printCards(dealer.getDealerCards()));

                    if (calculateScore(dealer.getDealerCards()) > 21) {
                        isGameOver = true;
                    }
                }

                isGameOver = true;
            }

            if (calculateScore(dealer.getPlayerCards()) > 21
                    || (calculateScore(dealer.getPlayerCards())
                    < calculateScore(dealer.getDealerCards())
                    && calculateScore(dealer.getDealerCards()) <= 21)) {
                dealerScore++;

                System.out.print("\nYou lose a round! Score " + playerScore + ":" + dealerScore);
            } else {
                playerScore++;

                System.out.print("\nYou win a round! Score " + playerScore + ":" + dealerScore);
            }

            if (playerScore > dealerScore) {
                System.out.println(" in your favor\n");
            } else if (dealerScore > playerScore) {
                System.out.println(" in dealer's favor\n");
            } else {
                System.out.println("\n");
            }

            dealer.makeNewRound();
            isGameOver = false;
            currentRound++;
        }

        return playerScore > dealerScore;
    }
}