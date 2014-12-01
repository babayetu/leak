package algorithm;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> va;

	public ArrayList<Vertex> getVa() {
		return va;
	}

	public void setVa(ArrayList<Vertex> va) {
		this.va = va;
	}

	public static void main(String[] args) {
		Graph a = new Graph();
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		Vertex v5 = new Vertex("v5");
		Vertex v6 = new Vertex("v6");
		Vertex v7 = new Vertex("v7");
		
		v1.addEdge(new Node("v2", v2)).addEdge(new Node("v4", v4)).addEdge(new Node("v3", v3));
		v2.increaseInDegree();
		v4.increaseInDegree();
		v3.increaseInDegree();
		
		v2.addEdge(new Node("v4", v4)).addEdge(new Node("v5", v5));
		v4.increaseInDegree();
		v5.increaseInDegree();
		
		v3.addEdge(new Node("v6", v6));
		v6.increaseInDegree();
		
		v4.addEdge(new Node("v6", v6)).addEdge(new Node("v7", v7)).addEdge(new Node("v3", v3));
		v6.increaseInDegree();
		v7.increaseInDegree();
		v3.increaseInDegree();
		
		v5.addEdge(new Node("v4", v4)).addEdge(new Node("v7", v7));
		v4.increaseInDegree();
		v7.increaseInDegree();
		
		v7.addEdge(new Node("v6", v6));
		v6.increaseInDegree();
		
		ArrayList<Vertex> buildList = new ArrayList<Vertex>();
		buildList.add(v1);
		buildList.add(v2);
		buildList.add(v3);
		buildList.add(v4);
		buildList.add(v5);
		buildList.add(v6);
		buildList.add(v7);
		
		a.setVa(buildList);	
		
//		打印
//		ArrayList<Vertex> alv = a.getVa();
//		for (int i =0; i<alv.size(); i++) {
//			alv.get(i).trackLink();
//		}
		
		
	}
}