import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SudokuUI_9x9 {
    private static final int GRID_SIZE = 9;
    private JTextField[][] fields = new JTextField[GRID_SIZE][GRID_SIZE];
    private int regenerateCount = 0;
    private Color[][] gridColors = new Color[GRID_SIZE][GRID_SIZE];
    private Timer timer;
    private int secondsPassed;
    private JLabel timerLabel;
    private int minutesPassed;
    private int hoursPassed;
    private int difficulty_option;
    
    
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
                    String[] options = {"Yes, I give up", "No, one more try!"};
                    int result = JOptionPane.showOptionDialog(null, "Seems not completed, do you want to stay longer?",
                            "Incomplete Puzzle",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, options, options[0]);
                    if (result == 0) {
                        int[][] solutionBoard = new int[GRID_SIZE][GRID_SIZE];
                        for (int i = 0; i < GRID_SIZE; i++) {
                            solutionBoard[i] = board[i].clone();
                        }
                        if(Sudoku_solver.solve_sudoku(solutionBoard)) {
                        	if(difficulty_option == 1) {
                            float correctness = Sudoku_corrector.getCorrectness(board, solutionBoard,difficulty_option); 
                            showCorrectnessDialog(correctness,board,solutionBoard);
                            resetGrid(); 
                            resettimer();
                            timer.start();
                        	}
                        }
                        if(Sudoku_solver.solve_sudoku(solutionBoard)) {
                        	if(difficulty_option == 2) {
                            float correctness = Sudoku_corrector.getCorrectness(board, solutionBoard,difficulty_option); 
                            showCorrectnessDialog(correctness,board,solutionBoard);
                            resetGrid(); 
                            resettimer();
                            timer.start();
                        	}
                        }
                        if(Sudoku_solver.solve_sudoku(solutionBoard)) {
                        	if(difficulty_option == 3) {
                            float correctness = Sudoku_corrector.getCorrectness(board, solutionBoard,difficulty_option); 
                            showCorrectnessDialog(correctness,board,solutionBoard);
                            resetGrid(); 
                            resettimer();
                            timer.start();
                        	}
                        }
                    }
                } else {
                    int[][] solutionBoard = new int[GRID_SIZE][GRID_SIZE];
                    for (int i = 0; i < GRID_SIZE; i++) {
                        solutionBoard[i] = board[i].clone();
                    }
                    if(Sudoku_solver.solve_sudoku(solutionBoard)) {
                        float correctness = Sudoku_corrector.getCorrectness(board, solutionBoard,difficulty_option);
                        if (correctness == 100.0) {
                            JOptionPane.showMessageDialog(null, "Congratulations! Your solution is correct!");
                            resetGrid(); 
                            resettimer();
                            timer.start();
                        } else {
                            showCorrectnessDialog(correctness,board,solutionBoard);
                        }
                    }
                }
            }
        });

        JButton button1 = new JButton("Regenerate");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Yes, I don't have any idea", "No, let me consider..."};
                int result = JOptionPane.showOptionDialog(null, "Any problems? Do you really want a new template?", 
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
            	String [] options = {"Not so bad to be a cute baby!","OK...let me consider..."};
            	int result = JOptionPane.showOptionDialog(null, "Serious? Baby Mode?", 
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
            	String [] options = {"Yes, let's start this level!","OK...let me consider..."};
            	int result = JOptionPane.showOptionDialog(null, "Any problems? Do you really want a new template?", 
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
            	String [] options = {"Yes, let's start this level!","OK...let me consider..."};
            	int result = JOptionPane.showOptionDialog(null, "Any problems? Do you really want a new template?", 
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
            public void actionPerformed(ActionEvent e) {String [] options = {"Yes, let's start this level!","OK...let me consider..."};
        	int result = JOptionPane.showOptionDialog(null, "Any problems? Do you really want a new template?", 
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

    private void showCorrectnessDialog(float correctness,int [][] board,int [][] solutionboard) {
        correctness = Sudoku_corrector.getCorrectness(board, solutionboard, difficulty_option);
        JOptionPane.showMessageDialog(null, "Your correctness: " + correctness + "%");
    }

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

    private void setCellFormatting(JTextField textField) {
        Font font = textField.getFont();
        Font largerBoldFont = font.deriveFont(font.getSize() + 8f).deriveFont(Font.BOLD);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(largerBoldFont);
}
   
    private void updateGridColors() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                fields[i][j].setBackground(gridColors[i][j]);
            }
        }
    }
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
    protected void resettimer() {
    	if (timer != null) {
    		timer.stop();
    		secondsPassed = 0;
    		minutesPassed = 0;
    		hoursPassed = 0;
    	}

    }
}
