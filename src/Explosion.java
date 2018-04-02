import java.awt.Color;
import java.awt.Graphics;

public class Explosion {

	double x, y;
	int r, max;
	
	public Explosion(double x, double y, int r, int max){
		this.x = x;
		this.y = y;
		this.r = r;
		this.max = max;
	}
	
	public boolean update(){
		r++;
		if(r >= max){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g){
		g.setColor(new Color(255, 255, 255, 128));
		g.drawOval((int)(x -r), (int)(y - r), 2*r, 2*r);
	}
}
