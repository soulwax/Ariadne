package de.soulwax.ariadne.entities.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.soulwax.ariadne.Art;
import de.soulwax.ariadne.Game;
import de.soulwax.ariadne.entities.gui.Button;
import de.soulwax.ariadne.entities.gui.SpritePreview;
import de.soulwax.ariadne.entities.gui.StringField;
import de.soulwax.ariadne.entities.gui.StringLabel;
import de.soulwax.ariadne.entities.gui.Window;
import de.soulwax.ariadne.entities.states.StartMenu;

/**
 * 
 * @author Soulwax
 */

public class CharacterCreation {

	private StartMenu menu;

	private Window window;
	private Button createCharacter;
	private Button back;
	private Button male;
	private Button female;
	private Button start;
	private StringField nameField;
	private StringLabel charNameLabel;
	private SpritePreview sheetPreview;
	private Image[][] sheet;
	
	public CharacterCreation(StartMenu menu) {
		this.menu = menu;
	}

	public void init(GameContainer container) throws SlickException {
		window = new Window(Game.WIDTH / 2 - 250, Game.HEIGHT / 2 - 300, 
				500, 600, menu, false, "Character Creation");
		//window.init(container);

		back = new Button(20, 500, 200, 60, window, "Back");
		back.setupButton("res/gui/button_up_main.png", 
				"res/gui/button_down_main.png", 
				"res/gui/button_over_main.png");
		window.addElement(back);

		nameField = new StringField(240, 40, 200, 30, window, "name");
		//nameField.init(container);
		window.addElement(nameField);
		
		charNameLabel = new StringLabel(60, 40, 60, 40, window, "Character name: ");
		window.addElement(charNameLabel);

		male = new Button(57, 100, 60, 60, window, "");
		male.setupButton("res/gui/button_up_sqr.png", 
				"res/gui/button_down_sqr.png", 
				"res/gui/button_over_sqr.png",
				"res/gui/symbols/male.png");
		window.addElement(male);

		female = new Button(127, 100, 60, 60, window, "");
		female.setupButton("res/gui/button_up_sqr.png", 
				"res/gui/button_down_sqr.png", 
				"res/gui/button_over_sqr.png", 
				"res/gui/symbols/female.png");
		window.addElement(female);

		sheet = Art.i.femCharGreen;
		sheetPreview = 
				new SpritePreview(57, 160, 64*Game.SCALE, 64*Game.SCALE, window, "", sheet);
		window.addElement(sheetPreview);
		
		start = new Button(280, 500, 200, 60, window, "Start");
		start.setupButton("res/gui/button_up_main.png", 
				"res/gui/button_down_main.png", 
				"res/gui/button_over_main.png");
		window.addElement(start);
		
		menu.addWindow(window);
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
		case "back":
			return back;
		case "createCharacter":
			return createCharacter;
		case "start":
			return start;
		default:
			return null;
		}
	}
	
	public Image[][] getSpriteSheet() {
		return sheet;
	}
}
