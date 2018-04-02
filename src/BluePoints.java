import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BluePoints {
		Image bluePoints;
		Controller controller;
		CollisionBox collisionBox;
		
	
		// Declaring variables
		double x;
		double y;
		double width;
		double height;
		double velY;
	
		// Constructor of the class
		public BluePoints(double x, double y, double width, double height){
			this.x = x;
		    this.y = y;
		    this.width = width;
		    this.height = height;
		    
		    collisionBox = new CollisionBox(x , y , 30, 30 );
		    
		    // Gets the image of the asteroid from file
		    bluePoints = Toolkit.getDefaultToolkit().getImage("background56.png");
		}
		
		// 
		public void tick(){
			y += velY;
			collisionBox.moveDownBy(velY);
		}
		
		
		// Sets the velocity of the asteroid in a y point in the screen
		public void setVelY(double velY){
			this.velY = velY; 
		}
		
		// Gets the y value of the position of the asteroid
		public double getY() {
			return y;
		}
		
		// Gets the y value of the position of the asteroid
		public double getX() {
			return x;
		}
		
		// Draws and displays graphics of the asteroids
		public void draw(Graphics g){
			g.drawImage(bluePoints, (int) x, (int) y, 30, 30, null);
		}
		
	}
