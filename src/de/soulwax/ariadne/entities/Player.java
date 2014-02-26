package de.soulwax.ariadne.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.soulwax.ariadne.Colors;
import de.soulwax.ariadne.InputHandler;
import de.soulwax.ariadne.states.Tutorial;

/**
 * 
 * @author Soulwax
 */

public class Player extends Mob {

	private Image[][] sheet;
	private double maxSpeed = 0.35;
	private double minSpeed = 0.2;
	private double speed = minSpeed;
	private double acceleration = 0.025;
	private double negAcceleration = 0.005;
	private float jumpCD = 0;
	private boolean justJumped = false;
	private boolean inAir = false;
	private boolean maxSpeedReached = false;
	private boolean minSpeedReached = false;
	private InputHandler input;

	public Player(Image[][] sheet) {
		this.sheet = sheet;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.x = 120;
		this.y = 120;
		this.z = 5;

		input = Tutorial.input;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		maxSpeedReached = speed >= maxSpeed;
		minSpeedReached = speed <= minSpeed;

		if (!inAir) {
			if (input.right) xa = speed * delta;
			else if (input.left) xa = -speed * delta;
			else xa = 0;

			if (input.up) ya = -speed * delta;
			else if (input.down) ya = speed * delta;
			else ya = 0;
		}

		if (ya > 0) yy = 10;
		else if (ya < 0) yy = 8;
		if (xa > 0) yy = 11;
		else if (xa < 0) yy = 9;

		if (xx >= 8) xx = 0;

		if (xa != 0 || ya != 0) xx += speed;
		else xx = 0;

		if (input.space && jumpCD <= 0 && !justJumped) {
			za = 10;
			inAir = true;
			if (z > 20) {
				jumpCD = (float) (1.0f * z / 10);
				justJumped = true;
			}
		}

		if (!input.space && !inAir) justJumped = false;

		if (jumpCD > 0) jumpCD -= 0.1f;
		else inAir = false;

		if (input.lshift && !maxSpeedReached) speed += acceleration;
		if (!input.lshift && !minSpeedReached) speed -= negAcceleration;

	}

	float xx = 0;
	float yy = 0;

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		renderShadows(g);
		sheet[(int) xx][(int) yy].draw((float) x, (float) y - (float) z);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	public  void renderShadows(Graphics g) {
		g.setColor(Colors.DARK_TRANSPARENT);
		g.fillOval((float)x + 44,(float) y + 110,(float) xr * 8,(float) yr * 4);
	}
}
