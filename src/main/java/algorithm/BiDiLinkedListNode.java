package algorithm;

public class BiDiLinkedListNode {
	private BiDiLinkedListNode prev;
	private BiDiLinkedListNode next;
	private int value;
	private String name;
	
	public BiDiLinkedListNode getPrev() {
		return prev;
	}
	public void setPrev(BiDiLinkedListNode prev) {
		this.prev = prev;
	}
	public BiDiLinkedListNode getNext() {
		return next;
	}
	public void setNext(BiDiLinkedListNode next) {
		this.next = next;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BiDiLinkedListNode(String name, int value) {
		this.value = value;
		this.name = name;
	}	
}