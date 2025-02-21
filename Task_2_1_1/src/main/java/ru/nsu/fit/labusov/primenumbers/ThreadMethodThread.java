package ru.nsu.fit.labusov.primenumbers;

import java.util.List;

/**
 * Extra second class.
 */
public class ThreadMethodThread extends Thread {
    private final Thread thread;
    private final List<Integer> cutOffArray;

    /**
     * Extra second class's constructor.
     */
    public ThreadMethodThread(List<Integer> numsList, int threadNum) {
        cutOffArray = numsList; // отсеченный массив
        String threadName = "Thread " + threadNum;
        thread = new Thread(this, threadName);
    }

    @Override
    public void run() {
        for (int num : cutOffArray) {
            if (ThreadMethod.findingResult) {
                return;
            }

            if (isComplexNum(num)) {
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

    private boolean isComplexNum(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }
}