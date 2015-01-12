package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

public class Entity
{
	private BufferedImage[] sprites;
	private int currentSprite = 0;
	
	protected boolean right = true;
	
	private int delay;
	
	protected int x;
	protected int y;
	
	public Entity(String... spriteList)
	{
		sprites = new BufferedImage[spriteList.length];
		for (int i = 0; i < spriteList.length; i++)
		{
			try
			{
				File file = new File(spriteList[i]);
				BufferedImage tempImage = ImageIO.read(file);
				sprites[i] = tempImage;
			} catch(IOException ex)
			{
				System.err.println("Couldn't load file: " + ex.getMessage());
				System.exit(1);
			}
		}
	}
	
	public void setDelay(int delay)
	{
		this.delay = delay;
	}
	
	public void incSprite()
	{
		currentSprite++;
		if (currentSprite >= sprites.length)
			currentSprite = 0;
	}
	
	public void setSprite(int spriteNum)
	{
		currentSprite = spriteNum;
	}
	
	long animationStart = System.currentTimeMillis();
	
	public void update()
	{
		if (delay != -1)
		{
			if (System.currentTimeMillis() - animationStart > delay)
			{
				incSprite();
				animationStart = System.currentTimeMillis();
			}
		}
	}
	
	public void draw(Graphics2D g)
	{
		BufferedImage img = sprites[currentSprite];
		if (right)
			g.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);
		else
			g.drawImage(img, x + img.getWidth(), y, -img.getWidth(), img.getHeight(), null);
	}
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	public int getWidth() { return sprites[currentSprite].getWidth(); }
	public int getHeight() { return sprites[currentSprite].getHeight(); }
}