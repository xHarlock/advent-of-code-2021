package day_02;

import utils.AdventDay;

public class Day_02 extends AdventDay {

	private String[] input;
	
	@Override
	public long part_1() {
		long x = 0, depth = 0;		
		for (String s : input) {
			String[] args = s.split(" ");
			if (args[0].equals("forward"))
				x += Long.parseLong(args[1]);
			else if (args[0].equals("down"))
				depth += Long.parseLong(args[1]);
			else
				depth -= Long.parseLong(args[1]);
		}
		return x * depth;
	}

	@Override
	public long part_2() {
		long horizontal = 0, aim = 0, depth = 0;		
		for (String s : input) {
			String[] args = s.split(" ");
			if (args[0].equals("forward")) {
				horizontal += Long.parseLong(args[1]);
				depth += aim * Long.parseLong(args[1]);
			} else if (args[0].equals("down")) {
				aim += Long.parseLong(args[1]);
			} else {
				aim -= Long.parseLong(args[1]);
			}
		}
		return horizontal * depth;
	}

	@Override
	public void initialize(String[] input) {
		this.input = input;
	}
}
