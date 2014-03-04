package de.soulwax.ariadne.states;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.soulwax.ariadne.Fonts;
import de.soulwax.ariadne.Game;
import de.soulwax.ariadne.InputHandler;
import de.soulwax.ariadne.State;
import de.soulwax.ariadne.entities.Player;
import de.soulwax.ariadne.gui.Window;
import de.soulwax.ariadne.menus.CharacterCreation;
import de.soulwax.ariadne.menus.MainMenu;

/**
 * 
 * @author Soulwax
 */
public class StartMenu extends BasicGameState implements ComponentListener {

	private List<Window> windows = new ArrayList<>();

	private static Image standardCursor;
	private Image background;
	private Game game;
	
	public GameContainer container;
	public AppGameContainer app;

	public static InputHandler input;
	
	// Main Menu
	MainMenu mainMenu;

	// Character Creation Menu
	CharacterCreation charCreateMenu;

	public Comparator<Window> spriteComparator = new Comparator<Window>() {
		@Override
		public int compare(Window s0, Window s1) {
			if (!s0.isFocused()) return -1;
			if (s0.isFocused()) return 1;
			if (!s1.isFocused()) return -1;
			if (s1.isFocused()) return 1;
			return 0;
		}
	};

	public StartMenu(int state, Game game) {
		this.game = game;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		if (container instanceof AppGameContainer) {
			this.app = (AppGameContainer) container;
		}

		standardCursor = new Image("cursors/mouse_standard.png");
		background = new Image("bg.jpg");
		container.setMouseCursor(standardCursor, 0, 0);

		Fonts.init();
		initInput(container);

		mainMenu = new MainMenu(this);
		mainMenu.init(container);

		charCreateMenu = new CharacterCreation(this);
		charCreateMenu.init(container);
		
		for(Window w: windows) {
			w.init(container);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {	
		g.drawImage(background, 0, 0);
		TreeSet<Window> sortedWindows = new TreeSet<>(spriteComparator);

		for (Window w : windows) {
			sortedWindows.add(w);
		}

		for (Window w : sortedWindows) {
			if (w.equals(mainMenu.getWindow()))
				mainMenu.render(container, game, g);
			else
				charCreateMenu.render(container, game, g);
				
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		mainMenu.update(container, game, delta);
		charCreateMenu.update(container, game, delta);
	}

	@Override
	public int getID() {
		return State.MAIN_MENU;
	}

	private void initInput(GameContainer container) {
		input = new InputHandler(container);
		container.getInput().addMouseListener(input);
		container.getInput().addKeyListener(input);
	}

	public AppGameContainer getAppGameContainer() {
		return app;
	}

	@Override
	public void componentActivated(AbstractComponent source) {

		/** New Game Button **/
		if (source == mainMenu.getButton("newGameButton").getArea()) {
			switchWindow(mainMenu.getWindow(), charCreateMenu.getWindow());
		}

		/** Back Button **/
		if (source == charCreateMenu.getButton("back").getArea()) {
			switchWindow(charCreateMenu.getWindow(), mainMenu.getWindow());
		}

		/** Exit Button **/
		if (source == mainMenu.getButton("exitButton").getArea()) {
			System.exit(0);
		}

		 /** Enter Tutorial state **/
		if (source == charCreateMenu.getButton("start").getArea()) {
			
			Player player = new Player(charCreateMenu.getSpriteSheet());
			try {
				this.game.tutorial.getLevel().addNewEntity(player);
			} catch (SlickException e) {
				Log.error(e);
			}
			game.enterStateId(State.GAME_1);
		}
	}

	private void switchWindow(Window w1, Window w2) {
		w1.setVisible(false);
		w2.setVisible(true);
	}

	public void addWindow(Window window) {
		this.windows.add(window);
	}

	public List<Window> getWindows() {
		return windows;
	}
}
