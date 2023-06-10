
public class is_valid_4x4 {
		public static boolean isvalid(int[][] sudoku, int row, int col, int num){
			for (int i = 0; i < 4; i ++) {
				if (sudoku[row][i]  == num || sudoku[i][col] == num) {
					return false;
				}
			}
			int start_row = row - row % 2;
			int start_col = col - col % 2;
			for (int i = 0; i < 2; i ++) {
				for (int j = 0; j < 2; j++) {
					if (sudoku[i + start_row][j + start_col] == num) {
						return false;
					}
				}
			}
			return true;
	}
}
