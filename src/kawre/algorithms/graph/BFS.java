package kawre.algorithms.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import kawre.util.Dummy;

public class BFS {
	public static <T> void iterative(Map<T, List<T>> graph, T start) {
		Set<T> vis = new HashSet<>();
		Queue<T> queue = new LinkedList<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			T node = queue.poll();
			if (!vis.add(node))
				continue;

			for (T child : graph.get(node))
				queue.add(child);
		}
	}

	public static void test() {
		BFS.iterative(Dummy.graphInt(), 0);
	}
}
