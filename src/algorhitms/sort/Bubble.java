package algorhitms.sort;

import util.Util;

public class Bubble implements SortFunction {
    @Override
    public void sortArr(int[] arr) {
        boolean flag = false;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++)
                if (arr[j - 1] > arr[j]) {
                    flag = true;
                    Util.swap(arr, j - 1, j);
                }

            if (!flag)
                break;
        }
    }

    public static void sort(int[] arr) {
        new Bubble().sortArr(arr);
    }
}
