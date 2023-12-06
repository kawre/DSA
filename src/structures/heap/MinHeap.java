package structures.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Heap
 */
public class MinHeap {
    private List<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public static MinHeap create() {
        return new MinHeap();
    }

    public Integer peek() {
        return heap.get(0);
    }

    public int poll() {
        int val = this.peek();

        Collections.swap(this.heap, 0, heap.size() - 1);
        this.heap.remove(this.heap.size() - 1);
        this.siftdown(0);

        return val;
    }

    public void insert(int value) {
        this.heap.add(value);
        siftup(this.heap.size() - 1);
    }

    public void siftdown(int i) {
        int n = heap.size();
        int l = (2 * i) + 1, r = l + 1;

        while ((l < n && this.heap.get(l) < this.heap.get(i)) ||
                ((r < n && this.heap.get(r) < this.heap.get(i)))) {
            int smallest = (r >= n || this.heap.get(l) < this.heap.get(r)) ? l : r;

            Collections.swap(this.heap, i, smallest);
            i = smallest;

            l = (2 * i) + 1;
            r = l + 1;
        }
    }

    public void siftup(int i) {
        int parent = (i - 1) / 2;

        while (i != 0 && this.heap.get(parent) > this.heap.get(i)) {
            Collections.swap(this.heap, parent, i);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public static MinHeap heapify(int[] arr) {
        MinHeap heap = new MinHeap();

        for (int n : arr)
            heap.insert(n);

        return heap;
    }

    public void print() {
        System.out.println(this.heap);
    }
}
