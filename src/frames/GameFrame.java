package frames;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import game.*;

public class GameFrame extends JFrame {

	private JMenuBar menuBar;
	private JMenuItem newGame;
	private JMenuItem saveAndExit;
	private JMenuItem exit;
	private GameManager gm;

	public void saveGame() throws IOException {
		gm.saveGame("last_save");
	}
	
	public void exitGame() {
		gm.exitGame();
	}

	public void createGame(int gameWidth, int gameHeight) {

		gm = new GameManager(this, gameWidth, gameHeight);
	}

	public void loadGame() throws ClassNotFoundException, IOException {
		gm = new GameManager(this);

		gm.loadGame("last_save");
	}

	void setExitActionListener( ActionListener l) {
		exit.addActionListener(l);
	}

	void setNewGameListener( ActionListener l) {
		newGame.addActionListener(l);
	}

	void setSaveAndExitActionListener( ActionListener l) {
		saveAndExit.addActionListener(l);
	}
	
	public GameFrame() {
		super("Nonogram");
					
		initMenuBar();
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
	}

	private void initMenuBar() {
		menuBar = new JMenuBar();
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS) );
		this.setJMenuBar(menuBar);

		initAndAddMenuItems();
	}

	private void initAndAddMenuItems(){
		initMenuItems();
		addMenuItems();
	}

	private void initMenuItems(){
		newGame = new JMenuItem("New Game");
		saveAndExit = new JMenuItem("Save & Exit");
		exit = new JMenuItem("Exit");

		initItemSizes();
	}

	private void initItemSizes(){
		Dimension itemDimension = new Dimension(150, 70);

		newGame.setMaximumSize(itemDimension);
		saveAndExit.setMaximumSize(itemDimension);
		exit.setMaximumSize(itemDimension);
	}

	private void addMenuItems(){
		menuBar.add(newGame);
		menuBar.add(saveAndExit);
		menuBar.add(exit);
	}
	

}
