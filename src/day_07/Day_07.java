package day_07;

import java.util.Arrays;

import utils.AdventDay;

public class Day_07 extends AdventDay {

	private int[] input;

	@Override
	public long part_1() {
		int[] array = new int[input.length];
		int max_pos = Arrays.stream(input).max().getAsInt();
		int cheapest = Integer.MAX_VALUE;

		for (int i = 0; i < max_pos; i++) {
			for (int j = 0; j < input.length; j++)
				array[j] = Math.abs(i - input[j]);

			int fuel = Arrays.stream(array).sum();

			if (fuel < cheapest)
				cheapest = fuel;
		}
		return cheapest;
	}

	@Override
	public long part_2() {
		int[] array = new int[input.length];
		int max_pos = Arrays.stream(input).max().getAsInt();
		int cheapest = Integer.MAX_VALUE;

		for (int i = 0; i < max_pos; i++) {
			for (int j = 0; j < input.length; j++) {
				int cost = Math.abs(i - input[j]);
				array[j] = cost * (cost + 1) / 2;
			}
			int fuel = Arrays.stream(array).sum();

			if (fuel < cheapest)
				cheapest = fuel;
		}
		return cheapest;
	}

	@Override
	public void initialize(String[] input) {
		this.input = toIntArray(input[0].split(","));
	}
}
