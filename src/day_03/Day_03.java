package day_03;

import java.util.List;

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
			int bit = findMostCommonBit(input, i);
			gamma += bit;
			epsilon += bit == 1 ? 0 : 1;
		}

		return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}

	public static long part_2(String[] input) {
		List<String> list = arrayToList(input);

		for (int i = 0; i < input[0].length(); i++) {
			if (list.size() == 1)
				break;

			int most_common = findMostCommonBit(list, i);
			int index = i;
			list = list.stream().filter(s -> equals(s.charAt(index), most_common)).toList();
		}

		String oxygen = list.get(0);
		list = arrayToList(input);

		for (int i = 0; i < input[0].length(); i++) {
			if (list.size() == 1)
				break;

			int least_common = findMostCommonBit(list, i) == 1 ? 0 : 1;
			int index = i;
			list = list.stream().filter(s -> equals(s.charAt(index), least_common)).toList();
		}

		String co2 = list.get(0);
		return Long.parseLong(oxygen, 2) * Long.parseLong(co2, 2);
	}

	private static int findMostCommonBit(String[] array, int index) {
		int count = 0;
		for (int j = 0; j < array.length; j++) {
			char[] chars = array[j].toCharArray();
			if (chars[index] == '1')
				count++;
		}
		return count >= array.length - count ? 1 : 0;
	}

	private static int findMostCommonBit(List<String> list, int index) {
		int count = 0;
		for (int j = 0; j < list.size(); j++) {
			char[] chars = list.get(j).toCharArray();
			if (chars[index] == '1')
				count++;
		}
		return count >= list.size() - count ? 1 : 0;
	}

	private static boolean equals(char c, int i) {
		return Integer.parseInt(String.valueOf(c)) == i;
	}
}
