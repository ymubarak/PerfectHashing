package model;

import java.util.Random;
import java.util.function.Function;

public class UniversalHashFunction {

	private long a;
	private long b;
	private long p;
	private int m;
	private Random r;
	// the Universal Hash Function
	private final Function<Integer, Integer> h = k -> (int) ((a * k + b) % p) % m;

	public Function<Integer, Integer> getHashFunction(int size) {
		long seed = (long) (Math.random() * 1000000);
		r = new Random(seed);
		a = r.nextInt();
		b = r.nextInt();
		if (a < 0)
			a *= -1;
		if (b < 0)
			b *= -1;
		int current = Math.max(size, ReadFile.getMax());
		p = getAprim(current); // largest prime integer that any 32-bit k
								// will be in it's range
		m = size;
		System.out.println(p);
		return h; // returns the generated hash function
	}

	private long getAprim(int current) {
		boolean isPrime = false;
		while (!isPrime) {
			current++;
			isPrime = true;
			for (int i = 2; i <= Math.sqrt(current); i++) {
				if (current % i == 0) {
					isPrime = false;
				}
			}
		}
		return current;
	}

}
