package de.oden.omega.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.oden.omega.Colors;

/**
 * 
 * @author Soulwax
 */

public class SpritePreview extends WindowElement {

	private Image[][] sheet;

	public SpritePreview(int xp, int yp, int w, int h, Window window, String caption, Image[][] sheet) {
		super(xp, yp, w, h, window, caption);
		this.sheet = sheet;

	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		super.init(container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Colors.DARK_TRANSPARENT);
		g.fillRect(xxp, yyp, w, h + 25);
		g.setColor(Colors.WHITE);
		g.drawRect(xxp, yyp, w, h + 25);

		// sheet.getSubImage((int)xx * 64 * Game.SCALE, (int) yy * 64 *
		// Game.SCALE, 64 * Game.SCALE, 64 * Game.SCALE).draw(xxp, yyp);
		sheet[(int)xx][(int)yy].draw(xxp, yyp);
	}

	private float xx = 0, yy = 10;
	private float xxa = 0.15f, yya = 0;

	@Override
	public void update(GameContainer container, int delta) {
		if (xx >= 8) {
			xx = 0f;
		}

		xx += xxa;
		yy += yya;
		super.update(container, delta);
	}
}
