package snake.entity;

import java.util.Random;

public class Location {
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int i) {
		this.x +=i;
	}
	public int getY() {
		return y;
	}
	public void setY(int i) {
		this.y +=i;
	}
	public Location() {
		Random random = new Random();
		x = random.nextInt(18)+1;//[4,9)
		y = random.nextInt(18)+1;
		//System.out.println(x+" |||"+y);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[x:"+x+" y:"+y+"]";
	}
	@Override
	protected Location clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Location(x,y);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean same = false;
		Location temp = (Location)obj;
		if(this.x==temp.x&&this.y==temp.y){
			same = true;
		}
		return same;
	}
	public Location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
