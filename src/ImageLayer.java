import java.awt.*;


public class ImageLayer 
{

	Image image;
	double x;
	double y;
	double z;
	int w;
	
	
	public ImageLayer(String filename, double x, double y, double z, int w)
	{
		image = Toolkit.getDefaultToolkit().getImage(filename);
		this.x = x;
		this.y = y; 
		this.z = z;
		
		//how wide the image is
		this.w =w;
	}
	
	
	public void draw(Graphics g)
	{
		
		for(int i = 0; i< 50; i++)
		{
			//w changes depending on my photo size
			g.drawImage(image, (int) (x - Camera2D.x / z) , (int) ( y + Camera2D.y / z) - w * i, null); 
		}
	}
	
	
	
}
