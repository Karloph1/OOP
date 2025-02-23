package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing class.
 */
public class PrimeNumbersTest {

    /**
     * generate function.
     */
    public static ArrayList<Integer> generatePrimeNums(int maxNum) {
        ArrayList<Integer> arrList = new ArrayList<>();

        for (int i = 1; i <= maxNum; i++) {
            if (!complexNumSearcher.isComplexNum(i)) {
                arrList.add(i);
            }
        }

        return arrList;
    }

    ArrayList<Integer> numList = generatePrimeNums(10000000);

    @Test
    void firstMethodMeasureTimeTest() {
        ConsistentMethod ob = new ConsistentMethod();

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum(numList));
        long finishTime = System.currentTimeMillis();
        System.out.println("FirstMethod Time = " + (finishTime - startTime));
    }

    @Test
    void secondMethodMeasureTimeTest() {
        ThreadMethod ob = new ThreadMethod(4);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum(numList));
        long finishTime = System.currentTimeMillis();
        System.out.println("SecondMethod Time = " + (finishTime - startTime));
    }

    @Test
    void thirdMethodMeasureTimeTest() {
        ParallelMethod ob = new ParallelMethod();

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum(numList));
        long finishTime = System.currentTimeMillis();
        System.out.println("ThirdMethod Time = " + (finishTime - startTime));
    }

    @Test
    void firstMethodHasComplexNumTest() {
        numList.add(10);
        ConsistentMethod ob = new ConsistentMethod();

        Assertions.assertTrue(ob.hasComplexNum(numList));
    }

    @Test
    void secondMethodHasComplexNumTest() {
        numList.add(10);
        ThreadMethod ob = new ThreadMethod(4);

        Assertions.assertTrue(ob.hasComplexNum(numList));
    }

    @Test
    void thirdMethodHasComplexNumTest() {
        numList.add(10);
        ParallelMethod ob = new ParallelMethod();

        Assertions.assertTrue(ob.hasComplexNum(numList));
    }
}