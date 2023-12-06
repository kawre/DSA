package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import algorhitms.sort.*;

/**
 * Tester
 */
public class Tester {
    private SortFunction func;
    private int testcases, arrSize;
    private List<Long> times;

    public Tester(SortFunction func, int testcases, int arrSize) {
        this.func = func;
        this.testcases = testcases;
        this.arrSize = arrSize;
        this.times = new ArrayList<>();
    }

    private void time(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long executionTime = System.currentTimeMillis() - startTime;
        this.times.add(executionTime);
    }

    private boolean testCases() {
        for (int i = 1; i <= testcases; i++) {
            if (!this.test(i))
                return false;
        }

        System.out
                .println("✅ " + testcases + "/" + testcases + " testcases passed | ⌛ " + this.totalExecTime() + " ms");
        return true;
    }

    private boolean test(int caseIndex) {
        int[] arr = Dummy.array(arrSize);
        int[] originalArr = arr.clone();

        time(() -> {
            func.sortArr(arr);
        });

        boolean isSorted = Util.isSorted(arr);
        if (!isSorted) {
            System.out.println("❌ Testcase #" + caseIndex + " failed");
            System.out.println("Input:    " + Arrays.toString(arr));
            System.out.println("Output:   " + Arrays.toString(originalArr));
            System.out.println("Expected: " + Arrays.toString(Util.sort(arr)));
        }

        return isSorted;
    }

    public long medianExecTime() {
        Collections.sort(this.times);
        int size = this.times.size();
        int mid = size / 2;

        if (size % 2 == 1) {
            return this.times.get(mid);
        } else {
            long firstMiddle = this.times.get(mid - 1);
            long secondMiddle = this.times.get(mid);
            return (firstMiddle + secondMiddle) / 2;
        }
    }

    public long totalExecTime() {
        return this.times.stream().mapToLong(Long::longValue).sum();
    }

    public static void run(SortFunction func, int testcases, int arrSize) {
        var tester = new Tester(func, testcases, arrSize);

        if (tester.testCases()) {
            System.out.println("⌛ Median " + tester.medianExecTime() + " ms");
        }
    }

    public static void runAll(int testcases, int arrSize) {
        LinkedHashMap<String, SortFunction> fns = new LinkedHashMap<>();

        fns.put("Bubble Sort", Bubble::sort);
        fns.put("Counting Sort", Counting::sort);
        fns.put("Insertion Sort", Insertion::sort);
        fns.put("Merge Sort", Merge::sort);
        fns.put("Radix Sort", Radix::sort);
        fns.put("Selection Sort", Selection::sort);
        fns.put("Heap Sort", Heap::sort);
        fns.put("Quick Sort", Quick::sort);

        for (var entry : fns.entrySet()) {
            System.out.println("[TESTING] " + entry.getKey());
            run(entry.getValue(), testcases, arrSize);
            System.out.println();
        }
    }
}
