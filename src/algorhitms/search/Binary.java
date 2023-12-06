package algorhitms.search;

/**
 * Binary
 */
public class Binary implements SearchFunction {
    public int searchArr(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target)
                return mid;

            if (arr[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;

        }

        return -1;
    }

    public static int search(int[] arr, int target) {
        return new Binary().searchArr(arr, target);
    }
}
