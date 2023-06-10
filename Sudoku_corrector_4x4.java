
public class Sudoku_corrector_4x4 {
	public static float getCorrectness(int[][] playerBoard, int[][] solutionBoard) {
        int correctCount = 0;
        int GRID_SIZE = 4;
        int totalCount = GRID_SIZE * GRID_SIZE;

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (playerBoard[i][j] == solutionBoard[i][j]) {
                    correctCount++;
                }
            }
        }

        return ((float)(correctCount - 5) / (totalCount - 5))* 100;
    }
    
        public static int getCorrectNumber(int[][] board, int row, int col) {
            for (int num = 1; num <= 4; num++) {
                if (is_valid_4x4.isvalid(board, row, col, num)) {
                    return num;
                }
            }
            return -1; // return -1 if no valid number can be placed at this position
        }
}
