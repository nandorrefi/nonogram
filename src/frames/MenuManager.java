package frames;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

public class MenuManager {
	private MainMenu mainMenu = new MainMenu();
	private SettingsMenu settingsMenu = new SettingsMenu();
	private GameFrame gameFrame = new GameFrame();

	public MenuManager() {
		initActionListeners();
		mainMenu.setVisible(true);
	}

	private void initActionListeners() {
		initMainMenuListeners();
		initSettingsMenuListeners();
		initGameFrameListeners();
	}

	private void switchFrames(JFrame f1, JFrame f2) {
		if(f1.isVisible()) {
			f1.setVisible(false);
			f2.setVisible(true);
		}
		else {
			f1.setVisible(true);
			f2.setVisible(false);
		}

	}

	private void initMainMenuListeners(){
		mainMenu.setNewGameButtonActionListener((ActionEvent e) -> switchFrames(settingsMenu, mainMenu));

		mainMenu.setContinueButtonActionListener((ActionEvent e) ->
		{
			try {
				gameFrame.loadGame();
			} catch (Exception ex) {
				ex.getMessage();
				ex.printStackTrace();
			}

			switchFrames(mainMenu, gameFrame);
		});

		mainMenu.setExitButtonActionListener((ActionEvent e) -> System.exit(0));
	}

	private void initSettingsMenuListeners(){
		settingsMenu.setStartButtonActionListener((ActionEvent e) ->
		{
			gameFrame.createGame(settingsMenu.getMatrixWidth(), settingsMenu.getMatrixHeight());

			switchFrames(settingsMenu, gameFrame);
		});

		settingsMenu.setBackButtonActionListener((ActionEvent e) -> switchFrames(settingsMenu, mainMenu));
	}

	private void initGameFrameListeners(){
		gameFrame.setSaveAndExitActionListener((ActionEvent e) ->
		{
			try {
				gameFrame.saveGame();
			} catch (IOException ex) {
				ex.getMessage();
				ex.printStackTrace();
			}
			gameFrame.exitGame();

			switchFrames(mainMenu, gameFrame);
		});

		gameFrame.setNewGameListener((ActionEvent e) ->
		{
			gameFrame.exitGame();

			switchFrames(settingsMenu, gameFrame);
		});

		gameFrame.setExitActionListener((ActionEvent e) ->
		{
			gameFrame.exitGame();

			switchFrames(mainMenu, gameFrame);
		});
	}

}
