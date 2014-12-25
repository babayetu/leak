package datastructure;

public class InterleavingMovies {
	static String[] interleave(String[] left, String[] right) {
		boolean leftIsLonger = false;
		
		if (left.length == right.length) {
			String[] output = new String[left.length * 2];
			for (int i = 0; i < left.length; i++) {
					output[2*i] = left[i];
					output[2*i+1] = right[i];			
			}
			return output;
		}
		
		if (left.length > right.length) {
			leftIsLonger = true;
		}

		int quota = 1;
		
		String[] output = new String[left.length + right.length];
		
		if (leftIsLonger) {
			// left is longer
			while (right.length < (left.length / quota - 1)) {
				quota++;
			}
			
			for (int i = 0; i < left.length; i++) {
				output[i+i/quota] = left[i];
			}
			
			for (int j = 0; j < right.length; j++) {
				output[j+(j+1)*quota] = right[j];
			}
		} else {
			//right is longer
			while (left.length < (right.length / quota - 1)) {
				quota++;
			}
			
			for (int i = 0; i < right.length; i++) {
				output[i+i/quota] = right[i];
			}
			
			for (int j = 0; j < left.length; j++) {
				output[j+(j+1)*quota] = left[j];
			}			
		}		
		
		return output;
    }
	
	public static void main(String[] args) {
		String[] A = new String[] {"A","A"};
		String[] B = new String[] {"B","B","B","B","B","B"};
		String[] C = InterleavingMovies.interleave(A,B);
		for (int i = 0; i < C.length; i++) {
			System.out.println(C[i]);			
		}
	}
}