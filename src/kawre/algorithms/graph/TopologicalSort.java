package kawre.algorithms.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kawre.util.Dummy;

public class TopologicalSort {
	public static <T> List<T> sort(Map<T, List<T>> graph) {
		LinkedList<T> res = new LinkedList<>();
		for (T v : graph.keySet())
			topSortDFS(graph, res, v);

		return res;
	}

	private static <T> void topSortDFS(Map<T, List<T>> graph, LinkedList<T> res, T cur) {
		if (res.contains(cur))
			return;

		for (T next : graph.get(cur))
			topSortDFS(graph, res, next);

		res.addFirst(cur);
	}

	public static void test() {
		System.out.println(TopologicalSort.sort(Dummy.graphChar()));
	}
}
