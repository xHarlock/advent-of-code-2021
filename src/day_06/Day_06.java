package day_06;

import java.util.Arrays;

import utils.AdventDay;

public class Day_06 extends AdventDay {

	private int[] input;
	
	@Override
	public long part_1() {
		return runSimulation(80);
	}

	@Override
	public long part_2() {
		return runSimulation(256);
	}

	@Override
	public void initialize(String[] input) {
		this.input = toIntArray(input[0].split(","));
	}
	
	private long runSimulation(int days) {
		long[] fishes = new long[9];

		for (int i : input)
			fishes[i]++;

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