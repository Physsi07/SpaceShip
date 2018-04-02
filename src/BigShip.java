import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

// ************* //
// BIGSHIP CLASS //
//************* //
public class BigShip {

	// ************************ //
	// OBJECTS OF OTHER CLASSES //
	// ************************ //
	Ship plane;
	Image bigShip;
	Controller controller;
	CollisionBox collisionBox;

	// ********* //
	// VARIABLES //
	// ********* //
	double x;
	double y;
	double velX;
	double velY;
	
	// ************************ // 
	// CONSTRUCTOR OF THE CLASS //
	// ************************ // 
	public BigShip(int x, int y){

		bigShip = Toolkit.getDefaultToolkit().getImage("FinalShip.png");
		this.x = (int) x;
		this.y = y;

		collisionBox = new CollisionBox(x + 5 ,y , 150, 150);
	}

	// *************************** //
	// MOVES THE BIGSHIP TO THE UP //
	// *************************** //
	public void enemyMoveUp() {
		this.y = y - velY;
		collisionBox.moveUpBy(velY);
	}

	// ****************************** //
	// MOVES THE BIGSHIP TO THE DOWN //
	// ****************************** //
	public void enemyMoveDown() {
		this.y = y + velY;
		collisionBox.moveDownBy(velY);
	}

	// ****************************** //
	// MOVES THE BIGSHIP TO THE LEFT //
	// ****************************** //
	public void enemyMoveLeft() {
		this.x = x - velX;
		collisionBox.moveLeftBy(velX);
	}

	// ****************************** //
	// MOVES THE BIGSHIP TO THE RIGHT //
	// ****************************** //
	public void enemyMoveRight(){
		this.x = x + velX;
		collisionBox.moveRightBy(velX);
	}

	// ************************************ //
	// SETS THE VELOCITY IN THE X DIRECTION //
	// ************************************ //
	public void setVelX(double d){
		this.velX =  d; 
	}

	// ************************************ //
	// SETS THE VELOCITY IN THE Y DIRECTION //
	// ************************************ //
	public void setVelY(double d){
		this.velY =  d; 
	}

	// *********************************************** //
	// SETS THE X VALUE OF THE POSITION OF THE BIGSHIP //
	// *********************************************** //
	public void setX(int x){
		this.x = x;
	}

	// *********************************************** //
	// SETS THE Y VALUE OF THE POSITION OF THE BIGSHIP //
	// *********************************************** //
	public void setY(int y){
		this.y = y;
	}

	// *********************************************** //
	// GETS THE Y VALUE OF THE POSITION OF THE BIGSHIP //
	// *********************************************** //
	public double getY() {
		return y;
	}

	// *********************************************** //
	// GETS THE X VALUE OF THE POSITION OF THE BIGSHIP //
	// *********************************************** //
	public double getX() {
		return x;
	}

	// ****************************************** //
	// DRAWS AND DISPLAYS GRAPHICS OF THE BIGSHIP //
	// ****************************************** //
	public void draw(Graphics g){
		g.drawImage(bigShip,  (int) x, (int) y, 150, 150, null);
	}	
}