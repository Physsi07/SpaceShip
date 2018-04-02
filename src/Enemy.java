import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Enemy {

	// Image and controller objects
	Image enemy;
	Controller controller;

	// Declaring variables
	int x;
	int y;
	int width;
	int height;
	int velY;

	// Constructor of the class
	public Enemy(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		// Gets the image of the asteroid from file
		enemy = Toolkit.getDefaultToolkit().getImage("starcraft_2_render7E0.png");
	}

	// Moves the enemy down the screen
	public void tick(){
		y += velY;
	}

	// Sets the velocity of the enemy in a y point in the screen
	public void setVelY(int velY){
		this.velY = velY; 
	}

	// Gets the y value of the position of the enemy
	public double getY() {
		return y;
	}

	// Gets the y value of the position of the enemy
	public double getX() {
		return x;
	}

	/*		public void mover(int ship, int ship) {
		    int xPos = x;
		    int yPos = sprite.getY();
		    if (x > ship.x){
		        x--;
		    }
		    else if (x < ship){
		        x++;
		    }
		    if (y > ship.y){
		        y--; //you might need to change this to ++ depending on how the axes are set up
		    }
		    else if (y < ship.y){
		        yPos++; //you might need to change this to -- depending on how the axes are set up
		    }

		    Enemy.setPosition( x, y );
  		}
	 */

	// Draws and displays graphics of the asteroids
	public void draw(Graphics g){
		g.drawImage(enemy, (int) x, (int) y, 60, 60, null);
	}
}
