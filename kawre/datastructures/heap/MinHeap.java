package kawre.datastructures.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinHeap {
	private List<Integer> heap;

	public MinHeap() {
		this.heap = new ArrayList<Integer>();
	}

	public MinHeap(List<Integer> list) {
		heapify(list);
	}

	public MinHeap(int[] nums) {
		heapify(nums);
	}

	public Integer peek() {
		return heap.size() > 0 ? heap.get(0) : null;
	}

	public Integer poll() {
		if (heap.size() == 0)
			return null;

		Collections.swap(heap, 0, heap.size() - 1);
		int res = heap.remove(heap.size() - 1);
		siftdown(0);
		return res;
	}

	private void heapify(int[] nums) {
		this.heap = new ArrayList<>(Arrays.stream(nums).boxed().toList());
		for (int i = heap.size() - 1; i >= 0; i--)
			siftdown(i);
	}

	private void heapify(List<Integer> list) {
		this.heap = list;
		for (int i = heap.size() - 1; i >= 0; i--)
			siftdown(i);
	}

	public void add(int val) {
		heap.add(val);
		siftup(heap.size() - 1);
	}

	private void siftdown(int i) {
		int n = heap.size();
		int left = (2 * i) + 1, right = (2 * i) + 2;

		while ((left < n && heap.get(i) > heap.get(left)) || (right < n && heap.get(i) > heap.get(right))) {
			int smallest = right >= n || heap.get(left) < heap.get(right) ? left : right;
			Collections.swap(heap, i, smallest);
			i = smallest;
			left = (2 * i) + 1;
			right = (2 * i) + 2;
		}
	}

	private void siftup(int i) {
		int parent = (i - 1) / 2;

		while (i != 0 && heap.get(parent) > heap.get(i)) {
			Collections.swap(heap, i, parent);
			i = parent;
			parent = (i - 1) / 2;
		}
	}

	@Override
	public String toString() {
		return heap.toString();
	}
}
