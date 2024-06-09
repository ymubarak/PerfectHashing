package model;

import java.io.File;
import java.util.Scanner;

public class ReadFile {

	private static int max;

	public static int[] read(File file) {
		String[] keysAsString = null;
		int[] keys = null;
		max = -1;
		try (Scanner scan = new Scanner(file);) {
			while (scan.hasNext()) {
				// store the values as strings
				keysAsString = scan.next().split(",");
			}
			// restore the values as 32-bit integers
			keys = new int[keysAsString.length];
			for (int j = 0; j < keys.length; j++) {
				keys[j] = Integer.parseInt(keysAsString[j]);
				if (keys[j] > max)
					max = keys[j];
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Bad File Format !.");
		}
		return keys;
	}

	public static int getMax() {
		return max;
	}
}
