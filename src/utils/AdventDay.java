package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AdventDay {

	public static int[] convert(String[] array) {
		return Arrays.stream(array).mapToInt(Integer::parseInt).toArray();
	}
	
	public static List<Integer> arrayToList(int[] array) {
		return Arrays.stream(array).boxed().toList();
	}
	
	public static List<String> arrayToList(String[] array){
		return Arrays.stream(array).toList();
	}
	
	public static int[] listToArray(List<Integer> list) {
		return list.stream().mapToInt(k -> k).toArray();
	}
	
	public static void print2DArray(int[][] array) {		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++)
				System.out.print(array[i][j] + " ");
			System.out.print("\n");
		}
	}
	
	public static void printMap(Map<Integer, Long> map) {
		for (Integer c : map.keySet())
			System.out.println(c + ": " + map.get(c));
	}

	public static void printMap2(Map<String, Integer> map) {
		for (String s : map.keySet())
			System.out.println(s + ": " + map.get(s));
	}
}
