package algorhitms.sort;

import java.util.Arrays;

import structures.heap.*;

/**
 * Heap
 */
public class Heap implements SortFunction {
    @Override
    public void sortArr(int[] arr) {
        MinHeap heap = MinHeap.heapify(arr);

        for (int i = 0; i < arr.length; i++)
            arr[i] = heap.poll();
    }

    public static void sort(int[] arr) {
        new Heap().sortArr(arr);
    }
}
