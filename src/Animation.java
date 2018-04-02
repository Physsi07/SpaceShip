import java.awt.*;

// Animation Class
public class Animation 
{
	// Array of type Image
	private Image[] image;
	
	// Declared variables
	private int current = 0;
    private int duration;
    private int counDown;
	
    // Constructor of the class
	public Animation(String filename, int count, int duration)
	{
		image = new Image[count];
		
		for(int i = 0; i < count; i++)
		{
			image[i] = Toolkit.getDefaultToolkit().getImage(filename + i + ".png");	
		}
		
		this.duration = duration;
		counDown = duration;
	}
    
	// <= or == or image.length - 1
	// Gets the next image in file
	public Image nextImage()
	{
		counDown--;
		
	    if(counDown == 0)
	    {
	    	counDown = duration;
	    	
			current++;
			
			if(current == image.length)
			{
				current = 1;
			}
			
	    }
	
	    return image[current];
	    
	}
	
	// First image of the list
	public Image staticImage()
	{
		return image[0];
	}
	
}