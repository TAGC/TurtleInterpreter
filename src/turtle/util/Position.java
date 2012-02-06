package turtle.util;

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

}
