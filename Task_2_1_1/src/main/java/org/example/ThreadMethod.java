package org.example;


import java.util.ArrayList;

/**
 * Third class.
 */
public class ThreadMethod extends ComplexNumberFinderBase {
    private ArrayList<Integer> row;

    public ThreadMethod(ArrayList<Integer> nums) {
        super("Method #3");
        row = nums;
    }

    @Override
    public boolean hasComplexNum() {
        boolean result = false;
        setStartTime();
        result = row.parallelStream().anyMatch(ThreadMethod::complyCheck);
        setEndTime();

        return result;
    }

    private static boolean complyCheck(int num) {
        return Utils.isComplexNum(num);
    }
}

