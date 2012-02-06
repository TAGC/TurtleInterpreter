package turtle.implementations;

import turtle.*;
import turtle.util.*;

public class BouncyTurtle extends Turtle {
	
	public BouncyTurtle(Position position, Direction direction,
			 String type, boolean pendown, Paper paper, char brushchar) {
		
		super(position, direction, type, pendown, paper, brushchar);
	}
	
	@Override
	public boolean borderHandler() {
		if (byWall()) {
			setDirection(this.getDirection().bounce());
			return false;
		}
		return true;
	}
}
