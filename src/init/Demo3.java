package init;

// factory pattern

class Rectangle {
	protected int width;
	protected int height;
	
	public Rectangle() {}
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getArea() {
		return this.width * this.height;
	}
	
	public boolean isSquare() {
		return width == height;
	}
}

class Square extends Rectangle {
	public Square() {}
	public Square(int size) {
		super.height = size;
		super.width = size;
	}
	
	@Override
	public int getWidth() {
		return super.getWidth();
	}
	
	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		super.setHeight(width);
	}
	
	@Override
	public int getHeight() {
		return super.getHeight();
	}
	
	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}
	
	@Override
	public int getArea() {
		return super.getArea();
	}
	
	public boolean isSquare() {
		return width == height;
	}
}

class RectangleFactory {
	public static Rectangle newRectangle(int width, int height) {
		return new Rectangle(width, height);
	}
	
	public static Rectangle newSquare(int size) {
		return new Square(size);
	}
}

public class Demo3 {
	static void useIt(Rectangle r) {
		int width = r.getWidth();
		r.setHeight(10);
		
		System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
	}
	
	public static void main(String[] args) {
		Rectangle rc = new Rectangle(2, 3);
		useIt(rc);
		
		Square sq = new Square(5);
		useIt(sq);
	}
}
