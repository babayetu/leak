package machinelearning;

public class XYPair {
	private int x;
	private int y;
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	public XYPair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public XYPair() {
		this.x = 0;
		this.y = 0;
	}
	
	public static XYPair convertOriginPoint2LeftCorner(XYPair leftCornerPos, int yAxislength) {
		  XYPair drawPos = new XYPair();
		  drawPos.setX(leftCornerPos.getX());
		  drawPos.setY(yAxislength - leftCornerPos.getY());
		  return drawPos;
	  }
	
}