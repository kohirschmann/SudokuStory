import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 * The `User` class manages the username of the game player.
 * It provides methods for setting and getting the username.
 */
public class User {
	static String Username;
	 /**
     * Method to change the username of the game player.
     * It prompts the user to input their name in a dialog box.
     */
	public static void changeUsername() {
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        ImageIcon ff = Scaling.Icon(new ImageIcon("img/Tiger_in_Enclousre.jpeg"));
        Object Input = JOptionPane.showInputDialog(null, "Welcome, what is your name?","Choose Username",JOptionPane.PLAIN_MESSAGE, ff, null, null);
        Username = Input.toString();

        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
	}
	/**
     * Method to retrieve the current user's name.
     *
     * @return The current user's name.
     */
	public static String getName() {
		return Username;
	}
}
