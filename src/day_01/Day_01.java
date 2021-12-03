package day_01;

import utils.AdventDay;
import utils.Reader;

public class Day_01 extends AdventDay {

	public static void main(String[] args) {
		int[] input = convert(Reader.getInput(1, 2021));

		System.out.println(part_1(input));
		System.out.println(part_2(input));
	}

	public static int part_1(int[] input) {
		int count = 0;
		
		for (int i = 0; i < input.length - 1; i++) {
			if (input[i + 1] > input[i])
				count++;
		}
		
		return count;
	}

	public static int part_2(int[] input) {
		int count = 0;
		
		for (int i = 0; i < input.length - 3; i++) {
			int curr = input[i] + input[i + 1] + input[i + 2];
			int next = input[i + 1] + input[i + 2] + input[i + 3];
			
			if (next > curr)
				count++;
		}
		
		return count;
	}
}
