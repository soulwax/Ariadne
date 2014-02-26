package de.soulwax.ariadne.entities.entities;

/**
 * @author Soulwax
 */

public class Entity extends GameObject {

    protected double xa, ya, za;

    public Entity() {
    }

    public void attemptMove() {
        int steps = (int) (Math.sqrt(xa * xa + ya * ya + za * za) + 1);

        for (int i = 0; i < steps; i++) {
            _move(xa / steps, 0, 0);
            _move(0, ya / steps, 0);
            _move(0, 0, za / steps);
        }
    }

    public boolean intersects(double x0, double y0, double z0, double x1, double y1, double z1) {
        if (x1 <= x - xr || x0 > x + xr || y1 <= y - yr || y0 > y + yr || z1 <= z || z0 > z + zh) return false;
        return true;
    }

	public boolean intersects(GameObject o1, GameObject o2) {
		if (o2.x <= x - xr || o1.x > x + xr || o2.y <= y - yr || o1.y > y + yr || o2.z <= z || o1.z > z + zh) return false;
		return true;
	}

    private void _move(double xxa, double yya, double zza) {
        if (removed) return;
        double xn = x + xxa;
        double yn = y + yya;
        double zn = z + zza;
        if (xn < 0 || yn < 0 || xn >= level.getWidth() * 8 || yn >= level.getHeight() * 8 || zn < 0 || zn > level.getMaxHeight()) {
            if (zn < 0) z = 0;
            collide(null, xxa, yya, zza);
            return;
        }

        x = xn;
        y = yn;
        z = zn;
        return;
    }

    public void collide(Entity e, double xxa, double yya, double zza) {
        if (xxa != 0) xa = 0;
        if (yya != 0) ya = 0;
        if (zza != 0) za = 0;
    }
}
