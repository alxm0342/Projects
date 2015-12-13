package edu.csupomona.cs480.hnefatafl.tests;
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import edu.csupomona.cs480.hnefatafl.interfaces.Color;
import edu.csupomona.cs480.hnefatafl.interfaces.Move;
import edu.csupomona.cs480.hnefatafl.interfaces.Board;
import edu.csupomona.cs480.hnefatafl.rules.RulesFactory;
import edu.csupomona.cs480.hnefatafl.interfaces.Piece;


public class Tests {
	/*
	 * Black Warrior Tests
	 */
	@Test
	public void testBlackWarriorIsLegalForCorner() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,4));
            Move m = RulesFactory.getDefault().makeMove(1, 4, 1, 1);
            assertTrue(!aPosition.isLegalFor(m, Color.Black));            
    }
	
	@Test
	public void testBlackWarriorIsLegalForCenter() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,6));
            Move m = RulesFactory.getDefault().makeMove(1, 6, 6, 6);
            assertTrue(!aPosition.isLegalFor(m, Color.Black));            
    }
	
	@Test
	public void testBlackWarriorIsLegalForJump() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,6));
            aPosition.addPieceAt(Piece.WhiteWarrior, new Point(3,5));
            Move m = RulesFactory.getDefault().makeMove(3, 6, 3, 1);
            assertTrue(!aPosition.isLegalFor(m, Color.Black));            
    }
	
	@Test
	public void testBlackWarriorMoveToCorner() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,4));
            Move m = RulesFactory.getDefault().makeMove(1, 4, 1, 1);
            aPosition.move(m);
            assertTrue(aPosition.getPieceAt(new Point(1,1)) == null);            
    }
	
	@Test
	public void testBlackWarriorMoveToCenter() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,6));
            Move m = RulesFactory.getDefault().makeMove(1, 6, 6, 6);
            aPosition.move(m);
            assertTrue(aPosition.getPieceAt(new Point(6,6)) == null);            
    }
	
	@Test
	public void testJumpPiece() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,6));
            aPosition.addPieceAt(Piece.WhiteWarrior, new Point(3,5));
            Move m = RulesFactory.getDefault().makeMove(3, 6, 3, 1);            
            aPosition.move(m);
            assertTrue(aPosition.getPieceAt(new Point(3,1)) == null);            
    }
	
	@Test
	public void testBlackWarriorIsLegalForJumpKing() 
    {
			Board aPosition = RulesFactory.getDefault().makePosition();
	        aPosition.addPieceAt(Piece.BlackWarrior, new Point(9,6));
	        aPosition.addPieceAt(Piece.WhiteKing, new Point(6,6));
	        Move m = RulesFactory.getDefault().makeMove(9, 6, 3, 6);
	        assertTrue(!aPosition.isLegalFor(m, Color.Black));            
    }	
		
	@Test
	public void testPieceIsLegalForFakeMove() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,6));
            Move m = RulesFactory.getDefault().makeMove(3, 6, 3, 6);            
            aPosition.move(m);
            assertTrue(!aPosition.isLegalFor(m, Color.Black));            
    }
	
	@Test
	public void testCheckForWhiteVictory() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.WhiteKing, new Point(1,6));
            Move m = RulesFactory.getDefault().makeMove(1, 6, 1, 1);            
            aPosition.move(m);
            assertTrue(aPosition.checkForVictory() == Color.White);      
    }
	
	@Test
	public void testCheckForBlackEdgeVictory() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.WhiteKing, new Point(1,6));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,5));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(1,7));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(2,6));            
            assertTrue(aPosition.checkForVictory() == Color.Black);      
    }
	
	@Test
	public void testCheckForBlackVictory() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.WhiteKing, new Point(3,3));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,4));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(3,2));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(2,3));            
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(4,3));
            assertTrue(aPosition.checkForVictory() == Color.Black);      
    }
	
	@Test
	public void testCheckForBlackThroneVictory() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.WhiteKing, new Point(5,6));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(5,5));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(5,7));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(4,6));
            assertTrue(aPosition.checkForVictory() == Color.Black);      
    }
	
	@Test
	public void testBasicPieceCapture() 
    {
            Board aPosition = RulesFactory.getDefault().makePosition();
            aPosition.addPieceAt(Piece.WhiteWarrior, new Point(5,5));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(5,4));
            aPosition.addPieceAt(Piece.BlackWarrior, new Point(4,6));
            Move m = RulesFactory.getDefault().makeMove(4, 6, 5, 6);   
            assertTrue(aPosition.getPieceAt(new Point(5,5)) == null);      
    }
}

