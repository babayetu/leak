package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

import util.Print;

public class TopoSort {
	public void sort(Graph a) throws CycleFoundException {
		LinkedList<Vertex> aQueue = new LinkedList<Vertex>();
		int count = 0;
		ArrayList<Vertex> vContainer =  a.getVa();
		
		for (int i=0; i<vContainer.size(); i++) {
			if (vContainer.get(i).getInDegree() == 0) {
				aQueue.push(vContainer.get(i));
			}
		}
		
		while (!aQueue.isEmpty()) {
			Vertex p = aQueue.pop();
			p.setTopoNum(count);
			count++;
			
			Node cursor = p.getStart();
			
			while (cursor!=null) {
				cursor.getV().decreaseInDegree();
				if (cursor.getV().getInDegree() == 0) {
					aQueue.push(cursor.getV());
				}
				cursor = cursor.getNext();
			}
		}
		
		if (count != vContainer.size()) {
			throw new CycleFoundException();
		}
	}
	
	//画图
	public static void main(String[] args) {
		Graph a = new Graph();
		
		//构建一个有序无环图
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
		
		TopoSort ts = new TopoSort();
		try {
			ts.sort(a);
		} catch (CycleFoundException e) {
			e.printStackTrace();
		}

		Print p = new Print();
		p.DirectionalGraphTopo(a);
		
		//构建一个有序有环图
		/*
		 *    1 -> 2   -> 3  -> 4
		 *         ^            |
		 *         |____________|
		 */
		Graph b = new Graph();
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
		b.setVa(bbList);
		
		try {
			ts.sort(b);
		} catch (CycleFoundException e) {
			e.printStackTrace();
		}
		p.DirectionalGraphTopo(b);
	}
}