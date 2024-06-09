package model;

import java.util.ArrayList;
import java.util.function.Function;

public class HashTableN {

	private UniversalHashFunction u;
	private Function<Integer, Integer> h;
	private int tableSize;
	private Slot[] table;
	private int numOfRehashs;
	private double space;

	public HashTableN(int[] keys) {
		u = new UniversalHashFunction();
		// max keys length 2^31-1 = 2147483647
		tableSize = keys.length;
		space = tableSize;
		numOfRehashs = 0;
		initialize(keys);
	}

	private void initialize(int[] keys) {
		table = new Slot[tableSize];
		h = u.getHashFunction(tableSize);
		ArrayList<Integer>[] freqArray = new ArrayList[tableSize];
		for (int i = 0; i < keys.length; i++) {
			int index = h.apply(keys[i]);
			if (freqArray[index] == null)
				freqArray[index] = new ArrayList<Integer>();
			freqArray[index].add(keys[i]);
		}
		insert(freqArray);
	}

	private void insert(ArrayList<Integer>[] freqArray) {
		for (int i = 0; i < freqArray.length; i++) {
			if (freqArray[i] != null) {
				if (freqArray[i].size() == 1) {
					int value = freqArray[i].get(0);
					Pair<Integer, Object>[] arr = new Pair[1];
					arr[0] = new Pair<Integer, Object>(value, null);
					table[i] = new Slot(arr, null);
					space += 1;
				} else {
					HashTableN2 hj = new HashTableN2(freqArray[i]);
					numOfRehashs += hj.getRehashsNum();
					table[i] = new Slot(hj.getTable(), hj.getH());
					space += hj.getTable().length;
				}
			}
		}
	}

	public boolean containsKey(int key) {
		int index = h.apply(key);
		if (table[index] == null)
			return false;

		if (table[index].s.length == 1) {
			if (table[index].s[0].first == key)
				return true;
			return false;
		} else {
			int newIndex = table[index].h.apply(key);
			if (table[index].s[newIndex] == null || table[index].s[newIndex].first != key)
				return false;
			return true;
		}

	}

	public Object get(int k) {
		if (!containsKey(k))
			return null;
		int index = h.apply(k);
		return table[index];
	}

	public int getRehashsNum() {
		return numOfRehashs;
	}

	public double spaceRatio() {
		return (double) 100 * (tableSize / space);
	}

	public double spaceOrder() {
		return (double) (space / tableSize);
	}
}
