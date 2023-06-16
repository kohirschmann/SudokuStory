import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * SudokuUI_9x9 class provides a graphical user interface (GUI) for a 9x9 Sudoku game.
 * It supports functionalities like puzzle generation, solution checking, providing hints, and time tracking.
 *
 */
public class SudokuUI_9x9 {
    private static final int GRID_SIZE = 9;
    private JTextField[][] fields = new JTextField[GRID_SIZE][GRID_SIZE];
    private Color[][] gridColors = new Color[GRID_SIZE][GRID_SIZE];
    private Timer timer;
    private int secondsPassed;
    private JLabel timerLabel;
    private int minutesPassed;
    private int hoursPassed;
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuUI_9x9::new);
    }
    
    private SudokuUI_9x9() {
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

        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;
            
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int cellWidth = getWidth() / GRID_SIZE;
                int cellHeight = getHeight() / GRID_SIZE;

                for (int i = 0; i < GRID_SIZE; i++) {
                    for (int j = 0; j < GRID_SIZE; j++) {
                        int gridRow = i / 3;
                        int gridCol = j / 3;
                        Color color = gridColors[gridRow][gridCol];
                        g.setColor(color);

                        int x = j * cellWidth;
                        int y = i * cellHeight;
                        g.fillRect(x, y, cellWidth, cellHeight);
                    }
                }
            }
        };
        
        panel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                fields[i][j] = new JTextField();
                setCellFormatting(fields[i][j]);
                panel.add(fields[i][j]);    
            }
        }
        
        JButton button2 = new JButton("Submit");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] board = new int[GRID_SIZE][GRID_SIZE];
                boolean isComplete = true;
                for (int i = 0; i < GRID_SIZE; i++) {
                    for (int j = 0; j < GRID_SIZE; j++) {
                        String text = fields[i][j].getText();
                        if (text.isEmpty()) {
                            isComplete = false;
                        }
                        board[i][j] = text.isEmpty() ? 0 : Integer.parseInt(text);
                    }
                }
                if (!isComplete) {
                    String[] options = {"Yes", "No"};
                    int result = JOptionPane.showOptionDialog(null, "Not completed, another try?",
                            "Incomplete Puzzle",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, options, options[0]);
                    if (result == 0) {
                        int[][] solutionBoard = new int[GRID_SIZE][GRID_SIZE];
                        for (int i = 0; i < GRID_SIZE; i++) {
                            solutionBoard[i] = board[i].clone();
                        }
                        if(Sudoku_solver.solve_sudoku(solutionBoard)) {
                       
                            resetGrid(); 
                            resettimer();
                            timer.start();
                        	
                        }
                      
                    }
                } else {
                    int[][] solutionBoard = new int[GRID_SIZE][GRID_SIZE];
                    for (int i = 0; i < GRID_SIZE; i++) {
                        solutionBoard[i] = board[i].clone();
                    }
                    if(Sudoku_solver.solve_sudoku(solutionBoard)) {
                     
                            JOptionPane.showMessageDialog(null, "Congratulations! You completed!");
                            resetGrid(); 
                            resettimer();
                            timer.start();
                       
                    }
                }
            }
        });

        JButton button1 = new JButton("Regenerate");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Yes", "No"};
                int result = JOptionPane.showOptionDialog(null, "Do you want to restart a new game?", 
                        "Regenerate Puzzle", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                        null, options, options[0]);
                        if (result == 0) {
                            int[][] sudokuPuzzle = Sudoku_generator_hard.sudokuGenerator();
                            resettimer();
                            timer.start();
                            for (int i = 0; i < GRID_SIZE; i++) {
                                for (int j = 0; j < GRID_SIZE; j++) {
                                    if (sudokuPuzzle[i][j] != 0) {
                                        fields[i][j].setText(String.valueOf(sudokuPuzzle[i][j]));
                                        fields[i][j].setEditable(false); // Here, make the initial numbers uneditable.
                                    } else {
                                        fields[i][j].setText("");
                                        fields[i][j].setEditable(true); // Make sure the other fields are editable.
                                    }
                                }
                            }
                            checkzero();
                        }
                    }
                });

        JButton button3 = new JButton("Hint");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	List<Point> emptyFields = new ArrayList<>();
                for (int i = 0; i < GRID_SIZE; i++) {
                    for (int j = 0; j < GRID_SIZE; j++) {
                        if (fields[i][j].getText().isEmpty()) {
                            emptyFields.add(new Point(i, j));
                        }
                    }
                }

                if (!emptyFields.isEmpty()) {
                    Point selectedField = emptyFields.get(new Random().nextInt(emptyFields.size()));
                    
                    int[][] board = new int[GRID_SIZE][GRID_SIZE];
                    for (int i = 0; i < GRID_SIZE; i++) {
                        for (int j = 0; j < GRID_SIZE; j++) {
                            String text = fields[i][j].getText();
                            board[i][j] = text.isEmpty() ? 0 : Integer.parseInt(text);
                        }
                    }
                    int correctNumber = Sudoku_corrector.getCorrectNumber(board,selectedField.x, selectedField.y);
                    fields[selectedField.x][selectedField.y].setText(String.valueOf(correctNumber));
                }
            }
        });
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed++;
                updateTimerLabel();
            }
        });

        timerLabel = new JLabel("Time Passed: 0 h 0 min 0 s");

        JButton startButton = new JButton("Start Timer");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimerLabel();
                timer.start();
            }
        });

        JButton stopButton = new JButton("Stop Timer");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        
        JButton beginner = new JButton("4x4 Sudoku");
        beginner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String [] options = {"Yes","No"};
            	int result = JOptionPane.showOptionDialog(null, "Switch to 4x4 puzzle?", 
                        "Regenerate Puzzle", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                        null, options, options[0]);
            	if (result == 0) {
            		frame.dispose();
            		SwingUtilities.invokeLater(SudokuUI_4x4::new);
            		resettimer();
            		Sound_test.stopMusic();;
                }
            }
        });

        JButton easy = new JButton("Easy");
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String [] options = {"Yes","No"};
            	int result = JOptionPane.showOptionDialog(null, "Switch to easy level?", 
                        "Regenerate Puzzle", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                        null, options, options[0]);
            	if (result == 0) {
            		int[][] sudokuPuzzle = Sudoku_generator_easy.sudokuGenerator();
                    resettimer();
                    timer.start();
                    for (int i = 0; i < GRID_SIZE; i++) {
                        for (int j = 0; j < GRID_SIZE; j++) {
                            if (sudokuPuzzle[i][j] != 0) {
                                fields[i][j].setText(String.valueOf(sudokuPuzzle[i][j]));
                                fields[i][j].setEditable(false); // Here, make the initial numbers uneditable.
                            } else {
                                fields[i][j].setText("");
                                fields[i][j].setEditable(true); // Make sure the other fields are editable.
                            }
                        }
                    }
                    checkzero();
                }
            }
        });
            
        JButton medium = new JButton("Medium");
        
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String [] options = {"Yes","No"};
            	int result = JOptionPane.showOptionDialog(null, "Switch to medium level?", 
                        "Regenerate Puzzle", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                        null, options, options[0]);
            	if (result == 0) {
            		int[][] sudokuPuzzle = Sudoku_generator_medium.sudokuGenerator();
            		resettimer();
                    timer.start();
                    for (int i = 0; i < GRID_SIZE; i++) {
                        for (int j = 0; j < GRID_SIZE; j++) {
                            if (sudokuPuzzle[i][j] != 0) {
                                fields[i][j].setText(String.valueOf(sudokuPuzzle[i][j]));
                                fields[i][j].setEditable(false); // Here, make the initial numbers uneditable.
                            } else {
                                fields[i][j].setText("");
                                fields[i][j].setEditable(true); // Make sure the other fields are editable.
                            }
                        }
                    }
                    checkzero();
            	}
            }
        });
        
        JButton hard = new JButton("Hard");
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {String [] options = {"Yes","No"};
        	int result = JOptionPane.showOptionDialog(null, "Switch to hard level?", 
                    "Regenerate Puzzle", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                    null, options, options[0]);
        	if (result == 0) {
        		int[][] sudokuPuzzle = Sudoku_generator_hard.sudokuGenerator();
        		resettimer();
                timer.start();
                for (int i = 0; i < GRID_SIZE; i++) {
                    for (int j = 0; j < GRID_SIZE; j++) {
                        if (sudokuPuzzle[i][j] != 0) {
                            fields[i][j].setText(String.valueOf(sudokuPuzzle[i][j]));
                            fields[i][j].setEditable(false); // Here, make the initial numbers uneditable.
                        } else {
                            fields[i][j].setText("");
                            fields[i][j].setEditable(true); // Make sure the other fields are editable.
                        }
                    }
                }
                checkzero();
            	}
            }
        });


        JPanel timerPanel = new JPanel();
        timerPanel.setPreferredSize(new Dimension(timerPanel.getPreferredSize().width, 50));
        timerPanel.setPreferredSize(new Dimension(timerPanel.getPreferredSize().height, 120));
        timerPanel.add(timerLabel);
        timerPanel.setBackground(Color.LIGHT_GRAY);
        timerPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible positioning

        // Create a GridBagConstraints object to configure the layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.weighty = 1.0; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(0, 10, 0, 10); 

        // Configure the font for the timerLabel
        Font labelFont = timerLabel.getFont();
        Font boldFont = new Font(labelFont.getFontName(), Font.BOLD, labelFont.getSize() + 40);
        timerLabel.setFont(boldFont);
        timerPanel.add(timerLabel, gbc);
       
        JPanel timerbutton = new JPanel();
        timerbutton.setLayout(new BoxLayout(timerbutton, BoxLayout.Y_AXIS));
        timerbutton.add(startButton);
        timerbutton.add(stopButton);
        
        JPanel difficulties = new JPanel();
        difficulties.setLayout(new BoxLayout(difficulties, BoxLayout.Y_AXIS));
        difficulties.add(beginner);
        difficulties.add(easy);
        difficulties.add(medium);
        difficulties.add(hard);

        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.add(timerPanel, BorderLayout.NORTH);      
        frame.add(timerbutton, BorderLayout.LINE_END);
        frame.add(difficulties, BorderLayout.LINE_START);
        frame.setVisible(true);
       
        setGridColors();
        updateGridColors();
        
        Sound_test.play("src/output.wav");


        int[][] sudokuPuzzle = Sudoku_generator_hard.sudokuGenerator();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (sudokuPuzzle[i][j] != 0) {
                	fields[i][j].setText(String.valueOf(sudokuPuzzle[i][j]));
                	fields[i][j].setEditable(false);
                }
            }
        }
        checkzero();
    }
    /**
     * This method is used to update the timer label.
     */
    private void updateTimerLabel() {
        timerLabel.setText("Time Passed: " + hoursPassed + " h " + minutesPassed+ " min " + secondsPassed + " s");
        if (secondsPassed == 60) {
        	secondsPassed = 0;
        	minutesPassed ++;
        }
        if (minutesPassed == 60) {
        	minutesPassed = 0;
        	hoursPassed ++;
        }
    }
    /**
     * This method is used to set grid colors.
     */
    private void setGridColors() {
       	String[] colorCodes = {"#65B952", "#2B70AA"};
    	int gridIndex = 0;
        	for (int i = 0; i < GRID_SIZE; i += 3) {
           		for (int j = 0; j < GRID_SIZE; j += 3) {
                		String colorCode = colorCodes[gridIndex % colorCodes.length];
                		gridIndex++;

                		Color color = Color.decode(colorCode);

                		for (int row = i; row < i + 3; row++) {
                    		for (int col = j; col < j + 3; col++) {
                        			gridColors[row][col] = color;
                    }
                }
            }
        }
    }
    /**
     * This method is used to apply formatting to each cell.
     * @param textField This is the text field representing a cell.
     */
    private void setCellFormatting(JTextField textField) {
        Font font = textField.getFont();
        Font largerBoldFont = font.deriveFont(font.getSize() + 8f).deriveFont(Font.BOLD);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(largerBoldFont);
}
    /**
     * This method iterates over the entire grid and updates the background color of each cell according
     * to the colors stored in the 'gridColors' array.
     */
    private void updateGridColors() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                fields[i][j].setBackground(gridColors[i][j]);
            }
        }
    }
    /**
     * This method checks each cell in the grid for a value of zero. If it finds a cell with a zero, it
     * replaces the zero with an empty string. This is likely used to clean up the UI presentation of 
     * the grid.
     */
    private void checkzero() {
    	for (int i = 0; i < GRID_SIZE; i ++) {
    		for (int j = 0; j < GRID_SIZE; j++) {
                int fieldValue;
                try {
                    fieldValue = Integer.parseInt(fields[i][j].getText());
                } catch (NumberFormatException e) {
                    fieldValue = 0;
                }
                if (fieldValue == 0) {
                    fields[i][j].setText("");
                }
            }
        }
    }
    /**
     * This method resets the entire grid to its initial state. It first makes all cells editable and 
     * clears their contents, then it generates a new Sudoku puzzle and fills the grid with its values, 
     * setting the cells with initial values to uneditable. 
     * After filling the grid, it checks for and removes any zeros.
     */
    private void resetGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                fields[i][j].setEditable(true);  // Reset all fields to be editable
                fields[i][j].setText("");  // Clear any existing text
                fields[i][j].setForeground(Color.BLACK);  // Reset text color
            }
        }
        int[][] sudokuPuzzle = Sudoku_generator_hard.sudokuGenerator();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (sudokuPuzzle[i][j] != 0) {
                    fields[i][j].setText(String.valueOf(sudokuPuzzle[i][j]));
                    fields[i][j].setEditable(false);  // Make the initial numbers uneditable
                }
            }
        }
        checkzero();
    }
    /**
     * This method is used to reset the timer that's likely used to track the time a user takes to solve
     * the Sudoku puzzle. It first checks if a timer exists, and if it does, it stops the timer and
     * resets the time variables (secondsPassed, minutesPassed, hoursPassed) back to zero.
     */
    protected void resettimer() {
    	if (timer != null) {
    		timer.stop();
    		secondsPassed = 0;
    		minutesPassed = 0;
    		hoursPassed = 0;
    	}

    }
}
