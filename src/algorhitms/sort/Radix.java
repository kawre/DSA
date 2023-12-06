package algorhitms.sort;

import util.Util;

/**
 * Radix
 */
public class Radix implements SortFunction {
    @Override
    public void sortArr(int[] arr) {
        int maxEl = Util.getMax(arr);

        for (int exp = 1; maxEl / exp > 0; exp *= 10)
            Counting.sort(arr, exp);
    }

    public static void sort(int[] arr) {
        new Radix().sortArr(arr);
    }
}
