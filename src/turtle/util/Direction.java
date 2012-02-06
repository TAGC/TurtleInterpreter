package turtle.util;

import turtle.Paper;

public enum Direction {
	NORTH          (0, -1), 
	NORTH_EAST     (1, -1), 
	EAST           (1, 0), 
	SOUTH_EAST     (1, 1), 
	SOUTH          (0, 1), 
	SOUTH_WEST     (-1, 1), 
	WEST           (-1, 0), 
	NORTH_WEST     (-1, -1);
	
	private int xtranslate, ytranslate;
	private final int enumlength = 8;
	
	private Direction(int x, int y) {
		xtranslate = x;
		ytranslate = y;
	}
	
	public Direction getDirection() {
		return this;
	}
	
	public int getXTranslation() {
		return xtranslate;
	}
	
	public int getYTranslation() {
		return ytranslate;
	}
	
	public Position move(Position position) {
		Position newposition;
		int newx, newy;
		
		newx        = position.getX() + getXTranslation();
		newy        = position.getY() + getYTranslation();
		newposition = new Position(newx, newy);
		return newposition;
	}
	
	public Direction rotate(int degrees) {
		Direction newdirection;
		int directionswitches;
		
		directionswitches = degrees / 45;
		newdirection      = Direction.values()[(this.ordinal() + directionswitches) % enumlength];
		return newdirection;		
	}
	
	public Direction bounce() {
		Direction newdirection;
		newdirection = this.rotate(180);
		return newdirection;
	}
	
	public Direction reflect(Position pos, Paper paper) {
		int xtranslate, ytranslate, dx, dy;
		boolean sides, floor_or_ceil;
		
		sides = false;
		floor_or_ceil = false;
		if (pos.getX() == 0 || pos.getX() == paper.getWidth()-1) {
			sides = true;
		}
		if (pos.getY() == 0 || pos.getY() == paper.getHeight()-1) {
			floor_or_ceil = true;
		}
		
		xtranslate = this.getXTranslation();
		ytranslate = this.getYTranslation();
		
		if (sides) {
			//side walls
			xtranslate *= -1;
		}
		
		if (floor_or_ceil) {
			//top or bottom walls
			ytranslate *= -1;
		}
		
		for(Direction d : Direction.values()) {
			dx = d.getXTranslation();
			dy = d.getYTranslation();
			if(xtranslate == dx && ytranslate == dy) {
				return d;
			}
		}
		
		return null;
	}
}
