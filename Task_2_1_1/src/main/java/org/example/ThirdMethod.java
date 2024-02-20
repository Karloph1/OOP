package org.example;


import java.util.ArrayList;

public class ThirdMethod extends ComplexNumberFinderBase {
    private ArrayList<Integer> row;

    public ThirdMethod(ArrayList<Integer> nums) {
        super("Method #3");
        row = nums;
    }

    @Override
    public boolean hasComplexNum() {
        boolean result = false;
        setStartTime();
        result = row.parallelStream().anyMatch(ThirdMethod::complyCheck);
        setEndTime();

        return result;
    }

    private static boolean complyCheck(int num) {
        return Utils.isComplexNum(num);
    }
}

