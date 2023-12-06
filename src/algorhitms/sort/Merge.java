package algorhitms.sort;

import util.Util;

/**
 * Merge
 */
public class Merge implements SortFunction {
    @Override
    public void sortArr(int[] arr) {
        this.splitArr(arr, 0, arr.length - 1);
    }

    public void splitArr(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            this.splitArr(arr, left, mid);
            this.splitArr(arr, mid + 1, right);

            this.merge(arr, left, mid, right);
        }
    }

    public void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];

        int[] rightArr = new int[n2];
        for (int i = 0; i < n2; i++)
            rightArr[i] = arr[mid + 1 + i];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j])
                arr[k] = leftArr[i++];
            else
                arr[k] = rightArr[j++];
            k++;
        }

        while (i < n1)
            arr[k++] = leftArr[i++];

        while (j < n2)
            arr[k++] = rightArr[j++];
    }

    public static void sort(int[] arr) {
        new Merge().sortArr(arr);
    }
}
