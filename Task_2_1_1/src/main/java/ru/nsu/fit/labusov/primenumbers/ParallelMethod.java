package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;

/**
 * Third class.
 */
public class ParallelMethod implements Method {

    /**
     * find complex num method.
     */
    @Override
    public boolean hasComplexNum(ArrayList<Integer> row) {
        boolean result;
        result = row.parallelStream().anyMatch(ParallelMethod::complyCheck);

        return result;
    }

    private static boolean complyCheck(int num) {
        return ComplexNumSearcher.isComplexNum(num);
    }
}