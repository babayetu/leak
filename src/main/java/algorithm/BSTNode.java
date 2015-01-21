package algorithm;

public class BSTNode {
	private int key;
	private String value;
	private BSTNode left;
	private BSTNode right;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public BSTNode getLeft() {
		return left;
	}
	public void setLeft(BSTNode left) {
		this.left = left;
	}
	public BSTNode getRight() {
		return right;
	}
	public void setRight(BSTNode right) {
		this.right = right;
	}
	public BSTNode(int key, String value) {
		this.key = key;
		this.value = value;
	}
	public BSTNode(String value) {
		this.key = -1;
		this.value = value;
	}
	
}