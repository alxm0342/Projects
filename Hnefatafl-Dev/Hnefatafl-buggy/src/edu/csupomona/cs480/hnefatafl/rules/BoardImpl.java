package edu.csupomona.cs480.hnefatafl.rules;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import edu.csupomona.cs480.hnefatafl.interfaces.Board;
import edu.csupomona.cs480.hnefatafl.interfaces.Color;
import edu.csupomona.cs480.hnefatafl.interfaces.IllegalMoveException;
import edu.csupomona.cs480.hnefatafl.interfaces.Move;
import edu.csupomona.cs480.hnefatafl.interfaces.Piece;

public class BoardImpl implements Board {
	
	/**This class member represents the game board,
	 * the index of the array corresponds with the piece's current
	 * location on the board.
	 */
	private Piece[][] grid;
	
	//-------------------Default Constructor-----------------------
	BoardImpl() {
		//Create an empty Board of size 11x11
		grid  = new Piece[11][11];
	}
	//-------------------------------------------------------------
	private boolean checkInBoard(Point position)
	{
		return (position.x >= 1 && position.x <= 11 && position.y >= 1 && position.y <= 11);
	}
	//-------------------------------------------------------------
	@Override
	public Color checkForVictory() 
	{
		//White king is at the four corner and white side win
		List<Point> kingLocations = this.getPieceLocations(Piece.WhiteKing);
		Point kingLoc = kingLocations.get(0);
		if ( (kingLoc.x == 1 && kingLoc.y == 1)
				|| (kingLoc.x == 1 && kingLoc.y == 11)
				|| (kingLoc.x == 11 && kingLoc.y == 1)
				|| (kingLoc.x == 11 && kingLoc.y == 11) )
			return Color.White;
		
		List<Point> blackLocations = this.getPieceLocations(Piece.BlackWarrior);
		
		// King get surrounded by black warriors
		boolean leftSide = false;
		boolean rightSide = false;
		boolean top = false;
		boolean bottom = false;
		
		for (int i = 0; i < blackLocations.size(); i++) {
			Point checkPositionOfBlack = blackLocations.get(i);
			if (checkPositionOfBlack.getX() == (kingLoc.getX() + 1)
					&& checkPositionOfBlack.getY() == kingLoc.getY()) 
			{
				rightSide = true;
			}
			if (checkPositionOfBlack.getX() == (kingLoc.getX() - 1)
					&& checkPositionOfBlack.getY() == kingLoc.getY())
			{
				leftSide = true;
			}
			if (checkPositionOfBlack.getX() == kingLoc.getX()
					&& checkPositionOfBlack.getY() == (kingLoc.getY() + 1)) 
			{
				top = true;
			}
			if (checkPositionOfBlack.getX() == kingLoc.getX()
					&& checkPositionOfBlack.getY() == (kingLoc.getY() - 1)) 
			{
				bottom = true;
			}
		}

		if (top && bottom && leftSide && rightSide) 
		{
			return Color.Black;
		}

		// King and His Castle
		if (bottom && leftSide && rightSide) {
			if (kingLoc.getX() == 6 && kingLoc.getY() - 1 == 6) {
				return Color.Black;
			}
		}
		if (top && bottom && leftSide) {
			if (kingLoc.getX() + 1 == 6 && kingLoc.getY() == 6) {
				return Color.Black;
			}
		}
		if (top && leftSide && rightSide) {
			if (kingLoc.getX() == 6 && kingLoc.getY() + 1 == 6) {
				return Color.Black;
			}
		}
		if (top && bottom && rightSide) {
			if (kingLoc.getX() - 1 == 6 && kingLoc.getY() == 6) {
				return Color.Black;
			}
		}
		
		return null;
	}
	//-------------------------------------------------------------
	@Override
	public void move(Move m) throws IllegalMoveException {
		Piece movingPiece = this.getPieceAt(m.getSource());
		//store current turn color, black is default
		Color turn = Color.Black, nextTurn = Color.White;
		//store correct turn
		if (movingPiece != null) {
			turn = movingPiece.getColor();
		}
		// store the next turn color
      nextTurn = (turn == Color.Black) ? Color.White : Color.Black;
      // Check if move is legal
      if (isLegalFor(m, turn)) {
	      // Declare and initiate variables for destination grid indices
	      // Subtract 1; grid[][] index begins with 0, board with 1
	      int destX = m.getDestination().x - 1;
	      int destY = m.getDestination().y - 1;
	      //create a temporary piece for move
	      Piece temp = this.getPieceAt(m.getSource());
	      // Remove the piece from source point
	      grid[m.getSource().x-1][m.getSource().y-1] = null;
	      // Copies the piece to destination point
	      addPieceAt(temp, m.getDestination());
	      // Check for a win here
	      if (checkForVictory() != null)
	          return; // Either black or white won
	      // Check each side of the destination for capture
	      // Check the left side for capture      
	      if (destY != 0) // Not on left edge of board
	      {
	      	// Check for opponent color on the left
	         if (grid[destX][destY-1] != null 
	          	&& grid[destX][destY-1].getColor() == nextTurn)
	         {   
	        	// Remove opponent sandwiched by wall or team piece
	        	if (destY == 1)
	        		grid[destX][destY-1] = null;
	        	else if (destY-2>0 && grid[destX][destY-2] != null
	         			&& grid[destX][destY-2].getColor() == turn)
	        		grid[destX][destY-1] = null;
	          }	
	      }
	      // Check the right side for capture     
	      if (destY != 10) // Not on right edge of board
	      {
	    	// Check for opponent color on the right
	      	if (grid[destX][destY+1] != null
	      			&& grid[destX][destY+1].getColor() == nextTurn)
	         {   
	      	// Remove opponent sandwiched by wall or team piece
	        	if (destY == 9)
	        		grid[destX][destY+1] = null;
	        	else if (destY+2<10 && grid[destX][destY+2] != null
	            		&& grid[destX][destY+2].getColor() == turn)
	            	grid[destX][destY+1] = null;
	         }        		
	      }
	      // Check the top side for capture
	      if (destX != 0)	// Not on top edge of board
	      {
	    	// Check for opponent color above
	      	if (grid[destX-1][destY] != null
	            	&& grid[destX-1][destY].getColor() == nextTurn)
	         {   
	      	// Remove opponent sandwiched by wall or team piece
	        	if (destX == 1)
	        		grid[destX-1][destY] = null;
	        	else if (destX-2>0 && grid[destX-2][destY] != null
	            		&& grid[destX-2][destY].getColor() == turn)
	            	grid[destX-1][destY] = null;
	         }        		        	
	      }
	      // Check the bottom side for capture
	      if (destX != 10) // Not on bottom edge of board
	      {
	    	// Check for opponent color below
	      	if (grid[destX+1][destY] != null
	      			&& grid[destX+1][destY].getColor() == nextTurn)
	         {   
	      		// Remove opponent sandwiched by wall or team piece
	        	if (destX == 9)
	        		grid[destX+1][destY] = null;
	        	else if (destX+2<10 && grid[destX+2][destY] != null
	            		&& grid[destX+2][destY].getColor() == turn)
	            	grid[destX+1][destY] = null;
	         }        		
	      }
      }
      //in case move is illegal, throw exception
      else {
      	throw new IllegalMoveException("Illegal move!");
      }
      return;
	}
	//-------------------------------------------------------------
	@Override
	public boolean isLegalFor(Move m, Color color) {
		//instantiate boolean return variable as false
		boolean isLegal = false;
		//retrieve list of legal moves for this color
		List<Move> legalMoves = this.getLegalMovesFor(color);
		for (int i=0; i<legalMoves.size(); i++) {
			//search for a match
			if ((m.getSource().equals(legalMoves.get(i).getSource()))
					&& (m.getDestination().equals(legalMoves.get(i).getDestination()))) {
				//set return variable to true if a match is found
				isLegal=true;
			}
		}
		return isLegal;
	}
	//-------------------------------------------------------------
	@Override
	public Piece getPieceAt(Point p) {
		// instantiate piece, default is null
		Piece thePiece = null;
		// check that the requested piece is within board's bounds
		if (p.x>11 || p.y>11 || p.x<1 || p.y<1) {
			throw new IllegalArgumentException();
		}
		else {
			thePiece = grid[p.x-1][p.y-1];
		}
		return thePiece;
	}
	//-------------------------------------------------------------
	@Override
	public List<Point> getPieceLocations(Piece kindOfPiece) {
		//Create list of locations to be returned
		List<Point> theLocations = new LinkedList<Point>();
		
		//If null is passed, return locations of all pieces
		if (kindOfPiece==null) {
			for (int i=0; i<11; i++) {
				for(int j=0; j<11; j++) {
					if (grid[i][j]!=null) {
						theLocations.add(new Point(i+1,j+1));
					}
				}
			}
		}
		//Otherwise, return locations of matching pieces
		else {
			for (int i=0; i<11; i++) {
				for(int j=0; j<11; j++) {
					if (grid[i][j]!=null) {
						if (grid[i][j].equals(kindOfPiece)) {
							theLocations.add(new Point(i+1,j+1));
						}
					}
				}
			}
		}
		return theLocations;
	}
	//-------------------------------------------------------------
	@Override
	public void addPieceAt(Piece kindOfPiece, Point location) {
		//Add the Piece at the location Passed
		if (!checkInBoard(location)) 
		{
			throw new IllegalArgumentException("Position out of bound");
		}
		grid[location.x-1][location.y-1] = kindOfPiece;
	}
	//-------------------------------------------------------------
	@Override
	public List<Move> getLegalMovesFor(Color color) {
		//instantiate the list of legal moves
		List<Move> legalMoves = new LinkedList<Move>();
		//retrieve the locations of all pieces currently on the board
		List<Point> blackWarriors = this.getPieceLocations(Piece.BlackWarrior);
		List<Point> whiteWarriors = this.getPieceLocations(Piece.WhiteWarrior);
		List<Point> whiteKing = this.getPieceLocations(Piece.WhiteKing);
		//instantiate castle positions
		Point centerCastle = new Point(6,6), ulCastle = new Point(1,1),
				urCastle = new Point(1,11), llCastle = new Point(11,1),
				lrCastle = new Point(11,11);
		//instantiate blocked flag and space counter
		boolean blocked;
		int numSpaces;
		
		//----------------------------BLACK'S TURN-----------------------------
		if (color.equals(Color.Black)) {
			//--------------------------BLACK WARRIOR---------------------------
			//notes: Warriors cannot jump over other piece or enter any of the
			//castles.  They are permitted to jump over the center castle.
			//------------------------------------------------------------------
			for (int i=0; i<blackWarriors.size(); i++) {
				Point start = blackWarriors.get(i).getLocation();
				blocked = false;
				numSpaces = 0;
				//BLACK WARRIORS: add legal horizontal moves in the positive direction
				do {
					numSpaces ++;
					Point tryPoint = new Point(start.x, start.y+numSpaces);
					//special case: allow pieces to jump over center castle
					if (tryPoint.equals(centerCastle)) {
						numSpaces ++;
						tryPoint = new Point(start.x, start.y+numSpaces);
					}
					//check if there is a piece or corner castle in the way
					if (start.y+numSpaces>11
							|| tryPoint.equals(ulCastle)
							|| tryPoint.equals(urCastle)
							|| tryPoint.equals(llCastle)
							|| tryPoint.equals(lrCastle)
							|| (this.getPieceAt(tryPoint) != null)) {
						blocked = true;
					}
					else {
						legalMoves.add(new MoveImpl(start, tryPoint));
					}
				} while (!blocked);
				blocked=false;
				numSpaces=0;
				//BLACK WARRIORS: add legal horizontal moves in the negative direction
				do {
					numSpaces ++;
					Point tryPoint = new Point(start.x, start.y-numSpaces);
					//special case: allow pieces to jump over center castle
					if (tryPoint.equals(centerCastle)) {
						numSpaces ++;
						tryPoint = new Point(start.x, start.y+numSpaces);
					}
					//check if there is a piece or corner castle in the way
					if (start.y-numSpaces<1
							|| tryPoint.equals(ulCastle)
							|| tryPoint.equals(urCastle)
							|| tryPoint.equals(llCastle)
							|| tryPoint.equals(lrCastle)
							|| (this.getPieceAt(tryPoint) != null)) {
						blocked = true;
					}
					else {
						legalMoves.add(new MoveImpl(start, tryPoint));
					}
				} while (!blocked);
				blocked = false;
				numSpaces = 0;
				//BLACK WARRIORS: add legal vertical moves in the positive direction
				do {
					numSpaces ++;
					Point tryPoint = new Point(start.x+numSpaces, start.y);
					//special case: allow pieces to jump over center castle
					if (tryPoint.equals(centerCastle)) {
						numSpaces ++;
						tryPoint = new Point(start.x, start.y+numSpaces);
					}
					//check if there is a piece or corner castle in the way
					if (start.x+numSpaces>11
							|| tryPoint.equals(ulCastle)
							|| tryPoint.equals(urCastle)
							|| tryPoint.equals(llCastle)
							|| tryPoint.equals(lrCastle)
							|| (this.getPieceAt(tryPoint) != null)) {
						blocked = true;
					}
					else {
						legalMoves.add(new MoveImpl(start, tryPoint));
					}
				} while (!blocked);
				blocked = false;
				numSpaces = 0;
				//BLACK WARRIORS: add legal vertical moves in the negative direction
				do {
					numSpaces ++;
					Point tryPoint = new Point(start.x-numSpaces, start.y);
					//special case: allow pieces to jump over center castle
					if (tryPoint.equals(centerCastle)) {
						numSpaces ++;
						tryPoint = new Point(start.x, start.y+numSpaces);
					}
					//check if there is a piece or corner castle in the way
					if (start.x-numSpaces<1
							|| tryPoint.equals(ulCastle)
							|| tryPoint.equals(urCastle)
							|| tryPoint.equals(llCastle)
							|| tryPoint.equals(lrCastle)
							|| (this.getPieceAt(tryPoint) != null)) {
						blocked = true;
					}
					else {
						legalMoves.add(new MoveImpl(start, tryPoint));
					}
				} while (!blocked);
			}
		}
		
		//----------------------------WHITE'S TURN-----------------------------
		if (color.equals(Color.White)) {
			//--------------------------WHITE WARRIOR---------------------------
			//notes: Warriors cannot jump over other piece or enter any of the
			//castles.  They are permitted to jump over the center castle.
			//------------------------------------------------------------------
			for (int i=0; i<whiteWarriors.size(); i++) {
				Point start = whiteWarriors.get(i).getLocation();
				blocked = false;
				numSpaces = 0;
				//WHITE WARRIORS: add legal horizontal moves in the positive direction
				do {
					numSpaces ++;
					Point tryPoint = new Point(start.x, start.y+numSpaces);
					//special case: allow pieces to jump over center castle
					if (tryPoint.equals(centerCastle)) {
						numSpaces ++;
						tryPoint = new Point(start.x, start.y+numSpaces);
					}
					//check if there is a piece or corner castle in the way
					if (start.y+numSpaces>11
							|| tryPoint.equals(ulCastle)
							|| tryPoint.equals(urCastle)
							|| tryPoint.equals(llCastle)
							|| tryPoint.equals(lrCastle)
							|| (this.getPieceAt(tryPoint) != null)) {
						blocked = true;
					}
					else {
						legalMoves.add(new MoveImpl(start, tryPoint));
					}
				} while (!blocked);
				blocked=false;
				numSpaces=0;
				//WHITE WARRIORS: add legal horizontal moves in the negative direction
				do {
					numSpaces ++;
					Point tryPoint = new Point(start.x, start.y-numSpaces);
					//special case: allow pieces to jump over center castle
					if (tryPoint.equals(centerCastle)) {
						numSpaces ++;
						tryPoint = new Point(start.x, start.y+numSpaces);
					}
					//check if there is a piece or corner castle in the way
					if (start.y-numSpaces<1
							|| tryPoint.equals(ulCastle)
							|| tryPoint.equals(urCastle)
							|| tryPoint.equals(llCastle)
							|| tryPoint.equals(lrCastle)
							|| (this.getPieceAt(tryPoint) != null)) {
						blocked = true;
					}
					else {
						legalMoves.add(new MoveImpl(start, tryPoint));
					}
				} while (!blocked);
				blocked = false;
				numSpaces = 0;
				//WHITE WARRIORS: add legal vertical moves in the positive direction
				do {
					numSpaces ++;
					Point tryPoint = new Point(start.x+numSpaces, start.y);
					//special case: allow pieces to jump over center castle
					if (tryPoint.equals(centerCastle)) {
						numSpaces ++;
						tryPoint = new Point(start.x, start.y+numSpaces);
					}
					//check if there is a piece or corner castle in the way
					if (start.x+numSpaces>11
							|| tryPoint.equals(ulCastle)
							|| tryPoint.equals(urCastle)
							|| tryPoint.equals(llCastle)
							|| tryPoint.equals(lrCastle)
							|| (this.getPieceAt(tryPoint) != null)) {
						blocked = true;
					}
					else {
						legalMoves.add(new MoveImpl(start, tryPoint));
					}
				} while (!blocked);
				blocked = false;
				numSpaces = 0;
				//WHITE WARRIORS: add legal vertical moves in the negative direction
				do {
					numSpaces ++;
					Point tryPoint = new Point(start.x-numSpaces, start.y);
					//special case: allow pieces to jump over center castle
					if (tryPoint.equals(centerCastle)) {
						numSpaces ++;
						tryPoint = new Point(start.x, start.y+numSpaces);
					}
					//check if there is a piece or corner castle in the way
					if (start.x-numSpaces<1
							|| tryPoint.equals(ulCastle)
							|| tryPoint.equals(urCastle)
							|| tryPoint.equals(llCastle)
							|| tryPoint.equals(lrCastle)
							|| (this.getPieceAt(tryPoint) != null)) {
						blocked = true;
					}
					else {
						legalMoves.add(new MoveImpl(start, tryPoint));
					}
				} while (!blocked);
			}
			//--------------------------WHITE KING------------------------------
			//notes: the king may not re-enter his castle, but can jump it
			//the king is only piece that can enter the corner castles
			//------------------------------------------------------------------
			Point start = whiteKing.get(0).getLocation();
			blocked = false;
			numSpaces = 0;
			//WHITE KING: add legal horizontal moves in the positive direction
			do {
				numSpaces ++;
				Point tryPoint = new Point(start.x, start.y+numSpaces);
				//special case: check for center castle 'jump'
				if (tryPoint.equals(centerCastle)) {
					numSpaces ++;
					tryPoint = new Point(start.x, start.y+numSpaces);
				}
				//check if there is an edge or piece in the way
				if (start.y+numSpaces>11 || (this.getPieceAt(tryPoint) != null)) {
					blocked = true;
				}
				else {
					legalMoves.add(new MoveImpl(start, tryPoint));
				}
			} while (!blocked);
			blocked = false;
			numSpaces = 0;
			//WHITE KING: add legal horizontal moves in the negative direction
			do {
				numSpaces ++;
				Point tryPoint = new Point(start.x, start.y-numSpaces);
				//special case: check for center castle 'jump'
				if (tryPoint.equals(centerCastle)) {
					numSpaces ++;
					tryPoint = new Point(start.x, start.y-numSpaces);
				}
				//check if there is an edge or piece in the way
				if (start.y+numSpaces<1 || (this.getPieceAt(tryPoint) != null)) {
					blocked = true;
				}
				else {
					legalMoves.add(new MoveImpl(start, tryPoint));
				}
			} while (!blocked);
			blocked = false;
			numSpaces = 0;
			//WHITE KING: add legal vertical moves in the positive direction
			do {
				numSpaces ++;
				Point tryPoint = new Point(start.x+numSpaces, start.y);
				//special case: check for center castle 'jump'
				if (tryPoint.equals(centerCastle)) {
					numSpaces ++;
					tryPoint = new Point(start.x+numSpaces, start.y);
				}
				//check if there is an edge or piece in the way
				if (start.x+numSpaces>11 || (this.getPieceAt(tryPoint) != null)) {
					blocked = true;
				}
				else {
					legalMoves.add(new MoveImpl(start, tryPoint));
				}
			} while (!blocked);
			//WHITE KING: add legal vertical moves in the negative direction
			do {
				numSpaces ++;
				Point tryPoint = new Point(start.x-numSpaces, start.y);
				//special case: check for center castle 'jump'
				if (tryPoint.equals(centerCastle)) {
					numSpaces ++;
					tryPoint = new Point(start.x-numSpaces, start.y);
				}
				//check if there is an edge or piece in the way
				if (start.x-numSpaces<1 || (this.getPieceAt(tryPoint) != null)) {
					blocked = true;
				}
				else {
					legalMoves.add(new MoveImpl(start, tryPoint));
				}
			} while (!blocked);
		}
		return legalMoves;
	}
	//-------------------------------------------------------------
	/** This methods returns an exact clone of the board
	 *  in a new instance.
	 */
	@Override
	public Board clone() {
		//instantiate new board to be returned
		Board theClone = new BoardImpl();
		//copy grid of this object to the clone
		List<Point> blackWarriors = this.getPieceLocations(Piece.BlackWarrior);
		List<Point> whiteWarriors = this.getPieceLocations(Piece.WhiteWarrior);
		List<Point> whiteKing = this.getPieceLocations(Piece.WhiteKing);
		//add black warriors to the clone
		for (int i=0; i<blackWarriors.size(); i++) {
			theClone.addPieceAt(Piece.BlackWarrior, blackWarriors.get(i));
		}
		//add white warriors to the clone
		for (int i=0; i<whiteWarriors.size(); i++) {
			theClone.addPieceAt(Piece.WhiteWarrior, whiteWarriors.get(i));
		}
		//add white king to the clone
		if( whiteKing.size() > 0) {
			theClone.addPieceAt(Piece.WhiteKing, whiteKing.get(0));
		}
		//return cloned board
		return theClone;
	}
	//-------------------------------------------------------------
}
