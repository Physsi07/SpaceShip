import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class SmallShip{

	Image smallImage;
	Ship plane;
	SmallShip smallShip;
	Controller controller;
	CollisionBox collisionBox;
	
	double x;
	double y;
	double velX;
	double velY;
	
	public SmallShip(int x, int y){
		
		smallImage = Toolkit.getDefaultToolkit().getImage("smallShip.png");
		this.x = x;
	    this.y = y;
	    
	    collisionBox = new CollisionBox(x, y, 60, 60);
	}
	
	public void enemyMoveUp() {
		this.y = y - velY;
		collisionBox.moveUpBy(velY);
	}

	public void enemyMoveDown() {
		this.y = y + velY;
		collisionBox.moveDownBy(velY);
	}

	public void enemyMoveLeft() {
		this.x = x - velX;
		collisionBox.moveLeftBy(velX);
	}
	
	public void enemyMoveRight(){
		this.x = x + velX;
		collisionBox.moveRightBy(velX);
	}
	
	public void setVelX(double d){	
		this.velX =  d; 
    }
	
	public void setVelY(double d){
		this.velY =  d; 
    }
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
		
	// Gets the y value of the position of the smallShip
	public double getY() {
		return y;
	}
		
	// Gets the x value of the position of the smallShip
	public double getX() {
		return x;
	}
	
	// Draws and displays graphics of the SmallShip.
	public void draw(Graphics g){
		g.drawImage(smallImage,  (int) x, (int) y, 60, 60, null);
		//collisionBox.draw(g);
	}
}
