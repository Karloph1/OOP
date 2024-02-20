import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class MethodsTest {
    ArrayList<Integer> numsList = Utils.generatePrimeNums(10000000);

    @Test
    void allMethodsTest() {
        List<сomplexтumberFinder> methodsList = new ArrayList<>();

        methodsList.add(new FirstMethod(numsList));
        methodsList.add(new SecondMethod(numsList, 10));
        methodsList.add(new SecondMethod(numsList, 100));
        methodsList.add(new SecondMethod(numsList, 1000));
        methodsList.add(new SecondMethod(numsList, 5000));
        methodsList.add(new ThirdMethod(numsList));

        for (сomplexnumberFinder method : methodsList) {
            boolean result = method.hasComplexNum();
            System.out.printf("%s. Complex numbers %s found. Execution time %d ms.%n", method.getName(), result ? "" : "not ", method.getExecutionTime());
        }
    }

    @Test
    void checkingFirstMethod() {
        FirstMethod ob = new FirstMethod(numsList);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("FirstMethod Time = " + Long.toString(finishTime - startTime));
    }

    @Test
    void checkingSecondMethod() {
        SecondMethod ob = new SecondMethod(numsList, 500);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("SecondMethod Time = " + Long.toString(finishTime - startTime));
    }

    @Test
    void checkingThirdMethod() {
        ThirdMethod ob = new ThirdMethod(numsList);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("ThirdMethod Time = " + Long.toString(finishTime - startTime));
    }

}

