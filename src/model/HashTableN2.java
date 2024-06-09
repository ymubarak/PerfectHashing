package model;

import java.util.ArrayList;
import java.util.function.Function;

import javax.management.RuntimeErrorException;

public class HashTableN2 {
	private UniversalHashFunction u;
	private Function<Integer, Integer> h;
	private int tableSize;
	private Pair<Integer, Object>[] table;
	private int numOfRehashs;
	private double spaceOcc;

	public HashTableN2(int[] keys) {
		u = new UniversalHashFunction();
		// max m=n^2 must be integer so max m = 2^31-1 = 2147483647
		// max keys length sqrt(2147483647) = 46340
		spaceOcc = 0;
		tableSize = keys.length * keys.length;
		if (keys.length <= 46340) {
			numOfRehashs = 0;
			hash(keys);
		}else
			System.out.println("N is too large to Method 2");
	}

	public HashTableN2(ArrayList<Integer> k) {
		int[] keys = new int[k.size()];
		for (int i = 0; i < k.size(); i++) {
			keys[i] = k.get(i);
		}
		u = new UniversalHashFunction();
		// max m=n^2 must be integer so max m = 2^31-1 = 2147483647
		// max keys length sqrt(2147483647) = 46340
		spaceOcc = 0;
		tableSize = keys.length * keys.length;
		numOfRehashs = 0;
		hash(keys);
	}

	@SuppressWarnings("unchecked")
	private void hash(int[] keys) {
		table = new Pair[tableSize];
		h = u.getHashFunction(tableSize);
		insert(keys);
	}

	private void insert(int[] keys) {
		int index;
		boolean collison = false;
		for (int i = 0; i < keys.length; i++) {
			index = h.apply(keys[i]);
			if (table[index] == null) {
				table[index] = new Pair<Integer, Object>(keys[i], null);
				spaceOcc++;
			} else if (table[index].first != keys[i]) {
				// collision occurs ,rehash
				collison = true;
				numOfRehashs++;
				System.out.println("collision " + keys[i]);
				break;
			}else{
				System.out.println("duplicates" +  keys[i]);
				//throw new RuntimeException();
			}
		}

		if (collison) {
			hash(keys);
		}
	}

	public boolean containsKey(int key) {
		int index = h.apply(key);
		if (table[index] == null || table[index].first != key)
			return false;
		return true;
	}

	public Object get(int k) {
		if (!containsKey(k))
			return null;
		int index = h.apply(k);
		return table[index].second;
	}

	public int getRehashsNum() {
		return numOfRehashs;
	}

	public Pair<Integer, Object>[] getTable() {
		return table;
	}

	public Function<Integer, Integer> getH() {
		return h;
	}

	public double spaceRatio() {
		return (double) 100 * (spaceOcc / tableSize);
	}
	
	public double spaceOrder() {
		return (double) (tableSize / spaceOcc);
	}
}
