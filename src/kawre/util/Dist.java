package kawre.util;

public class Dist implements Comparable<Dist> {
	public int index;
	public int dist;

	public Dist(Tuple<Integer, Integer> tuple) {
		this.index = tuple.first;
		this.dist = tuple.second;
	}

	public Dist(int index, int distance) {
		this.index = index;
		this.dist = distance;
	}

	@Override
	public int compareTo(Dist o) {
		return Integer.compare(this.dist, o.dist);
	}

	@Override
	public String toString() {
		return "(" + index + ", " + dist + ")";
	}
}
