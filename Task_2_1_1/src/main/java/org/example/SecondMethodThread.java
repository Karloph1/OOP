package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Extra second class.
 */
public class SecondMethodThread implements Runnable {

    private Thread thread;
    private String threadName;

    private List<Integer> cutOffArray;

    /**
     * Extra second class's constructor
     */
    public SecondMethodThread(List<Integer> numsList, int threadNum) {
        cutOffArray = numsList; // отсеченный массив
        threadName = "Thread " + threadNum;
        thread = new Thread(this, threadName);
    }

    @Override
    public void run() {
        for (int num : cutOffArray) {
            if (Utils.findingResult) {
                return;
            }

            if (isComplexNum(num)) {
                Utils.lock.writeLock().lock();
                try {
                    Utils.findingResult = true;
                } finally {
                    Utils.lock.writeLock().unlock();
                }

                return;
            }
        }
    }

    public String getName() {
        return threadName;
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



