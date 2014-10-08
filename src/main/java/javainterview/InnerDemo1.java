package javainterview;

public class InnerDemo1 {
	private boolean b;
	private int i;
	
	//inner class
	private class Inner {
		private boolean b;

		Inner(boolean b) {
			this.b = b;
		}
		
		public void print() {
			System.out.println(InnerDemo1.this.b & this.b);
		}		
	}
	
	public void f(final String str) {
		class Inner {
			public void print() {
				System.out.println(InnerDemo1.this.b);
			}
		}
	}	
}