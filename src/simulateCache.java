import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class simulateCache {

	public static int cost = 0;
	public static Map<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>();

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {

		try {
			FileInputStream fis = new FileInputStream("cache.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Map<Integer, Integer> temp = (Map<Integer, Integer>) ois
					.readObject();
			if (temp != null) {
				cache = temp;
			}
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			// ioe.printStackTrace();
			// return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}

		calculateAnswer();
		System.out.println("Cost is : " + cost);

		try {
			FileOutputStream fos = new FileOutputStream("cache.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cache);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void calculateAnswer() {
		System.out.println("Enter the number : ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int inputInteger = input.nextInt();
		Integer inputIntegerObject = new Integer(inputInteger);
		if (cache.containsKey(inputIntegerObject)) {
			System.out.println("Fetched from the cache");
			System.out.println("The sum is : " + cache.get(inputIntegerObject));
		} else {
			if (cache.size() == 11) {
				List<Integer> list = new ArrayList<Integer>(cache.keySet());
				cache.remove(list.get(0));

			}
			helper(inputIntegerObject);
		}
	}

	public static void helper(Integer input) {
		int sum = 0;
		for (int i = 1; i <= input.intValue(); i++) {
			cost++;
			sum = sum + i;
		}
		cache.put(input, new Integer(sum));
		System.out.println("Not found in cache");
		System.out.println("The sum is : " + cache.get(input));
	}

}
