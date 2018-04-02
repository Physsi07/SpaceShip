
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


//Menu Class
public class Menu extends MouseAdapter
{
	// Images objects
	Image logo;
	Image ship_original;
	Image ship;
	Image button;
	
	Controller controller;
	Game game;
	
	// Declared variables
	double angle;
	
	// Creating rectangles for the buttons
	public Rectangle playButton = new Rectangle(Game.WIDTH + 325, 382, 155, 80);
	public Rectangle helpButton = new Rectangle(Game.WIDTH + 325, 482, 155, 80);
	public Rectangle quitButton = new Rectangle(Game.WIDTH + 325, 582, 155, 80);

	// Creating rectangles for the buttons
	public Rectangle wrap = new Rectangle(Game.WIDTH + 50, 150, 700, 500);
	public Rectangle backButton = new Rectangle(Game.WIDTH + 300, 670, 200, 50);
	
	
	// Constructor of the class
	public Menu()
	{
		// Angle of the ship
		this.angle = 90;
		
		// Gets the image of the logo and the ship from file
		logo = Toolkit.getDefaultToolkit().getImage("SpaceShipLogo.png");
		ship_original = Toolkit.getDefaultToolkit().getImage("ship_dn_1.png");
		ship = ship_original;
		button = Toolkit.getDefaultToolkit().getImage("button.png");
	}
	
	// Setting the angle
	public void setAngle(double angle)
	{
		this.angle = angle;
	}
	
	// Getting the angle
	public double getAngle()
	{
		return angle;
	}
	
	
	public void rotateBy(double degree)
	{
		angle += degree;

	    if(angle >= 360) angle -= 360;
	    if(angle < 0)    angle += 360;
	}
	
	public void rotate(double degree)
	{
		rotateBy(degree);
		
		ship = getRotatedImage(ship_original, angle);	
	}
	
	 
	 public static Image getRotatedImage(Image image, double angle) 
	 {
	        // Set up the transform
	        AffineTransform transform = new AffineTransform();
	        transform.translate(image.getWidth(null) / 2.0, image.getHeight(null) / 2.0 );

	        transform.rotate(Math.toRadians(angle));

	        // Put origin back to upper left corner
	        transform.translate(-image.getWidth(null) / 2.0, -image.getHeight(null) / 2.0);

	        // Create a transparent (not translucent) image
	        Image newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), Transparency.BITMASK);

	        // Draw the transformed image
	        Graphics2D g = (Graphics2D)newImage.getGraphics();
	        AffineTransform origTransform = g.getTransform();
	        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	        g.drawImage(image, transform, null);
	        g.setTransform(origTransform);
	        g.dispose();

	        return newImage;
	 }
	 
	 public void mousePressed(MouseEvent e) 
	 {
		// Getters of the (x, y) position of the mouse
		int mx = e.getX();
		int my = e.getY();
			
		 // In the Menu State
		 if(Game.State == Game.STATE.MENU)
		 {
			 // Play Button
			 if(mx >= Game.WIDTH + 325 && mx <= Game.WIDTH + 480)
			 {
				 if(my >= 382 && my <= 462)
				 {
					 // Pressed play button
					 Game.State = Game.STATE.GAME;
					 
				 }
		     } 
				 
				 
		     // Help Button
		     if(mx >= Game.WIDTH + 325 && mx <= Game.WIDTH + 480)
		     {
		     	if(my >= 482 && my <= 562)
		     	 {
				     // Pressed help button
			    	 Game.State = Game.STATE.HELP;
			     }
		     } 
				 
		     // Quit Button
		     if(mx >= Game.WIDTH + 325 && mx <= Game.WIDTH + 480)
		     {
			   if(my >= 582 && my <= 662)
			   {
				  // Pressed quit button
				  System.exit(1);
		   	   }	
		     }
		   }
			 
		//In the Help State
		if(Game.State == Game.STATE.HELP)
		 {
			 //Back Button
			 if(mx >= Game.WIDTH + 345 && mx <= Game.WIDTH + 495)
			 {
				 if(my >= 665 && my <= 735)
				 {
					 Game.State = Game.STATE.MENU;
				 }
			 }
		  }
		
		//In the End State
		if(Game.State == Game.STATE.END)
		{
			// try again Button
		     if(mx >= Game.WIDTH + 300 && mx <= Game.WIDTH + 500)
		     {
			   if(my >= 600 && my <= 650)
			   {
				  // Pressed try againg button
				   Game.State = Game.STATE.GAME;
				   Game.health = 100;
				   Game.score = 0;
			   }
		     }
		}
		
	}
	 
    public void mouseReleased(MouseEvent e){}
	 
	// Drawing method
	public void draw(Graphics g)
	{
		if(Game.State == Game.STATE.MENU)
		{
			// Drawing images objects
			g.drawImage(logo, Game.WIDTH + 75, 50, 640, 200, null);
			g.drawImage(ship, 330, 230, 150, 150, null); 
			
			
			// Public abstract class that displays graphics
			Graphics2D g2d = (Graphics2D) g;
			
			// Drawing letters and displaying the buttons to the screen

			Font fnt1 = new Font("Arial", Font.BOLD, 30);
			g.setColor(Color.white);
			g.drawImage(button, 320, 375, null);
			g.setFont(fnt1);
			g.drawString("Play", playButton.x + 50, playButton.y + 50);
			//g2d.draw(playButton);
			
			g.drawImage(button, 320, 475, null);
			g.drawString("Help", helpButton.x + 50, helpButton.y + 50);
			//g2d.draw(helpButton);
			
			g.drawImage(button, 320, 575, null);
			g.drawString("Quit", quitButton.x + 50, quitButton.y + 50);
			//g2d.draw(quitButton);
		
		}

	}

}
