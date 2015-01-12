package main;

import gameState.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class Main extends JPanel implements KeyListener
{
	final static long serialVersionUID = 92184791724L;
	
	public final static int WIDTH  = 700;
	public final static int HEIGHT = 500;
	
	final static double FPS = 60.0;
	final static double CAP = 1000 / FPS;
	
	private BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private GameStateManager gsm = new GameStateManager(this);
	
	private JFrame frame = new JFrame("Game");
	
	public Main()
	{
		super();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		frame.add(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		grabFocus();
	}
	
	public void run()
	{
		long start = System.currentTimeMillis();
		
		while (true)
		{
			if (System.currentTimeMillis() - start > CAP)
			{
				update();
				draw((Graphics2D)img.getGraphics());
				drawToScreen();
				start = System.currentTimeMillis();
			}
		}
	}
	
	public void update()
	{
		gsm.update();
	}
	
	public void draw(Graphics2D g)
	{
		gsm.draw(g);
	}
	
	public void drawToScreen()
	{
		getGraphics().drawImage(img, 0, 0, WIDTH, HEIGHT, null);
	}
	
	public void keyPressed(KeyEvent e)
	{
		gsm.keyPressed(e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e)
	{
		gsm.keyReleased(e.getKeyCode());
	}
	
	public void keyTyped(KeyEvent e){}
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.run();
	}
}