package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing class.
 */
public class PrimeNumbersTest {

    /**
     * find complex num method.
     */
    public static boolean isComplexNum(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * generate function.
     */
    public static ArrayList<Integer> generatePrimeNums(int maxNum) {
        ArrayList<Integer> arrList = new ArrayList<>();

        for (int i = 1; i <= maxNum; i++) {
            if (!isComplexNum(i)) {
                arrList.add(i);
            }
        }

        return arrList;
    }

    @Test
    void firstMethodTimeTest() {
        ArrayList<Integer> numList = generatePrimeNums(10000000);
        ConsistentMethod ob = new ConsistentMethod(numList);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("FirstMethod Time = " + (finishTime - startTime));
    }

    @Test
    void secondMethodTimeTest() {
        ArrayList<Integer> numList = generatePrimeNums(100000);
        ThreadMethod ob = new ThreadMethod(numList, 500);

        long startTime = System.currentTimeMillis();

        System.out.println(ob.hasComplexNum());
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("SecondMethod Time = " + (finishTime - startTime));
    }

    @Test
    void thirdMethodTimeTest() {
        ArrayList<Integer> numList = generatePrimeNums(10000000);
        ParallelMethod ob = new ParallelMethod(numList);

        long startTime = System.currentTimeMillis();
        Assertions.assertFalse(ob.hasComplexNum());
        long finishTime = System.currentTimeMillis();
        System.out.println("ThirdMethod Time = " + (finishTime - startTime));
    }

    @Test
    void firstMethodTest2() {
        ArrayList<Integer> numList = generatePrimeNums(10000000);
        numList.add(10);
        ConsistentMethod ob = new ConsistentMethod(numList);

        Assertions.assertTrue(ob.hasComplexNum());
    }

    @Test
    void secondMethodTest2() {
        ArrayList<Integer> numList = generatePrimeNums(10000000);
        numList.add(10);
        ThreadMethod ob = new ThreadMethod(numList, 500);

        Assertions.assertTrue(ob.hasComplexNum());
    }

    @Test
    void thirdMethodTest2() {
        ArrayList<Integer> numList = generatePrimeNums(10000000);
        numList.add(10);
        ParallelMethod ob = new ParallelMethod(numList);

        Assertions.assertTrue(ob.hasComplexNum());
    }
}