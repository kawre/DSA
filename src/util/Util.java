package util;

import java.util.Arrays;
import java.util.List;

import algorhitms.sort.*;

/**
 * Util
 */
public class Util {
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void replArr(int[] originalArr, int[] newArr) {
        for (int i = 0; i < originalArr.length && i < newArr.length; i++) {
            originalArr[i] = newArr[i];
        }
    }

    public static int getMax(int[] arr) {
        int maxEl = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxEl)
                maxEl = arr[i];
        }

        return maxEl;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] > arr[i + 1])
                return false;
        return true;
    }

    public static int[] sort(int[] arr) {
        Radix.sort(arr);
        return arr;
    }

    public static int[] toArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
