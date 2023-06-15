/**
 * The `Level` class manages the game levels. 
 * It provides methods for moving between different levels, each of which represents a different stage of the game.
 */
public class Level {
	static int T_Storypart;
	static int currentLevel = 0;
	/**
	 * Create and set level 1 
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_1() {
		Story.setT_Storypart(1);
		SudokuUI_4x4.main(null);
		Start.frame.setVisible(false);
	}
	/**
	 * Create and set level 2, if the current level is 1 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_2() {
		if(currentLevel>=1) {
		Story.setT_Storypart(2);
		SudokuUI_4x4.main(null);
		Start.frame.setVisible(false);
		}
	}
	/**
	 * Create and set level 3, if the current level is 2 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_3() {
		if(currentLevel>=2) {
		Story.setT_Storypart(3);
		SudokuUI_9x9.main(null);
		Start.frame.setVisible(false);
		}
	}
	/**
	 * Create and set level 4, if the current level is 3 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_4() {
		if(currentLevel>=3) {
		Story.setT_Storypart(4);
		SudokuUI_9x9.main(null);
		
		}
	}
	/**
	 * Create and set level 5, if the current level is 4 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_5() {
		if(currentLevel>=4) {
		Story.setT_Storypart(5);
		SudokuUI_9x9.main(null);
		
		}
	}
	/**
	 * Create and set level 6, if the current level is 5 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_6() {
		if(currentLevel>=5) {
		Story.setT_Storypart(6);
		SudokuUI_9x9.main(null);
		
		}
	}
	/**
	 * Create and set level 7, if the current level is 6 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_7() {
		if(currentLevel>=6) {
		Story.setT_Storypart(7);
		SudokuUI_9x9.main(null);
		
		}
	}
	/**
	 * Create and set level 8, if the current level is 7 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_8() {
		if(currentLevel>=7) {
		Story.setT_Storypart(8);
		SudokuUI_9x9.main(null);
		
		}
	}
	/**
	 * Create and set level 9, if the current level is 8 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_9() {
		if(currentLevel>=8) {
		Story.setT_Storypart(9);
		SudokuUI_9x9.main(null);
		
		}
	}
	/**
	 * Create and set level 10, if the current level is 9 or above
	 * Initialize the relevant story part and starts the appropriate UI.
	 */
	public static void Level_10() {
		if(currentLevel>=9) {
		Story.setT_Storypart(10);
		SudokuUI_9x9.main(null);
		
		}
	}
	/**
	 * Creates the Level
	 * Is bulid so the Devs can change the levels as the wish
	 */
	public static void setcurrentLevel(int Level) {
		if(currentLevel < Level) {
			currentLevel = Level;
		}
	}
	 /**
     * Method to select a level based on user input.
     * The method calls the appropriate level setup method based on the provided level number.
     *
     * @param box The level number.
     */
	public static void selectLevel(int box) {
		if(box == 1) {
			Level_1();
		}
		if(box == 2) {
			Level_2();
		}
		if(box == 3) {
			Level_3();
		}
		if(box == 4) {
			Level_4();
		}
		if(box == 5) {
			Level_5();
		}
		if(box == 6) {
			Level_6();
		}
		if(box == 7) {
			Level_7();
		}
		if(box == 8) {
			Level_8();
		}
		if(box == 9) {
			Level_9();
		}
		if(box == 10) {
			Level_10();
		}
		
	}
}
