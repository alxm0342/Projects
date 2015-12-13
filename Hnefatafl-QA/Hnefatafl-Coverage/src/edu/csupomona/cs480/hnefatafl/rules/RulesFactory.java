package edu.csupomona.cs480.hnefatafl.rules;

import java.awt.Point;

import edu.csupomona.cs480.hnefatafl.interfaces.*;

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
		
		if (!isLegalValue(source.x) || !isLegalValue(source.y) || !isLegalValue(destination.x) || !isLegalValue(destination.y))
			throw new IllegalArgumentException("Outside of the board's range!");
		
		MoveImpl theMove = new MoveImpl(source, destination);
		
		return theMove;
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
		
		if (!isLegalValue(x1) || !isLegalValue(y1) || !isLegalValue(x2) || !isLegalValue(y2))
			throw new IllegalArgumentException("Outside of the board's range!");
		
		MoveImpl theMove = new MoveImpl(new Point(x1, y1), new Point(x2, y2));
		
		return theMove;
	}
	
	/** Creates the initial Position for a Hnefatafl game, with all pieces in
	 * their initial locations
	 */
	public Board makeInitialPosition() {
		Board emptyPosition = makePosition();

		Point [] blackPositions = getBlackStartingPositions();
		Point [] whitePositions = getWhiteStartingPositions();
		
		// Add black warriors to the new board
		for (int i = 0; i < blackPositions.length; i++)
		{
			emptyPosition.addPieceAt(Piece.BlackWarrior , blackPositions[i]);
		}
		
		// Add white warriors to the new board
		for (int j = 0; j < whitePositions.length; j++)
		{
			emptyPosition.addPieceAt(Piece.WhiteWarrior, whitePositions[j]);
		}
		
		// Add white king to the new board
		emptyPosition.addPieceAt(Piece.WhiteKing, new Point(6, 6));

		return emptyPosition;
	}

	/** Creates an empty Position, with no pieces on it.  Pieces must be added
	 * before the position is playable.  We provide this method separately from
	 * makeInitialPosition to facilitate testability.
	 */
	public Board makePosition() {
;
		BoardImpl theBoard = new BoardImpl();
		return theBoard;
	}
	
	/** Returns an array that holds the starting points for all of black's
	 *  warrior's.
	 */
	private static Point[] getBlackStartingPositions()
	{
		Point [] blackPositions = new Point[24];
		
		blackPositions[0] = new Point(1, 4); blackPositions[6] = new Point(11, 4);
		blackPositions[1] = new Point(1, 5); blackPositions[7] = new Point(11, 5);
		blackPositions[2] = new Point(1, 6); blackPositions[8] = new Point(11, 6);
		blackPositions[3] = new Point(1, 7); blackPositions[9] = new Point(11, 7);
		blackPositions[4] = new Point(1, 8); blackPositions[10] = new Point(11, 8);
		blackPositions[5] = new Point(2, 6); blackPositions[11] = new Point(10, 6);
		
		blackPositions[12] = new Point(4, 11); blackPositions[18] = new Point(4, 1);
		blackPositions[13] = new Point(5, 11); blackPositions[19] = new Point(5, 1);
		blackPositions[14] = new Point(6, 11); blackPositions[20] = new Point(6, 1);
		blackPositions[15] = new Point(7, 11); blackPositions[21] = new Point(7, 1);
		blackPositions[16] = new Point(8, 11); blackPositions[22] = new Point(8, 1);
		blackPositions[17] = new Point(6, 10); blackPositions[23] = new Point(6, 2);
		
		return blackPositions;
	}
	
	/** Returns an array that holds the starting points for all of white's
	 *  warrior's.
	 */
	private static Point[] getWhiteStartingPositions()
	{
		Point [] whitePositions = new Point[12];
		
		whitePositions[0] = new Point(4, 6); whitePositions[6] = new Point(6, 7);
		whitePositions[1] = new Point(5, 5); whitePositions[7] = new Point(6, 8);
		whitePositions[2] = new Point(5, 6); whitePositions[8] = new Point(7, 5);
		whitePositions[3] = new Point(5, 7); whitePositions[9] = new Point(7, 6);
		whitePositions[4] = new Point(6, 4); whitePositions[10] = new Point(7, 7);
		whitePositions[5] = new Point(6, 5); whitePositions[11] = new Point(8, 6);
		
		return whitePositions;
	}
	
	/** Checks if the value is less than 1 or greater than 11.
	 *  1 - 11 represents the acceptable range for the board.
	 */
	private static boolean isLegalValue(int value)
	{
		if (value < 1 || value > 11)
			return false;
		
		return true;
	}
}
