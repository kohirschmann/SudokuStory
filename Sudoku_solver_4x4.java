/**
 * This class provides a method to solve a 4x4 Sudoku puzzle.
 */
public class Sudoku_solver_4x4{
	 /**
     * Solves a given 4x4 Sudoku puzzle.
     * The method works by recursively trying out possible numbers for each empty cell in the Sudoku grid.
     * If no valid number can be found for a particular cell, it backtracks and tries a different number for the previous cell.
     * If all cells are filled in a valid way, the Sudoku is solved.
     *
     * @param sudoku The 4x4 Sudoku puzzle to solve, represented as a 2D integer array.
     * @return A boolean indicating whether the Sudoku puzzle was successfully solved.
     */
	public static boolean solve_sudoku(int[][] sudoku) {
	for(int i = 0; i < 4; i ++ ) {
		for(int j = 0; j < 4; j ++) {
			if(sudoku[i][j] == 0) {
				for(int num = 1; num < 5; num ++) {
					if(is_valid_4x4.isvalid(sudoku, i, j, num)) {
						sudoku[i][j] = num;
						if (solve_sudoku(sudoku) == true) {
							return true;	
						}
						sudoku[i][j] = 0;
					}
				}
				return false;
			}
		}
	}
	return true;
}

}
