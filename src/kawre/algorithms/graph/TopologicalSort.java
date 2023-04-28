package kawre.algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		Map<Character, List<Character>> graph = new HashMap<>();

		for (char c = 'A'; c <= 'M'; c++)
			graph.put(c, new ArrayList<>());

		graph.get('A').add('D');

		graph.get('B').add('D');

		graph.get('C').add('B');
		graph.get('C').add('A');

		graph.get('D').add('H');
		graph.get('D').add('G');

		graph.get('E').add('A');
		graph.get('E').add('D');
		graph.get('E').add('F');

		graph.get('F').add('K');
		graph.get('F').add('J');

		graph.get('G').add('I');

		graph.get('H').add('J');
		graph.get('H').add('I');

		graph.get('I').add('L');

		graph.get('J').add('M');
		graph.get('J').add('L');

		graph.get('K').add('J');

		System.out.println(TopologicalSort.sort(graph));
	}
}
