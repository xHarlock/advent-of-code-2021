package utils;

public class Print2DTable {

	public static void print(int[][] arr) {
		for (int i = 0; i <= arr.length - 1; i++) {
			for (int j = 0; j <= arr[0].length - 1; j++)
				System.out.print(arr[i][j] + " ");

			System.out.print("\n");
		}
	}
}
