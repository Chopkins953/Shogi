package Game;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.css.Counter;

import GameLogic.GameLogic;
import GameUI.GameController;
import GameUI.GamePanel;

public class GameAndBoardTest {

	private Game game;	
	
	
	@Before
	public void setUp() {
		
		game = new Game();
	
		
	}
	
	@Test
	public void testGameSetBoard() {
		
		try {
			Board board = new Board();
			
			game.setBoard(board);
			
			assertNotNull(game.getBoard());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBoardConstructorAndGetGameBoard() {
		
		try {
			Board board = new Board();
			
			Piece actualPiece = PieceFactory.getLance("down", "");
			
			Piece expectedPiece = board.getGameBoard()[0][0];
			
			assertEquals(expectedPiece.getId(), actualPiece.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGameBoardPieceCount() {
		try {
			Board board = new Board();
			HashMap<String, Integer> counterHashMap = new HashMap<String, Integer>();
			
			for (Piece[] row : board.getGameBoard()) {
				for (Piece piece : row) {
					if (piece != null) {
						if (counterHashMap.containsKey(piece.getId())){
							int currentCount = counterHashMap.get(piece.getId()); 
							counterHashMap.remove(piece.getId());
							currentCount += 1; 
							counterHashMap.put(piece.getId(), currentCount);
						}
						else {
							counterHashMap.put(piece.getId(), 1);
						}
					}
					
				}
			}
			//now lets see if the board built properly
			for (String keyString : counterHashMap.keySet()) {
				
			}
			
			assertTrue(counterHashMap.get("Bishop") == 2 &&
					   counterHashMap.get("King") == 2 &&
					   counterHashMap.get("Gold General") == 4 &&
					   counterHashMap.get("Knight") == 4 &&
					   counterHashMap.get("Lance") == 4 &&
					   counterHashMap.get("Silver General") == 4 &&
					   counterHashMap.get("Pawn") == 18 &&
					   counterHashMap.get("Rook") == 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testDirections() {
		try {
			int x = 0; 
			Board board = new Board();
			for (Piece[] pieceRow : board.getGameBoard()) {
				for (Piece piece : pieceRow) {
					if (piece != null) {
						if (x < 3) {
							if (!piece.getDirection().contentEquals("down")) {
								fail();
							}
						}
						else if(x > 5){
							if (!piece.getDirection().contentEquals("up")) {
								fail();
							}
						}
					}
				}
				x++; 
			}
			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	@Test
	public void testGame() {
		Game game = new Game(); 
		game.setMoving(true);
		//pretend we are moving a pawn 
	    int[][] move = {{2,0}, {3,0}};
	    game.setMoveSet(move);
	    Game referenceGame = new Game(); 
	    game.setPlayerOne("PlayerOne");
	    game.setPlayerTwo("PlayerTwo");
	    game.setPlayerOneAlias("down");
	    game.setPlayerTwoAlias("down");
	    game.getBoard().getGameBoard()[3][0] = game.getBoard().getGameBoard()[2][0];
	    game.getBoard().getGameBoard()[2][0] = null; 
	    assertTrue(GameLogic.handleMove(referenceGame, game, game.getPlayerOne()));
	}
}