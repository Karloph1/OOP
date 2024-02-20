package org.example;

public abstract class ComplexNumberFinderBase implements IComplexNumberFinder {
    private final String name;
    private long startTime = 0;
    private long endTime = 0;

    public ComplexNumberFinderBase(String methodName) {
        name = methodName;
    }

    /**
     * @return
     */
    @Override
    public abstract boolean hasComplexNum();

    /**
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    @Override
    public long getExecutionTime() {
        return endTime - startTime;
    }

    public void setEndTime() {
        this.endTime = System.currentTimeMillis();
    }

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }
}
