package kawre.algorithms.graph;

public class UnionFind {
	private final int C, N;

	private int[] parent, rank;
	private int count;

	public UnionFind(int maxSize, boolean indexed) {
		this.N = maxSize;
		maxSizeInBoundsOrThrow();

		this.parent = new int[N];
		this.rank = new int[N];
		this.C = indexed ? 0 : 1;
		this.count = N;

		for (int i = 0; i < N; i++) {
			parent[i] = i + C;
			rank[i] = 1;
		}
	}

	public void union(int[] edge) {
		edgeInBoundsOrThrow(edge);

		if (connected(edge))
			return;

		int p1 = find(edge[0]) - C, p2 = find(edge[1]) - C;

		if (rank[p1] > rank[p2]) {
			parent[p2] = p1 + C;
			rank[p1] += rank[p2];
		} else {
			parent[p1] = p2 + C;
			rank[p2] += rank[p1];
		}

		count--;
	}

	private int find(int n) {
		if (parent[n - C] == n)
			return n;
		return parent[n - C] = find(parent[n - C]);
	}

	private boolean connected(int[] edge) {
		return find(edge[0]) == find(edge[1]);
	}

	public int getCount() {
		return count;
	}

	private void edgeInBoundsOrThrow(int[] edge) {
		if (edge[0] >= N + C || edge[0] < C || edge[1] >= N + C || edge[1] < C)
			throw new IllegalArgumentException("edge out of range [" + C + "," + (N + C) + ")");
	}

	private void maxSizeInBoundsOrThrow() {
		if (N < 2)
			throw new IllegalArgumentException("maxSize must be in [2,∞)");
	}
}