package algorithm;

import java.util.ArrayList;

public class Dijkstra {
	public void findShortest(Graph g, Vertex s) {
		ArrayList<Vertex> alv = g.getVa();
		
		for (int i=0;i<alv.size();i++) {
			alv.get(i).setDist(Integer.MAX_VALUE);
			alv.get(i).setKnown(false);
		}
		
		//开始顶点到自己的距离为0
		s.setDist(0);
		
		while (true) {
			//找到unknown顶点里面距离最小的，第一个是开始顶点s
			int temp_min=Integer.MAX_VALUE;
			int find_index = -1;
			
			for (int i=0;i<alv.size();i++) {
				if (!alv.get(i).isKnown() && alv.get(i).getDist() < temp_min) {
					temp_min = alv.get(i).getDist();
					find_index = i;
				}
			}
			
			if (find_index == -1) {
				//全部都是known了
				break;
			}			
			
			Vertex v = alv.get(find_index);
			v.setKnown(true);
			
			//更新邻接的顶点vertex
			Node cursor = v.getStart();
			
			while (cursor != null) {
				if (!cursor.getV().isKnown()) {
					if (v.getDist() + cursor.getEdgeCost() < cursor.getV().getDist()) {
						//更新顶点v
						cursor.getV().setDist(v.getDist() + cursor.getEdgeCost());
						cursor.getV().setDijkPath(v);
					}
				}
				cursor = cursor.getNext();
			}
		}		
	}
	
	//到达顶点v最短的路径
	//调用前先更新从顶点s出发的图，用Dijkstra贪婪算法	
	public void shortestPath (Vertex v) {
		StringBuffer sb = new StringBuffer();
		sb.append(v.getName());
		
		while (v.getDijkPath() != null) {
			sb.append(" <- ");
			sb.append(v.getDijkPath().getName());
			v = v.getDijkPath();
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		Graph g = Graph.buildCostDAG();
		ArrayList<Vertex> alv = g.getVa();
		
		Dijkstra dijkstra = new Dijkstra();
		Vertex beginPoint = alv.get(0);
		dijkstra.findShortest(g,beginPoint);
		
		for (int i=0;i<alv.size();i++) {
			dijkstra.shortestPath(alv.get(i));
		}	
		
		for (int i=0;i<alv.size();i++) {
			System.out.println("From "+beginPoint.getName()+ " to " + alv.get(i).getName() + " shortest path is "+ alv.get(i).getDist());
		}
	}
}