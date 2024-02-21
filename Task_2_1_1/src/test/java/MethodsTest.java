import org.example.Complexfinder;
import org.example.ConsistentMethod;
import org.example.ThreadMethod;
import org.example.ParallelMethod;
import org.example.Utils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing class.
 */
public class MethodsTest {
    ArrayList<Integer> numsList = Utils.generatePrimeNums(10000000);

    @Test
    void allMethodsTest() {
        List<Complexfinder> methodsList = new ArrayList<>();

        methodsList.add(new ConsistentMethod(numsList));
        methodsList.add(new ThreadMethod(numsList, 10));
        methodsList.add(new ThreadMethod(numsList, 100));
        methodsList.add(new ThreadMethod(numsList, 1000));
        methodsList.add(new ThreadMethod(numsList, 5000));
        methodsList.add(new ParallelMethod(numsList));

        for (Complexfinder method : methodsList) {
            boolean result = method.hasComplexNum();
            System.out.printf("%s. Complex numbers %s found. Execution time %d ms.%n",
                    method.getName(), result ? "" : "not ", method.getExecutionTime());
        }
    }

    @Test
    void checkingFirstMethod() {
        ConsistentMethod ob = new ConsistentMethod(numsList);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("FirstMethod Time = " + Long.toString(finishTime - startTime));
    }

    @Test
    void checkingSecondMethod() {
        ThreadMethod ob = new ThreadMethod(numsList, 500);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("SecondMethod Time = " + Long.toString(finishTime - startTime));
    }

    @Test
    void checkingThirdMethod() {
        ParallelMethod ob = new ParallelMethod(numsList);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("ThirdMethod Time = " + Long.toString(finishTime - startTime));
    }

}

