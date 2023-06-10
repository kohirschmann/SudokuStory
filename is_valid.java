public class is_valid {
	public static boolean isvalid(int[][] sudoku, int row, int col, int num){
		for (int i = 0; i < 9; i ++) {
			if (sudoku[row][i]  == num || sudoku[i][col] == num) {
				return false;
			}
		}
		int start_row = row - row % 3;
		int start_col = col - col % 3;
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				if (sudoku[i + start_row][j + start_col] == num) {
					return false;
				}
			}
		}
		return true;
}
}