package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Utils {

    protected static volatile boolean findingResult = false;
    protected static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public static boolean isComplexNum(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Integer> generatePrimeNums(int maxNum) {
        ArrayList<Integer> arrList = new ArrayList<Integer>();

        for (int i = 1; i <= maxNum; i++) {
            if (!isComplexNum(i))
                arrList.add(i);
        }

        return arrList;
    }
}
