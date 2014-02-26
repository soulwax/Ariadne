package de.soulwax.ariadne.entities.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Soulwax
 */

public class Mob extends Entity {

    private float gravity = 0.981f;

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        attemptMove();
        if (z > 0) {
            za -= (float)(gravity * (level.getMaxHeight() / (z * 2)));
        }
    }
}