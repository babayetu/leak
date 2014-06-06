package zzm;


public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOKE = null;
	public void isAlive() {
		System.out.println("I'm alive");
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("finalize method executed");
		FinalizeEscapeGC.SAVE_HOOKE = this;
	}
	
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOKE = new FinalizeEscapeGC();
		SAVE_HOOKE = null;
		System.gc();
		Thread.sleep(500);
		if (SAVE_HOOKE != null) {
			SAVE_HOOKE.isAlive();
		} else {
			System.out.println("I'm dead");
		}
		
		SAVE_HOOKE = null;
		System.gc();
		if (SAVE_HOOKE != null) {
			SAVE_HOOKE.isAlive();
		} else {
			System.out.println("I'm dead");
		}
	}
}