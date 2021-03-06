package edu.csupomona.cs480.hnefatafl.tests;

import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.List;

import org.junit.Test;

import edu.csupomona.cs480.hnefatafl.interfaces.Color;
import edu.csupomona.cs480.hnefatafl.interfaces.Move;
import edu.csupomona.cs480.hnefatafl.interfaces.Board;
import edu.csupomona.cs480.hnefatafl.rules.RulesFactory;
import edu.csupomona.cs480.hnefatafl.interfaces.Piece;

/** TODO: implement one test case for each seeded defect in this class.
 * The 5 test cases here should succeed on your main implementation but
 * all 5 should fail on your implementation with the seeded defects.
 * 
 * Note that none of the test cases in SmokeTest.java should fail for
 * either the main or the seeded implementation.
 * 
 * If you haven't written a JUnit test before, see SmokeTest.java.  In
 * Eclipse you can run JUnit tests by right-clicking on the package and
 * selecting Run As..., then choosing JUnit Test.
 * 
 * @author 480 Students
 *
 */
//NON-BUGGY TESTS
public class TestsForSeededDefects 
	{
	/** Mess up the logic of capturing the king at his castle */
	@Test
	public void testCastle() {
		Board initialPosition = RulesFactory.getDefault().makePosition();
		initialPosition.addPieceAt(Piece.BlackWarrior,new Point(5,7));
		initialPosition.addPieceAt(Piece.WhiteKing,new Point(6,7));
		initialPosition.addPieceAt(Piece.BlackWarrior,new Point(7,7));
		initialPosition.addPieceAt(Piece.BlackWarrior,new Point(6,8));
		assertTrue(initialPosition.checkForVictory() == Color.Black);
	}
	
	 /*** Check to see if white will win if only 2 black warrior left */
    @Test
    public void testWinCondition() {
        Board initialPosition = RulesFactory.getDefault().makePosition();
        initialPosition.addPieceAt(Piece.WhiteKing, new Point(6,8));
        initialPosition.addPieceAt(Piece.BlackWarrior,new Point(2,1));
        initialPosition.addPieceAt(Piece.BlackWarrior,new Point(2,2));
        assertTrue(initialPosition.checkForVictory() == Color.White);
    }
    
    /*** Check if pieces can't jump center castle when it is occupied by King */
    @Test
    public void testCenterCastleJump() {
    	Board aPosition = RulesFactory.getDefault().makePosition();
    	aPosition.addPieceAt(Piece.WhiteKing, new Point(6,6));
    	//try piece jump from the left
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(6,5));
    	Move jumpCenterKingLeft = RulesFactory.getDefault().makeMove(6,5,6,7);
    	//try piece jump from the right
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(5,6));
    	Move jumpCenterKingRight = RulesFactory.getDefault().makeMove(5,6,7,6);
    	assertTrue(aPosition.isLegalFor(jumpCenterKingLeft, Color.Black) == false);
    	assertTrue(aPosition.isLegalFor(jumpCenterKingRight, Color.Black) == false);
    }
    
    /*** Check if pieces can't be captured by one opposing piece and a wall */
    @Test
    public void testWallCapture() {
    	Board aPosition = RulesFactory.getDefault().makePosition();
    	//static pieces prevent victory
    	aPosition.addPieceAt(Piece.WhiteKing, new Point(6,6));
    	aPosition.addPieceAt(Piece.WhiteWarrior, new Point(6,5));
    	aPosition.addPieceAt(Piece.WhiteWarrior, new Point(6,7));
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(6,4));
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(6,8));
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(5,5));
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(5,7));
    	//left
    	aPosition.addPieceAt(Piece.WhiteWarrior, new Point(6,1));
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(6,3));
    	Move captureAttemptLeft = RulesFactory.getDefault().makeMove(6,3,6,2);
    	aPosition.move(captureAttemptLeft);
    	assertTrue(aPosition.getPieceAt(new Point(6,1)) != null);
    	//right
    	aPosition.addPieceAt(Piece.WhiteWarrior, new Point(6,11));
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(6,9));
    	Move captureAttemptRight = RulesFactory.getDefault().makeMove(6,9,6,10);
    	aPosition.move(captureAttemptRight);
    	assertTrue(aPosition.getPieceAt(new Point(6,11)) != null);
    	//top
    	aPosition.addPieceAt(Piece.WhiteWarrior, new Point(1,6));
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,6));
    	Move captureAttemptTop = RulesFactory.getDefault().makeMove(3,6,2,6);
    	aPosition.move(captureAttemptTop);
    	assertTrue(aPosition.getPieceAt(new Point(1,6)) != null);
    	//bottom
    	aPosition.addPieceAt(Piece.WhiteWarrior, new Point(11,6));
    	aPosition.addPieceAt(Piece.BlackWarrior, new Point(9,6));
    	Move captureAttemptBottom = RulesFactory.getDefault().makeMove(9,6,10,6);
    	aPosition.move(captureAttemptBottom);
    	assertTrue(aPosition.getPieceAt(new Point(11,6)) != null);
    	//check for no victory
    	assertTrue(aPosition.checkForVictory() == null);
    }
    
    /*** Check if no more than one King is allowed */
    @Test
    public void testDuplicateKings() {
    	Board aPosition = RulesFactory.getDefault().makePosition();
    	aPosition.addPieceAt(Piece.WhiteKing, new Point(6,6));
    	aPosition.addPieceAt(Piece.WhiteKing, new Point(5,6));
    	assertTrue(aPosition.getPieceAt(new Point(5,6)) == null);
    }
}
