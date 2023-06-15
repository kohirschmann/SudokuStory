
public class Level {
	static int T_Storypart;
	static boolean L1_finished = false;
	static boolean L2_finished = false;
	static boolean L3_finished = false;
	static boolean L4_finished = false;
	static boolean L5_finished = false;
	static boolean L6_finished = false;
	static boolean L7_finished = false;
	static boolean L8_finished = false;
	static boolean L9_finished = false;
	static boolean L10_finished = false;
	
	public static void Level_1() {
		Story.setT_Storypart(1);
		SudokuUI_9x9.main(null);
		L1_finished = true;
		
	}
	public static void Level_2() {
		if(L1_finished == true) {
		Story.setT_Storypart(2);
		SudokuUI_4x4.main(null);
		L2_finished = true;
		
		}
	}
}
