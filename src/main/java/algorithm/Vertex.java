package algorithm;

public class Vertex {
	Node start;
	String name;
	//入度说明有几条边指向这个节点
	int inDegree;

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
	
	public synchronized void increaseInDegree() {
		inDegree++;
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