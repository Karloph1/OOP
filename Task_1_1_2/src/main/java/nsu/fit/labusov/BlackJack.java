package nsu.fit.labusov;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    private int round;
    private boolean isGameOver;
    private int playerScore;
    private int dealerScore;

    public BlackJack() {
        round = 1;
        isGameOver = false;
        playerScore = 0;
        dealerScore = 0;
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

    public void startGame(int decksAmount) {
        Dealer dealer = new Dealer(decksAmount);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в Блэкджек!");

        while (playerScore < 5 || dealerScore < 5) {
            System.out.println("Раунд " + round + "\nДилер раздал карты");

            dealer.drawCard(true);
            dealer.drawCard(true);
            dealer.drawCard(false);
            dealer.drawCard(false);

            System.out.println("    Ваши карты: " + printCards(dealer.getPlayerCards()));

            StringBuilder string = new StringBuilder(printCards(dealer.getDealerCards()));
            int index = string.indexOf(",");
            string.delete(index + 2, string.length());
            string.append("<закрытая карта>]");

            System.out.println("    Карты дилера:" + string);
            boolean isPlayerTurn = true;

            if (calculateScore(dealer.getPlayerCards()) == 21 || calculateScore(dealer.getDealerCards()) == 21) {
                isGameOver = true;
            }

            while (!isGameOver) {
                System.out.println("\nВаш ход\n------");

                while (!isGameOver && isPlayerTurn) {
                    System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
                    int commandNumber = scanner.nextInt();

                    if (commandNumber == 1) {
                        dealer.drawCard(true);

                        String drawnCard = showCard(dealer.getPlayerCards());

                        System.out.println("Вы открыли карту " + drawnCard);
                        System.out.println("    Ваши карты: " + printCards(dealer.getPlayerCards()));
                        System.out.println("    Карты дилера:" + string);

                        if (calculateScore(dealer.getPlayerCards()) > 21) {
                            isGameOver = true;
                        }
                    } else if (commandNumber == 0) {
                        isPlayerTurn = false;
                    }
                }

                if (!isGameOver) {
                    String hiddenCard = showCard(dealer.getDealerCards());

                    System.out.println("\nХод дилера\n------\nДилер открывает закрытую карту " + hiddenCard);
                    System.out.println("    Ваши карты: " + printCards(dealer.getPlayerCards()));
                    System.out.println("    Карты дилера:" + printCards(dealer.getDealerCards()));
                }

                while (calculateScore(dealer.getDealerCards()) < 18 && !isGameOver) {
                    dealer.drawCard(false);

                    String drawnCard = showCard(dealer.getDealerCards());

                    System.out.println("Дилер открывает карту " + drawnCard);
                    System.out.println("    Ваши карты: " + printCards(dealer.getPlayerCards()));
                    System.out.println("    Карты дилера:" + printCards(dealer.getDealerCards()));

                    if (calculateScore(dealer.getDealerCards()) > 21) {
                        isGameOver = true;
                    }
                }

                isGameOver = true;
            }

            if (calculateScore(dealer.getPlayerCards()) > 21 ||
                    (calculateScore(dealer.getPlayerCards()) < calculateScore(dealer.getDealerCards())
                            && calculateScore(dealer.getDealerCards()) <= 21)) {
                dealerScore++;

                System.out.print("\nВы проиграли раунд! Счет " + playerScore + ":" + dealerScore);
            } else {
                playerScore++;

                System.out.print("\nВы выиграли раунд! Счет " + playerScore + ":" + dealerScore);
            }

            if (playerScore > dealerScore) {
                System.out.println(" в вашу пользу\n");
            } else if (dealerScore > playerScore) {
                System.out.println(" в пользу дилера\n");
            } else {
                System.out.println("\n");
            }

            dealer.makeNewRound();
            isGameOver = false;
            round++;
        }
    }
}