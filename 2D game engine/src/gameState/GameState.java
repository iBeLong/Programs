package gameState;

import java.awt.Graphics2D;

public interface GameState
{
	public void update();
	public void draw(Graphics2D g);
	public void keyPressed(int k);
	public void keyReleased(int k);
}