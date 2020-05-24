package game;

import java.awt.Polygon;

public class CellPolygon extends Polygon {
	private final int x;
	private final int y;
	
	public CellPolygon( int x, int y, int cellSize ) {
		this.x = x;
		this.y = y;
		
		this.addPoint(x, y);
		this.addPoint(x+cellSize, y);
		this.addPoint(x+cellSize, y+cellSize);
		this.addPoint(x, y+cellSize);
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
