package ru.nsu.fit.labusov.primenumbers;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Extra second class.
 */
public class ThreadMethodSingleThread implements Runnable {

    private final Thread thread;
    private final List<Integer> cutOffArray;
    private final int indexStart;
    private final int indexEnd;
    private final FindingResult findingResult;
    private final ReentrantReadWriteLock lock;

    /**
     * Extra second class's constructor.
     */
    public ThreadMethodSingleThread(List<Integer> numsList,
                                    int indexStart, int indexEnd, int threadNum,
                                    FindingResult findingResult, ReentrantReadWriteLock lock) {
        cutOffArray = numsList;
        String threadName = "Thread " + threadNum;
        thread = new Thread(this, threadName);
        this.indexStart = indexStart;
        this.indexEnd = indexEnd;
        this.findingResult = findingResult;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = indexStart; i < indexEnd; i++) {
            if (findingResult.getFindingResult()) {
                return;
            }

            if (ComplexNumSearcher.isComplexNum(cutOffArray.get(i))) {
                this.lock.writeLock().lock();
                try {
                    findingResult.setFindingResult(true);
                } finally {
                    this.lock.writeLock().unlock();
                }

                return;
            }
        }
    }

    public Thread getThread() {
        return thread;
    }
}