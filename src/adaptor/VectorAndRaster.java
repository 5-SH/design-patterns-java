package adaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Point point = (Point) obj;
		if (this.x != point.x) return false;
		return this.y == point.y;
	}
	
	@Override
	public int hashCode() {
		int result = this.x;
		result = 31 * result + this.y;
		return result;
	}
}

class Line {
	Point start;
	Point end;

	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		
		Line line = (Line) obj;
		
		if (!start.equals(line.start)) return false;
		
		return end.equals(line.end);
	}
	
	@Override
	public int hashCode() {
		int result = start.hashCode();
		result = 31 * result + end.hashCode();
		return result;
	}
}

class VectorObject extends ArrayList<Line> {
}

class VectorRectangle extends VectorObject {

	public VectorRectangle(int x, int y, int width, int height) {
		add(new Line(new Point(x, y), new Point(x + width, y)));
		add(new Line(new Point(x + width, y), new Point(x + width, y + height)));
		add(new Line(new Point(x, y), new Point(x, y + height)));
		add(new Line(new Point(x, y + height), new Point(x + width, y + height)));
	}
}

class LineToPointAdapter implements Iterable<Point> {

	private static int count = 0;
	private static Map<Integer, List<Point>> cache = new HashMap<>();
	private int hash;
	
	public LineToPointAdapter(Line line) {

		hash = line.hashCode();
		if (cache.get(hash) != null) {
			return;
		}
		
		System.out.println(String.format("%d: Generating points for line [%d,%d]-[%d,%d] (with caching)", ++count,
				line.start.x, line.start.y, line.end.x, line.end.y));

		int left = Math.min(line.start.x, line.end.x);
		int right = Math.max(line.start.x, line.end.x);
		int top = Math.min(line.start.y, line.end.y);
		int bottom = Math.max(line.start.y, line.end.y);

		int dx = right - left;
		int dy = line.end.y - line.start.y;

		ArrayList<Point> points = new ArrayList<Point>();
		
		if (dx == 0) {
			for (int y = top; y <= bottom; ++y) {
				points.add(new Point(left, y));
			}
		} else if (dy == 0) {
			for (int x = left; x <= right; ++x) {
				points.add(new Point(x, top));
			}
		}
		
		cache.put(hash, points);
	}

	@Override
	public Iterator<Point> iterator() {
		return cache.get(hash).iterator();
	}
	@Override
	public Spliterator<Point> spliterator() {
		return cache.get(hash).spliterator();
	}
	@Override
	public void forEach(Consumer<? super Point> action) {
		cache.get(hash).forEach(action);
	}
}


public class VectorAndRaster {

	private static final List<VectorObject> vectorObjects = new ArrayList<>(
			Arrays.asList(new VectorRectangle(1, 1, 10, 10), new VectorRectangle(3, 3, 6, 6)));

	// 선을 그리는 API 가 아래와 같이 drawPoint 만 주어지게 된다면 Line을 Point로 Adapt 해줘야 한다.
	// VectorObject는 Line으로 구성되어 있기 때문
	public static void drawPoint(Point p) {
		System.out.print(".");
	}

	private static void draw() {
		for (VectorObject vector : vectorObjects) {
			for (Line line : vector) {
				LineToPointAdapter adapter = new LineToPointAdapter(line);
				adapter.forEach(VectorAndRaster::drawPoint);
				System.out.println("");
			}
		}
	}
	public static void main(String[] args) {
		draw();
		// cache 없이 adaptor를 구현하면 같은 line을 두 번 그리는 중복이 발생한다.
		draw();
	}
}
