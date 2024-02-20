package org.example;


import java.util.ArrayList;

public class FirstMethod extends ComplexNumberFinderBase {
    private final int[] row;

    public FirstMethod(ArrayList<Integer> nums) {
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
