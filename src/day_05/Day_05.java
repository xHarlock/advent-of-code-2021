package day_05;

import utils.AdventDay;

public class Day_05 extends AdventDay {

	private String[] input;
	private int[][] grid;

	@Override
	public long part_1() {
		int[][] grid = clone(this.grid);
		
		for (String s : input) {
			String[] parts = s.split(" -> ");
			Node n1 = new Node(parts[0]);
			Node n2 = new Node(parts[1]);

			// Vertical
			if (n1.x == n2.x && n1.y != n2.y) {
				for (int i = Math.min(n1.y, n2.y); i <= Math.max(n1.y, n2.y); i++)
					grid[i][n1.x]++;
			}

			// Horizontal
			else if (n1.x != n2.x && n1.y == n2.y) {
				for (int i = Math.min(n1.x, n2.x); i <= Math.max(n1.x, n2.x); i++)
					grid[n1.y][i]++;
			}
		}
		return count(grid);
	}

	@Override
	public long part_2() {
		int[][] grid = clone(this.grid);
		
		for (String s : input) {
			String[] parts = s.split(" -> ");
			Node n1 = new Node(parts[0]);
			Node n2 = new Node(parts[1]);

			// Vertical
			if (n1.x == n2.x && n1.y != n2.y) {
				for (int i = Math.min(n1.y, n2.y); i <= Math.max(n1.y, n2.y); i++)
					grid[i][n1.x]++;
			}

			// Horizontal
			else if (n1.x != n2.x && n1.y == n2.y) {
				for (int i = Math.min(n1.x, n2.x); i <= Math.max(n1.x, n2.x); i++)
					grid[n1.y][i]++;
			}

			// Diagonals
			else if (n1.x != n2.x && n1.y != n2.y) {

				// Top left to bottom right diagonal
				if (n1.x < n2.x && n1.y < n2.y || n2.x < n1.x && n2.y < n1.y) {
					Node start = n1.x < n2.x ? n1 : n2;
					Node end = n1.x < n2.x ? n2 : n1;
					int limit = end.x - start.x + 1;
					for (int i = 0; i < limit; i++)
						grid[start.y + i][start.x + i]++;
				}

				// Bottom left to top right diagonal
				else {
					Node start = n1.x < n2.x ? n1 : n2;
					Node end = n1.x < n2.x ? n2 : n1;
					int limit = end.x - start.x + 1;
					for (int i = 0; i < limit; i++)
						grid[start.y - i][start.x + i]++;
				}
			}
		}
		return count(grid);
	}

	@Override
	public void initialize(String[] input) {
		this.input = input;

		int length = 0, width = 0;
		for (String s : input) {
			String[] parts = s.split(" -> ");
			Node n1 = new Node(parts[0]);
			Node n2 = new Node(parts[1]);
			length = Math.max(length, Math.max(n1.y + 1, n2.y + 1));
			width = Math.max(width, Math.max(n1.x + 1, n2.x + 1));
		}
		this.grid = new int[length][width];
	}

	/**
	 * Counts all elements that are greater than 1
	 */
	private static int count(int[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				if (grid[i][j] > 1)
					count++;
		return count;
	}
}

class Node {
	int x, y;

	public Node(String s) {
		x = Integer.parseInt(s.split(",")[0]);
		y = Integer.parseInt(s.split(",")[1]);
	}

	@Override
	public String toString() {
		return "(" + x + "|" + y + ")";
	}
}
