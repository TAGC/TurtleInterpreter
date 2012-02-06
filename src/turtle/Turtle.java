package turtle;

import turtle.util.*;

public class Turtle {
	
	private boolean pendown;
	private Position position;
	private Direction direction;
	private String type;
	private Paper residingpaper;
	private char brushchar;
		
	public Turtle(Position position, Direction direction, String type, 
			      boolean pendown, Paper paper, char brushchar) {
		
		this.position = position;
		this.direction = direction;
		this.type = type;
		this.pendown = pendown;
		this.residingpaper = paper;
		this.brushchar = brushchar;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Direction getDirection() {
		return direction;
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
		Position newposition;
		
		for(int i=0; i < steps; i++) {
			newposition = getDirection().move(getPosition());
			if (!getResidingPaper().checkWithinBounds(newposition)) {
				if (isPenDown()) {
					residingpaper.markSpot(getPosition(), getBrushChar());
				}
				System.out.println("NOT WITHIN BOUNDS: " + newposition.toString());
				break;
			}
			
			//mark old spot before moving to the new spot if pen is down
			if (isPenDown()) {
				System.out.println("MARKING SPOT AT " + getPosition().toString() + " WITH " + getBrushChar());
				residingpaper.markSpot(getPosition(), getBrushChar());
			}
			position = newposition;
		}
	}
}
