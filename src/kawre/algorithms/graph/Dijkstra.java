package kawre.algorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import kawre.datastructures.heap.MinHeap;
import kawre.datastructures.heap.MinIndexedHeap;
import kawre.util.Dist;
import kawre.util.Edge;
import kawre.util.Tuple;

public class Dijkstra {
	public static Tuple<int[], int[]> eager(Map<Integer, List<Edge>> graph, int start) {
		int n = graph.size();
		nodeInBoundsOrThrow(start, n);

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

	public static Tuple<int[], int[]> lazy(Map<Integer, List<Edge>> graph, int start) {
		int n = graph.size();
		nodeInBoundsOrThrow(start, n);

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

	public static int shortestPath(Map<Integer, List<Edge>> graph, int start, int end) {
		rangeInBoundsOrThrow(start, end, graph.size());

		return eager(graph, start).first[end];
	}

	public static List<Integer> findShortestPath(Map<Integer, List<Edge>> graph, int start, int end) {
		rangeInBoundsOrThrow(start, end, graph.size());

		List<Integer> path = new ArrayList<>();
		Tuple<int[], int[]> tuple = eager(graph, start);
		int[] dist = tuple.first, prev = tuple.second;

		if (dist[end] == Integer.MAX_VALUE)
			return path;

		for (int at = end; at != -1; at = prev[at])
			path.add(at);

		Collections.reverse(path);
		return path;
	}

	private static void nodeInBoundsOrThrow(int i, int n) {
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Node " + i + " out of range of [0," + n + ")");
	}

	private static void rangeInBoundsOrThrow(int start, int end, int n) {
		if (start > end)
			throw new IllegalArgumentException("Starting path can't be bigger than the ending path.");
		if (start < 0 || start >= n || end < 0 || end >= n)
			throw new IndexOutOfBoundsException("Path " + start + " -> " + end + " out of range of [0," + n + ")");
	}
}