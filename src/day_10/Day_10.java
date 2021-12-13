package day_10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import utils.AdventDay;

public class Day_10 extends AdventDay {

	private String[] input;
	private Map<String, String> pairs;
	/** Cost for each corrupted character */
	private Map<String, Integer> corrupted;
	/** Cost for each missing character */
	private Map<String, Integer> missing;
	
	@Override
	public long part_1() {
		int score = 0;
		for (String line : input) {
			Stack<String> expected = new Stack<>();
			for (String s : line.split("")) {
				// ( [ { <
				if (pairs.containsKey(s)) 
					expected.push(pairs.get(s));
				// ) ] } >
				else { 
					if (!s.equals(expected.pop())) {
						score += corrupted.get(s);
						break;
					}
				}
			}
		}
		return score;
	}

	@Override
	public long part_2() {
		List<Long> scores = new ArrayList<>();
		for (String line : input) {
			LinkedList<String> missing = new LinkedList<>();
			
			boolean next = false;
			for (String s : line.split("")) {
				// ( [ { <
				if (pairs.containsKey(s))
					missing.addLast(pairs.get(s));
				// ) ] } >
				else {
					if (!s.equals(missing.removeLast())) {
						next = true;
						break;
					}
				}
			}
			if (next)
				continue;

			long score = 0;
			for (int i = missing.size() - 1; i >= 0; i--) {
				score *= 5;
				score += this.missing.get(missing.get(i));
			}
			scores.add(score);
		}

		if (scores.size() == 0)
			return -1;

		Collections.sort(scores);
		return scores.get(scores.size() / 2);
	}

	@Override
	public void initialize(String[] input) {
		this.input = input;
		this.pairs = Map.ofEntries(
				Map.entry("(", ")"),
				Map.entry("[", "]"),
				Map.entry("{", "}"),
				Map.entry("<", ">"));
		this.corrupted = Map.ofEntries(
				Map.entry(")", 3),
				Map.entry("]", 57),
				Map.entry("}", 1197),
				Map.entry(">", 25137));
		this.missing = Map.ofEntries(
				Map.entry(")", 1),
				Map.entry("]", 2),
				Map.entry("}", 3),
				Map.entry(">", 4));
	}
}
