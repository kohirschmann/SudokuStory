import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window_settings {
	
	private static String FrameName = "Sudoku";
	//Changes Tab Icon and Name
	public static void Tab(JFrame frame) {
		ImageIcon ff = new ImageIcon("img/Firefighter-amico.png");
		frame.setIconImage(ff.getImage());
		
	}

	public static void Close(JFrame frame) {
		//Closes the Game with Are you sure Message
        int result = JOptionPane.showOptionDialog(frame, "Do you really want to leave?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "No"}, null);

        if (result == JOptionPane.YES_OPTION) {
            frame.dispose();
            System.exit(0);
        }
	}
	
	public static String getFrameName() {
		return FrameName;
	}
}
