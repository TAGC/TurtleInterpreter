package turtle.implementations;

import turtle.*;
import turtle.util.*;

public class ReflectingTurtle extends Turtle {
	
	public ReflectingTurtle(Position position, Direction direction,
			 String type, boolean pendown, Paper paper, char brushchar) {
		
		super(position, direction, type, pendown, paper, brushchar);
	}
	
	@Override
	public boolean borderHandler() {
		int newx, newy;
		
		if (byWall()) {
			newx = getPosition().getX();
			newy = getPosition().getY();
			if (0 < newx && newx < getResidingPaper().getWidth()-1) {
				newx += getDirection().getXTranslation();
			}
			
			if (0 < newy && newy < getResidingPaper().getHeight()-1) {
				newy += getDirection().getYTranslation();
			}
			
			setPosition(new Position(newx, newy));
			if (isPenDown()) {
				getResidingPaper().markSpot(getPosition(), getBrushChar());
			}
			setDirection(this.getDirection().reflect(getPosition(), 
				                                 getResidingPaper()));
			return false;
		}
		return true;
	}
}
