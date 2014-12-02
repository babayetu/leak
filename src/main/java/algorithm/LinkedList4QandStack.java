package algorithm;

import java.util.LinkedList;

public class LinkedList4QandStack {
	private LinkedList<String> q = new LinkedList<String>();
	private LinkedList<String> stack = new LinkedList<String>();
	
	
	public LinkedList<String> getQ() {
		return q;
	}
	
	public void setQ(LinkedList<String> q) {
		this.q = q;
	}

	public LinkedList<String> getStack() {
		return stack;
	}

	public void setStack(LinkedList<String> stack) {
		this.stack = stack;
	}

	public static void main(String[] args) {
		LinkedList4QandStack al = new LinkedList4QandStack();
		al.getQ().add("1");
		al.getQ().add("2");
		al.getQ().add("3");
		
		while (!al.getQ().isEmpty()) {
			System.out.println(al.getQ().remove());
		}	
		
		al.getStack().push("4");
		al.getStack().push("5");
		al.getStack().push("6");
		
		while (!al.getStack().isEmpty()) {
			System.out.println(al.getStack().pop());
		}
	}
}