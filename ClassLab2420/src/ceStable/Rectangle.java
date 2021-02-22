package ceStable;

public class Rectangle implements Comparable<Rectangle> {
	private int length;
	private int width;
	
	public Rectangle(int length, int width) {
		super();
		this.length = length;
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}
	
	public int perimeter() {
		return 0;//TODO
	}
	
	public int area() {
		return 0; //TODO
	}

	@Override
	public String toString() {
		return "[length " + length + "x width" + width + "]";
	}

	@Override
	public int compareTo(Rectangle o) {
		return this.getLength() - o.getLength();
	}
	

}
