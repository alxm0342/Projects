package edu.csupomona.cs480.hnefatafl.rules;

import java.awt.Point;

import edu.csupomona.cs480.hnefatafl.interfaces.Move;
import edu.csupomona.cs480.hnefatafl.interfaces.Board;
import edu.csupomona.cs480.hnefatafl.interfaces.Piece;

public class RulesFactory {
	private static RulesFactory defaultInstance = new RulesFactory();
	
	/** Allows test cases and others to get at the default RulesFactory */
	public static RulesFactory getDefault() { return defaultInstance; }

	/** Creates a Move from the given Point representing source and destination
	 * 
	 * Precondition: source and destination must have number values
	 * in the range 1..11 inclusive (Note that we are not starting from zero;
	 * this is the board game convention).
	 *
	 * @exception IllegalArgumentException if either of the Points is not in range
	 */
	public Move makeMove(Point source, Point destination) throws IllegalArgumentException {
		
		boolean inRangeStartPosition = false;
        	boolean inRangeEndPosition    = false;

        	if (   source.getX() > 1.0 || source.getX() < 11.0
        	    && source.getY() > 1.0 || source.getY() < 11.0 )
        	{
            		inRangeStartPosition = true;
        	}

        	if (   destination.getX() > 1.0 || destination.getX() < 11.0
        	   &&  destination.getY() > 1.0 || destination.getY() < 11.0 )
        	{
            		inRangeEndPosition = true;
        	}
		
        	if (!inRangeStartPosition || !inRangeEndPosition)
        	{
            		throw new IllegalArgumentException("Move is out of bound of the board");
        	}

        	return new MoveImpl(source, destination);
	}
	
	/** Creates a Move from the point at x1,y1 to the point at x2, y2
	 * 
	 * Precondition: all arguments must be in the range 1..11 inclusive
	 * (Note that we are not starting from zero;
	 * this is the board game convention).
	 *
	 * @exception IllegalArgumentException if either of the Points is not in range
	 */
	public Move makeMove(int x1, int y1, int x2, int y2) {
		return makeMove(new Point(x1, y1), new Point(x2, y2));
	}
	
	/** Creates the initial Position for a Hnefatafl game, with all pieces in
	 * their initial locations
	 */
	public Board makeInitialPosition() {
		Board initialPosition = makePosition();
		
		//Add White Pieces to Board, King in center
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(4,6));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(5,5));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(5,6));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(5,7));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(6,4));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(6,5));
		initialPosition.addPieceAt(Piece.WhiteKing, new Point(6,6));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(6,7));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(6,8));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(7,5));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(7,6));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(7,7));
		initialPosition.addPieceAt(Piece.WhiteWarrior, new Point(8,6));
		
		//Add Black Pieces to Board
			//Top
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(1,4));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(1,5));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(1,6));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(1,7));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(1,8));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(2,6));
			//Left
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(4,1));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(5,1));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(6,1));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(7,1));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(8,1));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(6,2));
			//Right
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(4,11));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(5,11));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(6,11));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(7,11));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(8,11));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(6,10));
			//Bottom
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(11,4));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(11,5));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(11,6));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(11,7));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(11,8));
		initialPosition.addPieceAt(Piece.BlackWarrior, new Point(10,6));
		
		return initialPosition;
	}

	/** Creates an empty Position, with no pieces on it.  Pieces must be added
	 * before the position is playable.  We provide this method separately from
	 * makeInitialPosition to facilitate testability.
	 */
	public Board makePosition() {
		Board empty = new BoardImpl();
 		return empty;
	}

}
