package edu.csupomona.cs480.hnefatafl.tests;

import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.List;

import org.junit.Test;

import edu.csupomona.cs480.hnefatafl.interfaces.Color;
import edu.csupomona.cs480.hnefatafl.interfaces.IllegalMoveException;
import edu.csupomona.cs480.hnefatafl.interfaces.Move;
import edu.csupomona.cs480.hnefatafl.interfaces.Board;
import edu.csupomona.cs480.hnefatafl.rules.RulesFactory;
import edu.csupomona.cs480.hnefatafl.interfaces.Piece;

public class Tests {
	/** Test moves for an king piece--can move into center castle, but not jump over pieces */
	@Test
	public void testKingMoveIntoCenterCastle() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(5,7));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(5,5));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(4,6));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(7,6));
		aPosition.addPieceAt(Piece.WhiteKing, new Point(5,6));
		assertTrue(aPosition.getLegalMovesFor(Color.White).size() == 1);
		Move toCenter = RulesFactory.getDefault().makeMove(5,6,6,6);
		aPosition.move(toCenter);
		assertTrue(aPosition.getPieceAt(new Point(6,6))==Piece.WhiteKing);
	}
    
	/** Test if 2 kings are allowed to be on the board at the same time */
	@Test
	public void testTwoKings() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		aPosition.addPieceAt(Piece.WhiteKing, new Point(6,6));
		aPosition.addPieceAt(Piece.WhiteKing, new Point(5,5));
		assertTrue(aPosition.getPieceAt(new Point(5,5))==null);
		assertTrue(aPosition.getPieceAt(new Point(6,6))==Piece.WhiteKing);
		assertTrue(aPosition.getLegalMovesFor(Color.White).size()==20);
	}
	
	/** Test capture a white piece from bottom (bottom capture)
	*/
	@Test
	public void testCaptureWhiteBottomBottom() {
		
		Board aPosition = RulesFactory.getDefault().makePosition();
		Move move = RulesFactory.getDefault().makeMove(1, 6, 1, 5);

		aPosition.addPieceAt(Piece.WhiteKing, new Point(6,6));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,3));
		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(1,4));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,6));
		assertTrue(aPosition.isLegalFor(move, Color.Black));
		aPosition.move(move);
		assertTrue(aPosition.getPieceAt(new Point(1,4)) == null);
		assertTrue(aPosition.getPieceAt(new Point(1,3)) == Piece.BlackWarrior);
		assertTrue(aPosition.getPieceAt(new Point(1,5)) == Piece.BlackWarrior);
		assertTrue(aPosition.checkForVictory() == null);
	}
	
	/** Test capture a black piece from right (top capture)
	 */
	@Test
	public void testCaptureBlackRightTop() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		aPosition.addPieceAt(Piece.WhiteKing, new Point(1,6));

		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(2,4));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,5));
		Move move = RulesFactory.getDefault().makeMove(2, 4, 1, 4);
		assertTrue(aPosition.isLegalFor(move, Color.White));
		aPosition.move(move);
		assertTrue(aPosition.getPieceAt(new Point(1,5)) == null);
		assertTrue(aPosition.getPieceAt(new Point(1,4)) == Piece.WhiteWarrior);
		assertTrue(aPosition.getPieceAt(new Point(1,6)) == Piece.WhiteKing);
	}
	
	/** Test capture a black piece from top (right capture)
	 */
	@Test
	public void testCaptureBlackTopRight() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		Move move = RulesFactory.getDefault().makeMove(1, 6, 4, 6);
		
		aPosition.addPieceAt(Piece.WhiteKing, new Point(4,4));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(4,5));
		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(1,6));
		assertTrue(aPosition.isLegalFor(move, Color.White));
		aPosition.move(move);
		assertTrue(aPosition.getPieceAt(new Point(4,5)) == null);
		assertTrue(aPosition.getPieceAt(new Point(4,6)) == Piece.WhiteWarrior);
		assertTrue(aPosition.getPieceAt(new Point(4,4)) == Piece.WhiteKing);
	}
	
	/** Test capture a black piece from bottom (left capture)
	 */
	@Test
	public void testCaptureBlackBottomLeft() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		Move move = RulesFactory.getDefault().makeMove(2, 6, 2, 4);
		
		aPosition.addPieceAt(Piece.WhiteKing, new Point(4,4));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,4));
		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(2,6));
		assertTrue(aPosition.isLegalFor(move, Color.White));
		aPosition.move(move);
		assertTrue(aPosition.getPieceAt(new Point(3,4)) == null);
		assertTrue(aPosition.getPieceAt(new Point(2,4)) == Piece.WhiteWarrior);
		assertTrue(aPosition.getPieceAt(new Point(4,4)) == Piece.WhiteKing);
	}
	
	/** Test capture a black piece from bottom (right capture)
	 */
	@Test
	public void testCaptureBlackBottomRight() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		Move move = RulesFactory.getDefault().makeMove(4, 6, 4, 4);

		aPosition.addPieceAt(Piece.WhiteKing, new Point(2,4));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,4));
		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(4,6));
		assertTrue(aPosition.isLegalFor(move, Color.White));
		aPosition.move(move);
		assertTrue(aPosition.getPieceAt(new Point(3,4)) == null);
		assertTrue(aPosition.getPieceAt(new Point(2,4)) == Piece.WhiteKing);
		assertTrue(aPosition.getPieceAt(new Point(4,4)) == Piece.WhiteWarrior);
	}
	
	/** Test capture a white piece from right (top capture)
	 */
	@Test
	public void testCaptureWhiteRightTop() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		Move move = RulesFactory.getDefault().makeMove(2, 4, 1, 4);

		aPosition.addPieceAt(Piece.BlackWarrior, new Point(2,4));
		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(1,5));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,6));
		assertTrue(aPosition.isLegalFor(move, Color.Black));
		aPosition.move(move);
		assertTrue(aPosition.getPieceAt(new Point(1,5)) == null);
		assertTrue(aPosition.getPieceAt(new Point(1,4)) == Piece.BlackWarrior);
		assertTrue(aPosition.getPieceAt(new Point(1,6)) == Piece.BlackWarrior);
	}
	
	/** Test capture a white piece from bottom (left capture)
	 */
	@Test
	public void testCaptureWhiteBottomLeft() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		Move move = RulesFactory.getDefault().makeMove(2, 6, 2, 4);

		aPosition.addPieceAt(Piece.BlackWarrior, new Point(4,4));
		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(3,4));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(2,6));
		assertTrue(aPosition.isLegalFor(move, Color.Black));
		aPosition.move(move);
		assertTrue(aPosition.getPieceAt(new Point(3,4)) == null);
		assertTrue(aPosition.getPieceAt(new Point(2,4)) == Piece.BlackWarrior);
		assertTrue(aPosition.getPieceAt(new Point(4,4)) == Piece.BlackWarrior);
	
	}
	
	/** Test capture a white piece from bottom (right capture)
	 */
	@Test
	public void testCaptureWhiteBottomRight() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		Move move = RulesFactory.getDefault().makeMove(4, 6, 4, 4);
		
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(4,6));
		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(3,4));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(2,4));
		assertTrue(aPosition.isLegalFor(move, Color.Black));
		aPosition.move(move);
		assertTrue(aPosition.getPieceAt(new Point(3,4)) == null);
		assertTrue(aPosition.getPieceAt(new Point(2,4)) == Piece.BlackWarrior);
		assertTrue(aPosition.getPieceAt(new Point(4,4)) == Piece.BlackWarrior);
	}

	/** Tests possible illegal moves
	 */ 
	@Test
	public void testIllegalMoves() {
		Board aPosition = RulesFactory.getDefault().makeInitialPosition();
		
		//Move a piece into a black piece occupied by another piece
		Move blkOccupied = RulesFactory.getDefault().makeMove(1, 4, 1, 5);
		//Move a piece into a white piece occupied by another piece
		Move whtOccupied = RulesFactory.getDefault().makeMove(6, 4, 1, 4);
		//Move a piece over another piece, horizontal
		Move jumpX = RulesFactory.getDefault().makeMove(4, 1, 10, 1);
		//Move a piece over another piece, vertical
		Move jumpY = RulesFactory.getDefault().makeMove(1, 8, 1, 2);
		//Move a piece diagonally, points constructor
		Move diag = RulesFactory.getDefault().makeMove(new Point(1,4), new Point(2,3));
		//Move a null piece
		Move nullMove = RulesFactory.getDefault().makeMove(1,2,1,3);
		//Move a piece out-of-turn
		Move outOfTurn = RulesFactory.getDefault().makeMove(1,4,2,4);
		
		//try an diagonal move
		try {
			aPosition.move(diag);
		}
		catch (IllegalMoveException i) {
			assertTrue(aPosition.isLegalFor(diag, Color.Black)==false); 
		}
		//try making a move off the board using coordinates
		try {
			RulesFactory.getDefault().makeMove(1,4,0,4);
		}
		catch (IllegalArgumentException i){
			//empty catch, the makeMove() method should through an illegal argument exception
		}
		//try making a move off the board using points
		try {
			RulesFactory.getDefault().makeMove(new Point(1,4), new Point(0,4));
		}
		catch (IllegalArgumentException i){
			//empty catch, the makeMove() method should through an illegal argument exception
		}
		
		assertTrue(aPosition.getPieceAt(new Point(1, 4))!=null);
		assertTrue(aPosition.getPieceAt(new Point(1, 5))!=null);
		assertTrue(aPosition.isLegalFor(blkOccupied, Color.Black)==false);
		assertTrue(aPosition.getPieceAt(new Point(6, 4))!=null);
		assertTrue(aPosition.getPieceAt(new Point(1, 4))!=null);
		assertTrue(aPosition.isLegalFor(whtOccupied, Color.White)==false);
		assertTrue(aPosition.isLegalFor(jumpX, Color.Black)==false);
		assertTrue(aPosition.isLegalFor(jumpY, Color.Black)==false);
		assertTrue(aPosition.isLegalFor(nullMove, Color.Black)==false);
		assertTrue(aPosition.isLegalFor(outOfTurn, Color.Black)==true);
		assertTrue(aPosition.isLegalFor(outOfTurn, Color.White)==false);
	}
}
