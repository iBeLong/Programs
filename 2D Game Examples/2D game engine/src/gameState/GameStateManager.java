package gameState;

import main.*;

import java.awt.*;

public class GameStateManager
{
	final static int MENUSTATE   = 0;
	final static int LEVELSTATE  = 1;
	final static int FAILEDSTATE = 2;
	private Main main;
	
	private GameState gameState;
	
	public GameStateManager(Main main)
	{
		this.main = main;
		setState(MENUSTATE);
	}
	
	public void setState(int num)
	{
		if (num == MENUSTATE)
			this.gameState = new MenuState(this);
		else if (num == LEVELSTATE)
			this.gameState = new LevelState(this);
		else if (num == FAILEDSTATE)
			this.gameState = new FailedState(this);
	}
	
	public void update()
	{
		gameState.update();
	}
	
	public void draw(Graphics2D g)
	{
		gameState.draw(g);
	}
	
	public void keyPressed(int k)
	{
		gameState.keyPressed(k);
	}
	
	public void keyReleased(int k)
	{
		gameState.keyReleased(k);
	}
}