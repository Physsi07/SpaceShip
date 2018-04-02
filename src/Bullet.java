import java.awt.*;

// Bullet Class
public class Bullet
{
	// An image object
	Image bullet1;
	Image bullet2;
	Image bullet3;
	
	CollisionBox collisionBox;
	
	// Declared variables, (x, y) point of the bullet and the y velocity
	double x;
	double y;
	double velY;
	
	// Constructor of the class
	public Bullet(double x, double y)
	{
		//super(ship);
		this.x = x;
	    this.y = y;
	    
	    //Box for the bullet
	    collisionBox = new CollisionBox(x + 24, y,5,15);
	    
	    if(Game.score <= 200){
	    	// Gets the image of the bullet from file
	    	bullet1 = Toolkit.getDefaultToolkit().getImage("bullet1.png");
	    }
	    else if(Game.score >= 200 && Game.score <= 500){
	    	bullet2 = Toolkit.getDefaultToolkit().getImage("bullet2.png");
	    }
	    else if(Game.score > 500){
	    	bullet3 = Toolkit.getDefaultToolkit().getImage("bullet3.png");
	    }
	    
	}
	
	// Moves the bullet
	public void tick()
	{
		y -= velY;
		
		//To follow the bullets box going up.
		collisionBox.moveUpBy(velY);
	}
	
	// Sets the velocity of the bullet in a y point in the screen
	public void setVelY(double velY)
	{
		this.velY = velY; 

    }
	
	// Getting the y value of the position bullet 
	public double getY() 
	{
		return y;
	}
	
	// Draws and displays graphics of the bullet
	public void draw(Graphics g)
	{
		if(Game.score < 200){
			g.drawImage(bullet1, (int) x + 13, (int) y - 2, 30, 30, null);
		}
		else if(Game.score >= 200 && Game.score <= 500){
			g.drawImage(bullet2, (int) x + 9, (int) y - 2 , 30, 30, null);
		}
		else if(Game.score > 500){
			g.drawImage(bullet3, (int) x + 13, (int) y - 2, 30, 30, null);
		}
	}

}
