package day_12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.AdventDay;

public class Day_12 extends AdventDay {

	private Map<String, List<String>> connections;

	@Override
	public long part_1() {
		return countPaths(new ArrayList<>(List.of("start")), false);
	}

	@Override
	public long part_2() {
		return countPaths(new ArrayList<>(List.of("start")), true);
	}

	@Override
	public void initialize(String[] input) {
		connections = new HashMap<>();
		for (String s : input) {
			String[] caves = s.split("-");
			connections.putIfAbsent(caves[0], new ArrayList<>());
			connections.putIfAbsent(caves[1], new ArrayList<>());
			connections.get(caves[0]).add(caves[1]);
			connections.get(caves[1]).add(caves[0]);
		}
	}

	private long countPaths(List<String> path, boolean revisit) {
		// Base case, last cave is the exit
		if (path.get(path.size() - 1).equals("end")) {
			printPath(path);
			return 1;
		}

		int count = 0;
		
		for (String cave : connections.get(path.get(path.size() - 1)))
			// Big caves can revisited any times and small caves only once
			if (cave.equals(cave.toUpperCase()) || !path.contains(cave)) {
				path.add(cave);
				count += countPaths(path, revisit);
				path.remove(path.size() - 1);
			}
			// Visit small caves at most twice
			else if (revisit && !cave.equals("start") && !cave.equals("end")) {
				path.add(cave);
				count += countPaths(path, false);
				path.remove(path.size() - 1);
			}
		return count;
	}

	private void printPath(List<String> path) {
		System.out.print(path.get(0));
		for (int i = 1; i < path.size(); i++)
			System.out.print("," + path.get(i));
		System.out.println();
	}
}
