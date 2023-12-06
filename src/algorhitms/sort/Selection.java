package algorhitms.sort;

import util.Util;

/**
 * Selection
 */
public class Selection implements SortFunction {
    @Override
    public void sortArr(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minEl = i;

            for (int j = minEl + 1; j < arr.length; j++) {
                if (arr[minEl] > arr[j])
                    minEl = j;
            }

            Util.swap(arr, i, minEl);
        }

    }

    public static void sort(int[] arr) {
        new Selection().sortArr(arr);
    }
}
