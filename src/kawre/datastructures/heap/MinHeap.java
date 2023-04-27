package kawre.datastructures.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class MinHeap<T> {
	private List<T> heap;

	public MinHeap() {
		this.heap = new ArrayList<T>();
	}

	public MinHeap(int n) {
		this.heap = new ArrayList<T>(n);
	}

	public T peek() {
		isNotEmptyOrThrow();

		return heap.get(0);
	}

	public T poll() {
		T res = peek();

		Collections.swap(heap, 0, heap.size() - 1);
		heap.remove(heap.size() - 1);

		siftdown(0);

		return res;
	}

	public void offer(T val) {
		valueNotNullOrThrow(val);

		heap.add(val);
		siftup(heap.size() - 1);
	}

	public boolean contains(T val) {
		valueNotNullOrThrow(val);

		return heap.contains(val);
	}

	private void siftdown(int i) {
		int n = heap.size();
		int left = (2 * i) + 1, right = (2 * i) + 2;

		while ((left < n && less(left, i)) || (right < n && less(right, i))) {
			int smallest = right >= n || less(left, right) ? left : right;
			Collections.swap(heap, i, smallest);
			i = smallest;
			left = (2 * i) + 1;
			right = (2 * i) + 2;
		}
	}

	private void siftup(int i) {
		int parent = (i - 1) / 2;

		while (i != 0 && less(i, parent)) {
			Collections.swap(heap, i, parent);
			i = parent;
			parent = (i - 1) / 2;
		}
	}

	public void clear() {
		heap.clear();
	}

	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@SuppressWarnings("unchecked")
	private boolean less(int i, int j) {
		return ((Comparable<? super T>) heap.get(i)).compareTo((T) heap.get(j)) < 0;
	}

	private void isNotEmptyOrThrow() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
	}

	private void valueNotNullOrThrow(Object value) {
		if (value == null)
			throw new IllegalArgumentException("value cannot be null");
	}

	@Override
	public String toString() {
		return heap.toString();
	}
}
