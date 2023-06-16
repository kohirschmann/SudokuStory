/**
 * This class provides methods to assess the correctness of a player's 9x9 Sudoku board
 * compared to the solution board and to get the correct number that should be placed
 * in a specific location on the board.
 */
public class Sudoku_corrector {
	 /**
     * Calculates the correctness of the player's Sudoku board compared to the solution.
     * The correctness is calculated as the percentage of correctly filled cells,
     * with a correction factor for specific difficulties.
     *
     * @param playerBoard The Sudoku board filled out by the player.
     * @param solutionBoard The correct solution of the Sudoku puzzle.
     * @return The correctness of the player's Sudoku board as a percentage.
     */
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
    /**
     * Retrieves the correct number that should be placed at the specified row and
     * column on the Sudoku board.
     *
     * @param board The current state of the Sudoku board.
     * @param row The row in the Sudoku board to check.
     * @param col The column in the Sudoku board to check.
     * @return The correct number that should be placed at the specified location,
     *         or -1 if no valid number can be placed.
     */
        public static int getCorrectNumber(int[][] board, int row, int col) {
            for (int num = 1; num <= 9; num++) {
                if (is_valid.isvalid(board, row, col, num)) {
                    return num;
                }
            }
            return -1; // return -1 if no valid number can be placed at this position
        }
    }

