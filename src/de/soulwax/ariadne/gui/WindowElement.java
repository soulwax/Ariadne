package de.soulwax.ariadne.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Soulwax
 */
public class WindowElement extends GuiElement {

    protected Window window;
    protected String caption;
    
    protected float xxp, yyp;

    public WindowElement(int xp, int yp, int w, int h, Window window, String caption) {
        super(xp, yp, w, h);
        this.window = window;
        this.caption = caption;
        this.alpha = window.alpha;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
    }

    @Override
    public void update(GameContainer container, int delta) {
        xxp = window.xp + xp;
        yyp = window.yp + yp;

        if (xxp > window.w - w + window.xp) {
            xxp = window.w - w + window.xp - 2;
        } else if (xxp <= window.xp) {
            xxp = window.xp + 2;
        }

        if (yyp > window.h - h + window.yp) {
            yyp = window.h - h + window.yp - 2;
        } else if (yyp <= window.yp) {
            yyp = window.yp + 2;
        }
    }
}
