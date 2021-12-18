package day_14;

import java.util.HashMap;
import java.util.Map;

import utils.AdventDay;

public class Day_14 extends AdventDay {

	private String[] input;
	private String template;
	private Map<String, String> rules;
	private Map<String, Long> pair_count;

	@Override
	public long part_1() {
		return run(10);
	}

	@Override
	public long part_2() {
		// Reset map
		initialize(input);
		return run(40);
	}

	@Override
	public void initialize(String[] input) {
		this.input = input;

		// Process input
		template = input[0];
		rules = new HashMap<>();
		for (int i = 2; i < input.length; i++) {
			String[] parts = input[i].split(" -> ");
			rules.put(parts[0], parts[1]);
		}

		// Initialize quantity map with starting values
		this.pair_count = new HashMap<>();
		for (int i = 0; i < template.length() - 1; i++)
			pair_count.merge("" + template.charAt(i) + template.charAt(i + 1), 1L, Long::sum);
	}

	private long run(int steps) {
		for (int i = 0; i < steps; i++) {
			Map<String, Long> temp = new HashMap<>();
			for (String key : pair_count.keySet()) {
				// CH -> B => CB and BH
				temp.merge(key.charAt(0) + rules.get(key), pair_count.get(key), Long::sum);
				temp.merge(rules.get(key) + key.charAt(1), pair_count.get(key), Long::sum);
			}
			pair_count = temp;
		}

		// Map with count of each letter
		Map<Character, Long> char_count = new HashMap<>();
		for (Map.Entry<String, Long> entry : pair_count.entrySet())
			char_count.merge(entry.getKey().charAt(0), entry.getValue(), Long::sum);

		// Remaining letter since we always took the first letter of each pair
		char_count.merge(template.charAt(template.length() - 1), 1L, Long::sum);

		// Get quantity of most common and least common element
		long max = char_count.values().stream().max(Long::compareTo).get();
		long min = char_count.values().stream().min(Long::compareTo).get();

		return max - min;
	}
}