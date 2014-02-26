package de.soulwax.ariadne.entities.levels;

import de.soulwax.ariadne.entities.entities.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Soulwax
 */

public class Level {

    public List<Entity> entities = new ArrayList<Entity>();

    private int w, h;
    private int maxHeight = 100;

    private GameContainer container;
    private StateBasedGame game;

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
        for (Entity e : entities) {
            if (!e.removed) e.render(container, game, g);
        }
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
        entities.add(e);
        e.init(container, game);
        e.init(this, container, game);
    }
}
