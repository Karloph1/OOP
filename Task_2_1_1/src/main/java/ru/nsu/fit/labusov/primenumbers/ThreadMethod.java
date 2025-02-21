package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Second class.
 */
public class ThreadMethod {
    private final ArrayList<Integer> rows;
    private final int threadNum;
    protected static volatile boolean findingResult = false;
    protected static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);


    /**
     * Class's constructor.
     */
    public ThreadMethod(ArrayList<Integer> rows, int threadNum) {
        this.rows = rows;
        this.threadNum = threadNum;
    }


    public boolean hasComplexNum() {
        if (rows.size() >= threadNum) {
            List<ThreadMethodThread> numThreads = new ArrayList<>();
            for (int i = 0; i < threadNum; i++) {
                if (i == threadNum - 1) {
                    numThreads.add(new ThreadMethodThread(rows.subList(rows.size() / threadNum * i,
                            rows.size()), i));
                } else {
                    numThreads.add(new ThreadMethodThread(rows.subList(rows.size() / threadNum * i,
                            rows.size() / threadNum * (i + 1) - 1), i));
                }
            }

            for (ThreadMethodThread thr : numThreads) {
                thr.getThread().start();
            }

            for (ThreadMethodThread thr : numThreads) {
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