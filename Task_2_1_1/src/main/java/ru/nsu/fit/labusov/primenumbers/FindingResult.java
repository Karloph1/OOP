package ru.nsu.fit.labusov.primenumbers;

/**
 * Finding result class.
 */
public class FindingResult {
    private volatile boolean findingResult;

    public boolean getFindingResult() {
        return findingResult;
    }

    public void setFindingResult(boolean findingResult) {
        this.findingResult = findingResult;
    }
}
