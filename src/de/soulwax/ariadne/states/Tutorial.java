package de.soulwax.ariadne.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.soulwax.ariadne.InputHandler;
import de.soulwax.ariadne.State;
import de.soulwax.ariadne.levels.Level;

/**
 * 
 * @author Soulwax
 */

public class Tutorial extends BasicGameState {

	private Level tutorialLevel;
	public static InputHandler input;
	
	public Tutorial(int state) {

	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		tutorialLevel = new Level(128, 128);
		tutorialLevel.init(container, game);
		input = StartMenu.input;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		tutorialLevel.update(container, game, delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		tutorialLevel.render(container, game, g);
	}

	@Override
	public int getID() {
		return State.GAME_1;
	}
	
	public Level getLevel() {
		return tutorialLevel;
	}

}
