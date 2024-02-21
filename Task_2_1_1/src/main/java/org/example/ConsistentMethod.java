package org.example;


import java.util.ArrayList;

/**
 * First class.
 */
public class ConsistentMethod extends ComplexNumberFinderBase {
    private final int[] row;

    public ConsistentMethod(ArrayList<Integer> nums) {
        super("Method #1");
        row = nums.stream().mapToInt(i -> i).toArray();
    }

    @Override
    public boolean hasComplexNum() {
        setStartTime();
        for (int num : row) {
            if (Utils.isComplexNum(num)) {
                setEndTime();
                return true;
            }
        }
        setEndTime();
        return false;
    }
}
