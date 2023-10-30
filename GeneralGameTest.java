package sprint3_0.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint3_0.project.Board;
import sprint3_0.project.GeneralGame;
import sprint3_0.project.SimpleGame;

class GeneralGameTest {

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
    public void testMakeMove() {
		GeneralGame game = new GeneralGame(3);
        // Test making a valid move
        assertTrue(game.makeMove(0, 0, Board.Cell.LETTERS));
        assertEquals(Board.Cell.LETTERS, game.getSymbol(0, 0));
        assertEquals('R', game.getCurrentPlayer()); // Since Blue is the default starting player

        // Test making an invalid move (cell already occupied)
        assertFalse(game.makeMove(0, 0, Board.Cell.LETTERO));
	}

	@Test
	void testInvalidMove() {
	    GeneralGame game = new GeneralGame(3);
	    assertTrue(game.makeMove(0, 0, GeneralGame.Cell.LETTERS)); // Make a valid move
	    assertFalse(game.makeMove(0, 0, GeneralGame.Cell.LETTERO)); // Try to make a move in an occupied cell
	}

	@Test
	void testBoardFullAndGameState() {
	    GeneralGame game = new GeneralGame(2); // Create a 2x2 board
	    game.makeMove(0, 0, GeneralGame.Cell.LETTERS);
	    game.makeMove(0, 1, GeneralGame.Cell.LETTERO);
	    game.makeMove(1, 0, GeneralGame.Cell.LETTERS);
	    game.makeMove(1, 1, GeneralGame.Cell.LETTERO); // Fill the entire board without forming SOS
	    assertTrue(game.isBoardFull()); // The board is full
	    game.updateGameState('R'); // Red's turn
	    assertEquals(GeneralGame.GameState.DRAW, game.getGameState()); // The game should be a draw
	}
	 @Test
    public void testGameOutcome() {
		GeneralGame game = new GeneralGame(3);
        // Test game outcome when Blue wins
        game.makeMove(0, 0, Board.Cell.LETTERS);
        game.makeMove(0, 1, Board.Cell.LETTERO);
        game.makeMove(0, 2, Board.Cell.LETTERS);
        game.makeMove(1, 0, Board.Cell.LETTERO);
        game.makeMove(1, 1, Board.Cell.LETTERS);
        game.makeMove(1, 2, Board.Cell.LETTERS);
        game.makeMove(2, 0, Board.Cell.LETTERS);
        game.makeMove(2, 1, Board.Cell.LETTERO);
        game.makeMove(2, 2, Board.Cell.LETTERS);
        assertEquals(Board.GameState.BLUE_WON, game.getGameState());
	 }
}
