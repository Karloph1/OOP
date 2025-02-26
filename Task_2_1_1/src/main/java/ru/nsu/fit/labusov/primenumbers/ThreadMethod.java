package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Second class.
 */
public class ThreadMethod implements ComplexNummarable {
    private final int threadNum;
    private final Boolean[] findingResult;
    private final ReentrantReadWriteLock lock;

    /**
     * Class's constructor.
     */
    public ThreadMethod(int threadNum) {
        this.threadNum = threadNum;
        findingResult = new Boolean[1];
        findingResult[0] = false;
        lock = new ReentrantReadWriteLock(true);
    }

    /**
     * find complex num method.
     */
    @Override
    public boolean hasComplexNum(ArrayList<Integer> rows) {
        if (rows.size() >= threadNum) {
            ThreadMethodSingleThread[] numThreads = new ThreadMethodSingleThread[threadNum];

            for (int i = 0; i < threadNum; i++) {
                int indexStart = rows.size() / threadNum * i;
                int indexEnd;

                if (i == threadNum - 1) {
                    indexEnd = rows.size();
                } else {
                    indexEnd = rows.size() / threadNum * (i + 1);
                }

                numThreads[i] = new ThreadMethodSingleThread(rows,
                        indexStart, indexEnd, i, findingResult, lock);
            }

            for (ThreadMethodSingleThread thr : numThreads) {
                thr.getThread().start();
            }

            for (ThreadMethodSingleThread thr : numThreads) {
                try {
                    thr.getThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return findingResult[0];
        } else {
            return false;
        }
    }
}