import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;

/**
 * The `Story` class is used to manage and display the game's story. 
 * It contains methods for showing the story related to each game level.
 */
public class Story {
	static int T_Storypart = 1;

	

	static String T1  = "img/Story_1.jpeg";
	static String T2  = "img/Story_2.jpeg";
	static String T3  = "img/Story_3.jpeg";
	static String T4  = "img/Story_4.jpeg";
	static String T5  = "img/Story_5.jpeg";
	static String T6  = "img/Story_6.jpeg";
	static String T7  = "img/Story_7.jpeg";
	static String T8  = "img/Story_8.jpeg";
	static String T9  = "img/Story_9.jpeg";
	static String T10  = "img/Story_10.jpeg";
	
	/**
     * Method to present the story part based on the current game level.
     * This method checks the current game level and displays the relevant story part.
     */
	public static void TigerStorynGame() {
		//if(SudokuUI_9x9.correctness == 100) {
		//Level 1
		if(T_Storypart == 1) {
			Popup(T1);
			Level.setcurrentLevel(1);
		}
		//Level 2
		if(T_Storypart == 2) {
			Popup(T2);
			Level.setcurrentLevel(2);
		}
		//Level 3
		if(T_Storypart == 3) {
			Popup(T3);
			Level.setcurrentLevel(3);
		}
		//Level 4
		if(T_Storypart == 4) {
			Popup(T4);
			Level.setcurrentLevel(4);
		}
		//Level 5
		if(T_Storypart == 5) {
			Popup(T5);
			Level.setcurrentLevel(5);
		}
		//Level 6
		if(T_Storypart == 6) {
			Popup(T6);
			Level.setcurrentLevel(6);
		}
		//Level 7
		if(T_Storypart == 7) {
			Popup(T7);
			Level.setcurrentLevel(7);
		}
		//Level 8
		if(T_Storypart == 8) {
			Popup(T8);
			Level.setcurrentLevel(8);
		}
		//Level 9
		if(T_Storypart == 9) {
			Popup(T9);
			Level.setcurrentLevel(9);
		}
		//Level 10
		if(T_Storypart == 10) {
			Popup(T10);
			Level.setcurrentLevel(10);
		}
		}
	//}

		
	/**
     * Method to create and display a popup window showing a story part.
     * This method creates a JFrame, sets its dimensions and position, and populates it with an image 
     * representing the current story part.
     *
     * @param Story A string representing the path to the image file for the current story part.
     */
public static  void Popup(String Story) {
	JFrame popup = new JFrame("Storypart " + T_Storypart + "/10");
	popup.setExtendedState(JFrame.MAXIMIZED_BOTH);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //this is your screen size


    ImageIcon storypart = new ImageIcon(Story);//imports the image
    storypart = Scaling.Custom(storypart, Scaling.Window_x, 750);

    JLabel lbl = new JLabel(storypart); //puts the image into a jlabel
    popup.getContentPane().add(lbl); //puts label inside the jframe

    popup.setSize(Scaling.Window_x, Scaling.Window_y); //gets h and w of image and sets jframe to the size

    int x = (screenSize.width - popup.getSize().width)/2; //These two lines are the dimensions
    int y = (screenSize.height - popup.getSize().height)/2;//of the center of the screen

    popup.setLocation(x, y); //sets the location of the jframe
    popup.setVisible(true); //makes the jframe visible
	
    popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    popup.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            Start.MainMenu();
        }
    });
	
}


/**
 * Method to set the current story part based on the current level.
 *
 * @param level The current game level.
 */
public static void setT_Storypart(int level) {
	// TODO Auto-generated method stub
	T_Storypart = level;
}


}
