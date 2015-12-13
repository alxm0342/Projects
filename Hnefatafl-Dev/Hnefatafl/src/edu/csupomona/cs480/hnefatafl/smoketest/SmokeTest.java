/**
 * 
 */
package edu.csupomona.cs480.hnefatafl.smoketest;

import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.List;

import org.junit.Test;

import edu.csupomona.cs480.hnefatafl.interfaces.Color;
import edu.csupomona.cs480.hnefatafl.interfaces.Move;
import edu.csupomona.cs480.hnefatafl.interfaces.Board;
import edu.csupomona.cs480.hnefatafl.rules.RulesFactory;
import edu.csupomona.cs480.hnefatafl.interfaces.Piece;

/**
 * @author aldrich
 *
 */
public class SmokeTest {
	/** Get initial position, ensure the right numbers of defenders/attackers are
	 * 	present and in basically the right areas.
	 */
	@Test
	public void testInitialPosition() {
		Board initialPosition = RulesFactory.getDefault().makeInitialPosition();
		
		// 1 king in the center
		List<Point> kingPoints = initialPosition.getPieceLocations(Piece.WhiteKing);
		assertTrue(kingPoints.size() == 1);
		assertTrue(kingPoints.get(0).equals(new Point(6,6)));
		assertTrue(initialPosition.getPieceAt(kingPoints.get(0)) == Piece.WhiteKing);
		
		// 12 white warriors, all near center
		List<Point> whiteWarriorPoints = initialPosition.getPieceLocations(Piece.WhiteWarrior);
		assertTrue(whiteWarriorPoints.size() == 12);
		for (Point currentWarriorPoint : whiteWarriorPoints) {
			assertTrue(currentWarriorPoint.distance(6, 6) < 2.1);
			assertTrue(initialPosition.getPieceAt(currentWarriorPoint) == Piece.WhiteWarrior);
		}

		// 24 black warriors, all far from center
		List<Point> blackWarriorPoints = initialPosition.getPieceLocations(Piece.BlackWarrior);
		assertTrue(blackWarriorPoints.size() == 24);
		for (Point currentWarriorPoint : blackWarriorPoints) {
			assertTrue(currentWarriorPoint.distance(6, 6) > 3.9);
			assertTrue(initialPosition.getPieceAt(currentWarriorPoint) == Piece.BlackWarrior);
		}
}
	
	/** Get and try all the moves from initial position */
	@Test
	public void testInitialMoves() {
		Board initialPosition = RulesFactory.getDefault().makeInitialPosition();
		for (Move currentMove : initialPosition.getLegalMovesFor(Color.Black)) {
			initialPosition.clone().move(currentMove);
		}
	}
	
	/** Test moves for an ordinary piece--can't move into corner castle or over other piece */
	@Test
	public void testMove() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,3));
		aPosition.addPieceAt(Piece.WhiteKing, new Point(1,5));
		assertTrue(aPosition.getLegalMovesFor(Color.Black).size() == 1/*down to 1,2*/ + 1/*up to 1,4*/ + 10/*across to (2..11),3*/);
	}

	/** Test captures of an ordinary piece */
	@Test
	public void testCapture() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		aPosition.addPieceAt(Piece.WhiteWarrior, new Point(1,3));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,4));
		aPosition.addPieceAt(Piece.WhiteKing, new Point(1,6));
		Move captureMove = RulesFactory.getDefault().makeMove(1, 6, 1, 5);
		assertTrue(aPosition.isLegalFor(captureMove, Color.White));
		aPosition.move(captureMove);
		assertTrue(aPosition.getPieceAt(new Point(1,4)) == null);
		assertTrue(aPosition.getPieceAt(new Point(1,3)) == Piece.WhiteWarrior);
		assertTrue(aPosition.getPieceAt(new Point(1,5)) == Piece.WhiteKing);
		assertTrue(aPosition.checkForVictory() == null);
	}

	/** Test Black wins */
	@Test
	public void testBlackWins() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		aPosition.addPieceAt(Piece.WhiteKing, new Point(3,3));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,4));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,2));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(2,3));
		aPosition.addPieceAt(Piece.BlackWarrior, new Point(4,3));
		assertTrue(aPosition.checkForVictory() == Color.Black);
	}
	
	/** Test White wins */
	@Test
	public void testWhiteWins() {
		Board aPosition = RulesFactory.getDefault().makePosition();
		aPosition.addPieceAt(Piece.WhiteKing, new Point(1,1));
		assertTrue(aPosition.checkForVictory() == Color.White);
	}
}
