package javainterview;

import java.lang.reflect.Array;

public class Generics {
	public void unbox(Box<?> box) {
	    System.out.println(box.get());
	}
	public void rebox(Box<?> box) {
	    reboxHelper(box);
	}
	
	//巧妙的绕过了编译器的限制
	private<V> void reboxHelper(Box<V> box) {
	    box.put(box.get());
	}
	
	public static void main(String[] args) {
		Box<String> aBox = BoxImpl.make();
		String aStr = "12345";
		aBox.put(aStr);
		System.out.println(aBox.get());
		
		Generics g = new Generics();
		g.unbox(aBox);
		
		g.rebox(aBox);
		g.unbox(aBox);
		
		String[] aArray = new String[] {"a","b","c","d"};
		String[] bArray = Generics.copyOfRange(aArray, 0, 3);
		for (int i =0; i<bArray.length;i++) {
			System.out.println(bArray[i]);
		}
	}
	
    @SuppressWarnings("unchecked")
	private static <T> T[] copyOfRange(T[] original, int start, int end){
    	final int originalLength = original.length;
    	if(start > end){
    		throw new IllegalArgumentException();
    	}
    	if(start < 0 || end > originalLength){
    		throw new ArrayIndexOutOfBoundsException();
    	}
    	
    	final int resultLength = end - start;
    	final int copyLength = Math.min(resultLength, originalLength - start);
    	
    	final T[] result = (T[])Array.newInstance(original.getClass().getComponentType(), resultLength);
    	System.arraycopy(original, start, result, 0, copyLength);
    	return result;
    }
}