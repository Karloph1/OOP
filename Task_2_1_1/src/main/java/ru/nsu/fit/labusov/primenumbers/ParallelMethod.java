package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;

/**
 * Third class.
 */
public class ParallelMethod {
    private final ArrayList<Integer> row;

    public ParallelMethod(ArrayList<Integer> nums) {
        row = nums;
    }

    /**
     * find complex num method.
     */
    public boolean hasComplexNum() {
        boolean result;
        result = row.parallelStream().anyMatch(ParallelMethod::complyCheck);

        return result;
    }

    private static boolean complyCheck(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }

        return false;
    }
}