//Camera class
public class Camera2D {

	static double x;
	static double y;
	
	//static allows me to access the method 
	//anywhere I want in the project
	public static void moveUpBy(int dy) {
		y -= dy;
	}
	
	public static void moveDownBy(int dy){
		y += dy;
	}
	public static void moveLeftBy(int dy){
		x -= dy;
	}
	public static void moveRightBy(int dy){
		x += dy;
	}
	
}
