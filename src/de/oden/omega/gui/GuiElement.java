package de.oden.omega.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


/**
 *
 * @author Soulwax
 */
public abstract class GuiElement {

    protected float xp, yp, xz, yz;
    protected float w, h;
    protected boolean visible;
    protected float alpha;

    public GuiElement(int xp, int yp, int w, int h) {
        this.xp = xp;
        this.yp = yp;
        this.w = w;
        this.h = h;
    }

	public abstract void init(GameContainer container) throws SlickException;
    public abstract void render(GameContainer container, StateBasedGame game, Graphics g);
    public abstract void update(GameContainer container, int delta);
}
