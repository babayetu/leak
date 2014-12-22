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
		
		int n = 0;  //longer length
		int m=0;
		
		String[] output = new String[left.length + right.length];
		
		int j=0;
		int i=0;
		
		if (leftIsLonger) {
			n = left.length;
			m = right.length;
			int quota = 1;
			int cursor = 0;
			
			while (m < (n / quota - 1)) {
				quota++;
			}	
			
			while (i<n && j<m) {
				if (cursor == 0) {
					output[0] = left[0];
					cursor++;
					i++;
					continue;
				}
				if (i % quota != 0) {
					output[cursor] = left[i];
					i++;
					cursor++;
				} else {
					output[cursor] = right[j];
					j++;
					cursor++;					
				}				
			}
			while (i<n) {
				output[cursor] = left[i];
				i++;
				cursor++;
			}
		} else {
			//right is longer
			n = right.length;
			m = left.length;
			int quota = 1;
			int cursor = 0;
			
			while (m < (n / quota - 1)) {
				quota++;
			}

			while (i<n && j<m) {
				if (cursor == 0) {
					output[0] = right[0];
					cursor++;
					i++;
					continue;
				}
				if (i % quota != 0) {
					output[cursor] = right[i];
					i++;
					cursor++;
				} else {
					output[cursor] = left[j];
					j++;
					cursor++;					
				}				
			}
			while (i<n) {
				output[cursor] = right[i];
				i++;
				cursor++;
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