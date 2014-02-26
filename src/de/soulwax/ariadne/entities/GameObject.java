package de.soulwax.ariadne.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.soulwax.ariadne.levels.Level;

/**
 * 
 * @author Soulwax
 */

public class GameObject {
	public boolean removed;

	public double x, y, z;
	public double xr = 5;
	public double yr = 5;
	public double zh = 5;

	public Level level;

	public final void init(Level level, GameContainer container, StateBasedGame game) throws SlickException {
		this.level = level;
		init(container, game);
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {

	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
	}

	public void remove() {
		removed = true;
	}
}
