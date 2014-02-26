package de.soulwax.ariadne.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.soulwax.ariadne.Game;
import de.soulwax.ariadne.gui.Button;
import de.soulwax.ariadne.gui.Window;
import de.soulwax.ariadne.states.StartMenu;

/**
 * 
 * @author Soulwax
 */

public class MainMenu {

	private Window window;

	private StartMenu menu;
	private Button exitButton;
	private Button newGameButton;

	public MainMenu(StartMenu menu) {
		this.menu = menu;
	}

	public void init(GameContainer container) throws SlickException {
		window = new Window(Game.WIDTH / 2 - 200, Game.HEIGHT / 2 - 300, 
				400, 600, menu, false, "Main Menu");

		// Exit Button
		exitButton = new Button(125, 500, 150, 60, window, "Exit");
		exitButton.setupButton("res/gui/button_up_main.png", 
				"res/gui/button_down_main.png", 
				"res/gui/button_over_main.png");
		window.addElement(exitButton);

		// New Game Button
		newGameButton = new Button(125, 100, 150, 60, window, "New Game");
		newGameButton.setupButton("res/gui/button_up_main.png", 
				"res/gui/button_down_main.png", 
				"res/gui/button_over_main.png");
		window.addElement(newGameButton);

		menu.addWindow(window);
		window.setVisible(true);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		window.update(container, delta);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		window.render(container, game, g);
	}
	
	public Window getWindow() {
		return window;
	}

	public Button getButton(String button) {
		switch (button) {
		case "exitButton":
			return exitButton;
		case "newGameButton":
			return newGameButton;
		default:
			return null;
		}
	}
}
