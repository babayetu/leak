package javainterview;

/**
 * Immutable Object like String
 * When function use it as paramters, no need to clone(), because it will never be changed by client by accident
 * http://www.shivasoft.in/blog/java/how-to-create-immutable-class-in-java/
 * 
 * @author liukarl
 *
 */
public final class ImmutableObject {
	private final String mName;
	private final int mAge;
	
	public ImmutableObject(String mName, int mAge) {
		this.mName = mName;
		this.mAge = mAge;
	}

	public String getmName() {
		return mName;
	}

	public int getmAge() {
		return mAge;
	}
	
	public static void main(String[] args) {
		ImmutableObject io = new ImmutableObject("tc1", 1);
		System.out.println(io);
	}
}