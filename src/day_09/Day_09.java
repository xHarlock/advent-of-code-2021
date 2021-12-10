package day_09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.AdventDay;

public class Day_09 extends AdventDay {

	private Node[][] grid;

	@Override
	public long part_1() {
		long danger_levels = 0;
		for (Node[] row : grid) {
			for (Node node : row) {
				if (node.isLowPoint())
					danger_levels += 1 + node.height;
			}
		}
		return danger_levels;
	}
	
	@Override
	public long part_2() {
		List<Long> sizes = new ArrayList<>();		
		for (Node[] row : grid) {
			for (Node node : row) {
				if (node.height != 9 && node.height != -1)
					sizes.add(getBasinSize(node));
			}
		}		
		Collections.sort(sizes);
		return sizes.get(sizes.size() - 3) * sizes.get(sizes.size() - 2) * sizes.get(sizes.size() - 1);
	}

	@Override
	public void initialize(String[] input) {
		int[][] raw_grid = toGrid(input);
		this.grid = new Node[raw_grid.length][raw_grid[0].length];

		// Convert grid elements to nodes and connect them
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Node node = new Node(j, i, raw_grid[i][j]);
				grid[i][j] = node;
				// Top
				if (i > 0) {
					node.neighbours.add(grid[i - 1][j]);
					grid[i - 1][j].neighbours.add(node);
				}
				// Left
				if (j > 0) {
					node.neighbours.add(grid[i][j - 1]);
					grid[i][j - 1].neighbours.add(node);
				}
			}
		}
	}

	/** Counts surrounding points of the same basin */	
	public long getBasinSize(Node node) {
		// Base case
		if (node.height == -1 || node.height == 9)
			return 0;
		
		node.height = -1;
		
		long size = 0;
		for (Node n : node.neighbours)
			size += getBasinSize(n);
		return 1 + size;
	}
}

class Node {
	int x, y;
	int height;
	List<Node> neighbours;

	public Node(int x, int y, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.neighbours = new ArrayList<>();
	}

	boolean isLowPoint() {
		for (Node node : neighbours) {
			if (node.height <= this.height)
				return false;
		}
		return true;
	}
}