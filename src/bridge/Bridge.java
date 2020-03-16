package bridge;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

// 기능과 구현을 별도의 클래스로 정의해서 서로를 분리하는 방법
// 상속은 견고한 결합, 위임은 느슨한 결합
// 구현 클래스와 기능 클래스를 나누고 구현 클래스의 method를 기능 클래스에 위임해서 사용
// Cartesian product complexity explosion 을 막는다
// entity explosion 을 피한다
// implementation 에서 interface decouple 하는 메커니즘

// (기능 클래스) Shape -> Circle, Square
// (구현 클래스) Rendering -> Vector, Raster

// bridge 없이 구성하면 4개의 클래스가 필요
// VectorCircleRenderer, VectorSquareRenderer....
// Rendering 이 추가되면 8개 필요 -> Bridge 패턴이 필요


interface Renderer {
	void renderCircle(float radius);
}

class VectorRenderer implements Renderer {
	@Override
	public void renderCircle(float radius) {
		System.out.println("Drawing a circle of radius " + radius);
	}
}

class RasterRenderer implements Renderer {
	@Override
	public void renderCircle(float radius) {
		System.out.println("Drawing pixels for a circle of radius " + radius);
	}
}

abstract class Shape {
	// Bridge
	protected Renderer renderer;
	
	public Shape(Renderer renderer) {
		this.renderer = renderer;
	}
	
	public abstract void draw();
	public abstract void resize(float factor);
}

class Circle extends Shape {
	
	public float radius;
	
	@Inject
	public Circle(Renderer renderer, float radius) {
		super(renderer);
		this.radius = radius;
	}

	@Override
	public void draw() {
		renderer.renderCircle(radius);
	}

	@Override
	public void resize(float factor) {
		radius *= factor;
	}
	
}

// Google Guice 를 이용해서 protected Renderer renderer bridge 대신에
// Dependency Injection 으로 처리하는 방법
class ShapeModule extends AbstractModule {
	@Override
	  protected void configure() {
	    bind(Renderer.class).to(VectorRenderer.class);
	  }
}

public class Bridge {
	
	public static void main(String[] args) {
		
		RasterRenderer raster = new RasterRenderer();
		VectorRenderer vector = new VectorRenderer();
		
		Circle circle = new Circle(vector, 5);
		
		circle.draw();
		circle.resize(2);
		circle.draw();
		
		Injector injector = Guice.createInjector(new ShapeModule());
	    Circle instance = injector.getInstance(Circle.class);
	    instance.radius = 3;
	    instance.draw();
	    instance.resize(2);
	    instance.draw();
	}
}
