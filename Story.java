import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;


public class Story {
	static int T_Storypart = 1;

	

	static String T1  = "img/Story_1.jpeg";
	static String T2  = "img/Story_2.jpeg";
	static String T3  = "img/Story_3.jpeg";
	static String T4  = "img/Story_4.jpeg";
	static String T5  = "img/Story_5.jpeg";
	static String T6  = "img/Story_6.jpeg";
	static String T7  = "img/Story_7.jpeg";
	
	
	public static void TigerStorynGame() {
		if(SudokuUI_9x9.correctness == 100) {
		//Level 1
		if(T_Storypart == 1) {
			Popup(T1);
			
		}
		//Level 2
		if(T_Storypart == 2) {
			Popup(T2);
			
		}
		//Level 3
		if(T_Storypart == 3) {
			Popup(T3);
			
		}
		//Level 4
		if(T_Storypart == 4) {
			Popup(T4);
			
		}
		//Level 5
		if(T_Storypart == 5) {
			Popup(T5);
			
		}
		}
	}

		
public static  void Popup(String Story) {
	JFrame popup = new JFrame("Storypart " + T_Storypart + "/10");

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //this is your screen size


    ImageIcon storypart = new ImageIcon(Story);//imports the image
    storypart = Scaling.Custom(storypart, Scaling.Window_x, 800);

    JLabel lbl = new JLabel(storypart); //puts the image into a jlabel
    popup.getContentPane().add(lbl); //puts label inside the jframe

    popup.setSize(Scaling.Window_x, Scaling.Window_y); //gets h and w of image and sets jframe to the size

    int x = (screenSize.width - popup.getSize().width)/2; //These two lines are the dimensions
    int y = (screenSize.height - popup.getSize().height)/2;//of the center of the screen

    popup.setLocation(x, y); //sets the location of the jframe
    popup.setVisible(true); //makes the jframe visible
	
	
}



public static void setT_Storypart(int level) {
	// TODO Auto-generated method stub
	T_Storypart = level;
}


}
