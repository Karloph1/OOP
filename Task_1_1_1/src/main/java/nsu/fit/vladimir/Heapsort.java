package nsu.fit.vladimir;

/**
 * Main class.
 */

public class Heapsort {
    /**
     * sifting function.
     */

    private static void sift(int[] array, int index, int unsortedArrayLength) {
        int child1Index = index * 2 + 1;

        while (child1Index < unsortedArrayLength) {
            int child2Index = index * 2 + 2;
            int maxChildIndex = child1Index;

            if (child2Index < unsortedArrayLength) {
                maxChildIndex = array[child1Index] > array[child2Index] ? child1Index : child2Index;
            }

            if (array[index] >= array[maxChildIndex]) {
                return;
            }

            int temp = array[index];
            array[index] = array[maxChildIndex];
            array[maxChildIndex] = temp;

            index = maxChildIndex;
            child1Index = index * 2 + 1;
        }
    }


    /**
     * heapsort function.
     */

    public static void heapsort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            sift(array, i, array.length);
        }

        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            sift(array, 0, i);
        }
    }
}