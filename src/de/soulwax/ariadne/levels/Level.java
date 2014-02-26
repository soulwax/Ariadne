package de.soulwax.ariadne.levels;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.soulwax.ariadne.Colors;
import de.soulwax.ariadne.Fonts;
import de.soulwax.ariadne.entities.Entity;
import de.soulwax.ariadne.entities.Player;

/**
 * @author Soulwax
 */

public class Level {

    public List<Entity> entities = new ArrayList<Entity>();

    private int w, h;
    private int maxHeight = 100;

    private GameContainer container;
    private StateBasedGame game;

    private Player player; //just to reference the player directly, the actual instance is in the entity list.
    
    public Level(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        this.game = game;

        for (Entity e : entities) {
            e.init(container, game);
        }
	}

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        for (Entity e : entities) {
            if (!e.removed)
                e.update(container, game, delta);
            else
                entities.remove(e);
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	//temporary replacement for tiles
    	g.setColor(Colors.GREYISH_GREEN);
    	g.fillRect(0, 0, w * 16, h * 16);
       
    	
    	for (Entity e : entities) {
            if (!e.removed) e.render(container, game, g);
        }
        
        renderStats(g);
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void addNewEntity(Entity e) throws SlickException {
    	if(e instanceof Player) player = (Player) e;
        entities.add(e);
        e.init(container, game);
        e.init(this, container, game);
    }
    
    public void renderStats(Graphics g) {
		g.setFont(Fonts.WHITE_16_PIXELATED);
		g.setColor(Color.white);
		g.drawString("x: " + player.getX(), 6, 32);
		g.drawString("y: " + player.getY(), 6, 48);
		g.drawString("z: " + player.getZ(), 6, 64); 
		
		g.drawString("Controls: ", 256, 32);
		g.drawString("Arrows: walk, Arrows+LShift: run", 256, 48);
		g.drawString("Space: jump", 256, 64);
    }
}
