package javainterview;

import java.util.Comparator;

public class SquareComparator implements Comparator<Square> {

	public int compare(Square o1, Square o2) {
		
		Square s1 = (Square)o1;
		Square s2 = (Square)o2;
		
		if (s1.area() > s2.area()) {
			return 1;
		} else if (s1.area() == s2.area()) {
			return 0;
		} else {
			return -1;
		}
	}
	
}