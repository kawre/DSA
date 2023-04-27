package algorithms.Dijkstra;

import java.util.Arrays;

import Util.Edge;

public class Main {
	public static void main(String[] args) {
		int n = 6;
		Dijkstra dijkstra = new DijkstraLazy(n);

		dijkstra.addEdge(0, new Edge(1, 5));
		dijkstra.addEdge(0, new Edge(2, 1));
		dijkstra.addEdge(1, new Edge(4, 20));
		dijkstra.addEdge(1, new Edge(3, 3));
		dijkstra.addEdge(1, new Edge(2, 2));
		dijkstra.addEdge(2, new Edge(1, 3));
		dijkstra.addEdge(2, new Edge(4, 12));
		dijkstra.addEdge(3, new Edge(2, 3));
		dijkstra.addEdge(3, new Edge(4, 2));
		dijkstra.addEdge(3, new Edge(5, 6));
		dijkstra.addEdge(4, new Edge(5, 1));

		// dijkstra.addEdge(0, new Edge(1, 4));
		// dijkstra.addEdge(0, new Edge(2, 1));
		// dijkstra.addEdge(1, new Edge(3, 1));
		// dijkstra.addEdge(2, new Edge(1, 2));
		// dijkstra.addEdge(2, new Edge(3, 5));
		// dijkstra.addEdge(3, new Edge(4, 3));

		System.out.println(Arrays.toString(dijkstra.dijkstra(3).first));
		System.out.println(dijkstra.findShortestPath(3, 5));
		System.out.println(dijkstra.shortestPath(3, 5));
	}
}
