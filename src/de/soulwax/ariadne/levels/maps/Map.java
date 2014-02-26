package de.soulwax.ariadne.levels.maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Soulwax
 */

public class Map {
	private final int w, h;

	public Map(int w, int h) {
		this.w = w;
		this.h = h;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}
}
