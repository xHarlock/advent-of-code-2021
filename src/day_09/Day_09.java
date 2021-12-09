package day_09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.AdventDay;


// TODO Clean up this mess


public class Day_09 extends AdventDay {

	private String[] input;

	@Override
	public long part_1() {
		int[][] grid = toGrid(input);

		int danger_levels = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// Top row
				if (i == 0) {
					// Leftmost col
					if (j == 0) {
						if (grid[i][j] < grid[i + 1][j] && grid[i][j] < grid[i][j + 1])
							danger_levels += grid[i][j] + 1;
					}

					// Rightmost col
					else if (j == grid[i].length - 1) {
						if (grid[i][j] < grid[i + 1][j] && grid[i][j] < grid[i][j - 1])
							danger_levels += grid[i][j] + 1;
					}

					// Else
					else {
						if (grid[i][j] < grid[i + 1][j] && grid[i][j] < grid[i][j + 1] && grid[i][j] < grid[i][j - 1])
							danger_levels += grid[i][j] + 1;
					}
				}

				// Bottom row
				else if (i == grid.length - 1) {
					// Leftmost col
					if (j == 0) {
						if (grid[i][j] < grid[i - 1][j] && grid[i][j] < grid[i][j + 1])
							danger_levels += grid[i][j] + 1;
					}

					// Rightmost col
					else if (j == grid[i].length - 1) {
						if (grid[i][j] < grid[i - 1][j] && grid[i][j] < grid[i][j - 1])
							danger_levels += grid[i][j] + 1;
					}

					// Else
					else {
						if (grid[i][j] < grid[i - 1][j] && grid[i][j] < grid[i][j + 1] && grid[i][j] < grid[i][j - 1])
							danger_levels += grid[i][j] + 1;
					}
				}

				// Middle
				else {
					// Leftmost col
					if (j == 0) {
						if (grid[i][j] < grid[i + 1][j] && grid[i][j] < grid[i - 1][j] && grid[i][j] < grid[i][j + 1])
							danger_levels += grid[i][j] + 1;
					}

					// Rightmost col
					else if (j == grid[i].length - 1) {
						if (grid[i][j] < grid[i + 1][j] && grid[i][j] < grid[i - 1][j] && grid[i][j] < grid[i][j - 1])
							danger_levels += grid[i][j] + 1;
					}

					// Else
					else {
						if (grid[i][j] < grid[i + 1][j] && grid[i][j] < grid[i - 1][j] && grid[i][j] < grid[i][j + 1]
								&& grid[i][j] < grid[i][j - 1])
							danger_levels += grid[i][j] + 1;
					}
				}
			}
		}

		return danger_levels;
	}

	@Override
	public long part_2() {
		int[][] grid = toGrid(input);

		List<Long> sizes = new ArrayList<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != 9 && grid[i][j] != -1)
					sizes.add(getBasinSize(j, i, grid));
			}
		}

		Collections.sort(sizes);
		return sizes.get(sizes.size() - 3) * sizes.get(sizes.size() - 2) * sizes.get(sizes.size() - 1);
	}
	
	@Override
	public void initialize(String[] input) {
		this.input = input;
	}

	/**
	 * Counts surrounding points of the same basin
	 */
	public static long getBasinSize(int x, int y, int[][] grid) {
		// Base cases
		if (y < 0 || y >= grid.length)
			return 0;
		if (x < 0 || x >= grid[0].length)
			return 0;
		if (grid[y][x] == -1 || grid[y][x] == 9)
			return 0;

		grid[y][x] = -1;

		return 1 + getBasinSize(x - 1, y, grid) + getBasinSize(x + 1, y, grid) + getBasinSize(x, y - 1, grid)
				+ getBasinSize(x, y + 1, grid);
	}
}