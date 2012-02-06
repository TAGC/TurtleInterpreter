package turtle.implementations;

import turtle.*;
import turtle.util.*;

public class NormalTurtle extends Turtle {
	
	public NormalTurtle(Position position, Direction direction,
			 String type, boolean pendown, Paper paper, char brushchar) {
		
		super(position, direction, type, pendown, paper, brushchar);
	}
	
	@Override
	public boolean borderHandler() {
		return !byWall();
	}
}
