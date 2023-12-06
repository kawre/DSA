package algorhitms.sort;

import util.Util;

/**
 * Quick
 */
public class Quick implements SortFunction {
    @Override
    public void sortArr(int[] arr) {
        int n = arr.length;

        this.sortPartition(arr, 0, n - 1);
    }

    private void sortPartition(int[] arr, int low, int high) {
        if (low < high) {
            int pi = this.parition(arr, low, high);

            this.sortPartition(arr, low, pi - 1);
            this.sortPartition(arr, pi + 1, high);
        }
    }

    private int parition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (arr[j] < pivot) {
                i++;
                Util.swap(arr, i, j);
            }
        }

        Util.swap(arr, i + 1, high);
        return i + 1;
    }

    public static void sort(int[] arr) {
        new Quick().sortArr(arr);
    }
}
