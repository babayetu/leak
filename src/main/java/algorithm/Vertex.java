package algorithm;

public class Vertex {
	private static Vertex VertexNull = new Vertex("null");
	
	Node start;
	String name;
	
	// Topo排序使用
	//入度说明有几条边指向这个节点
	int inDegree;
	// topo 号码
	int topoNum;
	
	//无权最短路径使用
	//到开始顶点s的距离
	int distance;
	//上一个邻接的顶点
	Vertex pathVertex;

	//有向有权图，最短路径Dijkstra
	boolean known;
	//到某个顶点的最短路径
	int dist;
	//上一个最短顶点记录
	Vertex dijkPath;
	
	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public Vertex getDijkPath() {
		return dijkPath;
	}

	public void setDijkPath(Vertex dijkPath) {
		this.dijkPath = dijkPath;
	}

	protected Vertex(String name) {
		this.name = name;
		inDegree = 0;
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getInDegree() {
		return inDegree;
	}

	public synchronized void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}	
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Vertex getPathVertex() {
		if (pathVertex != null) {
			return pathVertex;
		} else {
			return VertexNull;
		}
		
	}

	public void setPathVertex(Vertex pathVertex) {
		this.pathVertex = pathVertex;
	}

	public synchronized void increaseInDegree() {
		inDegree++;
	}

	public synchronized void decreaseInDegree() {
		inDegree--;
	}
	
	public int getTopoNum() {
		return topoNum;
	}

	public void setTopoNum(int topoNum) {
		this.topoNum = topoNum;
	}

	public Vertex addEdge(Node a) {
		if (a!=null) {
			a.setNext(start);
			start = a;
		}
		return this;
	}
	
	public void trackLink() {
		Node cursor = start;
		StringBuffer sb = new StringBuffer();
		sb.append("Start from:").append(this.getName()).append(" -> ");
		while (cursor != null) {
			sb.append(cursor.getName()).append(": ").append(cursor.getEdgeCost()).append(" -> ");
			cursor = cursor.getNext();
		}
		sb.append("\n");
		System.out.println(sb.toString());		
	}
}