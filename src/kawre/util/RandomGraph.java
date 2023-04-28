package kawre.util;

// Create a Random Graph Using
// Random Edge Generation in Java
import java.util.*;

public class RandomGraph {

	public int vertices;
	public int edges;

	// Set a maximum limit to the vertices
	final int MAX_LIMIT = 20;

	// A Random instance to generate random values
	Random random = new Random();
	// An adjacency list to represent a graph
	public List<List<Integer>> adjacencyList;

	// Creating the constructor
	public RandomGraph() {
		// Set a maximum limit for the
		// number of vertices say 20
		this.vertices = random.nextInt(MAX_LIMIT) + 1;

		// compute the maximum possible number of edges
		// and randomly choose the number of edges less than
		// or equal to the maximum number of possible edges
		this.edges = random.nextInt(computeMaxEdges(vertices)) + 1;

		// Creating an adjacency list
		// representation for the random graph
		adjacencyList = new ArrayList<>(vertices);
		for (int i = 0; i < vertices; i++)
			adjacencyList.add(new ArrayList<>());

		// A for loop to randomly generate edges
		for (int i = 0; i < edges; i++) {
			// Randomly select two vertices to
			// create an edge between them
			int v = random.nextInt(vertices);
			int w = random.nextInt(vertices);

			// Check if there is already an edge between v
			// and w
			if ((v == w)
					|| adjacencyList.get(v).contains(w)) {
				// Reduce the value of i
				// so that again v and w can be chosen
				// for the same edge count
				i = i - 1;
				continue;
			}

			// Add an edge between them if
			// not previously created
			addEdge(v, w);
		}
	}

	// Method to compute the maximum number of
	// possible edges for a given number of vertices
	int computeMaxEdges(int numOfVertices) {
		// As it is a directed graph
		// So, for a given number of vertices
		// there can be at-most v*(v-1) number of edges
		return numOfVertices * (numOfVertices - 1);
	}

	// Method to add edges between given vertices
	void addEdge(int v, int w) {
		// Note: it is a directed graph

		// Add w to v's adjacency list
		adjacencyList.get(v).add(w);
	}
}
