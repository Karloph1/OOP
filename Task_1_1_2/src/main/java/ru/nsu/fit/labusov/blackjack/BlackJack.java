package ru.nsu.fit.labusov.blackjack;

import java.util.ArrayList;
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

    private int calculateScore(ArrayList<Card> cardsHand) {
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

    private String printCards(ArrayList<Card> cardsHand) {
        StringBuilder string = new StringBuilder("[");

        int cardSum = calculateScore(cardsHand);

        for (Card card : cardsHand) {
            StringBuilder substring = new StringBuilder();

            switch (card.getSuit()) {
                case HEARTS:
                    substring.append("Червы");

                    break;
                case SPADES:
                    substring.append("Пики");

                    break;
                case CROSSES:
                    substring.append("Крести");

                    break;
                case DIAMONDS:
                    substring.append("Буби");

                    break;
                default:
                    break;
            }

            switch (card.getValue()) {
                case 0:
                    substring.insert(0, "Двойка ");
                    substring.append(" (2)");

                    break;
                case 1:
                    substring.insert(0, "Тройка ");
                    substring.append(" (3)");

                    break;
                case 2:
                    substring.insert(0, "Четверка ");
                    substring.append(" (4)");

                    break;
                case 3:
                    substring.insert(0, "Пятерка ");
                    substring.append(" (5)");

                    break;
                case 4:
                    substring.insert(0, "Шестерка ");
                    substring.append(" (6)");

                    break;
                case 5:
                    substring.insert(0, "Семерка ");
                    substring.append(" (7)");

                    break;
                case 6:
                    substring.insert(0, "Восьмерка ");
                    substring.append(" (8)");

                    break;
                case 7:
                    substring.insert(0, "Девятка ");
                    substring.append(" (9)");

                    break;
                case 8:
                    substring.insert(0, "Десятка ");
                    substring.append(" (10)");

                    break;
                case 9:
                    substring.delete(substring.length() - 2, substring.length());
                    substring.append("овый Валет (10)");

                    break;
                case 10:
                    substring.delete(substring.length() - 2, substring.length());
                    substring.append("овая Дама (10)");

                    break;
                case 11:
                    substring.delete(substring.length() - 2, substring.length());
                    substring.append("овый Король (10)");

                    break;
                case 12:
                    substring.insert(0, "Туз ");
                    substring.append(" (").append(card.getBlackJackValue()).append(")");

                    break;
                default:
                    break;
            }

            string.append(substring).append(", ");
        }

        string.delete(string.length() - 2, string.length());
        string.append("] => ").append(cardSum);

        return String.valueOf(string);
    }

    private String showCard(ArrayList<Card> cardsHand) {
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