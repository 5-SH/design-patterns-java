package flyweight;

import java.util.*;

class Sentence {
	private String plainText;
	private List<WordToken> wts = new ArrayList<WordToken>();
	public Sentence(String plainText) {
		this.plainText = plainText;
		
		int startidx = 0;
		for (int i = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			if (c == ' ') {
				WordToken wt = new WordToken(startidx, i);
				wts.add(wt);
				startidx = i;
			}
		}
		WordToken wt = new WordToken(startidx, plainText.length());
		wts.add(wt);
	}

	public WordToken getWord(int index) {
		return this.wts.get(index);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.plainText.length(); i++) {
			char c = plainText.charAt(i);
			for (WordToken wt : wts) {
				if (wt.covers(i) && wt.capitalize) {
					c = Character.toUpperCase(c);
				}
			}
			sb.append(c);
		}
		
		return sb.toString();
	}

	class WordToken {
		private int start, end;
		public boolean capitalize;
		
		public WordToken(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public boolean covers(int idx) {
			return this.start <= idx && idx <= this.end;
		}
	}
}

public class FlyweightCodingExercise {

	public static void main(String[] args) {
		Sentence sentence = new Sentence("hello world");
		sentence.getWord(1).capitalize = true;
		System.out.println(sentence);
	}

}
