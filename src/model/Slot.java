package model;

import java.util.function.Function;

public class Slot {

	 Function<Integer, Integer> h;
	 Pair<Integer, Object>[] s;

	public Slot(Pair<Integer, Object>[] sj, Function<Integer, Integer> hj) {
		s = sj;
		h = hj;
	}
}
