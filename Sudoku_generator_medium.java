
import java.util.Random;
/**
 * This class provides a method for generating a valid 9x9 Sudoku puzzle (medium mode).
 */
public class Sudoku_generator_medium {

	/**
     * Generates a 9x9 Sudoku puzzle (easy mode).
     * The puzzle is initially filled with twenty five random numbers at random locations,
     * ensuring that each number is valid at its location. The puzzle is then solved
     * to check if it's valid. If it is, the puzzle with the initial thirty five numbers is returned.(retrieved from the solved puzzle)
     * If it's not valid, the process repeats until a valid puzzle is generated.
     *
     * @return A 9x9 Sudoku puzzle (easy mode) represented as a 2D integer array.
     */
	public static int [][] sudokuGenerator() {
			int[][] array1 = new int[9][9];
	        int[][] array2 = new int[9][9];
	        Random rand = new Random();

	        while (true) {
	            for (int i = 0; i < 9; i++) {
	                for (int j = 0; j < 9; j++) {
	                    array1[i][j] = 0;
	                }
	            }
	            int n = 0;
	            while (n < 25) {
	                int x = rand.nextInt(9);
	                int y = rand.nextInt(9);
	                int num = rand.nextInt(9) + 1;
	                if (array1[x][y] == 0 && is_valid.isvalid(array1, x, y, num)){
	                    array1[x][y] = num;
	                    n++;
	                }
	            }
	            for (int i = 0; i < 9; i++) {
	                array2[i] = array1[i].clone();
	            }
	            if (Sudoku_solver.solve_sudoku(array2)) {
	            	int m = 0;
	            	while(m < 46) {
	            		int x = rand.nextInt(9);
	            		int y = rand.nextInt(9);
	            		if (array2[x][y] != 0) {
	            			array2[x][y] = 0;
	            			m += 1;
	            	   }
	               }
	            	return array2;
	            }
	        }
	}
}