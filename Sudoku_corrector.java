
public class Sudoku_corrector {
  
    public static float getCorrectness(int[][] playerBoard, int[][] solutionBoard, int difficulty_option) {
        int correctCount = 0;
        int GRID_SIZE = 9;
        int totalCount = GRID_SIZE * GRID_SIZE;
        int mode_number;
        
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (playerBoard[i][j] == solutionBoard[i][j]) {
                    correctCount++;
                }
            }
        }
        if (difficulty_option == 1 ) {
        	mode_number = 45;
        }
        else if (difficulty_option == 2) {
        	mode_number = 35;
        }
        else {
        	mode_number = 25;
        }
        return ((float)(correctCount - mode_number) / (totalCount - mode_number))* 100;
    }
    	
        public static int getCorrectNumber(int[][] board, int row, int col) {
            for (int num = 1; num <= 9; num++) {
                if (is_valid.isvalid(board, row, col, num)) {
                    return num;
                }
            }
            return -1; // return -1 if no valid number can be placed at this position
        }
    }

