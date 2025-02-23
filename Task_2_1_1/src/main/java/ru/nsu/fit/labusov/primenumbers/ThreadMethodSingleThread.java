package ru.nsu.fit.labusov.primenumbers;

import java.util.List;

/**
 * Extra second class.
 */
public class ThreadMethodSingleThread implements Runnable {

    private final Thread thread;
    private final List<Integer> cutOffArray;
    private final int indexStart;
    private final int indexEnd;

    /**
     * Extra second class's constructor.
     */
    public ThreadMethodSingleThread(List<Integer> numsList,
                                    int indexStart, int indexEnd, int threadNum) {
        cutOffArray = numsList;
        String threadName = "Thread " + threadNum;
        thread = new Thread(this, threadName);
        this.indexStart = indexStart;
        this.indexEnd = indexEnd;
    }

    @Override
    public void run() {
        for (int i = indexStart; i < indexEnd; i++) {
            if (ThreadMethod.findingResult) {
                return;
            }

            if (ComplexNumSearcher.isComplexNum(cutOffArray.get(i))) {
                ThreadMethod.lock.writeLock().lock();
                try {
                    ThreadMethod.findingResult = true;
                } finally {
                    ThreadMethod.lock.writeLock().unlock();
                }

                return;
            }
        }
    }

    public Thread getThread() {
        return thread;
    }
}