package org.example;

public class Main {
    private static void siftUp(int len, int[] newArray) { //отсеивание вниз
        int x;
        if ((len - 1) / 2 >= 0 && newArray[len] < newArray[(len - 1) / 2]) {
            x = newArray[len];
            newArray[len] = newArray[(len - 1) / 2];
            newArray[(len - 1) / 2] = x;
            siftUp((len - 1) / 2, newArray);
        }
    }

    private static void siftDown(int current, int len, int[] newArray) { //отсеивание вверх
        int x;
        if (current * 2 + 2 < len && newArray[current * 2 + 1] < newArray[current] && newArray[current * 2 + 2] < newArray[current]) {
            if (newArray[current * 2 + 1] < newArray[current * 2 + 2]) {
                x = newArray[current * 2 + 1];
                newArray[current * 2 + 1] = newArray[current];
                newArray[current] = x;
                siftDown(current * 2 + 1, len, newArray);
            } else {
                x = newArray[current * 2 + 2];
                newArray[current * 2 + 2] = newArray[current];
                newArray[current] = x;
                siftDown(current * 2 + 2, len, newArray);
            }
        } else if (current * 2 + 1 < len && newArray[current * 2 + 1] < newArray[current]) {
            x = newArray[current * 2 + 1];
            newArray[current * 2 + 1] = newArray[current];
            newArray[current] = x;
            siftDown(current * 2 + 1, len, newArray);
        } else if (current * 2 + 2 < len && newArray[current * 2 + 2] < newArray[current]) {
            x = newArray[current * 2 + 2];
            newArray[current * 2 + 2] = newArray[current];
            newArray[current] = x;
            siftDown(current * 2 + 2, len, newArray);
        }
    }

    public static void heapsort(int[] list) {
        int size = list.length;
        int[] newTree = new int[size];
        for (int i = 0; i < size; i++) { // записывание в новый
            newTree[i] = list[i];
            siftUp(i, newTree);
        }
        int x;
        for (int i = size - 1; i >= 0; i--) { // отсев наименьшего
            x = newTree[0];
            newTree[0] = newTree[i];
            newTree[i] = x;
            siftDown(0, i, newTree);
        }
        for (int i = 0; i < size; i++) // записываем полученный в который дали на входе
            list[i] = newTree[size - 1 - i];
    }

    public static void main(String[] args){

    }

}