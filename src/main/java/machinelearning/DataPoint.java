package machinelearning;

public class DataPoint {
	private XYPair pos;
	private int sign;
	/**
	 * @return the pos
	 */
	public XYPair getPos() {
		return pos;
	}
	/**
	 * @param pos the pos to set
	 */
	public void setPos(XYPair pos) {
		this.pos = pos;
	}
	/**
	 * @return the sign
	 */
	public int getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(int sign) {
		this.sign = sign;
	}
	public DataPoint(XYPair pos, int sign) {
		this.pos = pos;
		this.sign = sign;
	}	
}