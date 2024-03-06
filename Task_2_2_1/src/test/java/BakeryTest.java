import java.util.ArrayList;
import org.example.Bakery;
import org.example.Baker;
import org.example.Courier;
import org.example.Parser;
import org.example.Storage;

/**
 * Test class.
 */
public class BakeryTest {
    static ArrayList<Baker> bakers = new ArrayList<>();
    static ArrayList<Courier> couriers = new ArrayList<>();
    static int capacity;

    /**
     * main function.
     */
    public static void main(String[] argc) {
        Parser.parser("src/test/resources/data.json");
        bakers = Parser.getBakers();
        couriers = Parser.getCouriers();
        capacity = Parser.getStorage();
        Storage storage = new Storage(capacity);
        Bakery bakery = new Bakery(bakers, couriers, storage);
        bakery.initialisingProcess();
    }
}
