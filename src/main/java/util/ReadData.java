package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadData {
	public static HashMap<Integer,ArrayList<Integer>> readFile(String name) {
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<Integer> field0 = new ArrayList<Integer>();
		ArrayList<Integer> field1 = new ArrayList<Integer>();
		HashMap<Integer,ArrayList<Integer>> result = new HashMap<Integer,ArrayList<Integer>>();
		
		try {
			fr = new FileReader(name);
			br = new BufferedReader(fr);
		
			while (br.ready()) {
				//System.out.println(br.readLine());
				String line = br.readLine();
				String[] field = line.split(" ");
				field0.add(new Integer(field[0]));
				field1.add(new Integer(field[1]));
			}
			
			result.put(new Integer(0), field0);
			result.put(new Integer(1), field1);
			return result;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public static void main(String[] args) {
		readFile("./src/main/resources/algorithm/data.in");
		System.out.println(System.getProperty("user.dir"));
	}
}