package nsu.fit.labusov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class BlackJackTest {

    @Test
    void blackjackTestPlayerLose() {
        BlackJack blackJack = new BlackJack(1, 1, false);
        Scanner scanner1 = new Scanner("1 1 1 1 1 1 1 1 1");

        boolean a = blackJack.startGame(scanner1);

        Assertions.assertFalse(a);
    }

    @Test
    void blackjackTestDealerLose() {
        BlackJack blackJack = new BlackJack(1, 1, true);
        Scanner scanner2 = new Scanner("0");

        boolean a = blackJack.startGame(scanner2);

        Assertions.assertTrue(a);
    }
}
