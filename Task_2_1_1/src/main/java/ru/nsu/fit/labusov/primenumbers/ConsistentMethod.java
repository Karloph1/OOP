package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;

/**
 * First class.
 */
public class ConsistentMethod{
    private final int[] row;

    public ConsistentMethod(ArrayList<Integer> nums) {
        row = nums.stream().mapToInt(i -> i).toArray();
    }

    public boolean hasComplexNum() {

        for (int num : row) {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    return true;
                }
            }
        }

        return false;
    }
}