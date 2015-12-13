package edu.csupomona.cs480.hnefatafl.rules;

/**
 * Created with IntelliJ IDEA.
 * User: satshabad
 * Date: 1/23/13
 * Time: 2:24 PM
 * To change this template use File | Settings | File Templates.
 */


import edu.csupomona.cs480.hnefatafl.interfaces.*;
import edu.csupomona.cs480.hnefatafl.interfaces.Color;
import sun.text.resources.CollationData_ro;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BoardImpl implements Board{

    private HashMap<Point, Piece> boardMap;

    public BoardImpl() {
        this.boardMap = new HashMap<Point, Piece>();
    }

    protected BoardImpl(HashMap<Point, Piece> newBoard){
        this.boardMap = newBoard;
    }


    @Override
    public Color checkForVictory() {

    	Point kingPoint = getPieceLocations(Piece.WhiteKing).get(0);
    	
        // check if king is in one of the four corners, but not the center castle
        if (isCastle(kingPoint) && !kingPoint.equals(new Point(6,6))){

            return Color.White;
        }

        //check if King is surrounded
        if (boardMap.get(new Point(kingPoint.x+1, kingPoint.y)) == Piece.BlackWarrior &&
                boardMap.get(new Point(kingPoint.x-1, kingPoint.y)) == Piece.BlackWarrior &&
                boardMap.get(new Point(kingPoint.x, kingPoint.y+1)) == Piece.BlackWarrior &&
                boardMap.get(new Point(kingPoint.x, kingPoint.y-1)) == Piece.BlackWarrior){
            return Color.Black;
        }



        return null;

    }

    @Override
    public void move(Move m) throws IllegalMoveException {
        Piece currentPiece = getPieceAt(m.getSource());

        if(isLegalFor(m, currentPiece.getColor())){
        	boardMap.put(m.getDestination(), currentPiece);
        	boardMap.remove(m.getSource());

            Point dest = m.getDestination();

            if (currentPiece == Piece.WhiteWarrior || currentPiece == Piece.WhiteKing){
                if (boardMap.get(new Point(dest.x+1, dest.y)) == Piece.BlackWarrior){

                    if (boardMap.get(new Point(dest.x+2, dest.y)) == Piece.WhiteWarrior ||
                            boardMap.get(new Point(dest.x+2, dest.y)) == Piece.WhiteKing){
                        boardMap.remove(new Point(dest.x+1, dest.y));
                    }
                }
                if (boardMap.get(new Point(dest.x-1, dest.y)) == Piece.BlackWarrior){

                    if (boardMap.get(new Point(dest.x-2, dest.y)) == Piece.WhiteWarrior ||
                            boardMap.get(new Point(dest.x-2, dest.y)) == Piece.WhiteKing){
                        boardMap.remove(new Point(dest.x-1, dest.y));
                    }

                }
                if (boardMap.get(new Point(dest.x, dest.y+1)) == Piece.BlackWarrior){

                    if (boardMap.get(new Point(dest.x, dest.y+2)) == Piece.WhiteWarrior ||
                            boardMap.get(new Point(dest.x, dest.y+2)) == Piece.WhiteKing){
                        boardMap.remove(new Point(dest.x, dest.y+1));
                    }

                }
                if (boardMap.get(new Point(dest.x, dest.y-1)) == Piece.BlackWarrior){

                    if (boardMap.get(new Point(dest.x, dest.y-2)) == Piece.WhiteWarrior ||
                            boardMap.get(new Point(dest.x, dest.y-2)) == Piece.WhiteKing){
                        boardMap.remove(new Point(dest.x, dest.y-1));
                    }

                }
            }

            if (currentPiece == Piece.BlackWarrior){

                if (boardMap.get(new Point(dest.x+1, dest.y)) == Piece.WhiteWarrior){

                    if (boardMap.get(new Point(dest.x+2, dest.y)) == Piece.BlackWarrior){
                        boardMap.remove(new Point(dest.x+1, dest.y));
                    }
                }
                if (boardMap.get(new Point(dest.x-1, dest.y)) == Piece.WhiteWarrior){

                    if (boardMap.get(new Point(dest.x-2, dest.y)) == Piece.BlackWarrior){
                        boardMap.remove(new Point(dest.x-1, dest.y));
                    }

                }
                if (boardMap.get(new Point(dest.x, dest.y+1)) == Piece.WhiteWarrior){

                    if (boardMap.get(new Point(dest.x, dest.y+2)) == Piece.BlackWarrior){
                        boardMap.remove(new Point(dest.x, dest.y+1));
                    }

                }
                if (boardMap.get(new Point(dest.x, dest.y-1)) == Piece.WhiteWarrior){

                    if (boardMap.get(new Point(dest.x, dest.y-2)) == Piece.BlackWarrior){
                        boardMap.remove(new Point(dest.x, dest.y-1));
                    }

                }


            }



        } 
        else {
            throw new IllegalMoveException("That's an illegal move");
        }
    }

    @Override
    public boolean isLegalFor(Move m, Color color) {
    	
    	Piece p = getPieceAt(m.getSource());
    	
    	if (p == null)
    		return false;
    	
    	if (!p.getColor().equals(color))
    		return false;

    	int xSource = m.getSource().x;
    	int ySource = m.getSource().y;
    	int xDest = m.getDestination().x;
    	int yDest = m.getDestination().y;
    	
    	// If both the x coordinates and y coordinates do not match, then the move is neither
    	// on the same row nor column, meaning it is not legal.
    	if (xSource != xDest && ySource != yDest){
            return false;
        }


        if (xSource != xDest){

            if (xSource < xDest){
                int xTemp = xSource;
                while(xTemp < xDest){
                    xTemp++;
                    if (boardMap.get(new Point(xTemp, ySource)) != null){
                        return false;
                    }

                }

            }else{
                int xTemp = xSource;
                while(xTemp > xDest){
                    xTemp--;
                    if (boardMap.get(new Point(xTemp, ySource)) != null){
                        return false;
                    }

                }
            }

        }

        if (ySource != yDest){

            if (ySource < yDest){

                int yTemp = ySource;
                while(yTemp < yDest){
                    yTemp++;
                    if (boardMap.get(new Point(xSource, yTemp)) != null){
                        return false;
                    }

                }
            }else{
                int yTemp = ySource;
                while(yTemp > yDest){
                    yTemp--;
                    if (boardMap.get(new Point(xSource, yTemp)) != null){
                        return false;
                    }

                }
            }

        }

    	return true;


    }

    @Override
    public Piece getPieceAt(Point p) {
        return boardMap.get(p);
    }

    @Override
    public java.util.List<Point> getPieceLocations(Piece kindOfPiece) {
        Set<Point> allPoints = boardMap.keySet();

        ArrayList<Point> piecePoints = new ArrayList<Point>();

        for (Point p : allPoints){
            if (boardMap.get(p).compareTo(kindOfPiece) == 0){
                piecePoints.add(p);
            }

        }

        return piecePoints;
    }
    
    // Gets all piece positions on the board besides the piece that is
    // passed as an argument.
    public java.util.List<Point> getAllOtherPieceLocations(Point current) {
    	Set<Point> allPoints = boardMap.keySet();
    	
    	ArrayList<Point> piecePoints = new ArrayList<Point>();
    	
    	for (Point p : allPoints) {
    		if (boardMap.get(p) != null && !p.equals(current))
    			piecePoints.add(p);
    	}
    	
    	return piecePoints;
    }

    @Override
    public void addPieceAt(Piece kindOfPiece, Point location) {
    	boolean full=true, occupied=true;
    	//board can only have 1 king
    	if (kindOfPiece.isKing() && this.getPieceLocations(Piece.WhiteKing).size()<1) {
            full=false;
    	}
    	//board can only have up to 12 defenders
    	if ((kindOfPiece==Piece.WhiteWarrior && this.getPieceLocations(Piece.WhiteWarrior).size()<12)) {
    		full=false;
    	}
    	//board can only have up to 24 attackers
    	if ((kindOfPiece==Piece.BlackWarrior && this.getPieceLocations(Piece.BlackWarrior).size()<24)) {
    		full=false;
    	}
    	//cannot add a piece to an occupied space
    	if (this.getPieceAt(location) == null) {
    		occupied=false;
    	}
    	if (!full && !occupied) {
    		boardMap.put(location, kindOfPiece);;
    	}
    }

    @Override
    public List<Move> getLegalMovesFor(Color color) {
    	
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	List<Point> currentLocations;
    	
    	if (color.equals(Color.Black))
    		currentLocations = getPieceLocations(Piece.BlackWarrior);
    	
    	else {
    		currentLocations = getPieceLocations(Piece.WhiteWarrior);
    		currentLocations.add(getPieceLocations(Piece.WhiteKing).get(0));
    	}
    	
    	for (Point p : currentLocations) {
    		List<Point> allLocations = getAllOtherPieceLocations(p);
    		PieceBorders pb = new PieceBorders(allLocations, p);
    		
    		// Adds all moves to the left of the piece.
    		for (int i = p.y - 1; i > pb.left; i--) {
    			Point dest = new Point(p.x, i);
    			if (!getPieceAt(p).isKing() && (isCastle(dest)))
    				{/* Do nothing. Illegal move.*/}
    			else
    				legalMoves.add(new MoveImpl(p, dest));
    		}
    		
    		// Adds all moves to the right of the piece. 
    		for (int j = p.y + 1; j < pb.right; j++) {
    			Point dest = new Point(p.x, j);
    			if (!getPieceAt(p).isKing() && (isCastle(dest)))
					{/* Do nothing. Illegal move.*/}
    			else
    				legalMoves.add(new MoveImpl(p, dest));
    		}
    		
    		// Adds all moves above the piece.
    		for (int k = p.x - 1; k > pb.above; k--) {
    			Point dest = new Point(k, p.y);
    			if (!getPieceAt(p).isKing() && (isCastle(dest)))
					{/* Do nothing. Illegal move.*/}
    			else
    				legalMoves.add(new MoveImpl(p, dest));
    		}
    		
    		// Adds all moves below the piece.
    		for (int m = p.x + 1; m < pb.below; m++) {
    			Point dest = new Point(m, p.y);
    			if (!getPieceAt(p).isKing() && (isCastle(dest)))
					{/* Do nothing. Illegal move.*/}
    			else
    				legalMoves.add(new MoveImpl(p, dest));
    		}
    	}
    	
        return legalMoves;
    }

    @Override
    public Board clone() {

        HashMap<Point, Piece> newMap = (HashMap<Point, Piece>) this.boardMap.clone();

        return new BoardImpl(newMap);
    }
    
    public boolean isCastle(Point p) {
    	if (p.equals(new Point(1, 1)))
    		return true;
    	
    	if (p.equals(new Point(1, 11)))
    		return true;
    	
    	if (p.equals(new Point(11, 1)))
    		return true;
    	
    	if (p.equals(new Point(11, 11)))
    		return true;
    	
    	if (p.equals(new Point(6, 6)))
    		return true;
    	
    	return false;
    }



    /** This class will do all the dirty work for isLegalFor. It
     *  will find the pieces that are closest to the piece being
     *  considered for movement.
     *
     *
     */
    private class PieceBorders {

    	private int left;
    	private int right;
    	private int above;
    	private int below;
    	private List<Point> locations;
    	private Point current;


    	public PieceBorders(List<Point> locations, Point current) {

    		left = 0;
    		right = 12;
    		above = 0;
    		below = 12;
    		this.locations = locations;
    		this.current = current;

    		findBorders();
    	}

    	private void findBorders()
    	{
    		for (Point p : locations) {
    			if (p.x == current.x && p.y < current.y && p.y > left)
    				left = p.y;

    			if (p.x == current.x && p.y > current.y && p.y < right)
    				right = p.y;

    			if (p.y == current.y && p.x < current.x && p.x > above)
    				above = p.x;

    			if (p.y == current.y && p.x > current.x && p.x < below)
    				below = p.x;
    		}
    	}
    }
}
