import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main extends JPanel implements KeyListener
{
	final static long serialVersionUID = 129847918274L;
	
	final static int WIDTH  = 700;
	final static int HEIGHT = 500;
	final static double FPS = 30.0;
	final static double CAP = 1000 / FPS;
	
	private boolean keysDown[] = new boolean[128];
	
	private BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private BufferedImage background;
	private int mapX = 0;
	
	private BufferedImage[] sprites;
	private int currentSprite = 0;
	
	private boolean facingRight = true;
	private int x = WIDTH / 2;
	private int y = HEIGHT / 2 - 50;
	
	private JFrame frame = new JFrame("Game");
	
	public Main()
	{
		super();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		frame.add(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		grabFocus();
	}
	
	public void init()
	{
		addKeyListener(this);
		loadBackground("res/background/underwater.jpg");
		loadSprites("res/sprite/octopus1-a.png", "res/sprite/octopus1-b.png");
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
	
	long moveStart = System.currentTimeMillis();
	long aniStart  = System.currentTimeMillis();
	
	public void update()
	{
		final int MOVE_DELAY = 10;
		final int ANI_DELAY = 200;
		boolean doesMove = false;
		
		if (System.currentTimeMillis() - moveStart > MOVE_DELAY)
		{
			if (keysDown[KeyEvent.VK_UP])
			{
				y -= 10;
				doesMove = true;
			}
			else if (keysDown[KeyEvent.VK_DOWN])
			{
				y += 10;
				doesMove = true;
			}
			
			if (keysDown[KeyEvent.VK_RIGHT])
			{
				if (x + sprites[currentSprite].getWidth() > WIDTH * 3 / 4)
					incBackground(-10);
				else
					x += 10;
				facingRight = true;
				doesMove = true;
			}
			else if (keysDown[KeyEvent.VK_LEFT])
			{
				if (x < WIDTH / 4)
					incBackground(10);
				else
					x -= 10;
				
				facingRight = false;
				doesMove = true;
			}
			moveStart = System.currentTimeMillis();
		}
		
		if (doesMove && System.currentTimeMillis() - aniStart > ANI_DELAY)
		{
			incSprite();
			aniStart = System.currentTimeMillis();
		}
		else if (!doesMove)
			currentSprite = 0;
	}
	
	public void draw(Graphics2D g)
	{
		drawBackground(g);
		drawSprite(g);
	}
	
	public void drawBackground(Graphics2D g)
	{
		g.drawImage(background, mapX - Main.WIDTH, 0, WIDTH, HEIGHT, null);
		g.drawImage(background, mapX, 0, WIDTH, HEIGHT, null);
	}
	
	public void drawSprite(Graphics2D g)
	{
		BufferedImage spriteImage = sprites[currentSprite];
		int width = spriteImage.getWidth();
		int height = spriteImage.getHeight();
		
		if (facingRight)
			g.drawImage(spriteImage, x, y, width, height, null);
		else
			g.drawImage(spriteImage, x + width, y, -width, height, null);
	}
	
	public void drawToScreen()
	{
		getGraphics().drawImage(img, 0, 0, WIDTH, HEIGHT, null);
	}
	
	public void loadBackground(String path)
	{
		try
		{
			File file = new File(path);
			background = ImageIO.read(file);
		} catch(IOException ex)
		{
			System.err.println("Error load background: " + ex.getMessage());
			System.exit(1);
		}
	}
	
	public void loadSprites(String... fileList)
	{
		sprites = new BufferedImage[fileList.length];
		for (int i = 0; i < fileList.length; i++)
		{
			try
			{
				File file = new File(fileList[i]);
				sprites[i] = ImageIO.read(file);
			} catch(IOException ex)
			{
				System.err.println("Error load sprites: " + ex.getMessage());
				System.exit(1);
			}
		}
	}
	
	public void incBackground(int increment)
	{
		mapX += increment;
		if (mapX > WIDTH)
			mapX -= WIDTH;
		else if (mapX < 0)
			mapX += WIDTH;
	}
	
	public void incSprite()
	{
		currentSprite++;
		if (currentSprite == sprites.length)
			currentSprite = 0;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int k = e.getKeyCode();
		if (k >= 0 && k < 128)
			keysDown[k] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		int k = e.getKeyCode();
		if (k >= 0 && k < 128)
			keysDown[k] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e){}
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.run();
	}
}