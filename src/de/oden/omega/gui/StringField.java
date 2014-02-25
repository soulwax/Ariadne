package de.oden.omega.gui;

import de.oden.omega.Fonts;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Soulwax
 */
public class StringField extends WindowElement {

    public TextField field;

    public StringField(int xp, int yp, int w, int h, Window window, String caption) {
        super(xp, yp, w, h, window, caption);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        field = new TextField(container, 
                Fonts.WHITE_16_PIXELATED, (int)xxp, (int)yyp, 
                (int)w, (int)h, new ComponentListener() {
            
            @Override
            public void componentActivated(AbstractComponent source) {        
            	clear();
            }
        });
        //field.setText(caption);
        clear();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        field.render(container, g);    
    }

    @Override
    public void update(GameContainer container, int delta) {
        super.update(container, delta);
        field.setLocation((int)xxp, (int)yyp);
    }

	public void clear() {
		field.setText("");
	}

}
