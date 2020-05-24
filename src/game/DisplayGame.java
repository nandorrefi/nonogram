package game;

import java.awt.*;
import java.util.Vector;

import javax.swing.JPanel;

public class DisplayGame extends JPanel{

	private PlayerMatrix playerMatrix;
	private SolutionMatrix solutionMatrix;
	
	private int cellSize;
	private int offsetX;
	private int offsetY;
	private int mainFontSize;
	
	private boolean victory = false;
	
	private Vector<Vector<CellPolygon>> cellPolygons;
	
	public DisplayGame(PlayerMatrix playerMatrix, SolutionMatrix solutionMatrix) {
		
		this.playerMatrix = playerMatrix;
		this.solutionMatrix = solutionMatrix;

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		double maxSizeWidth = 2*screen.getWidth()/(double)(playerMatrix.getWidth()+1)/3;
		double maxSizeHeight = 2*screen.getHeight()/(double)(playerMatrix.getHeight()+1)/3;

		this.cellSize = (int)Math.min(maxSizeHeight, maxSizeWidth);
		
		mainFontSize = cellSize/2;

		offsetX = (int)Math.ceil((double)playerMatrix.getWidth()*cellSize/2);
		offsetY = (int)Math.ceil((double)playerMatrix.getHeight()*cellSize/2);

		initCellPolygons(offsetX, offsetY);
	}
	
	private void initCellPolygons(int offsetX, int offsetY) {
		int width = playerMatrix.getWidth();
		int height = playerMatrix.getHeight();
		
		cellPolygons = new Vector<Vector<CellPolygon>>();
		
		for(int i = 0; i < height; ++i) {
			cellPolygons.add(new Vector<CellPolygon>());
			for(int j = 0; j < width; ++j) {
				cellPolygons.lastElement().add(new CellPolygon( offsetX+j*cellSize, offsetY+i*cellSize, cellSize ) );
			}
		}
	}
	
	private void drawMatrix(Graphics g) {
		for(int i = 0; i < cellPolygons.size(); ++i) {
			for(int j = 0; j < cellPolygons.get(i).size(); ++j) {
				if( playerMatrix.getCell(j, i) == CellState.EMPTY ) {
					g.drawPolygon(cellPolygons.get(i).get(j));
				}
				else if( playerMatrix.getCell(j, i) == CellState.MARKED_EMPTY ) {
					g.drawPolygon(cellPolygons.get(i).get(j));
					g.drawLine(cellPolygons.get(i).get(j).getX(), cellPolygons.get(i).get(j).getY(), cellPolygons.get(i).get(j).getX()+cellSize, cellPolygons.get(i).get(j).getY()+cellSize);
					g.drawLine(cellPolygons.get(i).get(j).getX()+cellSize, cellPolygons.get(i).get(j).getY(), cellPolygons.get(i).get(j).getX(), cellPolygons.get(i).get(j).getY()+cellSize);
				}
				else if( playerMatrix.getCell(j, i) == CellState.FILLED ) {
					g.drawPolygon(cellPolygons.get(i).get(j));
					g.fillPolygon(cellPolygons.get(i).get(j));
				}
			}
		}
		
		for(int i = 0; i <= cellPolygons.size(); ++i) {
			Graphics2D g2 = (Graphics2D) g;
			if(i%5 == 0) {
				g2.setStroke(new BasicStroke(5));
			} else {
				g2.setStroke(new BasicStroke(1));
			}
			g2.drawLine(0, offsetY+i*cellSize, offsetX+cellPolygons.get(0).size()*cellSize, offsetY+i*cellSize );
		}
		
		for(int i = 0; i <= cellPolygons.get(0).size(); ++i) {
			Graphics2D g2 = (Graphics2D) g;
			if(i%5 == 0) {
				g2.setStroke(new BasicStroke(5));
			} else {
				g2.setStroke(new BasicStroke(1));
			}
			
			g2.drawLine(offsetX+i*cellSize, 0, offsetX+i*cellSize, offsetY+cellPolygons.size()*cellSize  );
		}
		
	}
	
	private void drawSolutions(Graphics g) {
		Vector<Vector<Integer>> solCol = solutionMatrix.getSolutionColumns();
		
		for(int i = 0; i < solCol.size(); ++i) {
			if(solCol.get(i).size() == 0) {
				g.drawString("0", offsetX + i*cellSize + (int)cellSize/2 - mainFontSize/3, offsetY - (int)cellSize/2);
			} 
			else for(int j = 0; j < solCol.get(i).size(); ++j) {
				g.drawString( Integer.toString(solCol.get(i).get(j)) , offsetX + i*cellSize + (int)cellSize/2 - mainFontSize/3, offsetY - (solCol.get(i).size()-j)*cellSize + (int)cellSize/2);
			}
		}
		
		Vector<Vector<Integer>> solRow = solutionMatrix.getSolutionRows();
		
		for(int i = 0; i < solRow.size(); ++i) {
			if(solRow.get(i).size() == 0) {
				g.drawString("0",  offsetX - cellSize/2, offsetY + i * cellSize + cellSize/2 + mainFontSize/2);
			} 
			else for(int j = 0; j < solRow.get(i).size(); ++j) {
				g.drawString( Integer.toString(solRow.get(i).get(j)) , offsetX - (solRow.get(i).size()-j) * cellSize + cellSize/2, offsetY + i * cellSize + cellSize/2 + mainFontSize/2);
			}
		}
	}
	
	private void showVictory(Graphics g) {
		int x = 0;
		int y = offsetY/4;
		int winRectW = offsetX;
		int winRectH = offsetY/2;
			
		int fontSize = winRectH/3;
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
			
		g.setColor(Color.RED);
		g.fillRect(x, y, winRectW, winRectH);
		g.setColor(Color.BLACK);
		g.drawString("You Win!", x + winRectW/2 - fontSize*2, y + winRectH/2 + fontSize/4);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setFont(new Font("TimesRoman", Font.BOLD, mainFontSize));
		drawMatrix(g);
		drawSolutions(g);
		
		if(victory)
			showVictory(g);
		
	}
	
	public Vector<Vector<CellPolygon>> getCellPolygons() {
		return cellPolygons;
	}
	
	public void writeYouWin() {
		victory = true;
	}

}
