import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import day_01.Day_01;
import day_02.Day_02;
import day_03.Day_03;
import day_04.Day_04;
import day_05.Day_05;
import day_06.Day_06;
import day_07.Day_07;
import day_08.Day_08;
import day_09.Day_09;
import day_10.Day_10;
import utils.AdventDay;
import utils.Reader;

public class Main {

	private static List<AdventDay> days;

	public static void main(String[] args) {
		days = new ArrayList<>();
		addAllDays();

		//print(10, false);
		printAll();
	}

	static void print(int day_no, boolean test) {
		AdventDay day = days.get(day_no - 1);
		String[] input = null;

		if (test)
			input = Reader.readLines("./resources/day_" + format(day_no) + "_input_small.txt");
		else
			input = Reader.getInput(day_no, 2021);

		day.initialize(input);
		System.out.println("Day " + day_no);
		System.out.println("Part 1: " + day.part_1());
		System.out.println("Part 2: " + day.part_2());
	}

	static void printAll() {
		for (int i = 0; i < days.size(); i++) {
			String[] input = Reader.getInput(i + 1, 2021);
			AdventDay day = days.get(i);

			day.initialize(input);
			System.out.println("Day " + (i + 1));
			System.out.println("Part 1: " + day.part_1());
			System.out.println("Part 2: " + day.part_2() + "\n");
		}
	}
	
	static public String format(int day) {
		return new DecimalFormat("00").format(day);
	}
	
	static void addAllDays() {
		days.add(new Day_01());
		days.add(new Day_02());
		days.add(new Day_03());
		days.add(new Day_04());
		days.add(new Day_05());
		days.add(new Day_06());
		days.add(new Day_07());
		days.add(new Day_08());
		days.add(new Day_09());
		days.add(new Day_10());
	}
}
