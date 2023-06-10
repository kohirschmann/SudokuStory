import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Start {
	
	static ImageIcon Mike = new ImageIcon("img/Firefighter-pana.png");
	
	public static void main(String[] args) {
		JFrame frame = new JFrame(Window_settings.getFrameName());
		Window_settings.Tab(frame);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(1200, 900);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	Window_settings.Close(frame);
            }
        });
        frame.setLayout(new FlowLayout());
        //Objects on GUI
        JLabel LMike = new JLabel(Scaling.Custom(Mike, 1000, 1000));
        LMike.setLocation(300, 50);
        
        
		JButton nGame = new JButton("New Game");
		nGame.setPreferredSize(new Dimension(100, 30));
		nGame.setLocation(100, 500);
		nGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SudokuUI_9x9.main(null);
				frame.setVisible(false);
				
			}
			
		});
		User.changeUsername();
		frame.add(nGame);
		frame.add(LMike);
        frame.add(new Label("Hey "+ User.getName()+", so you are the new recruit for our Fire Department."));
        frame.add(new Label("Nice to meet you! My Name is Mike and I will be your instructor from now on"));
        frame.setVisible(true);
        
	}
	
	
}
