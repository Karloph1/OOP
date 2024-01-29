import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.task.Polynomial;

public class PolynomialTest {
    @Test
    void simplePlusCheck() {
        Polynomial test1 = new Polynomial(new int[]{1, 2, 3, 4});
        Polynomial test2 = new Polynomial(new int[]{1, 4, 1, 0});
        Assertions.assertEquals("2x^3 + 6x^2 + 4x + 4", test1.plus(test2).toString());
    }

    @Test
    void simpleMinusCheck() {
        Polynomial test1 = new Polynomial(new int[]{1, 2, 3, 4});
        Polynomial test2 = new Polynomial(new int[]{2, 4, 1, 0});
        Assertions.assertEquals("-x^3 - 2x^2 + 2x + 4", test1.minus(test2).toString());
    }

    @Test
    void simpleTimesCheck() {
        Polynomial test1 = new Polynomial(new int[]{2, 3, 4});
        Polynomial test2 = new Polynomial(new int[]{4, 1, 2});
        Assertions.assertEquals("8x^4 + 14x^3 + 23x^2 + 10x + 8", test1.times(test2).toString());
    }

    @Test
    void simpleEvaluateCheck() {
        Polynomial test = new Polynomial(new int[]{4, 2, 3, 4});
        Assertions.assertEquals(4234, test.evaluate(10));
    }

    @Test
    void simpleDifferentiateCheck() {
        Polynomial test = new Polynomial(new int[]{4, 2, 3, 4});
        Assertions.assertEquals("24x + 4", test.differentiate(2).toString());
    }

    @Test
    void simpleCompareCheck() {
        Polynomial test1 = new Polynomial(new int[]{2, 3, 4});
        Polynomial test2 = new Polynomial(new int[]{4, 1, 2});
        Assertions.assertFalse(test1.compare(test2));
    }

    @Test
    void everythingEvaluateCheck() {
        Polynomial test1 = new Polynomial(new int[]{5, 6, 7, 2, 3, 4, 0});
        Polynomial test2 = new Polynomial(new int[]{4, 0, 2});
        Polynomial test3 = new Polynomial(new int[]{1, 2, 3, 4});
        Polynomial test4 = new Polynomial(new int[]{5, 6, 1, -8});

        Assertions.assertEquals(15412278, test1.plus(test2).minus(test3).times(test4).differentiate(3).evaluate(3));
    }

    @Test
    void everythingToStringCheck() {
        Polynomial test1 = new Polynomial(new int[]{8, 21});
        Polynomial test2 = new Polynomial(new int[]{-1, 2, -40, 0, 0, 0});
        Polynomial test3 = new Polynomial(new int[]{-10, 102, 20, 0, -1});
        Polynomial test4 = new Polynomial(new int[]{4, 2, 0, -1});

        Assertions.assertEquals("-6720x^4 + 38640x^3 - 195840x^2 - 43560x - 480", test1.plus(test2).minus(test3).times(test4).differentiate(4).toString());
    }

    @Test
    void everythingCompareCheck() {
        Polynomial test1 = new Polynomial(new int[]{8, 21});
        Polynomial test2 = new Polynomial(new int[]{-6, 5, -40, 0, 0, 0});
        Polynomial test3 = new Polynomial(new int[]{1, -1, 10, 0, 4, 21, 7});

        Assertions.assertTrue(test1.minus(test2).compare(test3.differentiate(1)));
    }

    @Test
    void emptyPlusCheck() {
        Polynomial test1 = new Polynomial();
        Polynomial test2 = new Polynomial(new int[]{1, 5, 7});
        Assertions.assertEquals("x^2 + 5x + 7", test1.plus(test2).toString());

        Polynomial test3 = new Polynomial(new int[]{900, 0, 0, 0, 0, -2});
        Polynomial test4 = new Polynomial();
        Assertions.assertEquals("900x^5 - 2", test3.plus(test4).toString());

        Polynomial test5 = new Polynomial();
        Polynomial test6 = new Polynomial();
        Assertions.assertEquals("", test5.plus(test6).toString());
    }

    @Test
    void emptyMinusCheck() {
        Polynomial test1 = new Polynomial();
        Polynomial test2 = new Polynomial(new int[]{9, -2, 100, -111});
        Assertions.assertEquals("-9x^3 + 2x^2 - 100x + 111", test1.minus(test2).toString());

        Polynomial test3 = new Polynomial(new int[]{-100, 10231, 1230, 0, 0});
        Polynomial test4 = new Polynomial();
        Assertions.assertEquals("-100x^4 + 10231x^3 + 1230x^2", test3.minus(test4).toString());

        Polynomial test5 = new Polynomial();
        Polynomial test6 = new Polynomial();
        Assertions.assertEquals("", test5.minus(test6).toString());
    }

    @Test
    void emptyTimesCheck() {
        Polynomial test1 = new Polynomial();
        Polynomial test2 = new Polynomial(new int[]{1, 2, 3, 5, 1923, -123});
        Assertions.assertEquals("", test1.times(test2).toString());

        Polynomial test3 = new Polynomial(new int[]{-123, 0, 123, -231, 0, 0, 0});
        Polynomial test4 = new Polynomial();
        Assertions.assertEquals("", test3.times(test4).toString());

        Polynomial test5 = new Polynomial();
        Polynomial test6 = new Polynomial();
        Assertions.assertEquals("", test5.times(test6).toString());
    }

    @Test
    void emptyEvaluateCheck() {
        Polynomial test1 = new Polynomial();
        Polynomial test2 = new Polynomial();
        Assertions.assertEquals(0, test1.plus(test2).evaluate(100));
    }

    @Test
    void emptyDifferentiateCheck() {
        Polynomial test1 = new Polynomial(new int[]{10, -100});
        Assertions.assertEquals("", test1.differentiate(2).toString());
    }

    @Test
    void emptyCompareCheck() {
        Polynomial test1 = new Polynomial();
        Polynomial test2 = new Polynomial(new int[]{1, 2, 4, 0, -1});
        Assertions.assertFalse(test1.compare(test2));

        Polynomial test3 = new Polynomial(new int[]{1, 2, 0, 1, 1000});
        Polynomial test4 = new Polynomial();
        Assertions.assertFalse(test3.compare(test4));

        Polynomial test5 = new Polynomial();
        Polynomial test6 = new Polynomial();
        Assertions.assertTrue(test5.compare(test6));
    }
}
