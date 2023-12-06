package algorhitms.search;

/**
 * Linear
 */
public class Linear implements SearchFunction {
    @Override
    public int searchArr(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target)
                return i;

        return -1;
    }

    public static int search(int[] arr, int target) {
        return new Linear().searchArr(arr, target);
    }
}
