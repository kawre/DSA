package kawre.datastructures.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import kawre.util.Tuple;

public class MinIndexedHeap<T> {
	private final Object[] vals;
	private final int[] pm, im;

	private final int N;

	private int sz;

	public MinIndexedHeap(int maxSize) {
		if (maxSize <= 0)
			throw new IllegalArgumentException("maxSize <= 0");

		N = maxSize;
		this.vals = new Object[N];
		this.pm = new int[N];
		this.im = new int[N];

		for (int i = 0; i < N; i++)
			pm[i] = im[i] = -1;
	}

	private void siftup(int i) {
		int parent = (i - 1) / 2;

		while (i != 0 && less(i, parent)) {
			swap(i, parent);
			i = parent;
			parent = (i - 1) / 2;
		}
	}

	private void siftdown(int i) {
		int left = (i * 2) + 1, right = left + 1;

		while ((left < size() && less(left, i)) || (right < size() && less(right, i))) {
			int smallest = right >= size() || less(left, right) ? left : right;
			swap(i, smallest);
			i = smallest;
			left = (i * 2) + 1;
			right = left + 1;
		}

	}

	// O(log(n))
	public void offer(int ki, T val) {
		keyNotExistsOrThrow(ki);
		valueNotNullOrThrow(val);

		setValue(ki, val);
		setIndex(ki, sz);
		setKeyIndex(sz, ki);

		siftup(sz++);
	}

	// O(log(n))
	public T delete(int ki) {
		keyExistsOrThrow(ki);

		int i = getIndex(ki);

		swap(i, --sz);
		siftdown(i);
		siftup(i);

		T val = valueOf(ki);
		setValue(ki, null);
		setIndex(ki, -1);
		setKeyIndex(sz, -1);

		return val;
	}

	// O(log(n))
	public T update(int ki, T val) {
		keyExistsAndValueNotNullOrThrow(ki, val);

		int i = getIndex(ki);
		T oldVal = valueOf(ki);
		setValue(ki, val);

		siftdown(i);
		siftup(i);

		return oldVal;
	}

	// O(1)
	public int peekMinKeyIndex() {
		isNotEmptyOrThrow();

		return getKeyIndex(0);
	}

	// O(log(n))
	public int pollMinKeyIndex() {
		int minKi = peekMinKeyIndex();
		delete(minKi);
		return minKi;
	}

	// O(1)
	public T peekMinValue() {
		isNotEmptyOrThrow();
		return valueOf(peekMinKeyIndex());
	}

	// O(log(n))
	public T pollMinValue() {
		T minVal = peekMinValue();
		delete(peekMinKeyIndex());
		return minVal;
	}

	public Tuple<Integer, T> peek() {
		return new Tuple<>(peekMinKeyIndex(), peekMinValue());
	}

	public Tuple<Integer, T> poll() {
		Tuple<Integer, T> tuple = peek();
		delete(tuple.first);
		return tuple;
	}

	public void decrease(int ki, T val) {
		keyExistsAndValueNotNullOrThrow(ki, val);

		if (!less(val, valueOf(ki)))
			return;

		setValue(ki, val);
		siftup(getIndex(ki));
	}

	public void increase(int ki, T val) {
		keyExistsAndValueNotNullOrThrow(ki, val);

		if (!less(valueOf(ki), val))
			return;

		setValue(ki, val);
		siftdown(getIndex(ki));
	}

	// O(1)
	public boolean contains(int ki) {
		keyInBoundsOrThrow(ki);

		return getIndex(ki) != -1;
	}

	private void swap(int i, int j) {
		if (i == j)
			return;
		setIndex(getKeyIndex(i), j);
		setIndex(getKeyIndex(j), i);
		int tmp = getKeyIndex(i);
		setKeyIndex(i, getKeyIndex(j));
		setKeyIndex(j, tmp);
	}

	public int size() {
		return sz;
	}

	public boolean isEmpty() {
		return sz == 0;
	}

	// O(1)
	@SuppressWarnings("unchecked")
	public T valueOf(int ki) {
		keyExistsOrThrow(ki);
		return (T) vals[ki];
	}

	private void setValue(int ki, T val) {
		vals[ki] = val;
	}

	private int getKeyIndex(int i) {
		return im[i];
	}

	private void setKeyIndex(int i, int ki) {
		im[i] = ki;
	}

	private int getIndex(int ki) {
		return pm[ki];
	}

	private void setIndex(int ki, int i) {
		pm[ki] = i;
	}

	// Tests if the value of node i < node j
	@SuppressWarnings("unchecked")
	private boolean less(int i, int j) {
		return ((Comparable<? super T>) valueOf(getKeyIndex(i))).compareTo((T) valueOf(getKeyIndex(j))) < 0;
	}

	@SuppressWarnings("unchecked")
	private boolean less(Object obj1, Object obj2) {
		return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
	}

	private void isNotEmptyOrThrow() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
	}

	private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
		keyExistsOrThrow(ki);
		valueNotNullOrThrow(value);
	}

	private void keyExistsOrThrow(int ki) {
		if (!contains(ki))
			throw new NoSuchElementException("Index does not exist; received: " + ki);
	}

	private void keyNotExistsOrThrow(int ki) {
		if (contains(ki))
			throw new IllegalArgumentException("index already exists; received: " + ki);
	}

	private void valueNotNullOrThrow(Object value) {
		if (value == null)
			throw new IllegalArgumentException("value cannot be null");
	}

	private void keyInBoundsOrThrow(int ki) {
		if (ki < 0 || ki >= N)
			throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
	}

	public String toString() {
		List<Integer> lst = new ArrayList<>(sz);
		for (int i = 0; i < sz; i++)
			lst.add(getKeyIndex(i));
		return lst.toString();
	}
}
