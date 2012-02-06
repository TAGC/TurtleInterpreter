package turtle.util;

import turtle.Paper;

public class Position {
	
	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		String stringrep;
		stringrep = String.format("%s, %s", x, y);
		return stringrep;
	}
	
	public Position wrap(Paper paper, Direction direction) {
		Position newposition;
		int newx, newy;
		
		newx = getX();
		newy = getY();
		
		if (this.getX() == 0) {
			newx = paper.getWidth()-1;
		} else if (this.getX() == paper.getWidth()-1) {
			newx = 0;
		} else {
			newx += direction.getXTranslation();
		}
		
		if (this.getY() == 0) {
			newy = paper.getHeight()-1;
		} else if (this.getY() == paper.getHeight()-1) {
			newy = 0;
		} else {
			newy += direction.getYTranslation();
		}
		
		newposition = new Position(newx, newy);
		return newposition;
	}

}
