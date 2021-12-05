package day_03;

import java.util.Arrays;

import utils.AdventDay;
import utils.Reader;

public class Day_03 extends AdventDay {

	public static void main(String[] args) {
		String[] input = Reader.getInput(3, 2021);

		System.out.println(part_1(input));
		System.out.println(part_2(input));
	}

	public static long part_1(String[] input) {
		String gamma = "";
		String epsilon = "";

		for (int i = 0; i < input[0].length(); i++) {
			int bit = getMCB(input, i);
			gamma += bit;
			epsilon += bit == 1 ? 0 : 1;
		}
		
		return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}

	public static int part_2(String[] input) {
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
	private static int getMCB(String[] array, int index) {
		int count = 0;
		for (int j = 0; j < array.length; j++) {
			char[] chars = array[j].toCharArray();
			if (chars[index] == '1')
				count++;
		}
		return count >= array.length - count ? 1 : 0;
	}

	private static boolean equals(char c, int i) {
		return Integer.parseInt(String.valueOf(c)) == i;
	}
}
