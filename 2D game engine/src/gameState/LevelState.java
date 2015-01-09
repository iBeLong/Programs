package gameState;

import main.Main;
import background.Background;
import entity.*;

import java.awt.*;

public class LevelState implements GameState
{
	private GameStateManager gsm;
	private Background bg = new Background("res/background/underwater.gif", 0.0f);
	
	private Player player = new Player(this);
	
	public LevelState(GameStateManager gsm)
	{
		this.gsm = gsm;
	}
	
	public void update()
	{
		player.update();
	}
	
	public void draw(Graphics2D g)
	{
		bg.draw(g);
		player.draw(g);
	}
	
	public void keyPressed(int k)
	{
		player.keyPressed(k);
	}
	
	public void keyReleased(int k)
	{
		player.keyReleased(k);
	}
	
	public Background getBackground()
	{
		return bg;
	}
}