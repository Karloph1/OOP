package ru.nsu.fit.labusov.primenumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Second class.
 */
public class ThreadMethod extends Thread{
    private final ArrayList<Integer> rows;
    private final int threadNum;
    private static volatile boolean findingResult = false;
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    /**
     * Class's constructor.
     */
    public ThreadMethod(List<Integer> rows, int threadNum) {
        this.rows = new ArrayList<>(rows);
        this.threadNum = threadNum;
    }

    /**
     * find complex num method.
     */
    public boolean hasComplexNum() {
        if (rows.size() >= threadNum) {
            Thread[] numThreads = new Thread[threadNum];
            for (int i = 0; i < threadNum; i++) {
                if (i == threadNum - 1) {
                    numThreads[i] = new Thread(new ThreadMethod(rows.subList(rows.size() / threadNum * i,
                            rows.size()), i));
                } else {
                    numThreads[i] = new Thread(new ThreadMethod(rows.subList(rows.size() / threadNum * i,
                            rows.size() / threadNum * (i + 1) - 1), i));
                }
            }

            for (Thread thr : numThreads) {
                thr.start();
            }

            for (Thread thr : numThreads) {
                try {
                    thr.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return findingResult;
        } else {
            return false;
        }
    }

    private boolean isComplexNum(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        for (int num : rows) {
            if (findingResult) {
                return;
            }

            if (isComplexNum(num)) {
                lock.writeLock().lock();
                try {
                    findingResult = true;
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }
}