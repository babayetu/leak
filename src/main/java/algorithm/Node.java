package algorithm;

/**
 * Node是用来辅助找到Vertex的数据结构，flag信息记录在Vertex里面
 * @author jingjliu
 *
 */
public class Node {
	Node next;
	String name;
	Vertex v;

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	public Node(String aName, Vertex v) {
		this.next = null;
		this.name = aName;
		this.v = v;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vertex getV() {
		return v;
	}

	public void setV(Vertex v) {
		this.v = v;
	}	
}