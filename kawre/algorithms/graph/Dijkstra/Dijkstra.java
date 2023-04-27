package kawre.algorithms.graph.Dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kawre.util.Edge;
import kawre.util.Tuple;

public abstract class Dijkstra {
	protected List<List<Edge>> graph;
	protected int n;

	public Dijkstra(int n) {
		this.n = n;
		createEmptyGraph();
	}

	abstract public Tuple<int[], int[]> dijkstra(int start);

	public int shortestPath(int start, int end) {
		pathInBoundsOrThrow(start, end);

		return this.dijkstra(start).first[end];
	}

	public List<Integer> findShortestPath(int start, int end) {
		pathInBoundsOrThrow(start, end);

		List<Integer> path = new ArrayList<>();
		Tuple<int[], int[]> tuple = this.dijkstra(start);
		int[] dist = tuple.first, prev = tuple.second;

		if (dist[end] == Integer.MAX_VALUE)
			return path;

		for (int at = end; at != -1; at = prev[at])
			path.add(at);

		Collections.reverse(path);
		return path;
	}

	public void addEdge(int from, Edge edge) {
		nodeInBoundsOrThrow(from);
		edgeInBoundsOrThrow(edge.to);

		graph.get(from).add(edge);
	}

	private void createEmptyGraph() {
		graph = new ArrayList<>(n);
		for (int i = 0; i < n; i++)
			graph.add(new ArrayList<>());
	}

	protected void edgeInBoundsOrThrow(int to) {
		if (to < 0 || to >= n)
			throw new IndexOutOfBoundsException("Edge " + to + " out of range of [0," + n + ")");
	}

	protected void nodeInBoundsOrThrow(int i) {
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Node " + i + " out of range of [0," + n + ")");
	}

	protected void pathInBoundsOrThrow(int start, int end) {
		if (start > end)
			throw new IllegalArgumentException("Starting path can't be bigger than the ending path.");
		if (start < 0 || start >= n || end < 0 || end >= n)
			throw new IndexOutOfBoundsException("Path " + start + " -> " + end + " out of range of [0," + n + ")");
	}

	@Override
	public String toString() {
		return graph.toString();
	}
}