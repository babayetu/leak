package bean;

public class Boy {
	private Girl girl;
	
	public Boy(Girl girl) {
		this.girl = girl;
	}

	public Girl getGirl() {
		return girl;
	}
	public void setGirl(Girl aGirl) {
		this.girl = aGirl;
	}	
}