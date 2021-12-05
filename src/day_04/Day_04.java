package day_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.AdventDay;
import utils.Reader;

public class Day_04 extends AdventDay {

	private static int[] drawn;
	private static List<Board> boards;

	public static void main(String[] args) {
		String[] input = Reader.getInput(4, 2021);

		System.out.println(part_1(input));
		System.out.println(part_2(input));
	}

	public static int part_1(String[] input) {
		initialize(input);
		
		Board winner = null;

		for (int i = 0; i < drawn.length; i++) {
			for (Board board : boards) {
				board.drawn(drawn[i]);

				if (board.checkBingo()) {
					board.last = drawn[i];
					winner = board;
					break;
				}
			}

			if (winner != null)
				break;
		}

		return winner.sum * winner.last;
	}

	public static int part_2(String[] input) {
		initialize(input);
		
		Board last_winner = null;

		for (int i = 0; i < drawn.length; i++) {
			for (Board board : boards) {
				if (board.finished)
					continue;

				board.drawn(drawn[i]);

				if (board.checkBingo()) {
					board.finished = true;
					board.last = drawn[i];
					last_winner = board;
				}
			}
		}

		return last_winner.sum * last_winner.last;
	}

	private static void initialize(String[] input) {
		String[] groups = String.join("\n", input).split("\n\n");
		drawn = convert(groups[0].split(","));
		boards = new ArrayList<>();

		for (int i = 1; i < groups.length; i++)
			boards.add(new Board(groups[i].split("\n")));
	}
}

class Board {
	boolean finished;
	int[][] values;
	boolean[][] hits;
	int last;
	int sum;

	public Board(String[] lines) {
		values = new int[5][5];
		hits = new boolean[5][5];

		for (int i = 0; i < 5; i++) {
			Scanner scanner = new Scanner(lines[i]);
			
			for (int j = 0; j < 5; j++) {
				values[i][j] = scanner.nextInt();
				sum += values[i][j];
			}
			
			scanner.close();
		}
	}

	public void drawn(int number) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (values[i][j] == number) {
					hits[i][j] = true;
					sum -= number;
				}
			}
		}
	}

	public boolean checkBingo() {
		for (int i = 0; i < 5; i++) {
			boolean wins_row = true, wins_col = true;

			for (int j = 0; j < 5; j++) {
				if (hits[i][j] == false)
					wins_row = false;
				if (hits[j][i] == false)
					wins_col = false;
			}

			if (wins_row || wins_col)
				return true;
		}
		
		return false;
	}
}
