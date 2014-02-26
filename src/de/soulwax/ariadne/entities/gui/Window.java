package de.soulwax.ariadne.entities.gui;

import de.soulwax.ariadne.Colors;
import de.soulwax.ariadne.Fonts;
import de.soulwax.ariadne.Game;
import de.soulwax.ariadne.entities.states.StartMenu;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Soulwax
 */
public class Window extends GuiElement {

	public int id;

	public final List<WindowElement> elements = new ArrayList<>();

	private String title;
	private int cursorx, cursory;
	private final int HEADER_HEIGHT = 16;
	private boolean focused;
	private final StartMenu MENU;
	private MouseOverArea headerArea;
	private boolean shouldBlendIn = false, shouldBlendOut = false;
	private boolean shouldDrag;
	private boolean missed;

	public Window(int xp, int yp, int w, int h, StartMenu menu) {
		super(xp, yp, w, h);
		this.xz = xp;
		this.yz = yp;
		this.MENU = menu;
		this.visible = false;
	}

	public Window(int xp, int yp, int w, int h, StartMenu menu, boolean visible) {
		this(xp, yp, w, h, menu);
		this.xz = xp;
		this.yz = yp;
		this.visible = visible;
	}

	public Window(int xp, int yp, int w, int h, StartMenu menu, boolean visible, String title) {
		this(xp, yp, w, h, menu, visible);
		this.xz = xp;
		this.yz = yp;
		this.title = title;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		focused = false;
		headerArea = new MouseOverArea(MENU.getAppGameContainer(), 
				new Image("res/gui/empty.png"), 
				(int) xp, (int) yp, (int) w, (int) HEADER_HEIGHT, MENU);
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (!visible) {
			return;
		}

		headerArea.setX(xp);
		headerArea.setY(yp);
		float xm = StartMenu.input.x;
		float ym = StartMenu.input.y;
		boolean dragged = StartMenu.input.dragged;
		boolean lmbDown = StartMenu.input.mbl;

		for (WindowElement element : elements) {
			element.update(container, delta);
		}

		// If clicked on header
		if ((xm > xp - 16 && xm < w + xp + 16) && (ym > yp - 16 && ym < HEADER_HEIGHT * 2 + yp) && lmbDown) {
			for (Window w : MENU.getWindows()) {
				if (!w.equals(this)) {
					w.focused = false;
				}
			}
			if (!focused) {
				focused = true;
			}
			shouldDrag = true;

		} else {
			if (!shouldDrag) {
				missed = true;
			}
		}

		if (!lmbDown) {
			cursorx = (int) (xm - xp);
			cursory = (int) (ym - yp);
			shouldDrag = false;
			// focused = false;
			missed = false;
		}

		if (dragged && shouldDrag && focused && !(dragged && missed)) {
			xp = xz = (int) (xm - cursorx);
			yp = yz = (int) (ym - cursory);
		}

		// Keeps the window inside the visible screen area
		if (xp < 0) {
			xp = 0;
		} else if (xp + w > Game.WIDTH) {
			xp = (int) (Game.WIDTH - w);
		}

		if (yp < 0) {
			yp = 0;
		} else if (yp + h > Game.HEIGHT) {
			yp = (int) (Game.HEIGHT - h);
		}

		if (shouldBlendIn) {
			blendIn();
		} else if (shouldBlendOut) {
			blendOut();
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if (!visible) {
			return;
		}
		drawWindowBody(g);
		drawWindowHeader(g);

		for (WindowElement element : elements) {
			element.render(container, game, g);
		}

		headerArea.render(container, g);
	}

	public void addElement(WindowElement element) throws SlickException {
		elements.add(element);
		element.init(MENU.container);
	}

	public void drawWindowBody(Graphics g) {
		if (focused) {
			g.setColor(Colors.WHITE);
		} else {
			g.setColor(Colors.BLACK);
		}

		g.drawRect(xp, yp, w, h);
		g.setColor(new Color(0.05f, 0.05f, 0.1f, 0.6f * alpha));
		g.fillRect(xp + 1, yp + 1, w - 1, h - 1);
		g.setColor(new Color(1f, 1f, 1f, alpha));
	}

	public void drawWindowHeader(Graphics g) {
		g.drawRect(xp + 1, yp + 1, w - 2, HEADER_HEIGHT);
		g.setFont(Fonts.WHITE_14_PIXELATED);
		g.drawString(title, xp + w / 2 - Fonts.WHITE_14_PIXELATED.getWidth(title) / 2, yp - 2);
	}

	public boolean isFocused() {
		return focused;
	}

	public StartMenu getMainMenu() {
		return MENU;
	}

	public void moveWindow(int xz, int yz) {
		this.xz = xz;
		this.yz = yz;
	}

	public void setVisible(boolean visible) {
		shouldBlendIn = visible;
		shouldBlendOut = !visible;

		if (visible) {
			this.visible = visible;
		}
	}

	public boolean isVisible() {
		return visible;
	}

	private void blendIn() {
		if (alpha >= 1.0f) {
			alpha = 1.0f;
			shouldBlendIn = false;
			return;
		}
		alpha += 0.05f;
		Colors.updateGlobalAlpha(alpha);
	}

	private void blendOut() {
		if (alpha <= 0f) {
			shouldBlendOut = false;
			this.visible = false;
			return;
		}
		alpha -= 0.05f;
		Colors.updateGlobalAlpha(alpha);
		
	}
}
