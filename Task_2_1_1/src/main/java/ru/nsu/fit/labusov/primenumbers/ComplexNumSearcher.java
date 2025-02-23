package ru.nsu.fit.labusov.primenumbers;

/**
 * Complex num searcher class.
 */
public class ComplexNumSearcher {

    /**
     * is num complex method.
     */
    public static boolean isComplexNum(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }

        return false;
    }
}
