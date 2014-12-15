package datastructure;

import java.util.Iterator;

public class UseMyArrayListAndMyLinkedList {
	public static void main(String[] args) {
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
}