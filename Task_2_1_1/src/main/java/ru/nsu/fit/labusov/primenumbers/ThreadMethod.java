package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Second class.
 */
public class ThreadMethod implements Method {
    private final int threadNum;
    protected static volatile boolean findingResult = false;
    protected static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    /**
     * Class's constructor.
     */
    public ThreadMethod(int threadNum) {
        this.threadNum = threadNum;
    }

    /**
     * find complex num method.
     */
    @Override
    public boolean hasComplexNum(ArrayList<Integer> rows) {
        if (rows.size() >= threadNum) {
            ThreadMethodSingleThread[] numThreads = new ThreadMethodSingleThread[threadNum];

            for (int i = 0; i < threadNum; i++) {
                if (i == threadNum - 1) {
                    numThreads[i] = new ThreadMethodSingleThread(rows,
                            rows.size() / threadNum * i,
                            rows.size(), i);
                } else {
                    numThreads[i] = new ThreadMethodSingleThread(rows,
                            rows.size() / threadNum * i,
                            rows.size() / threadNum * (i + 1), i);
                }
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

            return findingResult;
        } else {
            return false;
        }
    }
}