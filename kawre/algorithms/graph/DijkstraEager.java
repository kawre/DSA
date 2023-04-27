package kawre.algorithms.graph;

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
		boolean[] vis = new boolean[n];
		int[] dist = new int[n], prev = new int[n];
		Arrays.setAll(dist, (i) -> i == start ? 0 : Integer.MAX_VALUE);
		Arrays.fill(prev, -1);

		MinIndexedHeap<Integer> ipq = new MinIndexedHeap<>(n);
		ipq.offer(start, 0);

		while (!ipq.isEmpty()) {
			Dist node = new Dist(ipq.poll());
			vis[node.index] = true;

			if (dist[node.index] < node.dist)
				continue;

			for (Edge edge : graph.get(node.index)) {
				int newDist = dist[node.index] + edge.cost;
				if (vis[edge.to] || newDist >= dist[edge.to])
					continue;

				dist[edge.to] = newDist;
				prev[edge.to] = node.index;

				if (!ipq.contains(edge.to))
					ipq.offer(edge.to, newDist);
				else
					ipq.decrease(edge.to, newDist);
			}
		}

		return new Tuple<>(dist, prev);
	}
}
