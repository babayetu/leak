package datastructure;

import java.util.Iterator;

public class UseMyArrayListAndMyLinkedList {
	private void testMyArrayList() {
		MyArrayList<Integer> mal = new MyArrayList<Integer>();
		for (int i=0;i<20;i++) {
			mal.add(i);
		}
		
		Iterator<Integer> ii = mal.iterator();
		while (ii.hasNext()) {
			if (ii.next() % 2 == 0) {
				ii.remove();			
			}
		}
		
		ii = mal.iterator();
		while (ii.hasNext()) {
			System.out.println(ii.next());
		}
		
		for (int i=0;i<mal.size();i++) {
			System.out.println(mal.get(i));
		}
	}
	
	private void testMyLinkedListAsArray() {
		MyLinkedList<Integer> mll = new MyLinkedList<Integer>();
		for (int i=0;i<20;i++) {
			mll.add(i);
		}
		
		Iterator<Integer> ii = mll.iterator();
		while (ii.hasNext()) {
			System.out.println(ii.next());
		}
		
		ii = mll.iterator();
		while (ii.hasNext()) {
			if (ii.next() % 2 == 0) {
				ii.remove();			
			}
		}
		for (int i=0;i<mll.size();i++) {
			System.out.println(mll.get(i));
		}
		
	}
	
	private void testMyLinkedListAsStack() {
		MyLinkedList<Integer> mll = new MyLinkedList<Integer>();
		for (int i=0;i<8;i++) {
			mll.push(i);
		}		
		
		System.out.println(mll.top());
		
		while (!mll.isEmpty()) {
			System.out.println(mll.pop());
		}
		
		System.out.println(mll.top());
	}
	
	public static void main(String[] args) {
		UseMyArrayListAndMyLinkedList umalam = new UseMyArrayListAndMyLinkedList();
		
		//umalam.testMyArrayList();
		//umalam.testMyLinkedListAsArray();
		umalam.testMyLinkedListAsStack();
	}
}