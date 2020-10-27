import java.util.Random;
import java.awt.Color;

public class Celestial {
	private static final double G_CONSTANT = 6.673e-11;

	private String name;
	private double mass, xspeed, yspeed, xforce = 0, yforce = 0;
	private int x, y, size;
	private Color color;
	private Random random = new Random();

	public Celestial(String n, String m, String x, String y, String xs, String ys, String s) {
		name   = n;
		mass   = Double.parseDouble(m);
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		xspeed = Double.parseDouble(xs);
		yspeed = Double.parseDouble(ys);
		size   = Integer.parseInt(s.substring(1, s.length()));
		color  = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}

	public void addForce(Celestial b, double scale) {
		//F = G (m1 × m2) / r^2 
		Celestial a = this;
		double dx = b.x-a.x;
		double dy = b.y-a.y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		double f = ((G_CONSTANT * a.mass * b.mass) / ((distance*distance)/scale));
		a.xforce += f * dx/distance;
		a.yforce += f * dy/distance;
	}

	public void updatePosition() {
		xspeed += xforce/mass;
		yspeed += yforce/mass;
		x += xspeed;
		y += yspeed;
	}

	public void resetForce() {
		xforce = 0;
		yforce = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getXSpeed() {
		return xspeed;
	}

	public double getYSpeed() {
		return yspeed;
	}

	public int getSize() {
		return size;
	}

	public double getMass() {
		return mass;
	}

	public Color getColor() {
		return color;
	}

	public void printCelestial() {
		System.out.println(name + "," + mass + "," + x + "," + y + "," + xspeed + "," + yspeed + "," + size);
	}
}