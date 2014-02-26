package de.soulwax.ariadne.entities.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import de.soulwax.ariadne.Fonts;

/**
 * 
 * @author Soulwax
 */
public class Button extends WindowElement {
	private Image buttonUp;
	private Image buttonDown;
	private Image buttonOver;

	private Image symbol;

	private MouseOverArea area;

	public Button(int xp, int yp, int w, int h, Window window, String caption) {
		super(xp, yp, w, h, window, caption);
	}

	public Button(int xp, int yp, int w, int h, Window window, String caption, Image symbol) {
		super(xp, yp, w, h, window, caption);
		this.symbol = symbol;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if (symbol != null) {
			g.drawImage(symbol, xxp, yyp);
		}
		area.render(container, g);
		g.setAntiAlias(false);
		g.setFont(Fonts.WHITE_16_PIXELATED);

		g.drawString(caption, xxp + w / 2 - Fonts.WHITE_16_PIXELATED.getWidth(caption) / 2, 
				yyp + h / 2 - Fonts.WHITE_16_PIXELATED.getLineHeight() / 2);	
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		area.setX(xxp);
		area.setY(yyp);

		buttonUp.setAlpha(window.alpha);
		buttonDown.setAlpha(window.alpha);
		buttonOver.setAlpha(window.alpha);
	}

	public MouseOverArea getArea() {
		return area;
	}

	public void setupButton(String pathUp, String pathDown, String pathOver) {
		try {
			buttonUp = new Image(pathUp);
			buttonDown = new Image(pathDown);
			buttonOver = new Image(pathOver);
			area = new MouseOverArea(window.getMainMenu().getAppGameContainer(), 
					buttonUp, (int) xxp, (int) yyp, (int) w, (int) h, window.getMainMenu());
			area.setMouseDownImage(buttonDown);
			area.setMouseOverImage(buttonOver);

		} catch (SlickException ex) {
			Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setupButton(String pathUp, String pathDown, String pathOver, String symbolPath) {
		setupButton(pathUp, pathDown, pathOver);
		try {
			this.symbol = new Image(symbolPath);
			this.symbol = symbol.getScaledCopy(w/symbol.getWidth());
		} catch (SlickException ex) {
			Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
