package gameState;

import main.Main;
import java.awt.*;
import java.awt.event.*;

public class MenuState implements GameState
{
	private GameStateManager gsm;
	
	final static String[] options = {"Play Game", "Exit"};
	private int currentOption = 0;
	
	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
	}
	
	public void update(){}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setColor(Color.black);
		String title = "Game Menu";
		g.drawString(title, Main.WIDTH / 2 - g.getFontMetrics().stringWidth(title) / 2, 100);
		
		for (int i = 0; i < options.length; i++)
		{
			String option = options[i];
			if (currentOption == i)
				g.setColor(Color.black);
			else
				g.setColor(Color.lightGray);
			
			g.drawString(option, Main.WIDTH / 2 - g.getFontMetrics().stringWidth(option) / 2, 130 + 15 * i);
		}
	}
	
	public void select()
	{
		switch(currentOption)
		{
			case 0:
				gsm.setState(GameStateManager.LEVELSTATE);
				break;
			case 1:
				System.exit(0);
				break;
		}
	}
	
	public void keyPressed(int k)
	{
		if (k == KeyEvent.VK_ENTER)
			select();
		else if (k == KeyEvent.VK_UP)
		{
			currentOption--;
			if (currentOption == -1)
				currentOption = options.length - 1;
		}
		else if (k == KeyEvent.VK_DOWN)
		{
			currentOption++;
			if (currentOption == options.length)
				currentOption = 0;
		}
	}
	
	public void keyReleased(int k)
	{
		
	}
}