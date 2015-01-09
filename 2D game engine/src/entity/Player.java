package entity;

import main.Main;
import background.Background;
import gameState.LevelState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity
{
	private LevelState lvl;
	private boolean keys[] = new boolean[128];
	private int moveDelay = 5;
	private int aniDelay = 100;
	
	public Player(LevelState lvl)
	{
		super("res/player/octopus1-a.png", "res/player/octopus1-b.png");
		x = Main.WIDTH / 2;
		y = Main.HEIGHT / 2;
		this.lvl = lvl;
	}
	
	long moveStart = System.currentTimeMillis();
	long aniStart = System.currentTimeMillis();
	
	public void update()
	{
		if (System.currentTimeMillis() - moveStart > moveDelay)
		{
			boolean movement = false;
			if (keys[KeyEvent.VK_UP])
			{
				if (this.getY() > 0)
				{
					this.setY(this.getY() - 10);
					movement = true;
				}
			}
			else if (keys[KeyEvent.VK_DOWN])
			{
				if (this.getY() + this.getHeight() < Main.HEIGHT - 10)
				{
					this.setY(this.getY() + 10);
					//movement = true;
				}
			}
			if (keys[KeyEvent.VK_RIGHT])
			{
				lvl.getBackground().incX(-10);
				right = true;
				movement = true;
			}
			else if (keys[KeyEvent.VK_LEFT])
			{
				lvl.getBackground().incX(10);
				right = false;
				movement = true;
			}
			
			if (!movement)
				return;
			
			moveStart = System.currentTimeMillis();
		}
		
		if (System.currentTimeMillis() - aniStart > aniDelay)
		{
			incSprite();
			aniStart = System.currentTimeMillis();
		}
	}
	
	public void keyPressed(int k)
	{
		if (k >= 0 && k < 128)
			keys[k] = true;
	}
	
	public void keyReleased(int k)
	{
		if (k >= 0 && k < 128)
			keys[k] = false;
	}
}