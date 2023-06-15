import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * The `Window_settings` class manages the settings related to the application window.
 * It provides methods for managing tab properties and window closure.
 */
public class Window_settings {
	
	private static String FrameName = "Call of Soduko";
	/**
     * Method to set the tab properties of the application window.
     * It sets the icon for the application tab.
     *
     * @param frame The JFrame object for which the tab properties are being set.
     */
	public static void Tab(JFrame frame) {
		ImageIcon ff = new ImageIcon("img/Tiger_in_Enclousre.jpeg");
		frame.setIconImage(ff.getImage());
		
	}
	 /**
     * Method to manage the closure of the application window.
     * It prompts the user with a confirmation dialog before closing the application.
     *
     * @param frame The JFrame object that is being closed.
     */
	public static void Close(JFrame frame) {
        int result = JOptionPane.showOptionDialog(frame, "Close or back to MainMenu?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Close", "MainMenu"}, null);

        if (result == JOptionPane.YES_OPTION) {
            frame.dispose();
            System.exit(0);
        }
        else {
        	Start.MainMenu();
        }
	}
	 /**
     * Method to get the name of the application frame.
     *
     * @return The name of the application frame.
     */
	public static String getFrameName() {
		return FrameName;
	}
	
}
