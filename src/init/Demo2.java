package init;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// specification pattern

enum Color {
	GREEN, RED, BLUE, YELLOW
}
enum Size {
	SMALL, MEDIUM, LARGE, HUGE
}

class Product {
	public Color color;
	public Size size;
	public String name;
	
	public Product(Color color, Size size, String name) {
		this.color = color;
		this.size = size;
		this.name = name;
	}
}

interface Specification<T> {
	boolean isSatisfied(T item);
}

interface Filter<T> {
	Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {
	private Color color;
	
	public ColorSpecification(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean isSatisfied(Product product) {
		return product.color == color;
	}
}

class SizeSpecification implements Specification<Product> {
	private Size size;
	
	public SizeSpecification(Size size) {
		this.size = size;
	}
	
	@Override
	public boolean isSatisfied(Product product) {
		return product.size == size;
	}
}

class AndSpecification<T> implements Specification<T> {
	private Specification<T> first;
	private Specification<T> second;
	
	public AndSpecification(Specification<T> first, Specification<T> second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public boolean isSatisfied(T item) {
		return first.isSatisfied(item) && second.isSatisfied(item);
	}

}

class BetterFilter implements Filter<Product> {
	@Override
	public Stream<Product> filter(List<Product> products, Specification<Product> spec) {
		return products.stream().filter(p -> spec.isSatisfied(p));
	}
}

class ProductFilter {
	public Stream<Product> filterBySize(List<Product> product, Size size) {
		return product.stream().filter(p -> p.size == size);
	}

	public Stream<Product> filterByColor(List<Product> product, Color color) {
		return product.stream().filter(p -> p.color == color);
	}
	
	public Stream<Product> filterByColorAndSize(List<Product> product, Color color, Size size) {
		return product.stream().filter(p -> p.size == size && p.color == color);
	}
}

public class Demo2 {
	public static void main(String[] args) {
		Product apple = new Product(Color.RED, Size.SMALL, "Apple");
		Product tree = new Product(Color.RED, Size.SMALL, "Tree");
		Product house = new Product(Color.RED, Size.SMALL, "House");
		
		List<Product> products = new ArrayList<Product>();
		products.add(apple);
		products.add(tree);
		products.add(house);
		
		ProductFilter pf = new ProductFilter();
		
		System.out.println("Green products(old):");
		pf.filterByColor(products,  Color.GREEN).forEach(p -> System.out.println(" - " + p.name + " is Green"));

		System.out.println("Large products(old):");
		pf.filterBySize(products,  Size.LARGE).forEach(p -> System.out.println(" - " + p.name + " is Large"));
		
		System.out.println("Large blue items(old):");
		pf.filterByColorAndSize(products,  Color.GREEN, Size.LARGE).forEach(p -> System.out.println(" - " + p.name + " is Large and Blue"));
		
		BetterFilter bf = new BetterFilter();
		
		System.out.println("Green products(new):");
		bf.filter(products,  new ColorSpecification(Color.GREEN)).forEach(p -> System.out.println(" - " + p.name + " is Green"));
		
		System.out.println("Large products(new):");
		bf.filter(products, new SizeSpecification(Size.LARGE)).forEach(p -> System.out.println(" - " + p.name + " is Large"));

		System.out.println("Large blue items(new):");
		bf.filter(products, new AndSpecification<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE))).forEach(p -> System.out.println(" - " + p.name + " is Large and Blue"));
	}

}
