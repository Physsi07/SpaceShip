import java.awt.*;

// ************ //
// PLAYER CLASS //
// ************ //
public class Ship extends Sprite
{
	// ************************** //
	// OBJECTS FROM OTHER CLASSES //
	// ************************** //
	Game game;
	CollisionBox collisionBox;

	// ******************************************** //
	// ARRAY OF TYPE STRING FOR THE SHIP 'POSITION' //
	// ******************************************** //
	private final static String[] pos = {
			"dn",
			"up",
			"lt",
			"rt"
	};

	// ****************************** //
	// CONSTRUCTOR FOR THE SHIP CLASS //
	// ****************************** //
	public Ship(int x, int y) {
		// ******************************************* //
		// CALL TO THE CONSTRUCTOR OF THE SPRITE CLASS //
		// ******************************************* //
		super(x, y, 75, 95, "ship", 3, 5, pos);

		// ***************** //
		// BOX FOR THE PLANE //
		// ***************** //
		collisionBox = new CollisionBox(x, y, 75, 75);
	}

	// ******************************* //
	// METHOD THAT MAKES THE SHIP MOVE //
	// ******************************* //
	public void moveBy(int dx, int dy){
		super.moveBy(dx, dy);

		// ********************************************** //
		// DOESN'T LET THE PLANE BOX GO OUT OF THE SCREEN //
		// ********************************************** //
		int tempY = (int)collisionBox.y + dy;
		if(tempY <= 350  || tempY >= 660)
			dy = 0;
		int tempX = (int)collisionBox.x + dx;
		if(tempX >= 725  || tempX <= 0)
			dx = 0;

		// ************************************** //
		// MOVING ITS COLLISION BOX WITH THE SHIP //
		// ************************************** //
		collisionBox.moveBy(dx, dy);
	}

	// ************** //
	// DRAWS THE SHIP //
	// ************** //
	public void draw(Graphics g){	
		super.draw(g);
		//collisionBox.draw(g);
	}
}
