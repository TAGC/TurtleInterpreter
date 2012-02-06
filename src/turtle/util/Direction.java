package turtle.util;

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
		
		newx = position.getX() + getXTranslation();
		newy = position.getY() + getYTranslation();
		newposition = new Position(newx, newy);
		return newposition;
	}
	
	public Direction rotate(int degrees) {
		Direction newdirection;
		int directionswitches;
		
		directionswitches = degrees / 45;
		newdirection = Direction.values()[(this.ordinal() + directionswitches) % enumlength];
		return newdirection;		
	}
}
