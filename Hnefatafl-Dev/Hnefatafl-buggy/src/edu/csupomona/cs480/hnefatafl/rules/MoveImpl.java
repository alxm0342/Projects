package edu.csupomona.cs480.hnefatafl.rules;

import java.awt.Point;

import edu.csupomona.cs480.hnefatafl.interfaces.Move;

public class MoveImpl implements Move {
	
	/** This class member is used to store the source and
	 *  destination of the move.
	 */
	private Point source, destination;
	
	//Single Constructor, source and destination required----------
	MoveImpl (Point inSource, Point inDestination) {
		source = inSource;
		destination = inDestination;
	}
	//-------------------------------------------------------------
	@Override
	public Point getSource() {
		return source;
	}
	//-------------------------------------------------------------
	@Override
	public Point getDestination() {
		return destination;
	}
	//-------------------------------------------------------------
}
