package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;

/**
 * First class.
 */
public class ConsistentMethod implements ComplexNummarable {

    /**
     * find complex num method.
     */
    @Override
    public boolean hasComplexNum(ArrayList<Integer> row) {
        for (int num : row) {
            if (ComplexNumSearcher.isComplexNum(num)) {
                return true;
            }
        }

        return false;
    }
}