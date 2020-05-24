package frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JFrame {
	
	private JButton newGameButton;
	private JButton continueButton;
	private JButton exitButton;
	JPanel mainMenuPanel = new JPanel();
	
	void setNewGameButtonActionListener( ActionListener l ) {
		newGameButton.addActionListener(l);
	}
	
	void setContinueButtonActionListener( ActionListener l ) {
		continueButton.addActionListener(l);
	}
	
	void setExitButtonActionListener( ActionListener l ) {
		exitButton.addActionListener(l);
	}

	public MainMenu(){
		super("Nonogram");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width/2, screenSize.height/2);

        initPanel();
	}

	private void initPanel() {
		mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.PAGE_AXIS ));
		this.add(mainMenuPanel, BorderLayout.CENTER);

		initButtons();
		addButtonsToPanel();
	}

	private void initButtons(){
		newGameButton = new JButton("New Game");
		continueButton = new JButton("Continue");
		exitButton = new JButton("Exit");

		alignButtons(CENTER_ALIGNMENT);
		initButtonSizes();
	}

	private void alignButtons(float alignment){
		newGameButton.setAlignmentX(alignment);
		continueButton.setAlignmentX(alignment);
		exitButton.setAlignmentX(alignment);
	}

	private void initButtonSizes(){
		int divisor = 5;
		Dimension buttonSize = new Dimension(this.getSize().width/divisor, this.getSize().height/divisor);

		newGameButton.setMaximumSize(buttonSize);
		continueButton.setMaximumSize(buttonSize);
		exitButton.setMaximumSize(buttonSize);
	}

	private void addButtonsToPanel(){
		addToPanel(newGameButton);
		addToPanel(continueButton);
		addToPanel(exitButton);
	}

	private void addToPanel(JComponent component){
		int verticalPadding = this.getSize().height/10;
		mainMenuPanel.add(Box.createRigidArea(new Dimension(0, verticalPadding)));
		mainMenuPanel.add(component);
	}


}
