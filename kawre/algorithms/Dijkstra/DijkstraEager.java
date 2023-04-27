package algorithms.Dijkstra;

import java.util.Arrays;

import Util.Dist;
import Util.Edge;
import Util.Tuple;
import datastructures.PriorityQueue.MinIndexedHeap;

public class DijkstraEager extends Dijkstra {
	public DijkstraEager(int n) {
		super(n);
	}

	public Tuple<int[], int[]> dijkstra(int start) {
		int[] dist = new int[n], prev = new int[n];

		return new Tuple<>(dist, prev);
	}
}
