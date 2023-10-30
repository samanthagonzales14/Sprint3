package sprint3_0.project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import sprint3_0.project.Board.Cell;
import sprint3_0.project.Board.GameState;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	protected Board board; // Field to store an instance of Board
	private SimpleGame simpleGame; // Field to store an instance of SimpleGame
    private GeneralGame generalGame; // Field to store an instance of GeneralGame
	protected int boardSize;
    
	public static final int SYMBOL_STROKE_WIDTH = 8; 
	public static final int CANVAS_SIZE = 300;
	public int cellSize;
	public int cellPadding;
	public int symbolSize;
	public char currentPlayerTurn;
	Cell currentPlayerSymbol;

	protected GameBoardCanvas gameBoardCanvas; 
	protected JTextField txtCurrentTurn;
	
	protected JRadioButton bluePlayerSButton;
	protected JRadioButton bluePlayerOButton;
	protected ButtonGroup bluePlayerSelection; // For Blue Player's radio buttons

	protected JRadioButton redPlayerSButton;
	protected JRadioButton redPlayerOButton;
    protected ButtonGroup redPlayerSelection;  // For Red Player's radio buttons
    protected ButtonGroup gameModeSelection; // For game mode radio buttons
    protected JSpinner boardSizeSpinner; // to adjust boardSize
    private JTextField ScoreDisplay;
    
    public GUI() {
    	setResizable(false);
    	setVisible(true);
    	setSize(341, 337); 
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));		
		generalGame = new GeneralGame();
        simpleGame = new SimpleGame();
        board = simpleGame;
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("SOS Game");
		
	}
	
	public Board getBoard(){
		return board;
	}
	
	protected void setContentPane(){	
		// Set a fixed size for the gameBoardCanvas
        gameBoardCanvas = new GameBoardCanvas();
        gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));
        Border gridBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        gameBoardCanvas.setBorder(gridBorder);
        
		
		 // panels that surround the board
        JPanel northPanel = new JPanel();
        northPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        northPanel.setBackground(new Color(255, 255, 255));
        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(255, 255, 255));
        JPanel westPanel = new JPanel();
        westPanel.setBackground(new Color(255, 255, 255));
        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(new Color(255, 255, 255));
        
    
        JLabel spaceLabel = new JLabel("       ");
    
        // Simple or general game mode option
        JLabel gameModeLabel = new JLabel("SOS ");
        gameModeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JRadioButton simpleGameButton = new JRadioButton("Simple", true);
        simpleGameButton.setBackground(new Color(255, 255, 255));
        JRadioButton generalGameButton = new JRadioButton("General");
        generalGameButton.setBackground(new Color(255, 255, 255));
        gameModeSelection = new ButtonGroup();
        gameModeSelection.add(simpleGameButton);
        gameModeSelection.add(generalGameButton);
        
        simpleGameButton.addActionListener(e -> {
            board = simpleGame;
        });

        generalGameButton.addActionListener(e -> {
            board = generalGame;
        });
            
        // Board size label
        JLabel boardSizeLabel = new JLabel("Board Size");
        boardSizeLabel.setBackground(new Color(255, 255, 255));  
    
        //  Options for blue player
        JLabel bluePlayerLabel = new JLabel("Blue Player");
        bluePlayerSButton = new JRadioButton("S", true);
        bluePlayerSButton.setActionCommand("S");
        bluePlayerSButton.setBackground(new Color(255, 255, 255));
        bluePlayerOButton = new JRadioButton("O"); 
        bluePlayerOButton.setActionCommand("O");
        bluePlayerOButton.setBackground(new Color(255, 255, 255));
        JRadioButton blueHumanButton = new JRadioButton("Human", true);
        blueHumanButton.setBackground(new Color(255, 255, 255));
        JRadioButton blueComputerButton = new JRadioButton("Computer");
        blueComputerButton.setBackground(new Color(255, 255, 255));
        bluePlayerSelection = new ButtonGroup();
        bluePlayerSelection.add(bluePlayerSButton);
        bluePlayerSelection.add(bluePlayerOButton);
        //bluePlayerSelection.clearSelection();
        //bluePlayerSelection.add(blueHumanButton);
        //bluePlayerSelection.add(blueComputerButton);
        
        // Options for red player
        JLabel redPlayerLabel = new JLabel("Red Player");
        redPlayerSButton = new JRadioButton("S", true);
        redPlayerSButton.setActionCommand("S");
        redPlayerSButton.setBackground(new Color(255, 255, 255));
        redPlayerOButton = new JRadioButton("O");   
        redPlayerOButton.setActionCommand("O");
        redPlayerOButton.setBackground(new Color(255, 255, 255));
        JRadioButton redHumanButton = new JRadioButton("Human");
        redHumanButton.setBackground(new Color(255, 255, 255));
        JRadioButton redComputerButton = new JRadioButton("Computer", true);
        redComputerButton.setBackground(new Color(255, 255, 255));
        redPlayerSelection = new ButtonGroup();
        redPlayerSelection.add(redPlayerSButton);
        redPlayerSelection.add(redPlayerOButton);
     
        //redPlayerSelection.add(RedHumanButton);
        //redPlayerSelection.add(RedComputerButton);
    
        // Add to north panel
        northPanel.add(gameModeLabel);
        northPanel.add(simpleGameButton);
        northPanel.add(generalGameButton);
        northPanel.add(spaceLabel);
        northPanel.add(boardSizeLabel);
        
    
        // Add to south panel
        txtCurrentTurn = new JTextField();
        txtCurrentTurn.setPreferredSize(new Dimension(10, 19));
        txtCurrentTurn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtCurrentTurn.setBorder(null);
        txtCurrentTurn.setBackground(new Color(255, 255, 255));
        southPanel.add(txtCurrentTurn);
        txtCurrentTurn.setColumns(10);
        
    	
    
        // Add to west panel
        westPanel.add(bluePlayerLabel);
        westPanel.add(bluePlayerSButton);
        westPanel.add(bluePlayerOButton);
        //westPanel.add(BlueHumanButton);
        //westPanel.add(BlueComputerButton);
    
        // Add to east panel
        eastPanel.add(redPlayerLabel);
        eastPanel.add(redPlayerSButton);
        eastPanel.add(redPlayerOButton);
        //eastPanel.add(RedHumanButton);
        //eastPanel.add(RedComputerButton);
    
        // Add panels to ContentPane
        Container ContentPane = getContentPane();
        ContentPane.setLayout(new BorderLayout());
        ContentPane.add(gameBoardCanvas, BorderLayout.CENTER);
        ContentPane.add(northPanel, BorderLayout.NORTH);
        ContentPane.add(southPanel, BorderLayout.SOUTH);
        ContentPane.add(westPanel, BorderLayout.WEST);
        ContentPane.add(eastPanel, BorderLayout.EAST);
        northPanel.setPreferredSize(new Dimension(150, 100));
        
        // board size options
        boardSizeSpinner = new JSpinner();
        boardSizeSpinner.setModel(new SpinnerNumberModel(3, 3, 10, 1));
        northPanel.add(boardSizeSpinner);
        
        ScoreDisplay = new JTextField();
        ScoreDisplay.setBorder(null);
        ScoreDisplay.setPreferredSize(new Dimension(10, 19));
        northPanel.add(ScoreDisplay);
        ScoreDisplay.setColumns(15);
        
        boardSizeSpinner.addChangeListener(e -> {
            int newSize = (int) boardSizeSpinner.getValue();
            board.setBoardSize(newSize); // Update the board size
            board.newGame();
            gameBoardCanvas.repaint(); // Repaint the canvas
        });
        
       
        southPanel.setPreferredSize(new Dimension(100, 100));
        
        JButton newGameButton = new JButton("New Game");
        southPanel.add(newGameButton);
        
        
        newGameButton.addActionListener((ActionListener) new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		// Reset the board and initialize a new one
                board.newGame();

                // Reset the board size spinner to its default value (3)
                boardSizeSpinner.setValue(3);

                // Clear the radio button selections
                bluePlayerSButton.setSelected(true);
                redPlayerSButton.setSelected(true);
                simpleGameButton.setSelected(true);

                // Clear the text field for the current turn
                txtCurrentTurn.setText("");
                // Clear the text field for the current turn
                ScoreDisplay.setText("");
                

                // Repaint the canvas to clear the board
                gameBoardCanvas.repaint();
        	}
        });
        
        westPanel.setPreferredSize(new Dimension(100, 100));
        
        JTextPane textPane = new JTextPane();
        westPanel.add(textPane);
        eastPanel.setPreferredSize(new Dimension(100, 100));		
		
	}

	class GameBoardCanvas extends JPanel {			
		GameBoardCanvas(){	 

			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
					if (board.getGameState() == GameState.PLAYING) {
					
						
						int rowSelected = e.getY() / cellSize;
						int colSelected = e.getX() / cellSize;
				        
				        currentPlayerTurn = board.getCurrentPlayer();
				        
				        if (board.getCell(rowSelected, colSelected) == Cell.EMPTY) {
				        ButtonModel blueSelection = bluePlayerSelection.getSelection();
				        ButtonModel redSelection = redPlayerSelection.getSelection();
				        
				        if (currentPlayerTurn == 'B') {
					        if (blueSelection != null && blueSelection.getActionCommand() != null && blueSelection.getActionCommand().equals("S")) {
						            currentPlayerSymbol = Cell.LETTERS; // Blue Player selected 'S'
						        } else if (blueSelection != null && blueSelection.getActionCommand() != null && blueSelection.getActionCommand().equals("O")) {
						            currentPlayerSymbol = Cell.LETTERO; // Blue Player selected 'O'
						        }
					        board.makeMove(rowSelected, colSelected, currentPlayerSymbol);
				        }
					     else {
					        if (board.getCell(rowSelected, colSelected) == Cell.EMPTY) {
			                    // Make a move based on the current player's symbol
					        	if (redSelection != null && redSelection.getActionCommand() != null && redSelection.getActionCommand().equals("S")) {
						            currentPlayerSymbol = Cell.LETTERS; // Red Player selected 'S'
						        } else if (redSelection != null && redSelection.getActionCommand() != null && redSelection.getActionCommand().equals("O")){
						            currentPlayerSymbol = Cell.LETTERO; // Red
						        }
					        	board.makeMove(rowSelected, colSelected, currentPlayerSymbol);
					        } 
					        
					     }			    	
	                    repaint();
	                    handleGameResult();
				        }
					}
				}
				
				
				
			});
			
														
		}
		
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);		
			// Calculate cellSize based on canvas size and board size
		    int canvasSize = Math.min(getWidth(), getHeight());
		    cellSize = canvasSize / board.getBoardSize();

		    // Calculate cellPadding based on cellSize
		    cellPadding = cellSize / 6;

		    // Calculate symbolSize based on cellSize
		    symbolSize = cellSize - cellPadding * 2;
			drawGridLines(g);
			drawBoard(g);
		}
		
		private void drawGridLines(Graphics g) {
            g.setColor(Color.BLACK);
            int boardSize = board.getBoardSize();
            int gridSize = CANVAS_SIZE / boardSize;

            for (int row = 1; row < boardSize; row++) {
                int y = row * gridSize;
                g.drawLine(0, y, CANVAS_SIZE, y);
            }

            for (int col = 1; col < boardSize; col++) {
                int x = col * gridSize;
                g.drawLine(x, 0, x, CANVAS_SIZE);
            }
        }

		private void drawBoard(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
		
			for (int row = 0; row < board.getBoardSize(); row++) {
				for (int col = 0; col < board.getBoardSize(); col++) {
					int x1 = col * cellSize + cellPadding;
					int y1 = row * cellSize + cellPadding;
					Cell cellValue = board.getCell(row, col);
					if (cellValue == Cell.LETTERS) {
						drawSymbol(g2d, 'S', x1,y1);
					}
					else if (cellValue == Cell.LETTERO) {
						drawSymbol(g2d, 'O', x1,y1);
					}
				}	
			}
			
			/*for (int row = 0; row < board.getBoardSize(); row++) {
		        for (int col = 0; col < board.getBoardSize(); col++) {
		            if (board.getCell(row, col) != Cell.EMPTY) {		            	
		            	 checkAndDrawSOS(g2d, row, col);
		            	
		            }
		        }
		    }*/
		}
	}
	
	private void drawSymbol(Graphics2D g2d, char symbol, int x, int y) {
	    String letter = String.valueOf(symbol);
	    Font font;

	    // Check the cell size, and adjust the font size accordingly
	    if (cellSize > 30) {
	        font = new Font("Arial", Font.BOLD, symbolSize);
	    } else {
	        font = new Font("Arial", Font.BOLD, 24); // Adjust the size as needed
	    }

	    // Calculate the center position for the text within the cell
	    int centerX = x + (cellSize - g2d.getFontMetrics(font).stringWidth(letter)) / 2;
	    int centerY = y + (cellSize + g2d.getFontMetrics(font).getHeight()) / 2 - g2d.getFontMetrics(font).getDescent();

	    g2d.setFont(font);
	    g2d.drawString(letter, centerX, centerY);
	}

	/*private void checkAndDrawSOS(Graphics2D g2d, int row, int col) {
		Cell[] symbols = { Cell.LETTERS, Cell.LETTERO, Cell.LETTERS };
		currentPlayerTurn = board.getCurrentPlayer();
	    // Check horizontally (right and left)
	    if (col <= board.getBoardSize() - 3) {
	        if (board.getCell(row, col) == symbols[0] &&
	            board.getCell(row, col + 1) == symbols[1] &&
	            board.getCell(row, col + 2) == symbols[2]) {
	        	
	        	if (currentPlayerTurn == 'B') {
	        		drawLineToHighlightSOS(g2d, row, col, "horizontal", 'B');
	        	}
	        	else {
	        		drawLineToHighlightSOS(g2d, row, col, "horizontal", 'R');
	        	}
	        }
	    }
	    if (col >= 2) {
	        if (board.getCell(row, col) == symbols[0] &&
	            board.getCell(row, col - 1) == symbols[1] &&
	            board.getCell(row, col - 2) == symbols[2]) {
	        	if (currentPlayerTurn == 'B') {
	        		drawLineToHighlightSOS(g2d, row, col, "horizontal", 'B');
	        	}
	        	else {
	        		drawLineToHighlightSOS(g2d, row, col, "horizontal", 'R');
	        	}
	        }
	    }

	    // Check vertically (up and down)
	    if (row <= board.getBoardSize() - 3) {
	        if (board.getCell(row, col) == symbols[0] &&
	            board.getCell(row + 1, col) == symbols[1] &&
	            board.getCell(row + 2, col) == symbols[2]) {
	        	if (currentPlayerTurn == 'B') {
	        		drawLineToHighlightSOS(g2d, row, col, "vertical", 'B');
	        	}
	        	else {
	        		drawLineToHighlightSOS(g2d, row, col, "vertical", 'R');
	        	}
	        }
	    }
	    if (row >= 2) {
	        if (board.getCell(row, col) == symbols[0] &&
	            board.getCell(row - 1, col) == symbols[1] &&
	            board.getCell(row - 2, col) == symbols[2]) {
	        	if (currentPlayerTurn == 'B') {
	        		drawLineToHighlightSOS(g2d, row, col, "vertical", 'B');
	        	}
	        	else {
	        		drawLineToHighlightSOS(g2d, row, col, "vertical", 'R');
	        	}
	        }
	    }

	    // Check diagonals (from top-left to bottom-right)
	    if (row <= board.getBoardSize() - 3 && col <= board.getBoardSize() - 3) {
	        if (board.getCell(row, col) == symbols[0] &&
	            board.getCell(row + 1, col + 1) == symbols[1] &&
	            board.getCell(row + 2, col + 2) == symbols[2]) {
	            if (currentPlayerTurn == 'B') {
	        		drawLineToHighlightSOS(g2d, row, col, "diagonalTLBR", 'B');
	        	}
	        	else {
	        		drawLineToHighlightSOS(g2d, row, col, "diagonalTLBR", 'R');
	        	}
	        }
	    }

	    if (row >= 2 && col >= 2) {
	        if (board.getCell(row, col) == symbols[0] &&
	            board.getCell(row - 1, col - 1) == symbols[1] &&
	            board.getCell(row - 2, col - 2) == symbols[2]) {
	        	if (currentPlayerTurn == 'B') {
	        		drawLineToHighlightSOS(g2d, row, col, "diagonalTLBR", 'B');
	        	}
	        	else {
	        		drawLineToHighlightSOS(g2d, row, col, "diagonalTLBR", 'R');
	        	}
	        }
	    }

	    // Check diagonals (from top-right to bottom-left)
	    if (row <= board.getBoardSize() - 3 && col >= 2) {
	        if (board.getCell(row, col) == symbols[0] &&
	            board.getCell(row + 1, col - 1) == symbols[1] &&
	            board.getCell(row + 2, col - 2) == symbols[2]) {
	            if (currentPlayerTurn == 'B') {
	        		drawLineToHighlightSOS(g2d, row, col, "diagonalTRBL", 'B');
	        	}
	        	else {
	        		drawLineToHighlightSOS(g2d, row, col, "diagonalTRBL", 'R');
	        	}
	        }
	    }

	    if (row >= 2 && col <= board.getBoardSize() - 3) {
	        if (board.getCell(row, col) == symbols[0] &&
	            board.getCell(row - 1, col + 1) == symbols[1] &&
	            board.getCell(row - 2, col + 2) == symbols[2]) {
	        	if (currentPlayerTurn == 'B') {
	        		drawLineToHighlightSOS(g2d, row, col, "diagonalTRBL", 'B');
	        	}
	        	else {
	        		drawLineToHighlightSOS(g2d, row, col, "diagonalTRBL", 'R');
	        	}
	        }
	    }
	}
	
	private void drawLineToHighlightSOS(Graphics2D g2d, int row, int col, String direction, char turn) {
		int x1 = col * cellSize + cellSize / 2;
	    int y1 = row * cellSize + cellSize / 2;
	    int lineLength = cellSize; 
	    
	    Color lineColor = null;
	    if (turn == 'B') {
	    	lineColor = Color.BLUE;
	    }
	    else {
	    	lineColor = Color.RED;
	    }
	    g2d.setColor(lineColor);
	    g2d.setStroke(new BasicStroke(4)); // You can adjust the line width

	    if (direction.equals("horizontal")) {
	        g2d.drawLine(x1 - lineLength, y1, x1 + lineLength, y1);
	    } else if (direction.equals("vertical")) {
	        g2d.drawLine(x1, y1 - lineLength, x1, y1 + lineLength);
	    } else if (direction.equals("diagonalTLBR")) {
	        g2d.drawLine(x1 - lineLength, y1 - lineLength, x1 + lineLength, y1 + lineLength);
	    }
	    else if (direction.equals("diagonalTRBL")) {
	        g2d.drawLine(x1 - lineLength, y1 + lineLength, x1 + lineLength, y1 - lineLength);
	    }
	}*/
	
	/*private void drawLineToHighlightSOS(Graphics2D g2d, int row, int col, String direction) {
		
		int x1 = col * cellSize + cellSize / 2;
	    int y1 = row * cellSize + cellSize / 2;
	    int lineLength = cellSize;
	    // Get the SOS pattern associated with this position
	    SOSPattern pattern = new SOSPattern(board.getSymbol(row, col), row, col, direction);
	    
	    // Get the player who formed this SOS pattern
	    char playerFormingSOS = sosPatternPlayers.get(pattern);

	    // Set the line color based on the player who formed the SOS pattern
	    Color lineColor = (playerFormingSOS == 'B') ? Color.BLUE : Color.RED;
	    g2d.setColor(lineColor);
	    g2d.setStroke(new BasicStroke(4)); // You can adjust the line width

	    if (direction.equals("horizontal")) {
	        g2d.drawLine(x1 - lineLength, y1, x1 + lineLength, y1);
	    } else if (direction.equals("vertical")) {
	        g2d.drawLine(x1, y1 - lineLength, x1, y1 + lineLength);
	    } else if (direction.equals("diagonalTLBR")) {
	        g2d.drawLine(x1 - lineLength, y1 - lineLength, x1 + lineLength, y1 + lineLength);
	    }
	    else if (direction.equals("diagonalTRBL")) {
	        g2d.drawLine(x1 - lineLength, y1 + lineLength, x1 + lineLength, y1 - lineLength);
	    }
	}*/

	
	public void handleGameResult() {
		String score = null;
	    if (board.getGameState() == GameState.BLUE_WON) {
	        txtCurrentTurn.setText("Player Blue Wins!");
	    } else if (board.getGameState() == GameState.RED_WON) {
	        txtCurrentTurn.setText("Player Red Wins!");
	    } else if (board.getGameState() == GameState.DRAW) {
	        txtCurrentTurn.setText("It's a draw!");
	    } else {
	        // Alternate turns according to gameMode logic
	        txtCurrentTurn.setText("Current turn: " + (currentPlayerTurn == 'B' ? "Blue" : "Red"));
	        
	    }
	    currentPlayerTurn = board.getCurrentPlayer();
        if (currentPlayerTurn == 'B') {
        	score = Integer.toString(board.getBlueCount());
        	ScoreDisplay.setText(" Blue Player Score: " + score);
        }
        else {
        	score = Integer.toString(board.getRedCount());
        	ScoreDisplay.setText(" Red Player Score: " + score);
        }
	    repaint();
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI gui = new GUI();
                gui.setVisible(true);
                gui.board.initializeBoard();
            }
		});
	}
}
