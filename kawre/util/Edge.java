package Util;

public class Edge {
	public int to;
	public int cost;

	public Edge(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "(" + to + ", " + cost + ")";
	}
}
