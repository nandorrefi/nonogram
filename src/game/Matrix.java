package game;

import java.io.Serializable;
import java.util.Vector;

public abstract class Matrix implements Serializable {
	
	protected Vector<Vector<CellState>> cells;
	
	protected Vector<Vector<Integer>> solutionRows;
	protected Vector<Vector<Integer>> solutionColumns;
	
	public Matrix() {
		
	}	
	
	public Vector<Vector<Integer>> getSolutionRows() {
		return solutionRows;
	}
	
	public Vector<Vector<Integer>> getSolutionColumns() {
		return solutionColumns;
	}
	
	public void initSolutionRows() {
		
		int blockSize = 0;
		solutionRows = new Vector<Vector<Integer>>();
		for(int i = 0; i < cells.size(); ++i) {
			solutionRows.add(new Vector<Integer>());
			for(int j = 0; j < cells.get(i).size(); ++j) {
				if(cells.get(i).get(j) == CellState.FILLED) {
					++blockSize;
				}
				else if( (cells.get(i).get(j) == CellState.EMPTY || cells.get(i).get(j) == CellState.MARKED_EMPTY) && blockSize > 0) {
					solutionRows.lastElement().add(blockSize);
					blockSize = 0;
				}
			}
			if(blockSize > 0) {
				solutionRows.lastElement().add(blockSize);
				blockSize = 0;
			}
		}
	
	}
	
	public void initSolutionColumns() {
		int blockSize = 0;
		solutionColumns = new Vector<Vector<Integer>>();
		
		for(int i = 0; i < cells.get(0).size(); ++i) {
			solutionColumns.add(new Vector<Integer>());
			for(int j = 0; j < cells.size(); ++j) {
				if(cells.get(j).get(i) == CellState.FILLED) {
					++blockSize;
				}
				else if( (cells.get(j).get(i) == CellState.EMPTY || cells.get(j).get(i) == CellState.MARKED_EMPTY)  && blockSize > 0) {
					solutionColumns.lastElement().add(blockSize);
					blockSize = 0;
				}
			}
			if(blockSize > 0) {
				solutionColumns.lastElement().add(blockSize);
				blockSize = 0;
			}
		}
		
	}
	
	public int getWidth() {
		return cells.get(0).size();
	}
	
	public int getHeight() {
		return cells.size();
	}
	
	public CellState getCell(int x, int y)  throws IndexOutOfBoundsException {
		return cells.get(y).get(x);
	}
	
}
