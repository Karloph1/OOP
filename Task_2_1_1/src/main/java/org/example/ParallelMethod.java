package org.example;


import java.util.ArrayList;

/**
 * Third class.
 */
public class ParallelMethod extends ComplexNumberFinderBase {
    private ArrayList<Integer> row;

    public ParallelMethod(ArrayList<Integer> nums) {
        super("Method #3");
        row = nums;
    }

    @Override
    public boolean hasComplexNum() {
        boolean result = false;
        setStartTime();
        result = row.parallelStream().anyMatch(ParallelMethod::complyCheck);
        setEndTime();

        return result;
    }

    private static boolean complyCheck(int num) {
        return Utils.isComplexNum(num);
    }
}

