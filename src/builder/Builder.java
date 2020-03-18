package builder;

import java.util.ArrayList;
import java.util.Collections;

/** 
* when construction gets a little bit too complicated
* some objects are simple and can be created in single constructor call
* other objects require a lot of ceremeony to create
* having an object with 10 constructor arguments is not productive
* instaead, opt for peicewise construction
* builder provides an API for construction an object step-by-step
* builder - when piecewise object construction is complicated, provide an API for doing it succinctly
**/
class HtmlElement {
	public String name;
	public String text;
	public ArrayList<HtmlElement> elements = new ArrayList<>();
	
	private final int indentSize = 2;
	private final String newLine = System.lineSeparator();
	
	public HtmlElement() {}
	
	public HtmlElement(String name, String text) {
		this.name = name;
		this.text = text;
	}
	
	private String toStringImpl(int indent) {
		StringBuilder sb = new StringBuilder();
		String i = String.join("",  Collections.nCopies(indent * indentSize,  " "));
		sb.append(String.format("%s<%s>%s", i, name, newLine));
		if (text != null && !text.isEmpty()) {
			sb.append(String.join("",  Collections.nCopies(indentSize * (indent + 1),  " ")))
				.append(text)
				.append(newLine);
		}
		
		for (HtmlElement e : elements)
			sb.append(e.toStringImpl(indent + 1));
		
		sb.append(String.format("%s</%s>%s", i, name, newLine));
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return toStringImpl(0);
	}
}

class HtmlBuilder {
	private String rootName;
	private HtmlElement root = new HtmlElement();
	
	public HtmlBuilder(String rootName) {
		this.rootName = rootName;
	}
	
	public void addChild(String childName, String childText) {
		HtmlElement e = new HtmlElement(childName, childText);
		root.elements.add(e);
	}
	
	public void clear() {
		root = new HtmlElement();
		root.name = rootName;
	}
	
	@Override
	public String toString() {
		return root.toString();
	}
}

public class Builder {
	public static void main(String[] args) {
		String hello = "hello";
		System.out.println("<p>" + hello + "</p>");
		String[] words = { "hello", "world" };
		// 하나씩 태그를 붙이면 출력문 만들기 까다롭다.
		System.out.println("<ul>\n" + "<li>" + words[0] + "</p>");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<ul>\n");
		for (String word : words) {
			sb.append(String.format(" <li>%s</li>",  word));
		}
		sb.append("</ul>");
		System.out.println(sb);
		
		HtmlBuilder builder = new HtmlBuilder("ul");
		builder.addChild("li", "hello");
		builder.addChild("li", "world");
		
		System.out.println(builder);		
	}
}