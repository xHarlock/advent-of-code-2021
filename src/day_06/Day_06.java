package day_06;

import java.util.Arrays;

import utils.AdventDay;
import utils.Reader;

public class Day_06 extends AdventDay {

	public static void main(String[] args) {
		String[] input = Reader.getInput(6, 2021);
		
		System.out.println(part_1(input[0]));
		System.out.println(part_2(input[0]));
	}

	public static long part_1(String input) {
		return runSimulation(80, input);
	}

	public static long part_2(String input) {
		return runSimulation(256, input);
	}

	private static long runSimulation(int days, String input) {
		long[] school = new long[9];

		for (String s : input.split(","))
			school[Integer.parseInt(s)]++;

		for (int day = 0; day < days; day++) {
			long[] new_school = new long[9];

			for (int i = 0; i < 9; i++) {
				if (i == 0) {
					new_school[6] += school[0];
					new_school[8] += school[0];
				} else {
					new_school[i - 1] += school[i];
				}
			}
			school = new_school;
		}
		return Arrays.stream(school).sum();
	}
}