package background;

import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Background
{
	private int x;
	private BufferedImage background;
	private float vector;
	
	public Background(String fileName, float moveSpeed)
	{
		try
		{
			File file = new File(fileName);
			background = ImageIO.read(file);
		} catch(IOException ex)
		{
			System.err.println("Problem Reading file: " + ex.getMessage());
			System.exit(1);
		}
		vector = moveSpeed;
	}
	
	public void incX(int increment)
	{
		x += increment;
		
	 	if (x > Main.WIDTH)
			x = x - Main.WIDTH;
		
		else if (x < 0)
			x = x + Main.WIDTH;
	}
	
	public void setX(int x) { this.x = x; }
	public int getX() { return x; }
	
	public void draw(Graphics2D g)
	{
		g.drawImage(background, x - Main.WIDTH, 0, Main.WIDTH, Main.HEIGHT, null);
		g.drawImage(background, x, 0, Main.WIDTH, Main.HEIGHT, null);
	}
	
	public void setVector(float vector) { this.vector = vector; }
}