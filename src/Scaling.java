import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * The `Scaling` class is used for image scaling in the application. 
 * It includes two methods for scaling image icons to custom or preset dimensions.
 */
public class Scaling {
	static int Window_x = 1200;
	static int Window_y = 900;
	/**
     * This method scales an ImageIcon to custom dimensions.
     * It uses the java.awt.Image.getScaledInstance method to scale the image.
     *
     * @param Icon The ImageIcon that needs to be scaled.
     * @param x The desired width of the image after scaling.
     * @param y The desired height of the image after scaling.
     * @return ImageIcon The scaled ImageIcon.
     */
	public static ImageIcon Custom(ImageIcon Icon,int x, int y) {
		Image image = Icon.getImage();
        Image scaledImage = image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		return scaledIcon;

	}

	/**
     * This method scales an ImageIcon to preset dimensions (150x150).
     * It uses the java.awt.Image.getScaledInstance method to scale the image.
     *
     * @param Icon The ImageIcon that needs to be scaled.
     * @return ImageIcon The scaled ImageIcon.
     */
	public static ImageIcon Icon(ImageIcon Icon) {
		Image image = Icon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		return scaledIcon;

	}
}

