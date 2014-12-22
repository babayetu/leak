package datastructure;

/**
 * https://oj.leetcode.com/problems/reorder-list/
 * http://blog.csdn.net/linhuanmars/article/details/21503215
 * 
 * 
 * 
 * 
Given a singly linked list L: L0→L1→…→Ln-1→Ln,

reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

 * @author jingjliu
 *
 */
public class ReorderList {
	static class ListNode {
		 int val;
		 ListNode next;		 
		 ListNode(int x) {
			val = x;
		    next = null;
		 }
		 
		 public void setNext(ListNode aNext) {
			 this.next = aNext;
		 }
		 
		 public ListNode getNext() {
			 return this.next;
		 }
	}
	
	public void reorderList(ListNode head) {
		if (head == null || head.next == null) {
            return;
        }
		
        //第一步找到单向链表的中点
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		// head1: L0→L1→…→Ln/2-1
		// head2: Ln/2-1→ Ln/2 → Ln/2+1→...→Ln
		ListNode head1 = head;
		ListNode head2 = slow.next;
		//解开两个链表，不然会循环，非常重要
		slow.next = null;
		
		//head2 逆序, pre指向第一个节点
		ListNode cur = head2;
		ListNode pre = null;
		ListNode nex = null;
		while(cur != null) {
			nex = cur.next;			
			cur.next = pre;
			pre=cur;
			cur=nex;
		}
		head2 = pre;
		
		//merge head1 和 head2一个隔一个,按顺序
		ListNode tmp = head2.next;
		ListNode result = head1;
				
		while(head1 !=null && head2 !=null) {
			tmp = head2.next;
			head2.next = head1.next;
			head1.next = head2;
			head1 = head2.next;
			head2 = tmp;
		}
    }
	
	public void printMe(ListNode head) {
		StringBuffer sb = new StringBuffer();
		while (head != null) {
			sb.append(head.val).append(" -> ");
			head = head.next;
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		ReorderList rl = new ReorderList();
		
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		
		rl.printMe(n1);
		
		rl.reorderList(n1);
		rl.printMe(n1);
	}
}