package kawre.algorithms.graph.Dijkstra;

import java.util.Arrays;

import kawre.datastructures.heap.MinIndexedHeap;
import kawre.util.Dist;
import kawre.util.Edge;
import kawre.util.Tuple;

public class DijkstraEager extends Dijkstra {
	public DijkstraEager(int n) {
		super(n);
	}

	public Tuple<int[], int[]> dijkstra(int start) {
		int[] dist = new int[n], prev = new int[n];

		return new Tuple<>(dist, prev);
	}
}
