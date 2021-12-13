package day_11;

import utils.AdventDay;

public class Day_11 extends AdventDay {

	private String[] input;
	private int[][] grid;

	@Override
	public long part_1() {
		long solution = 0;
		for (int i = 0; i < 100; i++)
			solution += step();
		return solution;
	}

	@Override
	public long part_2() {
		initialize(input);
		int steps = 0;
		// Keeps going until all squids flash at once
		while (true) {
			if (isSynchronized(grid))
				return steps;
			step();
			steps++;
		}
	}

	@Override
	public void initialize(String[] input) {
		this.input = input;
		grid = toGrid(input);
	}

	private long step() {
		int[][] new_grid = new int[10][10];
		boolean[][] flashed = new boolean[10][10];
		
		// Increment all values
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				new_grid[i][j] = grid[i][j] + 1;
			}
		}
		
		// Flash
		int cycles = 0;
		while (flash(new_grid, flashed)) {
			cycles++;
		}
		
		// Set squids to 0
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (flashed[i][j]) {
					flashed[i][j] = false;
					new_grid[i][j] = 0;
				}
			}
		}
		
		// Store new values in the grid
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				grid[i][j] = new_grid[i][j];
			}
		}
		return cycles;
	}

	/*
	 * Flash all neighbours
	 */
	private boolean flash(int[][] grid, boolean[][] flashed) {
		// Position of neighbours		
		int[] xs = { -1, 0, 1, 1, 1, 0, -1, -1};
		int[] ys = { 1, 1, 1, 0, -1, -1, -1, 0};
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// Squid hasn't flashed yet
				if (grid[i][j] > 9 && !flashed[i][j]) {
					flashed[i][j] = true;
					for (int k = 0; k < xs.length; k++) {
						// Flashes neighbours
						if (!outOfBounds(i + xs[k], j + ys[k], grid))
							grid[i + xs[k]][j + ys[k]]++;
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check if all squids are flashing at once
	 */
	private boolean isSynchronized(int[][] grid) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid[i][j] != 0)
					return false;
			}
		}
		return true;
	}
}
