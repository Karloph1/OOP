package ru.nsu.fit.labusov.bakery;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Lock queue class.
 */
public class LockQueue<E> {
    private final Queue<E> lockQueue;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public LockQueue() {
        lockQueue = new ArrayDeque<>();
    }

    /**
     * add method.
     */
    public synchronized void add(E element) {
        lock.writeLock().lock();
        try {
            lockQueue.add(element);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * take method.
     */
    public synchronized E take() {
        E element;

        lock.writeLock().lock();
        try {
            element = lockQueue.remove();
        } finally {
            lock.writeLock().unlock();
        }

        return element;
    }

    public E peek() {
        return lockQueue.peek();
    }

    public boolean isEmpty() {
        return lockQueue.isEmpty();
    }

    public int size() {
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
