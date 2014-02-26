package de.soulwax.ariadne;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author Soulwax
 */

public class Art {
	public static Art i;

	public static void init() {
		i = new Art();
	}

	public Image[][] femCharGreen = loadAndCut("res/charsprites/sprite_fem_blond_green.png", 64, 64, 2);
	public Image[][] basicTiles = loadAndCut("res/tiles/basicTiles.png", 16, 16, 2);

	public Image[][] loadAndCut(String location, int sw, int sh, int scale) {
		Image sheet;
		try {
			sheet = new Image(location);
		} catch (SlickException e) {
			throw new RuntimeException("Failed to load file at " + location);
		}

		int xSlices = sheet.getWidth() / sw;
		int ySlices = sheet.getHeight() / sw;

		Image[][] result = new Image[xSlices][ySlices];

		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				result[x][y] = sheet.getSubImage(x * sw, y * sh, sw, sh).getScaledCopy(scale);
				result[x][y].setFilter(Image.FILTER_NEAREST);
			}
		}

		return result;
	}

	/*public static Map loadMap(String imagePath, int sw, int sh) {
		BufferedImage image;
		try {
			image = ImageIO.read(Art.class.getResource(imagePath));
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load file at: " + imagePath);
		}

		byte[] pixels = null;
		if (image.getType() == BufferedImage.TYPE_INT_RGB) {
			pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		} else if (image.getType() == BufferedImage.TYPE_4BYTE_ABGR) {
			pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		}


		int w = image.getWidth();
		int h = image.getHeight();

		MapTile[][] tiles = new MapTile[w][h];

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (pixels[x + y * w] == 0)
					tiles[x][y] = new BasicWall(x * sw, y * sh, sw, sh);
				else
					tiles[x][y] = new BasicGround(x * sw, y * sh, sw, sh);
			}
		}

		Map result = new Map(w, h, tiles);

		return result;
	}        */
}
