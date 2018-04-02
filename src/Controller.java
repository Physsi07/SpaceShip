import java.awt.*;
import java.util.*;
import Audio.AudioPlayer;

// **************** //
// CONTROLLER CLASS //
//**************** //
public class Controller 
{
	// ******************************************* //
	// LINKED LIST OF EVERY CLASS THAT WILL BE USE //
	// ******************************************* // 
	public LinkedList<Bullet> bullet = new LinkedList<Bullet>();
	public LinkedList<Bullet> bulletForSmallShip = new LinkedList<Bullet>();
	public LinkedList<Asteroid> asteroidArray = new LinkedList<Asteroid>();
	public LinkedList<SmallShip> smallShipArray = new LinkedList<SmallShip>();
	public LinkedList<BigShip> BigShipArray = new LinkedList<BigShip>();
	public LinkedList<BluePoints> bp = new LinkedList<BluePoints>();

	// **************************** //
	// ARRAY FOR MY EXPLOSION CLASS //
	// **************************** //
	public static ArrayList<Explosion> explosion; 

	// ************************** //
	// OBJECTS FROM OTHER CLASSES //
	// ************************** //
	Game       game;
	Ship       plane;
	Sprite     sprite;
	BigShip    bigShip;
	SmallShip  smallShip;
	BluePoints Templebp;
	Bullet     Templebullet;
	Asteroid   Templeasteroid;
	Bullet 	   TemplebulletForSmallShip;

	// ********************************** //
	// OBJECTS FROM THE AUDIOPLAYER CLASS //
	// ********************************** //
	private AudioPlayer explode;
	private AudioPlayer bigExplode;
	private AudioPlayer softExplode;
	private AudioPlayer gameover;

	// ********************** //
	// VARIABLE BIGSHIP SCORE //
	// ********************** //
	private int bigShipScore = 200;

	// **************************** //
	// TIMERS TO CONTROL MY BIGSHIP //
	// **************************** //
	private int timer1       = 50;
	private int timer2       = 45;

	// ******************************************* //
	//  CLOCK VARIABLES TO CONTROL TO CONTROL THE  //
	// THE TIMING OF WHEN MY OBSTACLES ARE DISPLAY //
	// ******************************************* //
	private int clockForAsteroid    = 1000;
	private int clockForBluePoints  = 2000;
	private int clockForBigShip     = 150;

	// ************************ //
	// IMAGE OBJECT FOR LEVEL 2 //
	// ************************ //
	Image level2;

	// ************************ //
	// CONSTRUCTOR OF THE CLASS //
	// ************************ //
	public Controller(Ship ship){
		this.plane   = ship;
		explosion    = new ArrayList<Explosion>();
		explode      = new AudioPlayer("/Music/explode.wav");
		gameover     = new AudioPlayer("/Music/gameover.wav");
		bigExplode   = new AudioPlayer("/Music/bigExplode.wav");
		softExplode  = new AudioPlayer("/Music/softexplosion.wav");
		level2       = Toolkit.getDefaultToolkit().getImage("Level2.png");
	}

	// ****************************************** //
	// METHOD THAT CREATES EVERYTHING AND ADDS IT //
	// ****************************************** //
	public void generatesRandomEverything(int amount){

		if(clockForAsteroid > 0){
			// *********************** //
			// GENERATES THE ASTEROIDS //
			// *********************** //
			if(asteroidArray.isEmpty()){
				int asteroidsAmount = new Random().nextInt(20) + 10;
				for(int i = 0; i < asteroidsAmount; i++) {
					Asteroid asteroid = new Asteroid(randomPositionForX(), amount - 100);
					asteroid.setVelY(random());
					addAsteroid(asteroid);
				}
			}
		}

		if(clockForBluePoints > 0){
			// ************************ //
			// GENERATES THE BLUEPOINTS //
			// ************************ //
			if(bp.isEmpty()){
				for(int i = 0; i < 1; i++) {
					BluePoints bpoints = new BluePoints(randomPositionForX(), -100, 10, 60);
					bpoints.setVelY(5);
					addBluePoints(bpoints);
				}
			}
		}

		if(clockForAsteroid < 0 && clockForBluePoints > 0){
			// *************************** //
			// GENERATES THE ENEMIES SHIPS //
			// *************************** //
			if (smallShipArray.isEmpty()) {
				int smallShipAmount = 740;
				for (int i = 0; i < smallShipAmount; i+=30) {
						SmallShip smallShipObject = new SmallShip(i, - 100);
						TemplebulletForSmallShip = new Bullet(smallShipObject.getX(), smallShipObject.getY());
						addEnemyShip(smallShipObject);
						addBullet(TemplebulletForSmallShip);
				}
			}
		}

		if(clockForBluePoints < 0){
			// ******************** //
			// GENERATE THE BIGSHIP //
			// ******************** //
			if (BigShipArray.isEmpty()) {
				for (int i = 0; i < 1; i++) {
					BigShip bigship = new BigShip(315, -250);
					addEnemyBigShip(bigship);
				}
			}	
		}
	}

	// ***************************************** //
	// GENERATE BULLETS AND ASTEROID AND BIGSHIP //
	//   GENERATES ALL ARTIFICIAL INTELLIGECE    //
	//		    CKECKS FOR ALL COLLISION  	     //
	// ***************************************** //
	public void tick(){

		// *************************************************** //
		// CLOCKS THAT DECIDES WHEN ALL MY OBSTACLES COMES OUT //
		// *************************************************** //
		clockForAsteroid--;
		clockForBluePoints--;

		// ************************************ //
		// LOOPS THROUGH ALL THE EXPLOSION ANIM //
		// ************************************ //
		for(int i = 0; i < explosion.size(); i++){
			boolean remove = explosion.get(i).update();
			if(remove){
				explosion.remove(i);
			}
		}

		// ***************************** //
		// LOOPS THROUGH ALL THE BULLETS //
		// ***************************** //
		for(int i = 0; i < bullet.size(); i++){
			Templebullet = bullet.get(i);
			TemplebulletForSmallShip = bullet.get(i);
			TemplebulletForSmallShip.setVelY(25);
			Templebullet.setVelY(40);
			Templebullet.tick();

			// ***************************************************** //
			// REMOVES BULLETS WHEN ITS Y-POSITION ITS LETS THAN -50 //
			// ***************************************************** //
			if(Templebullet.getY() < -5){
				removeBullet(Templebullet);
			}
			
			if(TemplebulletForSmallShip.getY() > 750) {
				removeBullet(TemplebulletForSmallShip);
			}
		}

		// ******************************* //
		// LOOPS THROUGH ALL THE ASTEROIDS //
		// ******************************* //
		for(int i = 0; i < asteroidArray.size(); i++){
			Templeasteroid = asteroidArray.get(i);
			Templeasteroid.tick();

			// ********************************************************* //
			// REMOVES ASTEROIDS WHEN ITS X-POSITION IS GREATER THAN 740 //
			// ********************************************************* //
			if(Templeasteroid.getY() > 740) {
				removeAsteroid(Templeasteroid);
			}
		}

		for(int i = 0; i < bp.size(); i++){
			Templebp = bp.get(i);
			Templebp.tick();

			if(Templebp.collisionBox.hasCollidedWith(plane.collisionBox))	{
				Game.health = Game.health+5;
			}
			if(Templebp.getY() > 740)
				removeBluePoints(Templebp);
		}

		// ************************************************** //
		// ALL THE COLLISION DETECTION RELATED WITH ASTEROIDS //
		// ************************************************** //
		for(int i = 0; i < asteroidArray.size(); i ++) {

			// ************************************ //
			// VARIABLE THAT GETS ALL THE ASTEROIDS //
			// ************************************ //
			Templeasteroid = asteroidArray.get(i);
			Templeasteroid.tick();

			// ***************************************** //
			// COLLISION DETECTION FOR ASTEROIDS & PLANE //
			// ***************************************** //
			if(asteroidArray.get(i).collisionBox.hasCollidedWith(plane.collisionBox)) {

				// ******************* //
				// EXPLOSION ANIMATION //
				// ******************* //
				explosion.add(new Explosion(asteroidArray.get(i).x + 30, asteroidArray.get(i).y + 30, 25, 50));

				// **************** //
				// HEALTH DECREASED //
				// **************** //
				Game.health -= 5;

				// **************************** //
				// IF PLAYER DIES THIS HAPPENDS //
				// **************************** //
				if(Game.health <= 0){

					// ************************************ //
					// CHANGING THE GAME STATE TO GAME OVER //
					// ************************************ //
					Game.State = Game.STATE.END;
					gameover.play();

					// **************************** //
					// RESETING THE PLANES POSITION //
					// **************************** //
					plane.x = Game.WIDTH/2 - 38;
					plane.y = Game.HEIGHT - 100;

					// ******************* //
					// RESETING THE CLOCKS //
					// ******************* //
					clockForAsteroid   = 1000;
					clockForBluePoints = 2000;
					clockForBigShip    = 150;
				}

				// ************************************ //
				// REMOVES ASTEROIDS THAT HIT THE PLANE //
				// ************************************ //
				removeAsteroid(asteroidArray.get(i));
			}

			// ***************************************** //
			// COLLISION DETETION FOR ASTEROIDS & BULLET //
			// ***************************************** //
			for(int k = 0; k < bullet.size(); k ++){
				if(bullet.get(k).collisionBox.hasCollidedWith(asteroidArray.get(i).collisionBox)){

					// **************** //
					// HEALTH INCREASES //
					// **************** //
					Game.score += 2;

					// ******************* //
					// EXPLOSION ANIMATION //
					// ******************* //
					explosion.add(new Explosion(asteroidArray.get(i).x + 30, asteroidArray.get(i).y + 30, 25, 50));

					// ******************* //
					// SOUND FOR EXPLOSION //
					// ******************* //
					softExplode.play();

					// ***************************** //
					// REMOVES ASTEROID THAT GOT HIT //
					// ***************************** //
					removeAsteroid(asteroidArray.get(i));

					// ***************************** //
					// REMOVES BULLET THAT WERE USED //
					// ***************************** //
					removeBullet(bullet.get(k));
				}
			}
		}

		// ************************************** //
		// COLLISION DETECTION BLUEPOINTS & PLANE //
		// **************8*********************** //
		for(int c = 0; c < bp.size(); c ++){
			if(bp.get(c).collisionBox.hasCollidedWith(plane.collisionBox)){

				// **************************** //
				// PLAYER SCORE INCREASES BY 10 //
				// **************************** //
				Game.score += 10;

				// ********************************************** //
				// REMOVES ALL THE ASTEROIDS THAT HITS THE PLAYER //
				// ********************************************** //
				removeBluePoints(bp.get(c));
			}		
		}

		// **************************************** //
		// ARIFICIAL INTELLIGENCE FOR THE SMALLSHIP //
		// **************************************** //
		for (int i = 0; i < smallShipArray.size(); i++) {

			smallShip = smallShipArray.get(i);
			smallShip.enemyMoveDown();
			smallShip.setVelY(1);  // VELOCITY FOR THE SMALLSHIP GOING DOWN //
			smallShip.setVelX(random());

			TemplebulletForSmallShip.setVelY(15);


			/*
			smallShip.setVelY(random());

			if(smallShip.getX() < plane.getX()) {
				smallShip.enemyMoveRight();
			}

			else if(smallShip.getX() > plane.getX()) {
				smallShip.enemyMoveLeft();
			}

			else if(smallShip.getX() == plane.getX()) {
				smallShip.enemyMoveDown();
			}
			 */
			if(smallShip.getY() > 740){
				removeEnemyShip(smallShip);
			}
		}

		// *********************************************** //
		// COLLISION DETECTION FOR THE ENEMY SHIPS & PLANE //
		// *********************************************** //
		for (int i = 0; i < smallShipArray.size(); i++) {
			if (smallShipArray.get(i).collisionBox.hasCollidedWith(plane.collisionBox)) {
				Game.health -= 2;
				removeEnemyShip(smallShipArray.get(i));
			}

			// ************************************************ //
			// COLLISION DETECTION FOR THE BULLETS & SMALLAHIPS //
			// ************************************************ //
			for (int k = 0; k < bullet.size(); k++) { 
				if (bullet.get(k).collisionBox.hasCollidedWith(smallShipArray.get(i).collisionBox)) {
					Game.score += 2;
					explosion.add(new Explosion(smallShipArray.get(i).x + 30, smallShipArray.get(i).y + 30, 25, 50));
					removeBullet(bullet.get(k));
					removeEnemyShip(smallShipArray.get(i));
					explode.play();
				}
			}
		}

		// ********************************************************** //
		// A TIMER FOR ME TO KNOW WHEN THE BIG SHIP COMES TO THE GAME //
		// ********************************************************** //
		if(clockForBluePoints < 0){

			// ************************************** //
			// ARIFICIAL INTELLIGENCE FOR THE BIGSHIP //
			// ************************************** //
			for (int i = 0; i < BigShipArray.size(); i++) {
				
				clockForBigShip--;
				if(clockForBigShip > 0){
					bigShip.velX = 0;
					bigShip.velY = 2;
				}

				// *********************************** //
				// MAKES THE BIGSHIP MOVE SIDE TO SIDE //
				// *********************************** //
				bigShip = BigShipArray.get(i);
				bigShip.x += bigShip.velX;
				bigShip.collisionBox.moveRightBy(bigShip.velX);
				bigShip.y += bigShip.velY;
				bigShip.collisionBox.moveDownBy(bigShip.velY);

				// ********************************************************** //
				// SETTING A TIMER FOR THE BIGSHIP TO MAKE IT STOP GOING DOWN //
				// ********************************************************** //
				if(timer1 <= 0) bigShip.velY = 0;
				else timer1--;

				if(timer1 <= 0) timer2--;

				if(timer2 <= 0){
					if(bigShip.velX == 0)  bigShip.velX = 4;
					if(bigShip.x > 665) bigShip.velX = -4;
					if(bigShip.x < 0) bigShip.velX = 4;
				}
			}
		}

		// ********************************************* //
		// COLLISION DETECTION FOR THE BIG SHIPS & PLANE //
		// ********************************************* //
		for (int i = 0; i < BigShipArray.size(); i++) {
			if (BigShipArray.get(i).collisionBox.hasCollidedWith(plane.collisionBox)) {
				// ********************************************************************************** //
				// THIS SHOuLD NEVER HAPPEND BECAUSE THE PLANE DOESNT HAVE THE ABILITY TO GO UP THERE //
				// ********************************************************************************** //
			}

			// *************************************************** //
			// COLLISION DETECTION OF THE BULLETS AND THE BIGSHIIP //
			// *************************************************** //			
			for (int k = 0; k < bullet.size(); k++) {
				if (bullet.get(k).collisionBox.hasCollidedWith(BigShipArray.get(i).collisionBox)) {
					removeBullet(bullet.get(k));
					Game.score += 12;
					bigShipScore -= 4;
					bigExplode.play();
					if(bigShipScore <= 0 ){
						removeEnemyBigShip(BigShipArray.get(i));
					}
				}
			}
		}
	}

	// ***************************************** //
	// ADDS BIGSHIP FROM THE BIGSHIP LINKED LIST //
	// ***************************************** //
	public void addEnemyBigShip(BigShip bigShip) {
		BigShipArray.add(bigShip);
	}

	// *************************** // 
	// REMOVES BIGSHIP FROM MEMORY //
	// *************************** //
	public void removeEnemyBigShip(BigShip bigShip) {
		BigShipArray.remove(bigShip);
	}

	// ********************************************* //
	// ADDS SMALLSHIP FROM THE SMALLSHIP LINKED LIST //
	// ********************************************* //
	public void addEnemyShip(SmallShip enemyShip) {
		smallShipArray.add(enemyShip);
	}

	// ***************************** //
	// REMOVES SMALLSHIP FROM MEMORY //
	// ***************************** //
	public void removeEnemyShip(SmallShip enemyShip) {
		smallShipArray.remove(enemyShip);
	}

	// *************************************** //
	// ADDS BULLET FROM THE BULLET LINKED LIST //
	// *************************************** //
	public void addBullet(Bullet shot) {
		bullet.add(shot);
	}

	// **************************** //
	// REMOVES A BULLET FROM MEMORY //
	// **************************** //
	public void removeBullet(Bullet shot) {
		bullet.remove(shot);
	}

	// ********************************************** //
	// ADDS AN ASTEROID FROM THE ASTEROID LINKED LIST //
	// ********************************************** //
	public void addAsteroid(Asteroid shot) {
		asteroidArray.add(shot);
	}

	// ******************************* //
	// REMOVES AN ASTEROID FROM MEMORY //
	// ******************************* //
	public void removeAsteroid(Asteroid shot) {
		asteroidArray.remove(shot);
	}

	// *********************************************** //
	// ADDS BLUEPOINTS FROM THE BLUEPOINTS LINKED LIST //
	// *********************************************** //
	public void addBluePoints(BluePoints shot) {
		bp.add(shot);
	}

	// ****************************** //
	// REMOVES BLUEPOINTS FROM MEMORY //
	// ****************************** //
	public void removeBluePoints(BluePoints shot) {
		bp.remove(shot);
	}

	// *************************************************** //
	// RANDOM METHOD THAT RETURNS A RANDOM # BETWEEN 1 & 1 //
	// *************************************************** //
	public double random() {
		return Math.random() * 1 + 1;
	}

	// **************************************************** //
	// RANDOM METHOD THAT RETURNS A NUMBER FOR A X-POSITION //
	// **************************************************** //
	public double randomPositionForX() {
		return (int) (Math.random() * 720 + 30);
	}

	// ************************************************************ //
	// DRAWS BULLETS, ASTEROIDS, SMALLSHIPS & BIGSHIP TO THE SCREEN //
	// ************************************************************ //
	public void draw(Graphics g){
		// ***************************************** //
		// GOES THROUGH ALL THE BULLETS AND DRAWS IT //
		// ***************************************** //
		for(int i = 0; i < bullet.size(); i++){
			TemplebulletForSmallShip = bullet.get(i);
			Templebullet = bullet.get(i);
			TemplebulletForSmallShip.draw(g);
			Templebullet.draw(g);
		}

		// ******************************************* //
		// GOES THROUGH ALL THE ASTEROIDS AND DRAWS IT //
		// ******************************************* //
		for(int i = 0; i < asteroidArray.size(); i++){
			Templeasteroid = asteroidArray.get(i);
			Templeasteroid.draw(g);
		}

		// ******************************************** //
		// GOES THROUGH ALL THE SMALLSHIPS AND DRAWS IT //
		// ******************************************** //
		for (int i = 0; i < smallShipArray.size(); i++) {
			smallShip = smallShipArray.get(i);
			smallShip.draw(g);
		}

		// ******************************************* //
		// GOES THROUGH ALL THE BLUEPOITS AND DRAWS IT //
		// ******************************************* //
		for(int i = 0; i < bp.size(); i++){
			Templebp = bp.get(i);
			Templebp.draw(g);
		}

		// *************************************************** //
		// GOES THROUGH ALL THE EXPLOSIONS ANIM AND DRAWS THEM //
		// *************************************************** //
		for(int i = 0; i < explosion.size(); i++){
			explosion.get(i).draw(g);
		}

		// ************************************* //
		// GOES THROUGH THE BIGSHIP AND DRAWS IT //
		// ************************************* //
		for (int i = 0; i < BigShipArray.size(); i++) {
			bigShip = BigShipArray.get(i);
			bigShip.draw(g);
			

			// **************************** //
			// DRAWING THE HEADS UP DISPLAY //
			// **************************** //
			if(bigShipScore > 0) {
				Font fnt1 = new Font("Arial", Font.BOLD, 15);
				g.setColor(Color.green);
				g.setFont(fnt1);
				g.drawString("Boss Score", 710, 20);
				g.setColor(Color.red);
				g.fillRect(750, 25, 10, 200);
				g.setColor(Color.yellow);
				g.fillRect(750, 25, 10, bigShipScore);
			}

			// *********************************************************** //
			// REMOVES THE BIGSHIP FROM THE GAME WHEN SCORE ITS SCORE IS 0 //
			// *********************************************************** //
			if(bigShipScore <= 0) {
				g.drawImage(level2, 150, 200, null);

			}
		}
	}
}