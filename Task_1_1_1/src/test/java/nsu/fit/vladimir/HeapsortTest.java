package nsu.fit.vladimir;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeapsortTest {
    @Test
    void heapsort1() {
        int[] ans = {5, 2, 3, 4, 1};
        Heapsort.heapsort(ans);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ans);
    }

    @Test
    void heapsort2() {
        int[] ans = {-10, 1, 0, -100, 10000, 123, 23};
        Heapsort.heapsort(ans);
        Assertions.assertArrayEquals(new int[]{-100, -10, 0, 1, 23, 123, 10000}, ans);
    }

    @Test
    void heapsort3() {
        int[] ans = {-10};
        Heapsort.heapsort(ans);
        Assertions.assertArrayEquals(new int[]{-10}, ans);
    }

    @Test
    void heapsort4() {
        int[] ans = new int[0];
        Heapsort.heapsort(ans);
        Assertions.assertArrayEquals(new int[]{}, ans);
    }

    @Test
    void heapsort5() {
        int[] ans = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Heapsort.heapsort(ans);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, ans);
    }

    @Test
    void heapsort6() {
        int[] ans = {5, 5, 5, 5, 5};
        Heapsort.heapsort(ans);
        Assertions.assertArrayEquals(new int[]{5, 5, 5, 5, 5}, ans);
    }

    @Test
    void heapsort7() {
        int[] ans = {10, -23, 9, 0, -23, 0, 0, -3, 100, 23, 10, -23};
        Heapsort.heapsort(ans);
        Assertions.assertArrayEquals(new int[]{-23, -23, -23, -3, 0, 0, 0,
                9, 10, 10, 23, 100}, ans);
    }

    @Test
    void heapsortTimeTest() {
        int[] ans = new int[20];

        long x = System.currentTimeMillis();
        Heapsort.heapsort(ans);
        System.out.println(System.currentTimeMillis() - x);
    }
}
