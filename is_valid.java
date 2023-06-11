/**
 * This class provides a static method to validate whether a given number
 * can be placed at a specific location in a 9x9 Sudoku puzzle.
 */
public class is_valid {
	 /**
     * Checks if a number can be placed at the specified row and column
     * in a 9x9 Sudoku puzzle.
     *
     * @param sudoku The 9x9 Sudoku puzzle represented as a 2D integer array.
     * @param row The row in the Sudoku puzzle to check.
     * @param col The column in the Sudoku puzzle to check.
     * @param num The number that is attempted to be placed in the Sudoku puzzle.
     * @return True if the number can be placed at the specified location,
     *         false otherwise.
     */
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