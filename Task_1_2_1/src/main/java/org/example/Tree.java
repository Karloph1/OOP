package org.example;

import java.util.*;

public class Tree <T> {

    private T var;
    private Tree<T> parent;
    private ArrayList<Tree<T>> children;
    private int modCounter = 0;


    public Tree(T var) {
        this.var = var;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public void updateModCounter(Tree<T> current) {
        if (current.parent == null) {
            return;
        }
        current.parent.modCounter++;
        updateModCounter(current.parent);
    }

    public Tree<T> addChild(T var) {
        this.modCounter++;
        updateModCounter(this);

        Tree<T> child = new Tree<>(var);
        child.parent = this;
        this.children.add(child);
        return child;
    }

    public void addChild(Tree<T> subtree) {
        this.modCounter++;
        updateModCounter(this);

        subtree.parent = this;
        this.children.add(subtree);
    }

    public void remove() {
        this.modCounter++;
        updateModCounter(this);

        this.children.clear();
        if (this.parent != null) {
            this.parent.children.remove(this);
        }
        this.var = null;
    }


    @Override
    public boolean equals(Object var) {
        if (!(var instanceof Tree)) {
            return false;
        }
        Tree<T> tree2 = (Tree<T>) var;
        if (this.var != tree2.var) {
            return false;
        }
        if (this.children.size() != tree2.children.size()) {
            return false;
        }
        Iterator<T> iterator1 = this.iteratorDFS();
        Iterator<T> iterator2 = tree2.iteratorDFS();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (iterator1.next() != iterator2.next()) {
                return false;
            }
        }
        return true;
    }


    public Iterator<T> iteratorDFS() {
        return new DfsIterator<T>(this);
    }
    class DfsIterator<T> implements Iterator<T> {
        private Stack<Tree<T>> stack;
        private int currentModCounter;

        DfsIterator(Tree<T> root) {
            this.stack = new Stack<Tree<T>>();
            if (root.var != null) {
                this.stack.push(root);
                this.currentModCounter = root.modCounter;
            }
        }

        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if (currentModCounter != modCounter) {
                throw new ConcurrentModificationException();
            }
            return (!this.stack.isEmpty());
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> current = this.stack.pop();
            for (int i = 0; i < current.children.size(); i++) {
                if (current.children.get(i).var != null) {
                    this.stack.push(current.children.get(i));
                }
            }
            return current.var;
        }

        @Override
        public void remove() throws ConcurrentModificationException {
            throw new ConcurrentModificationException();
        }
    }


    public Iterator<T> iteratorBFS() {
        return new BfsIterator<T>(this);
    }

    class BfsIterator<T> implements Iterator<T> {
        private Queue<Tree<T>> queue;
        private int currentModCounter;

        BfsIterator(Tree<T> root) {
            this.queue = new ArrayDeque<Tree<T>>();
            if (root.var != null) {
                this.queue.add(root);
                this.currentModCounter = root.modCounter;
            }
        }

        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if (currentModCounter != modCounter) {
                throw new ConcurrentModificationException();
            }
            return !this.queue.isEmpty();
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> current = this.queue.poll();
            for (int i = 0; i < current.children.size(); i++) {
                if (current.children.get(i).var != null) {
                    this.queue.add(current.children.get(i));
                }
            }
            return current.var;
        }

        @Override
        public void remove() throws ConcurrentModificationException {
            throw new ConcurrentModificationException();
        }
    }
}