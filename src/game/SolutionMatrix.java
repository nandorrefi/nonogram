package game;

import java.util.Random;
import java.util.Vector;

public class SolutionMatrix extends Matrix {
	
	public SolutionMatrix(int width, int height) {
		initCells(width, height);
		
		initSolutionRows();
		initSolutionColumns();	
	}

	private void initCells(int width, int height) {
		cells = new Vector<Vector<CellState>>();

		Random rand = new Random();

		for(int i = 0; i < height; ++i) {
			cells.add(new Vector<CellState>());
			for(int j = 0; j < width; ++j) {
				boolean filled = rand.nextBoolean();

				if(filled) {
					cells.lastElement().add(CellState.FILLED);
				}
				else {
					cells.lastElement().add(CellState.EMPTY);
				}

			}
		}
	}
	
	
}
