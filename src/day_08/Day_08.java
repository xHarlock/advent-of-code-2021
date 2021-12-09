package day_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.AdventDay;

public class Day_08 extends AdventDay {

	private List<Entry> entries;
	
	@Override
	public long part_1() {
		return entries.stream().mapToLong(e -> e.count1478()).sum();
	}

	@Override
	public long part_2() {
		int sum = 0;
		for (Entry entry : entries) {
			int temp = 0;
			for (String s : entry.output)
				temp = temp * 10 + entry.map.get(s);
			sum += temp;
		}
		return sum;
	}
	
	@Override
	public void initialize(String[] input) {
		this.entries = new ArrayList<>();
		for (String s : input)
			entries.add(new Entry(s));
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

		sortLetters(patterns);
		sortLetters(output);
		fillMap();
	}

	public long count1478() {
		return Arrays.stream(output).filter(s -> s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7).count();
	}

	private void sortLetters(String[] array) {		
		for (int i = 0; i < array.length; i++) {
			char[] chars = array[i].toCharArray();
			Arrays.sort(chars);
			array[i] = new String(chars);
		}
	}

	private void fillMap() {
		// Sort patterns by length
		Arrays.sort(patterns, (a, b) -> Integer.compare(a.length(), b.length()));

		map = new HashMap<>();
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
			// Letter not found
			if (!patterns[i].contains(String.valueOf(c)))
				return false;
		}
		return true;
	}
}
