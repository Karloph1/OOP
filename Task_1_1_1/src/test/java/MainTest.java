import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
class MainTest {
    @Test
    void heapsort1() {
        int[] ans = {5, 2, 3, 4, 1};
        org.example.Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {1, 2, 3, 4, 5}, ans);
    }

    @Test
    void heapsort2(){
        int[] ans = {-10, 1, 0, -100, 10000, 123, 23};
        org.example.Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {-100, -10, 0, 1, 23, 123, 10000}, ans);
    }

    @Test
    void heapsort3(){
        int[] ans = {-10};
        org.example.Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {-10}, ans);
    }

    @Test
    void heapsort4(){
        int[] ans = new int[0];
        org.example.Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {}, ans);
    }
}