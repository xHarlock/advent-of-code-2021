package day_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.AdventDay;

public class Day_04 extends AdventDay {

	private String[] input;
	private int[] drawn;
	private List<Board> boards;

	@Override
	public long part_1() {
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

	@Override
	public long part_2() {
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

	@Override
	public void initialize(String[] input) {
		this.input = input;
		String[] groups = String.join("\n", input).split("\n\n");
		drawn = toIntArray(groups[0].split(","));
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
