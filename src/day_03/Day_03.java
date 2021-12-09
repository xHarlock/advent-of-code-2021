package day_03;

import java.util.Arrays;

import utils.AdventDay;

public class Day_03 extends AdventDay {

	private String[] input;
	
	@Override
	public long part_1() {
		String gamma = "";
		String epsilon = "";
		
		for (int i = 0; i < input[0].length(); i++) {
			int bit = getMCB(input, i);
			gamma += bit;
			epsilon += bit == 1 ? 0 : 1;
		}
		return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}

	@Override
	public long part_2() {
		String[] o2_array = Arrays.copyOf(input, input.length);
		String[] co2_array = Arrays.copyOf(input, input.length);

		for (int i = 0; i < input[0].length(); i++) {
			int index = i;

			if (o2_array.length > 1) {
				// mcb = Most common bit
				int mcb = getMCB(o2_array, i);
				o2_array = Arrays.stream(o2_array).filter(s -> equals(s.charAt(index), mcb)).toArray(String[]::new);
			}

			if (co2_array.length > 1) {
				// lcb = Least common bit
				int lcb = getMCB(co2_array, i) == 1 ? 0 : 1;
				co2_array = Arrays.stream(co2_array).filter(s -> equals(s.charAt(index), lcb)).toArray(String[]::new);
			}
		}
		return Integer.parseInt(o2_array[0], 2) * Integer.parseInt(co2_array[0], 2);
	}

	/**
	 * Returns the most common bit of all strings at a given index
	 */
	private int getMCB(String[] array, int index) {
		int count = 0;
		for (int j = 0; j < array.length; j++) {
			char[] chars = array[j].toCharArray();
			if (chars[index] == '1')
				count++;
		}
		return count >= array.length - count ? 1 : 0;
	}

	private boolean equals(char c, int i) {
		return Integer.parseInt(String.valueOf(c)) == i;
	}

	@Override
	public void initialize(String[] input) {
		this.input = input;
	}
}
