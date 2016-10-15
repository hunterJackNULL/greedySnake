package snake.entity;

import java.util.LinkedList;

public class Entity implements Activity {
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int LEFT = 3;
	public static final int DOWN = 4;
	public final int cellSize = 40;
	public int goal;
	public int length = 4;
	public Location location;// 头部坐标,初始时蛇身4单位
	public Location nextLocation;// 下一次移动
	public LinkedList<Location> snake = new LinkedList<>();
	public int direction = DOWN;
	public Location food;// = new Location(8,9);
	private boolean gameOver = false;

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Entity() {
		/*
		 * headLocation = new HeadLocation(); int random =
		 * (int)Math.random()*4+1; direction = random;
		 */
		location = new Location(6, 5);// down
		for (int i = 0; i < length; i++) {
			snake.add(new Location(location.getX(), location.getY() - i));
		}
		// System.out.println(snake.size());
		food = new Location(8, 9);
	}

	public void check() throws CloneNotSupportedException {
		Location temp = location.clone();
		switch (direction) {
		case UP:
			nextLocation = new Location(temp.getX(), temp.getY() - 1);
			break;
		case DOWN:
			nextLocation = new Location(temp.getX(), temp.getY() + 1);
			break;
		case LEFT:
			nextLocation = new Location(temp.getX() - 1, temp.getY());
			break;
		case RIGHT:
			nextLocation = new Location(temp.getX() + 1, temp.getY());
			break;

		default:
			break;
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if (!border()) {
			snake.addFirst(nextLocation);
			location = nextLocation;
			if (!eat()) {
				snake.removeLast();
			}
		} else {
			//System.out.println("out of border!");
		}

	}

	public boolean createFood() {
		boolean flag = true;
		for (Location location : snake) {
			if (location.equals(food)) {

				flag = false;
				break;
			}
		}
		//System.out.println("----" + flag);
		// System.out.println("食物点"+food.toString());
		return flag;
	}

	@Override
	public boolean eat() {
		// TODO Auto-generated method stub
		//System.out.println(food.toString() + "*****\n" + nextLocation.toString());
		boolean canEat = false;
		if (nextLocation.equals(food)) {
			//System.out.println("eat!!!!!!!!!");
			goal += 100;
			canEat = true;
			/*
			 * food = new Location(); if(!createFood()){ food = new Location();
			 * }
			 */
			do {
				food = new Location();
			} while (!createFood());
		}
		return canEat;
	}

	@Override
	public boolean border() {
		// TODO Auto-generated method stub
		boolean isBorder = false;
		if (nextLocation.getX() >= 20 || nextLocation.getX() < 0 || nextLocation.getY() < 0
				|| nextLocation.getY() >= 20) {
			isBorder = true;
			gameOver = true;
		} else {
			for (Location location : snake) {
				if (nextLocation.equals(location)) {
					isBorder = true;
					gameOver = true;
					break;
				}
			}
		}
		return isBorder;
	}// 实体

}
