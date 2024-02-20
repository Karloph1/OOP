package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SecondMethod extends ComplexNumberFinderBase {
    private final ArrayList<Integer> rows;
    private final int threadNum;


    public SecondMethod(ArrayList<Integer> rows, int threadNum) {
        super("Method #2");
        this.rows = rows;
        this.threadNum = threadNum;
    }

    @Override
    public boolean hasComplexNum() {
        setStartTime();
        if (rows.size() >= threadNum) {
            List<SecondMethodThread> numThreads = new ArrayList<>();
            for (int i = 0; i < threadNum; i++) {
                if (i == threadNum - 1) {
                    numThreads.add(new SecondMethodThread
                            (rows.subList(rows.size() / threadNum * i, rows.size()), i));
                } else {
                    numThreads.add(new SecondMethodThread
                            (rows.subList(rows.size() / threadNum * i,
                                    rows.size() / threadNum * (i + 1) - 1), i));
                }
            }

            for (SecondMethodThread thr : numThreads)
                thr.getThread().start();


            for (SecondMethodThread thr : numThreads) {
                try {
                    thr.getThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            setEndTime();
            return Utils.findingResult;
        } else {
            return false;
        }
    }
}
