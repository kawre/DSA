package algorhitms.sort;

import util.Util;

/**
 * Counting
 */
public class Counting implements SortFunction {
    @Override
    public void sortArr(int[] arr) {
        int maxEl = Util.getMax(arr);
        int[] count = new int[maxEl + 1];

        for (int n : arr)
            count[n]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        Util.replArr(arr, output);
    }

    public void sortArr(int[] arr, int exp) {
        int[] count = new int[10];

        for (int n : arr)
            count[(n / exp) % 10]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        Util.replArr(arr, output);
    }

    public static void sort(int[] arr) {
        new Counting().sortArr(arr);
    }

    public static void sort(int[] arr, int exp) {
        new Counting().sortArr(arr, exp);
    }
}
