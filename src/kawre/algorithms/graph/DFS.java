package kawre.algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import kawre.util.Dummy;

public class DFS {

	public static <T> void iterative(Map<T, List<T>> graph, T start) {
		Set<T> vis = new HashSet<>();
		Stack<T> stack = new Stack<>();
		stack.add(start);

		while (!stack.isEmpty()) {
			T node = stack.pop();
			if (!vis.add(node))
				continue;

			System.out.print(node + " ");
			for (T child : graph.get(node))
				stack.push(child);
		}
	}

	public static <T> void recursive(Map<T, List<T>> graph, Set<T> vis, T cur) {
		if (!vis.add(cur))
			return;

		System.out.print(cur + " ");
		for (T child : graph.get(cur))
			recursive(graph, vis, child);
	}

	public static void test() {
		System.out.print("Iterative: ");
		DFS.iterative(Dummy.graph(), 'A');
		System.out.println();

		System.out.print("Recursive: ");
		DFS.recursive(Dummy.graph(), new HashSet<>(), 'A');
		System.out.println();
	}
}
