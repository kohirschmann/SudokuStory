import java.util.Random;
/**
 * This class provides a method for generating a valid 4x4 Sudoku puzzle.
 */
public class Sudoku_generator_4x4 {
	 /**
     * Generates a 4x4 Sudoku puzzle.
     * The puzzle is initially filled with five random numbers at random locations,
     * ensuring that each number is valid at its location. The puzzle is then solved
     * to check if it's valid. If it is, the puzzle with the initial five numbers is returned.
     * If it's not valid, the process repeats until a valid puzzle is generated.
     *
     * @return A 4x4 Sudoku puzzle represented as a 2D integer array.
     */
    public static int[][] sudokuGenerator() {
        int[][] array1 = new int[4][4];
        int[][] array2 = new int[4][4];
        Random rand = new Random();

        while (true) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    array1[i][j] = 0;
                }
            }
            int n = 0;
            while (n < 5) {
                int x = rand.nextInt(4);
                int y = rand.nextInt(4);
                int num = rand.nextInt(4) + 1;
                if (array1[x][y] == 0 && is_valid_4x4.isvalid(array1, x, y, num)){
                    array1[x][y] = num;
                    n++;
                }
            }
            for (int i = 0; i < 4; i++) {
                array2[i] = array1[i].clone();
            }
            if (Sudoku_solver_4x4.solve_sudoku(array2)) {
                return array1;
            }
        }
    }
}
