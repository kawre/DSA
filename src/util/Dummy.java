package util;

import java.util.Random;

import algorhitms.sort.*;
import algorhitms.search.*;

/**
 * Dummy
 */
public class Dummy {
    public static int[] array(int len) {
        int[] arr = new int[len];

        Random random = new Random();
        for (int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(0, 10);

        return arr;
    }

    public static int[] sortedArray(int len) {
        int[] dummy = Dummy.array(len);
        Merge.sort(dummy);
        return dummy;
    }
}
