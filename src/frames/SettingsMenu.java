package frames;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingsMenu extends JFrame {

	private JComboBox<Object> matrixWidth;
	private JComboBox<Object> matrixHeight;
	
	private JButton backButton;
	private JButton startButton;
	private final GridBagConstraints gridBag = new GridBagConstraints();
	private final JPanel mainPanel = new JPanel(new GridBagLayout());

	public int getMatrixWidth(){
		try {
			return (int)matrixWidth.getSelectedItem();
		}catch (NullPointerException e){
			e.getMessage();
			e.getStackTrace();
		}

		return 0;
	}
	
	public int getMatrixHeight(){
		try {
			return (int)matrixHeight.getSelectedItem();
		}catch (NullPointerException e){
			e.getMessage();
			e.getStackTrace();
		}

		return 0;
	}
	
	void setBackButtonActionListener( ActionListener l ) {
		backButton.addActionListener(l);
	}
	
	void setStartButtonActionListener( ActionListener l ) {
		startButton.addActionListener(l);
	}

	public SettingsMenu() {

		super("Game Settings");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width/4, screenSize.height/4);

		initPanel();
	}

	private void initPanel(){
		initComponents();
		addAllComponentsToGridBagPanel();
		
		this.add(mainPanel);
	}

	private void initComponents(){
		Object[] matrixSizes = { 5, 10, 15, 20 };
		matrixWidth = new JComboBox<Object>(matrixSizes);
		matrixHeight = new JComboBox<Object>(matrixSizes);
		backButton = new JButton("Back");
		startButton = new JButton("Start Game");
	}

	private void addAllComponentsToGridBagPanel(){
		gridBag.fill = GridBagConstraints.HORIZONTAL;
		gridBag.insets = new Insets(5, 5, 5, 5);

		addToGridBagPanel(new JLabel("Width:"), 0, 0);
		addToGridBagPanel(new JLabel("Height:"), 1, 0);

		addToGridBagPanel(matrixWidth, 0, 1);
		addToGridBagPanel(matrixHeight, 1, 1);

		addToGridBagPanel(backButton, 0, 2);
		addToGridBagPanel(startButton, 1, 2);
	}

	private void addToGridBagPanel(JComponent component, int x, int y){
		gridBag.gridx = x;
		gridBag.gridy = y;
		mainPanel.add(component, gridBag);
	}

}
