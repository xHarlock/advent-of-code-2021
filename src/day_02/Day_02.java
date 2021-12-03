package day_02;

import utils.AdventDay;
import utils.Reader;

public class Day_02 extends AdventDay {

	public static void main(String[] args) {
		String[] input = Reader.getInput(2, 2021);

		System.out.println(part_1(input));
		System.out.println(part_2(input));
	}

	public static int part_1(String[] input) {
		int x = 0;
		int depth = 0;

		for (String s : input) {
			String[] args = s.split(" ");

			if (args[0].equals("forward"))
				x += Integer.parseInt(args[1]);
			else if (args[0].equals("down"))
				depth += Integer.parseInt(args[1]);
			else
				depth -= Integer.parseInt(args[1]);
		}
		
		return x * depth;
	}

	public static long part_2(String[] input) {
		long horizontal = 0;
		long aim = 0;
		long depth = 0;

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
}