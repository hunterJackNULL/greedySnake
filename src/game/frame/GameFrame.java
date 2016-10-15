package game.frame;

import java.awt.Toolkit;

import javax.swing.JFrame;

import game.cellpanel.GameCellPanel;


public class GameFrame extends JFrame{
	private static int screenWidth;
	private static int screenHeight;
	static{
		screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
	public GameFrame(String name){
		super(name);
		GameCellPanel gameCellPanel = new GameCellPanel();
		Thread thread = new Thread(gameCellPanel);
		this.setBounds((screenWidth-gameCellPanel.getWidth())/2,
				(screenHeight-gameCellPanel.getHeight())/2, gameCellPanel.getWidth(),
				gameCellPanel.getHeight());
		this.add(gameCellPanel);
		thread.start();
		this.addKeyListener(gameCellPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new GameFrame("GreedySnake designed by JW");
	}
}
