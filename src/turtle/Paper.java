package turtle;

import turtle.util.*;

public class Paper {
	
	private int width, height;
	private char[][] markings;
	
	public Paper(int width, int height) {
		this.width = width;
		this.height = height;
		
		markings = new char[width][];
		for(int x=0; x < width; x++) {
			markings[x] = new char[height];
			for(int y=0; y < height; y++) {
				markings[x][y] = ' ';
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean checkWithinBounds(Position p) {
		boolean withinbounds;
		withinbounds = 0 <= p.getX() && p.getX() < getWidth()
					&& 0 <= p.getY() && p.getY() < getHeight();
		
		return withinbounds;
	}
	
	public void markSpot(Position p, char c) {
		if (checkWithinBounds(p)) {
			markings[p.getX()][p.getY()] = c;
		}
	}
	
	public String toString(boolean borders) {
		String stringrep, topborder, bottomborder;
		
		
		stringrep = "";
		topborder = "";
		bottomborder = "";
		
		for(int x=0; x < getWidth() + 2; x++) {
			topborder += "v";
			bottomborder += "^";
		}
		
		if (borders) {
			stringrep += topborder;
		}
		
		for(int y=0; y < getHeight(); y++) {
			if (borders) {
				stringrep += ">";
			}
			for(int x=0; x < getWidth(); x++) {
				stringrep += markings[x][y];
			}
			if (borders) {
				stringrep += "<\n";
			} else {
				stringrep += "\n";
			}
		}
		
		if (borders) {
			stringrep += bottomborder + "\n";
		}
		
		return stringrep;
	}
}
