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
		long[] fishes = new long[9];

		for (String s : input.split(","))
			fishes[Integer.parseInt(s)]++;

		for (int day = 0; day < days; day++) {
			long[] fishes_new = new long[9];

			for (int i = 0; i < 9; i++) {
				if (i == 0) {
					fishes_new[6] += fishes[0];
					fishes_new[8] += fishes[0];
				} else {
					fishes_new[i - 1] += fishes[i];
				}
			}
			fishes = fishes_new;
		}
		return Arrays.stream(fishes).sum();
	}
}