package kawre.datastructures.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		int n = 15;
		MinIndexedHeap<Integer> ipq = new MinIndexedHeap<>(n);

		ipq.insert(1, 5);
		ipq.insert(2, 1);

		System.out.println(ipq);
		ipq.pollMinValue();
		System.out.println(ipq);
	}
}
