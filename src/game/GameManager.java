package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;

public class GameManager {
	
	private DisplayGame display;
	private PlayerMatrix playerMatrix;
	private SolutionMatrix solutionMatrix;
	private int cellSize;
	private Vector<Vector<CellPolygon>> cellPolygons;
	private Interact interaction;
	private JFrame frame;
	
	public GameManager(JFrame frame) {
		this.frame = frame;
	}
	
	public GameManager( JFrame frame, int width, int height ) {
		playerMatrix = new PlayerMatrix(width, height);
		solutionMatrix = new SolutionMatrix(width, height);
		setCellSize();
		display = new DisplayGame(playerMatrix, solutionMatrix);
		
		cellPolygons = display.getCellPolygons();
		interaction = new Interact();
		
		display.addMouseListener(interaction);
		
		this.frame = frame;
		frame.add(display);
	}
	
	private void setCellSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if(playerMatrix.getWidth() > playerMatrix.getHeight()) {
			cellSize = (int)(screenSize.getWidth()/playerMatrix.getWidth()/2);
		} else {
			cellSize = (int)(screenSize.getHeight()/playerMatrix.getHeight()/2);
		}
		
	}
	
	public void exitGame() {
		frame.remove(display);
	}
	
	public void saveGame(String name) throws IOException {
		
		ArrayList<Matrix> matrixes = new ArrayList<>();

		FileOutputStream f = new  FileOutputStream(name);
		ObjectOutputStream out = new ObjectOutputStream(f);
		matrixes.add(playerMatrix);
		matrixes.add(solutionMatrix);
		out.writeObject(matrixes);
		out.close();
		
	}
	
	public void loadGame(String name) throws IOException, ClassNotFoundException {
		FileInputStream f = new FileInputStream(name);
		ObjectInputStream in = new ObjectInputStream(f);
			
		@SuppressWarnings("unchecked")
		ArrayList<Matrix> matrixes = (ArrayList<Matrix>)in.readObject();
			
		in.close();
			
		playerMatrix = (PlayerMatrix) matrixes.get(0);
		solutionMatrix = (SolutionMatrix) matrixes.get(1);
			
		setCellSize();
		display = new DisplayGame(playerMatrix, solutionMatrix);
			
		cellPolygons = display.getCellPolygons();
		interaction = new Interact();
			
		display.addMouseListener(interaction);
		frame.add(display);
			 
	}
	
	private class Interact implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			for(int i = 0; i < cellPolygons.size(); ++i) {
				for(int j = 0; j < cellPolygons.get(i).size(); ++j) {
					int x = cellPolygons.get(i).get(j).getX();
					int y = cellPolygons.get(i).get(j).getY();
					
					if(e.getPoint().x >= x && 
						e.getPoint().x <= x + cellSize &&
						e.getPoint().y >= y &&
						e.getPoint().y <= y + cellSize ) {
						
						if( e.getButton() == MouseEvent.BUTTON1 ) {
							if( playerMatrix.getCell(j, i) == CellState.FILLED) {
								playerMatrix.setState(j, i, CellState.EMPTY);
							} else {
								playerMatrix.setState(j, i, CellState.FILLED);
							}
						}
						else if( e.getButton() == MouseEvent.BUTTON3 ) {
							if(playerMatrix.getCell(j, i) == CellState.MARKED_EMPTY) {
								playerMatrix.setState(j, i, CellState.EMPTY);
							} else {
								playerMatrix.setState(j, i, CellState.MARKED_EMPTY);
							}
						}
						
						
						if(checkSolution()) {
							display.writeYouWin();
							display.removeMouseListener(this);
						}
						display.repaint();
						
						return;
					}
				}
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
	
	public boolean checkSolution() {
		playerMatrix.initSolutionColumns();
		playerMatrix.initSolutionRows();
		
		return playerMatrix.getSolutionColumns().equals(solutionMatrix.getSolutionColumns()) && playerMatrix.getSolutionRows().equals(solutionMatrix.getSolutionRows());
	}
	
	
}
