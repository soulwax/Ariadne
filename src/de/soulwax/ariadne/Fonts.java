package de.soulwax.ariadne;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Soulwax
 */
public class Fonts {

    public static Font WHITE_14_PIXELATED;
    public static Font WHITE_16_PIXELATED;
    public static Font WHITE_12_PIXELATED;
    public static Font WHITE_14_PIXELATED_AA;

    public Fonts() {

    }

    public static void init() throws SlickException {
        WHITE_14_PIXELATED = new AngelCodeFont("res/fonts/14whitePIX.fnt", 
        		new Image("res/fonts/14whitePIX_00.png"));
        WHITE_16_PIXELATED = new AngelCodeFont("res/fonts/16whitePIX.fnt", 
        		new Image("res/fonts/16whitePIX_00.png"));
        WHITE_12_PIXELATED = new AngelCodeFont("res/fonts/12whitePIX.fnt", 
        		new Image("res/fonts/12whitePIX_00.png"));
        WHITE_14_PIXELATED_AA = new AngelCodeFont("res/fonts/14whitePIX_AA.fnt", 
        		new Image("res/fonts/14whitePIX_AA_00.png"));
    }
}
