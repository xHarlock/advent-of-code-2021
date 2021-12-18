package day_15;

import utils.AdventDay;

public class Day_15 extends AdventDay {

	private int[][] grid;

	@Override
	public long part_1() {
		return getLeastRiskyPath();
	}

	@Override
	public long part_2() {
		expandGrid();
		return getLeastRiskyPath();
	}

	@Override
	public void initialize(String[] input) {
		this.grid = toGrid(input);
	}

	/**
	 * Method that calculates the total risk of the least risky path from top left
	 * to bottom right.
	 */
	private int getLeastRiskyPath() {
		int[][] sums = new int[grid.length][grid[0].length];

		// Initializes each node with arbitrary high value
		for (int i = 0; i < sums.length; i++) {
			for (int j = 0; j < sums[i].length; j++) {
				sums[i][j] = 10000;
			}
		}

		sums[sums.length - 1][sums[0].length - 1] = 0;

		boolean changed = true;

		while (changed) {
			changed = false;

			// Iterate from bottom right to top left
			for (int i = sums.length - 1; i >= 0; i--) {
				for (int j = sums[i].length - 1; j >= 0; j--) {
					int min = Integer.MAX_VALUE;
					// Top
					if (i - 1 >= 0)
						min = Math.min(min, grid[i - 1][j] + sums[i - 1][j]);
					// Bottom
					if (i + 1 < sums.length)
						min = Math.min(min, grid[i + 1][j] + sums[i + 1][j]);
					// Left
					if (j - 1 >= 0)
						min = Math.min(min, grid[i][j - 1] + sums[i][j - 1]);
					// Right
					if (j + 1 < sums[i].length)
						min = Math.min(min, grid[i][j + 1] + sums[i][j + 1]);

					int old_risk = sums[i][j];
					sums[i][j] = Math.min(sums[i][j], min);

					// Risk sum of current node is now smaller
					if (sums[i][j] != old_risk)
						changed = true;
				}
			}
		}

		// Risk sum of top left node is the solution
		return sums[0][0];
	}

	/**
	 * Expands the original tile to a 5x5 tile area. For each tile to the right or
	 * downward, the risk levels are 1 higher. Risk levels above 9 wrap back around
	 * to 1.
	 */
	private void expandGrid() {
		int[][] new_grid = new int[5 * grid.length][5 * grid.length];
		int[][] copy = clone(grid);

		// Expand first tile to the right
		for (int k = 0; k < 5 * grid.length; k += grid.length) {
			for (int i = 0; i < grid.length; i++)
				for (int j = 0; j < grid.length; j++)
					new_grid[i][j + k] = copy[i][j];
			increment(copy);
		}

		// Make copy of top tile row
		copy = new int[grid.length][5 * grid.length];
		for (int i = 0; i < copy.length; i++)
			for (int j = 0; j < new_grid[0].length; j++)
				copy[i][j] = new_grid[i][j];

		// Expand downwards
		for (int k = grid.length; k < 5 * grid.length; k += grid.length) {
			increment(copy);
			for (int i = 0; i < grid.length; i++)
				for (int j = 0; j < new_grid.length; j++)
					new_grid[i + k][j] = copy[i][j];
		}
		
		this.grid = new_grid;
	}

	private void increment(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 9)
					grid[i][j] = 1;
				else
					grid[i][j]++;
			}
		}
	}
}