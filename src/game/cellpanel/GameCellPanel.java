package game.cellpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import snake.entity.Entity;
import snake.entity.Location;

public class GameCellPanel extends JPanel implements Runnable, KeyListener {
	public final int ROW = 19;
	public final int COLUMN = 19;
	public int goal;// 得分
	public String message = "得分:";
	public int[][] cell = new int[ROW][COLUMN];
	public Entity entity = new Entity();
	private static Image snakeHead;
	private static Image snakeBody;
	private static Image backGround, backGround2, gameOver;
	private static Image food;
	static {
		try {
			File path = new File("E:/素材/");
			Image buffer;

			buffer = ImageIO.read(new File(path, "snakeHead.jpg"));
			snakeHead = buffer.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);// sacle规模
			buffer = ImageIO.read(new File(path, "snakeBody.png"));
			snakeBody = buffer.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);
			buffer = ImageIO.read(new File(path, "food.png"));
			food = buffer.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);
			buffer = ImageIO.read(new File(path, "backGround.jpg"));
			backGround = buffer.getScaledInstance(800, 800, Image.SCALE_AREA_AVERAGING);
			buffer = ImageIO.read(new File(path, "backGround2.jpg"));
			backGround2 = buffer.getScaledInstance(200, 800, Image.SCALE_AREA_AVERAGING);
			buffer = ImageIO.read(new File(path, "gameOver.jpg"));
			gameOver = buffer.getScaledInstance(800, 800, Image.SCALE_AREA_AVERAGING);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GameCellPanel() {
		Component component = this;
		this.setLayout(null);
		// this.addKeyListener(this);
		component.setBounds(0, 0, 1000, 820);

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(backGround, 0, 0, null);
		g.drawImage(backGround2, 800, 0, null);
		g.setColor(Color.blue);
		g.setFont(new Font("楷体", Font.BOLD, 30));
		g.drawString(message, 800, 300);
		g.drawString(entity.goal + "", 800, 500);
		int i = 0;
		for (Location location : entity.snake) {// 绘制蛇身
			//System.out.println(location.getX() + " " + location.getY() + " " + i);
			/*
			 * g.drawRect(entity.cellSize * location.getX(), entity.cellSize *
			 * location.getY(), entity.cellSize, entity.cellSize);
			 */
			Image image;
			if (i == 0) {
				image = snakeHead;
			} else {
				image = snakeBody;
			}
			i++;
			g.drawImage(snakeBody, entity.cellSize * location.getX(), entity.cellSize * location.getY(), null);
		}
		g.drawImage(food, entity.cellSize * entity.food.getX(), entity.cellSize * entity.food.getY(), null);
		if (entity.isGameOver()) {
			g.drawImage(gameOver, 0, 0, null);
		}

	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				if (!entity.isGameOver()) {
					entity.check();
					entity.move();
					repaint();
				}
				Thread.sleep(300);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W) {//禁止倒转：如up强制转为down
			entity.direction = entity.direction==entity.DOWN?entity.direction:entity.UP;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			entity.direction = entity.direction==entity.UP?entity.direction:Entity.DOWN;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			//System.out.println("kkk");
			entity.direction = entity.direction==entity.RIGHT?entity.direction:Entity.LEFT;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			entity.direction = entity.direction==entity.LEFT?entity.direction:Entity.RIGHT;
		} else if (e.getKeyCode() == KeyEvent.VK_R) {//按r 重新开始
			entity = new Entity();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
