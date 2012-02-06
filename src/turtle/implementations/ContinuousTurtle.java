package turtle.implementations;

import turtle.*;
import turtle.util.*;

public class ContinuousTurtle extends Turtle {
	
	public ContinuousTurtle(Position position, Direction direction,
			 String type, boolean pendown, Paper paper, char brushchar) {
		
		super(position, direction, type, pendown, paper, brushchar);
	}
	
	@Override
	public boolean borderHandler() {
		return true;
	}
}
