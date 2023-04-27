package kawre.algorithms.graph;

import java.util.Arrays;

import kawre.datastructures.heap.MinHeap;
import kawre.util.Dist;
import kawre.util.Edge;
import kawre.util.Tuple;

public class DijkstraLazy extends Dijkstra {
	public DijkstraLazy(int n) {
		super(n);
	}

	public Tuple<int[], int[]> dijkstra(int start) {
		nodeInBoundsOrThrow(start);

		boolean[] vis = new boolean[n];
		int[] dist = new int[n], prev = new int[n];
		Arrays.setAll(dist, (i) -> i == start ? 0 : Integer.MAX_VALUE);
		Arrays.fill(prev, -1);

		MinHeap<Dist> pq = new MinHeap<>(n);
		pq.offer(new Dist(start, 0));

		while (!pq.isEmpty()) {
			Dist node = pq.poll();
			vis[node.index] = true;

			if (dist[node.index] < node.dist)
				continue;

			for (Edge edge : graph.get(node.index)) {
				int newDist = dist[node.index] + edge.cost;
				if (vis[edge.to] || newDist >= dist[edge.to])
					continue;

				prev[edge.to] = node.index;
				dist[edge.to] = newDist;
				pq.offer(new Dist(edge.to, newDist));
			}
		}

		return new Tuple<>(dist, prev);
	}
}
