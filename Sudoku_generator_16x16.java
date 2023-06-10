
import java.util.Random;

public class Sudoku_generator_16x16 {

    public static void main(String[] args) {
        int[][] generatedSudoku = sudokuGenerator();
        printSudoku(generatedSudoku);
        System.out.println("");
        System.out.println("");
        if (Sudoku_solver_16x16.solve_sudoku(generatedSudoku)) {
        	printSudoku(generatedSudoku);
        }
        	
    }
    
    public static int[][] sudokuGenerator() {
        int[][] array1 = new int[16][16];
        int[][] array2 = new int[16][16];
        Random rand = new Random();

        while (true) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    array1[i][j] = 0;
                }
            }
            int n = 0;
            while (n < 25) {
                int x = rand.nextInt(16);
                int y = rand.nextInt(16);
                int num = rand.nextInt(16) + 1;
                if (array1[x][y] == 0 && is_valid_16x16.isvalid(array1, x, y, num)){
                    array1[x][y] = num;
                    n++;
                }
            }
            for (int i = 0; i < 16; i++) {
                array2[i] = array1[i].clone();
            }
            if (Sudoku_solver_16x16.solve_sudoku(array2)) {
                return array1;
            }
        }
    }

    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0 && i != 0) {
                System.out.println("- - - - - - - - - - - - - - - - - - - - - -");
            }
            for (int j = 0; j < 16; j++) {
                if (j % 4 == 0 && j != 0) {
                    System.out.print(" | ");
                }
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}
