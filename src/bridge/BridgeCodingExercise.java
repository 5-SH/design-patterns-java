package bridge;
/*
abstract class Shape {
	public abstract String getName();
}

class Triangle extends Shape {

	private Renderer renderer;

	public Triangle(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public String getName() {
		return "Triangle";
	}
	
	@Override
	public String toString() {
		return "Drawing " + getName() + " as " + renderer.whatToRenderAs();
	}
}

class Square extends Shape {

	private Renderer renderer;

	public Square(Renderer renderer) {
		this.renderer = renderer;
	}
	
	@Override
	public String getName() {
		return "Square";
	}
	
	@Override
	public String toString() {
		return "Drawing " + getName() + " as " + renderer.whatToRenderAs();
	}
}

// imagine VectorTriangle and RasterTriangle are here too
interface Renderer {
	public String whatToRenderAs();
}

class RasterRenderer implements Renderer {
	@Override
	public String whatToRenderAs() {
		return "pixels";
	}
}

class VectorRenderer implements Renderer {
	@Override
	public String whatToRenderAs() {
		return "lines";
	}
}

public class BridgeCodingExercise {
	public static void main(String[] args) {
		System.out.print(new Triangle(new RasterRenderer()).toString());
	}
}

*/