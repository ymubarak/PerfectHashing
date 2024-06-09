package runner;

import java.io.File;

import model.HashTableN;
import model.HashTableN2;
import model.ReadFile;

public class Runner {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\win8.1\\Desktop\\testCases_lab4\\keys10000001000000.txt");
		int[] keys = ReadFile.read(file);
		
		
		//test O(N^2)
//		 HashTableN2 h2 = new HashTableN2(keys);
//		 System.out.println("Ratio : " +h2.spaceRatio());
//		 System.out.println("rehash : " +h2.getRehashsNum());
		
		//test O(N)
		 HashTableN h = new HashTableN(keys);
		 System.out.println("Ratio : " + h.spaceRatio());
		 System.out.println("order : " + h.spaceOrder());
		 System.out.println("rehash : " + h.getRehashsNum());
	
	}
}
