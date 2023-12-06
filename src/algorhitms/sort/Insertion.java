package algorhitms.sort;

import util.Util;

/**
 * Insertion
 */
public class Insertion implements SortFunction {
    @Override
    public void sortArr(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int k = i;

            while (k >= 1 && arr[k - 1] > arr[k]) {
                Util.swap(arr, k - 1, k);
                k--;
            }
        }
    }

    public static void sort(int[] arr) {
        new Insertion().sortArr(arr);
    }
}
