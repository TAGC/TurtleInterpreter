package turtle.implementations;

import turtle.*;
import turtle.util.*;

public class WrappingTurtle extends Turtle {
	
	public WrappingTurtle(Position position, Direction direction,
			 String type, boolean pendown, Paper paper, char brushchar) {
		
		super(position, direction, type, pendown, paper, brushchar);
	}
	
	@Override
	public boolean borderHandler() {
		if (byWall()) {
			setPosition(this.getPosition().wrap(getResidingPaper(), getDirection()));
			return false;
		}
		return true;
	}
}
