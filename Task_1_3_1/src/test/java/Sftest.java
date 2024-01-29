import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * main test class.
 */
public class Sftest {

    @Test
    void littleCheck() {
        try {
            ArrayList<Integer> realAnswer = new ArrayList<>(Arrays.asList(1, 8));
            ArrayList<Integer> answer;
            answer = org.example.StringFinder.find("src/test/resources/textfile.txt", "bra");
            Assertions.assertEquals(realAnswer, answer);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void emptyCheck() {
        try {
            ArrayList<Integer> realAnswer = new ArrayList<>();
            ArrayList<Integer> answer;
            answer = org.example.StringFinder.find("src/test/resources/emptytext.txt", "12");
            Assertions.assertEquals(realAnswer, answer);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

}
