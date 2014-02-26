package de.soulwax.ariadne;

import org.newdawn.slick.Color;

/**
 * 
 * @author Soulwax
 */

public class Colors {
	public static Color DARK_TRANSPARENT = new Color(0.1f, 0.1f, 0.1f, 0.6f);
	public static Color WHITE = new Color(1f, 1f, 1f, 1f);
	public static Color BLACK = new Color(0f, 0f, 0f, 1f);

	
	public static Color getColor(int r, int g, int b, int alpha) {
		return new Color(r / 255, g / 255, b / 255, alpha / 255);
	}

	public static Color getColor(float rf, float gf, float bf, float alphaf) {
		return new Color(rf, gf, bf, alphaf);
	}
	
	public static void updateGlobalAlpha(float alpha) {
		DARK_TRANSPARENT = new Color(0.1f, 0.1f, 0.1f, 0.6f * alpha);
		WHITE.a = alpha;
		BLACK.a = alpha;
	}
}
