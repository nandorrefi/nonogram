package game;

import java.io.Serializable;
import java.util.Vector;

public class PlayerMatrix extends Matrix implements Serializable{

	public PlayerMatrix(int width, int height) {

		cells = new Vector<Vector<CellState>>();
		
		for(int i = 0; i < height; ++i) {
			cells.add(new Vector<CellState>());
			for(int j = 0; j < width; ++j) {
				cells.lastElement().add(CellState.EMPTY);
			}
		}
	}
	
	public void setState( int x, int y, CellState cs) {
		cells.get(y).set(x, cs);
	}
}
