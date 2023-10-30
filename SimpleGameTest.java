package sprint3_0.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint3_0.project.SimpleGame;

class SimpleGameTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmptyBoard() {
	    SimpleGame game = new SimpleGame(3); // Create a 3x3 board
	    assertFalse(game.isBoardFull()); // The board is initially empty, so it should be empty
	}

	@Test
	void testPartiallyFilledBoard() {
	    SimpleGame game = new SimpleGame(3);
	    game.makeMove(0, 0, SimpleGame.Cell.LETTERS); // Fill one cell
	    assertFalse(game.isBoardFull()); // The board is not full yet
	}

	@Test
	void testCompletelyFilledBoard() {
	    SimpleGame game = new SimpleGame(3);
	    game.makeMove(0, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(0, 1, SimpleGame.Cell.LETTERO);
	    game.makeMove(0, 2, SimpleGame.Cell.LETTERO);
	    game.makeMove(1, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(1, 1, SimpleGame.Cell.LETTERO);
	    game.makeMove(1, 2, SimpleGame.Cell.LETTERO);
	    game.makeMove(2, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(2, 1, SimpleGame.Cell.LETTERO);
	    game.makeMove(2, 2, SimpleGame.Cell.LETTERO);
	    assertTrue(game.isBoardFull()); // The board is completely filled
	}
	@Test
	void testValidMove() {
	    SimpleGame game = new SimpleGame(3); // Create a 3x3 board
	    assertTrue(game.makeMove(0, 0, SimpleGame.Cell.LETTERS)); // Make a valid move
	    assertEquals(SimpleGame.Cell.LETTERS, game.getSymbol(0, 0)); // Check that the cell was updated
	    assertEquals('R', game.getCurrentPlayer()); // Check that the player was switched
	}

	@Test
	void testInvalidMoveOutOfBoard() {
	    SimpleGame game = new SimpleGame(3);
	    assertFalse(game.makeMove(3, 3, SimpleGame.Cell.LETTERS)); // Try to make a move outside the board
	}

	@Test
	void testInvalidMoveOccupiedCell() {
	    SimpleGame game = new SimpleGame(3);
	    game.makeMove(1, 1, SimpleGame.Cell.LETTERS); // Fill a cell
	    assertFalse(game.makeMove(1, 1, SimpleGame.Cell.LETTERO)); // Try to make a move in an occupied cell
	}
	
	@Test
	void testBlueWins() {
	    SimpleGame game = new SimpleGame(3); // Create a 3x3 board
	    game.makeMove(0, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(0, 1, SimpleGame.Cell.LETTERO);
	    game.makeMove(0, 2, SimpleGame.Cell.LETTERS); // Blue forms an SOS
	    game.updateGameState('B');
	    assertEquals(SimpleGame.GameState.BLUE_WON, game.getGameState()); // Blue should win
	}
	@Test
	void testRedWins() {
	    SimpleGame game = new SimpleGame(3);
	    game.makeMove(0, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(0, 1, SimpleGame.Cell.LETTERO);
	    game.makeMove(0, 2, SimpleGame.Cell.LETTERS); // Blue forms an SOS
	    game.updateGameState('R'); // Red's turn
	    game.makeMove(1, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(1, 1, SimpleGame.Cell.LETTERO);
	    game.makeMove(1, 2, SimpleGame.Cell.LETTERS); // Red forms an SOS
	    game.updateGameState('R');
	    assertEquals(SimpleGame.GameState.RED_WON, game.getGameState()); // Red should win
	}
	@Test
	void testSOSInRow() {
	    SimpleGame game = new SimpleGame(3); // Create a 3x3 board
	    game.makeMove(0, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(0, 1, SimpleGame.Cell.LETTERO);
	    game.makeMove(0, 2, SimpleGame.Cell.LETTERS); // Form an SOS in the first row
	    assertTrue(game.checkForSOS()); // The SOS should be detected
	}

	@Test
	void testSOSInColumn() {
	    SimpleGame game = new SimpleGame(3);
	    game.makeMove(0, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(1, 0, SimpleGame.Cell.LETTERO);
	    game.makeMove(2, 0, SimpleGame.Cell.LETTERS); // Form an SOS in the first column
	    assertTrue(game.checkForSOS()); // The SOS should be detected
	}

	@Test
	void testSOSInDiagonal() {
	    SimpleGame game = new SimpleGame(3);
	    game.makeMove(0, 0, SimpleGame.Cell.LETTERS);
	    game.makeMove(1, 1, SimpleGame.Cell.LETTERO);
	    game.makeMove(2, 2, SimpleGame.Cell.LETTERS); // Form an SOS in the diagonal
	    assertTrue(game.checkForSOS()); // The SOS should be detected
	}
}
