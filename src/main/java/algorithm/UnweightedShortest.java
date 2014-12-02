package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

import util.Print;

public class UnweightedShortest {
	private Graph graph;	
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public void find(int s) {  // s表示图邻接表的第几个顶点
		//LinkedList既可以是Stack用push和pop，又可以是queue用put和get
		LinkedList<Vertex> q = new LinkedList<Vertex>();
		
		if (graph == null) {
			return;			
		}
		
		ArrayList<Vertex> alv = graph.getVa(); 
		for (int i=0;i<alv.size();i++) {
			alv.get(i).setDistance(Integer.MAX_VALUE);  // 设置每个顶点的距离为无穷大
		}
		
		alv.get(s).setDistance(0); // 开始顶点s到开始顶点s最短距离为
		q.add(alv.get(s));
		
		while (!q.isEmpty()) {
			Vertex v = q.remove();
			int dist = v.getDistance();
			Node cursor = v.getStart();
			
			while (cursor!=null) {
				// 距离为无穷大，说明这个顶点还没有处理过
				if (cursor.getV().getDistance() == Integer.MAX_VALUE) {
					cursor.getV().setDistance(dist + 1);
					cursor.getV().setPathVertex(v);  // 记录路径
					q.add(cursor.getV());
				}
				
				cursor = cursor.getNext();				
			}
		}
	}
	
	public static void main(String[] args) {
		Graph a = Graph.buildDirectionalNoLoopGraph();
		UnweightedShortest us = new UnweightedShortest();
		us.setGraph(a);
		us.find(2);
		
		Print p = new Print();
		p.DirectionalGraphShortest(a);
	}
}