package zzm;

/**
 * VMargs: -Xss2M
 * Save windows jobs, it may cause system to fake dead state
 * @author jingjliu
 *
 */

public class JavaVMStackOOM {
	private void dontStop() {
		while (true) {
			
		}
	}
	
	public void stackLeakByThread() {
		while (true) {
			Thread aThread = new Thread(new Runnable() {

				/* (non-Javadoc)
				 * @see java.lang.Runnable#run()
				 */
				public void run() {
					// TODO Auto-generated method stub
					dontStop();
				}
				
			});
			aThread.start();
		}
	}
	
	public static void main(String[] args) {
		JavaVMStackOOM startPoint = new JavaVMStackOOM();
		startPoint.stackLeakByThread();
	}
}