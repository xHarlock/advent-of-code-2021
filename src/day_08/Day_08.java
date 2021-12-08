package day_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.AdventDay;
import utils.Reader;

public class Day_08 extends AdventDay {

	public static void main(String[] args) {
		String[] input = Reader.getInput(8, 2021);

		System.out.println(part_1(input));
		System.out.println(part_2(input));
	}

	public static int part_1(String[] input) {
		List<Entry> entries = toEntries(input);
		int count = 0;
		for (Entry e : entries)
			count += e.count1478();
		return count;
	}

	public static int part_2(String[] input) {
		List<Entry> entries = toEntries(input);
		int sum = 0;
		for (Entry e : entries) {
			int temp = 0;
			for (String s : e.output)
				temp = temp * 10 + e.map.get(s);
			sum += temp;
		}
		return sum;
	}

	private static List<Entry> toEntries(String[] input) {
		List<Entry> entries = new ArrayList<>();
		for (String s : input)
			entries.add(new Entry(s));
		return entries;
	}
}

class Entry {
	String[] patterns;
	String[] output;
	Map<String, Integer> map;

	public Entry(String line) {
		String[] parts = line.split(" \\| ");
		patterns = parts[0].split(" ");
		output = parts[1].split(" ");

		sort(patterns);
		sort(output);
		
		map = new HashMap<>();
		fillMap();
	}

	public long count1478() {
		return Arrays.stream(output).filter(s -> s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7).count();
	}

	private void sort(String[] array) {
		for (int i = 0; i < array.length; i++) {
			char[] chars = array[i].toCharArray();
			Arrays.sort(chars);
			array[i] = new String(chars);
		}
	}

	private void fillMap() {		
		Arrays.sort(patterns, (a, b) -> Integer.compare(a.length(), b.length()));

		map.put(patterns[0], 1);
		map.put(patterns[1], 7);
		map.put(patterns[2], 4);
		map.put(patterns[9], 8);

		int six_idx = 0;
		
		for (int i = 6; i < 9; i++) {
			String s = patterns[i];

			if (matches(i, 0) && matches(i, 1) && matches(i, 2))
				map.put(s, 9);
			else if (matches(i, 0) && matches(i, 1))
				map.put(s, 0);
			else {
				map.put(s, 6);
				six_idx = i;
			}
		}

		for (int i = 3; i < 6; i++) {
			String s = patterns[i];
			
			if (matches(i, 0) && matches(i, 1))
				map.put(s, 3);
			else if (matches(six_idx, i))
				map.put(s, 5);
			else
				map.put(s, 2);
		}

	}

	private boolean matches(int i, int j) {
		for (char c : patterns[j].toCharArray()) {
			if (patterns[i].indexOf(c) == -1)
				return false;
		}
		return true;
	}
}
