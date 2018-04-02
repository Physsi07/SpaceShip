
import java.awt.*;

public class Help extends Menu{

	// Creating rectangles for the buttons
	public Rectangle wrap = new Rectangle(Game.WIDTH + 50, 150, 700, 500);
	public Rectangle backButton = new Rectangle(Game.WIDTH + 345, 665, 150, 70);
	

	Image instruction;
	public Help(){

		instruction = Toolkit.getDefaultToolkit().getImage("instruction.png");
	}
	
	// Drawing methods
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(instruction, 90, 10, null);
		
		Font fnt1 = new Font("Arial", Font.ITALIC, 80);
		Font fnt2 = new Font("Arial", Font.BOLD, 30);
		Font fnt3 = new Font("SignPainter", Font.BOLD, 30);
		g.setColor(Color.white);
		g.setFont(fnt1);
		
		g.setFont(fnt3);
		g.drawString("Are you ready to fly ?", wrap.x + 20, wrap.y + 35);
		g.drawString("Directions to play: ", wrap.x + 15, wrap.y + 70);
		g.drawString("• Use arrow keys on the keyboard to direct your ship", wrap.x + 70, wrap.y + 105);
		g.drawString("• The up key allows your ship to move forward", wrap.x + 70, wrap.y + 140);
		g.drawString("• The down key allows your ship to move backward", wrap.x + 70, wrap.y + 175);
		g.drawString("• The left and right keys allow your ship to move left and right", wrap.x + 70, wrap.y + 210);
		g.drawString("• The x key allows your ship to shoot", wrap.x + 70, wrap.y + 245);
		

		g.drawImage(button, 340, 655, null);
		
		g.setFont(fnt2);
		g2d.draw(wrap);
		//g2d.draw(backButton);
		g2d.drawString("Back", backButton.x + 45, backButton.y + 45);
		
		g.drawImage(ship, 280, 670, 50, 50, null);
		g.drawImage(ship, 510, 670, 50, 50, null);
	}


}
