package edu.csupomona.cs480.hnefatafl.rules;

/**
 * 
 * User: Ringeis
 * Date: 1/25/13
 * Time: 10:30 AM
 * To change this template use File | Settings | File Templates.
 */


import edu.csupomona.cs480.hnefatafl.interfaces.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MoveImpl implements Move{
		
		Point current;
		Point destination;
	
		MoveImpl(Point p, Point q){
			current = p;
			destination = q;
		}
		
        public Point getSource(){
                return current;
        }
        
        public Point getDestination(){
                return destination;
        }
    
}
