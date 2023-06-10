
public class Sudoku_solver {
	public static boolean solve_sudoku(int[][] sudoku) {
		for(int i = 0; i < 9; i ++ ) {
			for(int j = 0; j < 9; j ++) {
				if(sudoku[i][j] == 0) {
					for(int num = 1; num < 10; num ++) {
						if(is_valid.isvalid(sudoku, i, j, num)) {
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
