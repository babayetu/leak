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
		while (cursor != null) {
			sb.append(cursor.getName()).append(" -> ");
			cursor = cursor.getNext();
		}
		sb.append("\n");
		System.out.println(sb.toString());		
	}
}