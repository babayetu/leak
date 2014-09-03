package ajoo;

public enum LoggerConstant {
	INFO(1), DEBUG(2), WARNING(4),ERROR(8),ALL(15),NONE(-1);
	
	private int mValue;
	private LoggerConstant(int value) {
		this.mValue = value;
	}
	
	public int getValue() {
		return mValue;
	}
}