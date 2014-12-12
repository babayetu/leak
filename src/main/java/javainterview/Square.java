package javainterview;

public class Square implements Shape, Comparable<Square> {
	private double width;
	private double height;
	
	public double area() {
		return width * height;
	}

	public Square(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public int compareTo(Square o) {
		if (this.area() > o.area()) {
			return 1;
		} else if (this.area() < o.area()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public String toString() {
		return "width :" + width + " height:" + height; 
	}
}