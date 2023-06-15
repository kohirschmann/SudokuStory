import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The `Start` class serves as the main entry point of the application.
 * It creates a graphical user interface (GUI) that welcomes the user and introduces the game.
 * The GUI includes a greeting section, a level selection section, and a "Start New Game" button.
 * This class utilizes other classes such as `User`, `Window_settings`, `Level`, and `SudokuUI_9x9`.
 */
public class Start {
	static JFrame frame;

    static ImageIcon tigerImage = new ImageIcon("Zoo_Story/image12.png");

    /**
     * Main method which sets up the GUI for the application.
     * The GUI consists of a frame with a greeting panel at the top,
     * a level selection panel at the center, and a "Start New Game" button at the bottom.
     * Each level button in the level selection panel is associated with a different image and 
     * a different action event, which starts the corresponding level of the game.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        User.changeUsername();
        frame = new JFrame(Window_settings.getFrameName());
        Window_settings.Tab(frame);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(new Color(160, 216, 144));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Window_settings.Close(frame);
            }
        });
        frame.setLayout(new BorderLayout());

        // Greeting and Explanation
        JPanel greetingPanel = new JPanel();
        greetingPanel.setLayout(new BorderLayout());
        greetingPanel.setBackground(new Color(160, 216, 144));

        JLabel usernameLabel = new JLabel("Welcome, " + User.getName() + "!");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        greetingPanel.add(usernameLabel, BorderLayout.NORTH);

        JLabel explanationLabel = new JLabel("<html><center>Introducing StorySudoku: Engage your mind and unlock captivating tales!<br>"
                + "Immerse yourself in a unique puzzling experience that combines the timeless challenge of Sudoku with the thrilling progression of a story.<br>"
                + "StorySudoku offers a twist to the classic game, where each solved sudoku unveils a part of an enchanting story.</center></html>");
        explanationLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        explanationLabel.setHorizontalAlignment(JLabel.CENTER);
        greetingPanel.add(explanationLabel, BorderLayout.CENTER);

        frame.add(greetingPanel, BorderLayout.NORTH);

        // Level Selection
        JPanel levelPanel = new JPanel(new GridLayout(2, 5, 20, 20));
        levelPanel.setBackground(new Color(160, 216, 144));
        levelPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Button Images
        JPanel levelPanel1 = new JPanel(new GridLayout(2, 5, 20, 20));
        levelPanel1.setBackground(new Color(160, 216, 144));
        levelPanel1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Button Images
        ImageIcon image1 = new ImageIcon("img/Story_1.jpeg");
        ImageIcon image2 = new ImageIcon("img/Story_2.jpeg");
        ImageIcon image3 = new ImageIcon("img/Story_3.jpeg");
        ImageIcon image4 = new ImageIcon("img/Story_4.jpeg");
        ImageIcon image5 = new ImageIcon("img/Story_5.jpeg");
        ImageIcon image6 = new ImageIcon("img/Story_6.jpeg");
        ImageIcon image7 = new ImageIcon("img/Story_7.jpeg");
        ImageIcon image8 = new ImageIcon("img/Story_8.jpeg");
        ImageIcon image9 = new ImageIcon("img/Story_9.jpeg");
        ImageIcon image10 = new ImageIcon("img/Story_10.jpeg");

        ImageIcon[] buttonImages = { image1, image2, image3, image4, image5, image6, image7, image8, image9, image10 };

        for (int i = 1; i <= 10; i++) {
            JButton levelButton = new JButton();
            levelButton.setFont(new Font("Arial", Font.BOLD, 20));
            levelButton.setPreferredSize(new Dimension(200, 180));
            levelButton.setBackground(new Color(81, 139, 61));
            levelButton.setForeground(Color.WHITE);
            levelButton.setBorder(BorderFactory.createLineBorder(new Color(56, 97, 43), 3));
            levelButton.setFocusPainted(false);

            if (i <= buttonImages.length) {
                ImageIcon buttonImage = buttonImages[i - 1];
                Image scaledImage = buttonImage.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
                ImageIcon scaledButtonImage = new ImageIcon(scaledImage);
                levelButton.setIcon(scaledButtonImage);
            }

            JPanel buttonPanel = new JPanel(new BorderLayout());
            buttonPanel.setOpaque(false);
            JLabel levelLabel = new JLabel("Level " + i);
            levelLabel.setFont(new Font("Arial", Font.BOLD, 18));
            levelLabel.setHorizontalAlignment(JLabel.CENTER);
            buttonPanel.add(levelLabel, BorderLayout.NORTH);
            buttonPanel.add(levelButton, BorderLayout.CENTER);
            final int level = i;
            levelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Level.selectLevel(level);
                }
            });
            levelPanel1.add(buttonPanel);
        }

        frame.add(levelPanel1, BorderLayout.CENTER);


        // Start New Game Button
        JButton newGameButton = new JButton("Start a New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 24));
        newGameButton.setPreferredSize(new Dimension(300, 75));
        newGameButton.setBackground(new Color(81, 139, 61));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setBorder(BorderFactory.createLineBorder(new Color(56, 97, 43), 3));
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SudokuUI_9x9.main(null);
                frame.setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(160, 216, 144));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        buttonPanel.add(newGameButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    /**
     * Sets the main menu visibility to true, making the main menu visible again.
     * This method is typically called after a game session is completed, or when a game session is exited before completion.
     */
    public static void MainMenu() {
    	frame.setVisible(true);
    }
    
}







