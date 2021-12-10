package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AdventDay {

	public abstract long part_1();

	public abstract long part_2();

	public abstract void initialize(String[] input);

	/** Method that runs a subprogram, such as a visualization */
	public void runSpecial() {
		System.out.println("Nothing special!");
	}

	public int[] toIntArray(String[] input) {
		return Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
	}

	public static int[][] toGrid(String[] input) {
		int[][] grid = new int[input.length][input[0].length()];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++)
				grid[i][j] = Integer.parseInt(String.valueOf(input[i].charAt(j)));
		return grid;
	}

	public List<Integer> arrayToList(int[] array) {
		return Arrays.stream(array).boxed().toList();
	}

	public List<String> arrayToList(String[] array) {
		return Arrays.stream(array).toList();
	}

	public int[] listToArray(List<Integer> list) {
		return list.stream().mapToInt(k -> k).toArray();
	}

	public <T> Map<T, T> swap(Map<T, T> map) {
		return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
	}

	public int[][] clone(int[][] array) {
		int[][] copy = new int[array.length][];
		for (int i = 0; i < array.length; i++)
			copy[i] = array[i].clone();
		return copy;
	}

	public void print2DArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++)
				System.out.print(array[i][j] + " ");
			System.out.print("\n");
		}
	}

	public void printMap(Map<Integer, Long> map) {
		for (Integer c : map.keySet())
			System.out.println(c + ": " + map.get(c));
	}

	public void printMap2(Map<String, Integer> map) {
		for (String s : map.keySet())
			System.out.println(s + ": " + map.get(s));
	}
}
