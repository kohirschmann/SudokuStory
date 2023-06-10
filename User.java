import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class User {
	static String Username;
	
	public static void changeUsername() {
		ImageIcon ff = Scaling.Icon(new ImageIcon("img/Firefighter-amico.png"));
		Object Input = JOptionPane.showInputDialog(null, "Recruit! What is your Name?","Choose Username",JOptionPane.PLAIN_MESSAGE, ff, null, null);
		Username = Input.toString();
	}
	
	public static String getName() {
		return Username;
	}
}
