/**
 * This class represents the main entry point of the StorySudoku game.
 * It provides a graphical user interface (GUI) where the user can interact with the game.
 * The GUI displays a welcome message, an explanation of the game, and level selection options.
 * The user can choose a level and start a new game.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start {
    /**
     * This nested class represents a user in the game.
     * It provides functionality to change the user's username and retrieve the current username.
     */
    static class User {
        static String username;
        /**
         * Prompts the user to enter their name and sets it as the username.
         * Uses a dialog box to display the prompt.
         */
        public static void changeUsername() {
            UIManager.put("OptionPane.background", Color.WHITE);
            UIManager.put("Panel.background", Color.WHITE);
            ImageIcon ff = Scaling.Icon(new ImageIcon("img/Tiger-cage.png"));
            Object input = JOptionPane.showInputDialog(null, "Welcome, what is your name?", "Choose Username", JOptionPane.PLAIN_MESSAGE, ff, null, null);
            username = input.toString();

            UIManager.put("OptionPane.background", null);
            UIManager.put("Panel.background", null);
        }
        /**
         * Retrieves the current username.
         *
         * @return The username of the user.
         */
        public static String getName() {
            return username;
        }
    }

    static ImageIcon tigerImage = new ImageIcon("img/Tiger-only.png");
    /**
     * The main method that starts the game and initializes the graphical user interface.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        User.changeUsername();
        JFrame frame = new JFrame(Window_settings.getFrameName());
        Window_settings.Tab(frame);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1200, 900);
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
        ImageIcon image1 = new ImageIcon("img/Tiger-only.png");
        ImageIcon image2 = new ImageIcon("img/image2.png");
        ImageIcon image3 = new ImageIcon("img/image3.png");
        ImageIcon image4 = new ImageIcon("img/image4.png");
        ImageIcon image5 = new ImageIcon("img/image5.png");
        ImageIcon image6 = new ImageIcon("img/image6.png");
        ImageIcon image7 = new ImageIcon("img/image7.png");
        ImageIcon image8 = new ImageIcon("img/image8.png");
        ImageIcon image9 = new ImageIcon("img/image9.png");
        ImageIcon image10 = new ImageIcon("img/image10.png");

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

            levelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SudokuUI_4x4.main(null);
                    frame.setVisible(false);
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
            @Override
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
}







