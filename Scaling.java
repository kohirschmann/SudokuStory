import java.awt.Image;

import javax.swing.ImageIcon;

public class Scaling {
	
	public static ImageIcon Custom(ImageIcon Icon,int x, int y) {
		Image image = Icon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		return scaledIcon;

	}

	public static ImageIcon Icon(ImageIcon Icon) {
		Image image = Icon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		return scaledIcon;

	}
	
}

