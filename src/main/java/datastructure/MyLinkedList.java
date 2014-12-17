package datastructure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
	private Node<T> head;
	private Node<T> tail;	
	private int size;
	private int modCount = 0;  //集合被修改的次数
	
	public MyLinkedList() {
		clear();
	}

	public void clear() {
		head = new Node<T>(null, null, null);
		tail = new Node<T>(null, null, null);
		head.setNext(tail);
		tail.setPrev(head);
		size = 0;
		modCount++;
	}
	
	public void add (T x) {
		add(size(),x);		
	}
	
	public void add (int idx, T x) {
		addBefore(getNode(idx),x );
	}
	
	private void addBefore(Node<T> n, T x) {
		Node<T> newNode = new Node<T>(x,null,null);
		n.getPrev().setNext(newNode);
		newNode.setPrev(n.getPrev());
		newNode.setNext(n);
		n.setPrev(newNode);
		size++;
		modCount++;
	}
	
	public T get(int idx) {
		return getNode(idx).getData();
	}
	
	public void set(int idx, T value) {
		getNode(idx).setData(value);
	}
	
	public T remove(int idx) {
		return remove(getNode(idx));
	}
	
	public T remove(Node<T> n) {
		n.getPrev().setNext(n.getNext());
		n.getNext().setPrev(n.getPrev());
		size--;
		modCount++;
		return n.getData();
	}
	
	private Node<T> getNode(int idx) {
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> temp = head;
		for (int i=0;i<=idx;i++) {
			temp = temp.getNext();
		}
		return temp;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/*
	 * 作为stack使用
	 */
	public void push(T value) {
		addBefore(tail,value);
	}
	
	// LinkedList元素从0开始
	// head <=> 0 <=> 1 <=> tail
	// size = 2
	public T pop() {
		return remove(size() - 1);
	}
	
	public T top() {
		if (size() > 0) {
			return get(size() - 1);
		} else {
			return null;		}
		
	}
	/*
	 * 作为stack使用
	 * 部分结束
	 */
	
	/*
	 * 作为queue使用
	 */
	public void enqueue(T value) {
		addBefore(tail,value);
	}
	
	// LinkedList元素从0开始
	// head <=> 0 <=> 1 <=> tail
	// size = 2
	public T dequeue() {
		return remove(0);
	}

	/*
	 * 作为queue使用
	 * 部分结束
	 */
	
	private static class Node<T> {
		private T data;
		private Node<T> prev;
		private Node<T> next;
		
		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public void setPrev(Node<T> prev) {
			this.prev = prev;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}
	}

	public Iterator<T> iterator() {		
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T> {
		private Node<T> pointer;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		public LinkedListIterator() {
			pointer = head.getNext();
		}

		public boolean hasNext() {
			if (pointer == head || pointer == tail) {
				return false;
			}
			if (pointer.getNext() == null) {
				return false;
			} else {
				return true;
			}
		}

		public T next() {
			if (expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (hasNext()) {
				Node<T> temp = pointer;
				pointer = pointer.getNext();
				okToRemove = true;
				return temp.getData();
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			if (expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			
			if (pointer == head || pointer == tail) {
				return;
			} else {
				MyLinkedList.this.remove(pointer.getPrev());
				okToRemove = false;
				expectedModCount++;
			}			
			
		}
		
	}
}