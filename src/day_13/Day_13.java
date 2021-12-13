package day_13;

import java.util.ArrayList;
import java.util.List;

import utils.AdventDay;

public class Day_13 extends AdventDay {

	private int[][] paper;
	private record Fold(String axis, int value) {}
	private List<Fold> folds;
	private int width;
	private int height;

	@Override
	public long part_1() {
		makeFold(folds.get(0));

		long count = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (paper[i][j] > 0)
					count++;
			}
		}
		return count;
	}

	@Override
	public long part_2() {
		for (Fold fold : folds)
			makeFold(fold);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(paper[i][j] == 0 ? " " : "#");
			}
			System.out.println();
		}
		return -1;
	}

	@Override
	public void initialize(String[] input) {
		folds = new ArrayList<>();
		List<Integer> xs = new ArrayList<>();
		List<Integer> ys = new ArrayList<>();

		for (String s : input) {
			// Empty line
			if (s.length() == 0)
				continue;
			// Fold instruction
			if (s.contains("fold")) {
				String[] parts = s.replace("fold along ", "").split("=");
				folds.add(new Fold(parts[0], Integer.parseInt(parts[1])));
				continue;
			}
			// Dot
			String[] parts = s.split(",");
			xs.add(Integer.parseInt(parts[0]));
			ys.add(Integer.parseInt(parts[1]));
		}

		width = xs.stream().mapToInt(k -> k).max().getAsInt() + 1;
		height = ys.stream().mapToInt(k -> k).max().getAsInt() + 1;

		paper = new int[height][width];
		for (int i = 0; i < xs.size(); i++)
			paper[ys.get(i)][xs.get(i)] = 1;
	}

	private void makeFold(Fold fold) {
		// Vertical
		if (fold.axis.equals("x")) {
			int col = fold.value;
			for (int i = 0; i < height; i++) {
				int other_col = col - 1;
				for (int j = col + 1; j < width; j++) {
					paper[i][other_col] += paper[i][j];
					other_col--;
				}
			}
			width = col;
		}
		// Horizontal
		else {
			int row = fold.value, other_row = row - 1;
			for (int i = row + 1; i < height; i++) {
				for (int j = 0; j < width; j++) {
					paper[other_row][j] += paper[i][j];
				}
				other_row--;
			}
			height = row;
		}
	}
}
