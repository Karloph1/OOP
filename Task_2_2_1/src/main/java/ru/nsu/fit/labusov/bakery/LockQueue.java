package ru.nsu.fit.labusov.bakery;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Lock queue class.
 */
public class LockQueue<E> {
    private final Queue<E> lockQueue;
    private final int capacity;

    public LockQueue(int capacity) {
        this.capacity = capacity;
        lockQueue = new ArrayDeque<>();
    }

    /**
     * add method.
     */
    public synchronized void put(E element) throws InterruptedException {
        while (lockQueue.size() == capacity) {
            wait();
        }

        lockQueue.add(element);
        notifyAll();
    }

    /**
     * take method.
     */
    public synchronized E take() throws InterruptedException {
        while (lockQueue.isEmpty()) {
            wait();
        }

        E element = lockQueue.remove();
        notifyAll();

        return element;
    }

    public E peek() {
        return lockQueue.peek();
    }

    public boolean isEmpty() {
        return lockQueue.isEmpty();
    }

    public synchronized int size() {
        return lockQueue.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LockQueue<?> lockQueue1 = (LockQueue<?>) o;

        if (this.lockQueue.size() != lockQueue1.size()) {
            return false;
        }

        for (int i = 0; i < lockQueue.size(); i++) {
            if (lockQueue.remove() != lockQueue1.lockQueue.remove()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return lockQueue.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < lockQueue.size(); i++) {
            stringBuilder.append(lockQueue.peek()).append(", ");
        }

        return stringBuilder.toString();
    }
}
