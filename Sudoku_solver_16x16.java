
public class Sudoku_solver_16x16 {
	public static boolean solve_sudoku(int[][] sudoku) {
	for(int i = 0; i < 16; i ++ ) {
		for(int j = 0; j < 16; j ++) {
			if(sudoku[i][j] == 0) {
				for(int num = 1; num < 17; num ++) {
					if(is_valid_16x16.isvalid(sudoku, i, j, num)) {
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


