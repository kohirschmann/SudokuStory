import java.util.Random;

public class Sudoku_generator_4x4 {

    public static void main(String[] args) {
        int[][] generatedSudoku = sudokuGenerator();
        printSudoku(generatedSudoku);
        System.out.println("");
        System.out.println("");
        if (Sudoku_solver_4x4.solve_sudoku(generatedSudoku)) {
        	printSudoku(generatedSudoku);
        }
        	
    }
    
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

    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0 && i != 0) {
                System.out.println("- - - - - -");
            }
            for (int j = 0; j < 4; j++) {
                if (j % 2 == 0 && j != 0) {
                    System.out.print(" | ");
                }
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}
