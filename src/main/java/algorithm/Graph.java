package algorithm;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> va;
	private static Graph directionalNoLoopGraph = null;
	private static Graph directionalLoopedGraph = null;
	private static Graph costDAG = null;

	public ArrayList<Vertex> getVa() {
		return va;
	}

	public void setVa(ArrayList<Vertex> va) {
		this.va = va;
	}
	
	public static Graph buildDirectionalNoLoopGraph() {
		if (directionalNoLoopGraph == null) {
			directionalNoLoopGraph = new Graph();
			Vertex v1 = new Vertex("v1");
			Vertex v2 = new Vertex("v2");
			Vertex v3 = new Vertex("v3");
			Vertex v4 = new Vertex("v4");
			Vertex v5 = new Vertex("v5");
			Vertex v6 = new Vertex("v6");
			Vertex v7 = new Vertex("v7");
			
			v1.addEdge(new Node("v2", v2)).addEdge(new Node("v4", v4));
			v2.increaseInDegree();
			v4.increaseInDegree();
			
			v2.addEdge(new Node("v4", v4)).addEdge(new Node("v5", v5));
			v4.increaseInDegree();
			v5.increaseInDegree();
			
			v3.addEdge(new Node("v1", v1)).addEdge(new Node("v6", v6));
			v1.increaseInDegree();
			v6.increaseInDegree();
			
			v4.addEdge(new Node("v6", v6)).addEdge(new Node("v7", v7)).addEdge(new Node("v3", v3)).addEdge(new Node("v5", v5));
			v6.increaseInDegree();
			v7.increaseInDegree();
			v3.increaseInDegree();
			v5.increaseInDegree();
			
			v5.addEdge(new Node("v7", v7));
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
			
			directionalNoLoopGraph.setVa(buildList);
		}
		
		return directionalNoLoopGraph;		
	}

	//创建带权 有向无环图(DAG)
	public static Graph buildCostDAG() {
		if (costDAG == null) {
			costDAG = new Graph();
			Vertex v1 = new Vertex("v1");
			Vertex v2 = new Vertex("v2");
			Vertex v3 = new Vertex("v3");
			Vertex v4 = new Vertex("v4");
			Vertex v5 = new Vertex("v5");
			Vertex v6 = new Vertex("v6");
			Vertex v7 = new Vertex("v7");
			
			//添加edge和顶点
			v1.addEdge(new Node("v2", v2,2)).addEdge(new Node("v4", v4,1));			
			v2.addEdge(new Node("v4", v4,3)).addEdge(new Node("v5", v5,10));			
			v3.addEdge(new Node("v1", v1,4)).addEdge(new Node("v6", v6,5));			
			v4.addEdge(new Node("v6", v6,8)).addEdge(new Node("v7", v7,4)).
			   addEdge(new Node("v3", v3,2)).addEdge(new Node("v5", v5,2));			
			v5.addEdge(new Node("v7", v7,6));			
			v7.addEdge(new Node("v6", v6,1));
			
			ArrayList<Vertex> buildList = new ArrayList<Vertex>();
			buildList.add(v1);
			buildList.add(v2);
			buildList.add(v3);
			buildList.add(v4);
			buildList.add(v5);
			buildList.add(v6);
			buildList.add(v7);
			
			costDAG.setVa(buildList);
		}
		
		return costDAG;		
	}
	
	public static Graph buildDirectionalLoopedGraph() {
		if (directionalLoopedGraph == null) {
			//构建一个有序有环图
			/*
			 *    1 -> 2   -> 3  -> 4
			 *         ^            |
			 *         |____________|
			 */
			directionalLoopedGraph = new Graph();
			Vertex bv1 = new Vertex("bv1");
			Vertex bv2 = new Vertex("bv2");
			Vertex bv3 = new Vertex("bv3");
			Vertex bv4 = new Vertex("bv4");
			
			bv1.addEdge(new Node("bv2", bv2));  // 当前bv1顶点指向的node节点bv2
			bv2.increaseInDegree();
			bv2.addEdge(new Node("bv3", bv3));
			bv3.increaseInDegree();
			bv3.addEdge(new Node("bv4", bv4));
			bv4.increaseInDegree();
			bv4.addEdge(new Node("bv2", bv2));
			bv2.increaseInDegree();
			
			ArrayList<Vertex> bbList = new ArrayList<Vertex>();
			bbList.add(bv1);
			bbList.add(bv2);
			bbList.add(bv3);
			bbList.add(bv4);
			directionalLoopedGraph.setVa(bbList);			
		}
		return directionalLoopedGraph;
	}
	
	public static void main(String[] args) {		
		
		//打印
		ArrayList<Vertex> alv = Graph.buildCostDAG().getVa();
		for (int i =0; i<alv.size(); i++) {
			alv.get(i).trackLink();
		}
		
		
	}
}