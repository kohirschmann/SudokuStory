
public class Sudoku_solver_4x4{
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
