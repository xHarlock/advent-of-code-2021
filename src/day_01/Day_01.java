package day_01;

import utils.AdventDay;

public class Day_01 extends AdventDay {

	private int[] input;
	
	@Override
	public long part_1() {
		int count = 0;
		for (int i = 0; i < input.length - 1; i++) {
			if (input[i + 1] > input[i])
				count++;
		}
		return count;
	}

	@Override
	public long part_2() {
		int count = 0;
		for (int i = 0; i < input.length - 3; i++) {
			int curr = input[i] + input[i + 1] + input[i + 2];
			int next = input[i + 1] + input[i + 2] + input[i + 3];
			if (next > curr)
				count++;
		}
		return count;
	}
	
	@Override
	public void initialize(String[] input) {
		this.input = toIntArray(input);
	}
}
