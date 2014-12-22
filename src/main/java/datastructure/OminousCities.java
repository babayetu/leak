package datastructure;

public class OminousCities {
	static String findBestCity(String homeCity, String[] allCities) {
		if (allCities.length == 0) {
			return "NOT_FOUND";
		}
		
		int[] c = new int[allCities.length];
		int[] v = new int[allCities.length];
		int oc = 0;
		int ov = 0;
		
		char[] arr = homeCity.toCharArray();		
		for (int j = 0; j < arr.length; j++) {
			if (arr[j] == 'a' || arr[j] == 'e' || arr[j] == 'i' || arr[j] == 'o' || arr[j] == 'u' ||
					arr[j] == 'A' || arr[j] == 'E' || arr[j] == 'I' || arr[j] == 'O' || arr[j] == 'U') {
				ov++;
			} else {
				oc++;
			}
		}	
		
		for (int i = 0; i < allCities.length; i++) {
			arr = allCities[i].toCharArray();
			
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] == 'a' || arr[j] == 'e' || arr[j] == 'i' || arr[j] == 'o' || arr[j] == 'u' ||
						arr[j] == 'A' || arr[j] == 'E' || arr[j] == 'I' || arr[j] == 'O' || arr[j] == 'U') {
					v[i]++;
				} else {
					c[i]++;
				}
			}				
		}
		
		if (oc==0) {
			int r1 = -1;
			int miss = Integer.MAX_VALUE;
			for (int i = 0; i < c.length; i++) {
				if (c[i]==0 && !homeCity.equalsIgnoreCase(allCities[i])) {
					if (miss > Math.abs(v[i] - ov)) {
						miss = Math.abs(v[i] - ov);
						r1 = i;
					}					
				}
			}
			if (r1 != -1) {
				return allCities[r1];
			} else {
				return "NOT_FOUND";
			}	
		}
		
		if (ov==0) {
			int r2 = -1;
			int miss = Integer.MAX_VALUE;
			for (int i = 0; i < c.length; i++) {
				if (v[i]==0 && !homeCity.equalsIgnoreCase(allCities[i])) {
					if (miss > Math.abs(c[i] - oc)) {
						miss = Math.abs(c[i] - oc);
						r2 = i;
					}
				}
			}
			if (r2 != -1) {
				return allCities[r2];
			} else {
				return "NOT_FOUND";
			}
		}
		
		float target = (float) (oc/ov);
		int record = -1;
		
		for (int i = 0; i < c.length; i++) {
			float value = Float.MAX_VALUE;			
			
			if (!homeCity.equalsIgnoreCase(allCities[i])) {
				if (v[i] != 0 ) {
					float t = (float) (c[i] / v[i]);
					if ((Math.abs(target - t)) <value) {
						value = t;
						record = i;
					}
				}				
			}
		}
		
		if (record == -1) {
			return "NOT_FOUND";
		} else {
			return allCities[record];
		}	
				
    }
	
	

	
	public static void main(String[] args) {
		OminousCities oc = new OminousCities();
		String[] country = new String[] {"aaaaa","ddddd","eeee"};
		String homeCity = "aaaaa";
		System.out.println(OminousCities.findBestCity(homeCity,country));	
	}
}