package de.oden.omega.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.oden.omega.Fonts;

/**
 * 
 * @author Soulwax
 */

public class StringLabel extends WindowElement {

	private Font font;
	private Color color;

	public StringLabel(int xp, int yp, int w, int h, Window window, String caption) {
		super(xp, yp, w, h, window, caption);
		this.font = Fonts.WHITE_14_PIXELATED;
		this.color = Color.white;
	}

	public StringLabel(int xp, int yp, int w, int h, Window window, String caption, Font font, Color color) {
		super(xp, yp, w, h, window, caption);
		this.font = font;
		this.color = color;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(caption, xxp, yyp);
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
	}

}
