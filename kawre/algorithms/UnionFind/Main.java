package algorithms.UnionFind;

public class Main {
	public static void main(String[] args) {
		int[][] edges = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } };
		UnionFind uf = new UnionFind(5, false);

		for (int[] edge : edges)
			uf.union(edge);

		System.out.println(uf.getCount());
	}
}
