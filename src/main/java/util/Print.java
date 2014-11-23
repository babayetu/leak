package util;

import algorithm.BSTNode;

public class Print {
	public static void array (int[] client) {
		assert(client!=null);
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<client.length;i++) {
			sb.append(client[i]).append(" ");
		}
		System.out.println(sb.toString());
	}

	//根结点（N）、左子树（L）、右子树（R）
	//NLR、LNR和LRN三种即可，分别称为“先序遍历”、“中序遍历”和“后序遍历”
	public void preOrderBST (BSTNode client) {
		System.out.println(client.getKey()+ ":"+ client.getValue());
		
		if (client.getLeft() != null) {
			preOrderBST(client.getLeft());
		}		
		
		if (client.getRight() != null) {
			preOrderBST(client.getRight());
		}		
	}
	
	public void midOrderBST (BSTNode client) {
		//中序遍历也叫做中根遍历，可记做左根右		
		if (client.getLeft() != null) {
			midOrderBST(client.getLeft());
		}		
		
		System.out.println(client.getKey()+ ":"+ client.getValue());
		
		if (client.getRight() != null) {
			midOrderBST(client.getRight());
		}		
	}
	
	public void postOrderBST (BSTNode client) {
	
		if (client.getLeft() != null) {
			postOrderBST(client.getLeft());
		}		
		
		if (client.getRight() != null) {
			postOrderBST(client.getRight());
		}
		
		System.out.println(client.getKey()+ ":"+ client.getValue());
	}
	
	public void linkedListFromLeft (BSTNode client) {
		assert(client!=null);
		StringBuffer sb = new StringBuffer();

		BSTNode rightSon = client;
		while ( rightSon!= null) {			
			sb.append(rightSon.getKey()).append(":")
			  .append(rightSon.getValue()).append(" ");
			rightSon = rightSon.getRight();
		}
		System.out.println(sb.toString());		
	}
	
	public void linkedListFromRight (BSTNode client) {
		assert(client!=null);
		StringBuffer sb = new StringBuffer();

		BSTNode leftSon = client;
		while ( leftSon!= null) {			
			sb.append(leftSon.getKey()).append(":")
			  .append(leftSon.getValue()).append(" ");
			leftSon = leftSon.getLeft();
		}
		System.out.println(sb.toString());		
	}
}