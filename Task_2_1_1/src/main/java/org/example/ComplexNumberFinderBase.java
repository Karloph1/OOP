package org.example;

/**
 * abstract main class.
 */
public abstract class ComplexNumberFinderBase implements icomplexnumberFinder {
    private final String name;
    private long startTime = 0;
    private long endTime = 0;

    public ComplexNumberFinderBase(String methodName) {
        name = methodName;
    }

    /**
     * check function.
     */
    @Override
    public abstract boolean hasComplexNum();

    /**
     * name funtcion.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * get time function.
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
