package algorithm;

/**
 * 有序双向链表
 * 按照从小到大排序
 * 合并sorted list
 * @author jingjliu
 *
 */
public class BiDiLinkedList {
	private BiDiLinkedListNode head;
	
	public BiDiLinkedListNode getHead() {
		return head;
	}

	public void setHead(BiDiLinkedListNode head) {
		this.head = head;
	}

	public void add2List(BiDiLinkedListNode newNode) {
		if (head == null) {
			head = newNode;
			head.setPrev(null);
			head.setNext(null);
			return;
		}
		
		BiDiLinkedListNode cursor = head;
		
		//从小到大扫描
		while (cursor != null) {
			if (cursor.getValue() > newNode.getValue()) {
				//找到了比newNode大的，插到他前面
				BiDiLinkedListNode cur_prev = cursor.getPrev();
				if (cur_prev == null) {
					//插在队列的第一个
					newNode.setNext(cursor);
					newNode.setPrev(null);
					cursor.setPrev(newNode);
					head = newNode;
				} else {
					cur_prev.setNext(newNode);
					cursor.setPrev(newNode);
					newNode.setNext(cursor);
					newNode.setPrev(cur_prev);
				}
				break;
			}
			cursor = cursor.getNext();
		}
	}
	
	public static void print(BiDiLinkedListNode aHead) {
		StringBuffer sb = new StringBuffer();
		BiDiLinkedListNode cursor = aHead;
		BiDiLinkedListNode tail = aHead; // 从尾巴再遍历一遍
		
		sb.append(cursor.getName()).append(":").append(cursor.getValue());
		cursor = cursor.getNext();
		
		while (cursor!=null) {
			sb.append("->");
			sb.append(cursor.getName()).append(":").append(cursor.getValue());
			if (cursor.getNext() == null) {
				tail = cursor;
			}
			cursor = cursor.getNext();			
		}
		System.out.println(sb.toString());
		sb.delete(0, sb.length());
		
		cursor = tail;
		sb.append(cursor.getName()).append(":").append(cursor.getValue());
		cursor = cursor.getPrev();
		while (cursor!=null) {
			sb.append("->");
			sb.append(cursor.getName()).append(":").append(cursor.getValue());
			cursor = cursor.getPrev();			
		}
		System.out.println(sb.toString());
	}
	
	public static BiDiLinkedListNode mergeSortedList(BiDiLinkedListNode h1, BiDiLinkedListNode h2) {
		if (h1 == null) {
			return h2;
		}
		if (h2 == null) {
			return h1;
		}
		
		//两个有序链表的游标
		BiDiLinkedListNode c1 = h1;  //下一个待处理的节点
		BiDiLinkedListNode c2 = h2;
		BiDiLinkedListNode head =null;
		
		if (h1.getValue() < h2.getValue()){
			head = h1;
			c1 = h1.getNext();
		} else {
			head = h2;
			c2 = h2.getNext();			
		}

		BiDiLinkedListNode tail = head;   // 最终队列已完成排序部分的最后一个节点
		
		while (c1!=null && c2!=null) {
			if (c1 == c2) {
				//两个链表已经合二为一了，直接退出，排序合并完成
				break;				
			}
			
			if (c1.getValue() < c2.getValue()) {
				//下一个把c1队列的节点接入
				tail.setNext(c1);
				c1.setPrev(tail);
				tail = c1;
				c1 = c1.getNext();
			} else {
				//下一个把c1队列的节点接入
				tail.setNext(c2);
				c2.setPrev(tail);
				tail = c2;
				c2 = c2.getNext();
			}			
		}
		
		return head;
	}
	
	public static void main(String[] args) {
		BiDiLinkedList bll = new BiDiLinkedList();
		bll.add2List(new BiDiLinkedListNode("l1n1",10));
		bll.add2List(new BiDiLinkedListNode("l1n2",3));
		bll.add2List(new BiDiLinkedListNode("l1n3",6));
		bll.add2List(new BiDiLinkedListNode("l1n4",2));
		BiDiLinkedList.print(bll.getHead());
		
		BiDiLinkedList bll2 = new BiDiLinkedList();
		bll2.add2List(new BiDiLinkedListNode("l2n1",8));
		bll2.add2List(new BiDiLinkedListNode("l2n2",5));
		bll2.add2List(new BiDiLinkedListNode("l2n3",7));
		bll2.add2List(new BiDiLinkedListNode("l2n4",1));
		BiDiLinkedList.print(bll2.getHead());
		
		BiDiLinkedListNode mergeList = BiDiLinkedList.mergeSortedList(bll.getHead(),bll2.getHead());
		BiDiLinkedList.print(mergeList);
	}
}