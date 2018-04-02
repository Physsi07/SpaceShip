import java.awt.*;

// Asteroid Class
public class Asteroid
{
	// Image and classes objects
	Image asteroid;
	Controller controller;
	CollisionBox collisionBox;
	
	// Declaring variables
    double x;
	double y;
	double width;
	double height;
	double velY;
	boolean explode ;
	Animation explosion;
	
	
	// Constructor of the class
	public Asteroid(double x, double y)
	{
		this.x = x;
	    this.y = y;
	    
	    // BOX FOR THE ASTEROID //
	    collisionBox = new CollisionBox( x, y, 60, 60);
	    
	    // GETS THE IMAGE OF THE ASTEROID FROM FILE //
	    asteroid = Toolkit.getDefaultToolkit().getImage("asteroid.png");
	    
	    // CREATES THE ANIMATION FOR EXPLOSIONS //
	    explosion = new Animation("asteriodDeath_", 6, 3);
	}
	
	// MOVES THE ASTEROID DOWN THE SCREEN //
	public void tick()
	{
		y += velY;
		
		// TO FOLLOW THE ASTEROID BOX GOING DOWN //
		collisionBox.moveDownBy(velY);
		
	}
	
	// Sets the velocity of the asteroid in a y point in the screen
	public void setVelY(double velY)
	{
		this.velY = velY; 
    }
	
	// Gets the y value of the position of the asteroid
	public double getY() 
	{
		return y;
	}
	
	// Gets the y value of the position of the asteroid
	public double getX() 
	{
		return x;
	}

	// Draws and displays graphics of the asteroids
	public void draw(Graphics g)
	{
		g.drawImage(asteroid, (int) x, (int) y, 60, 60, null);
		//g.setColor(Color.green);
		//collisionBox.draw(g);
		
		if(explode == true){
			System.out.println("explosion");
			g.drawImage(explosion.nextImage(), (int) x, (int) y, null);
		}
	}

}
