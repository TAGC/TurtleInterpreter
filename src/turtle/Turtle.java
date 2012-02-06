package turtle;

import turtle.util.*;

public abstract class Turtle {
	
	private boolean pendown;
	private Position position;
	private Direction direction;
	private Paper residingpaper;
	private char brushchar;
		
	public Turtle(Position position, Direction direction, String type, 
			      boolean pendown, Paper paper, char brushchar) {
		
		this.position = position;
		this.direction = direction;
		this.pendown = pendown;
		this.residingpaper = paper;
		this.brushchar = brushchar;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position p) {
		position = p;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction d) {
		direction = d;
	}
	
	public Paper getResidingPaper() {
		return residingpaper;
	}
	
	public char getBrushChar() {
		return brushchar;
	}
	
	public boolean isPenDown() {
		return pendown;
	}
	
	public void setPenDown() {
		pendown = true;
	}
	
	public void setPenUp() {
		pendown = false;
	}
	
	public void setChar(char c) {
		brushchar = c;
	}
	
	public void rotate(int rotation) {
		direction = getDirection().rotate(rotation);
	}
	
	public void move(int steps) {
		
		for(int i=0; i < steps; i++) {
			
			//mark old spot before moving to the new spot if pen is down
			if (isPenDown()) {
				residingpaper.markSpot(getPosition(), getBrushChar());
			}
			if (borderHandler()) {
				setPosition(getDirection().move(getPosition()));
			}
		}
	}
	
	//returns true iff the turtle is adjacent to the ceiling, floor or left or right walls
	//and is directed towards the wall
	public boolean byWall() {
		int x, y, xmax, ymax, xtranslate, ytranslate;
		boolean condition;
		
		x = getPosition().getX();
		y = getPosition().getY();
		xmax = getResidingPaper().getWidth()-1;
		ymax = getResidingPaper().getHeight()-1;
		xtranslate = getDirection().getXTranslation();
		ytranslate = getDirection().getYTranslation();
		
		condition = x == 0    && xtranslate == -1
		         || x == xmax && xtranslate ==  1
		         || y == 0    && ytranslate == -1
		         || y == ymax && ytranslate ==  1;
		
		return condition;		
	}
	
	public abstract boolean borderHandler();
}
