package util;

import java.util.ArrayList;

import algorithm.BSTNode;
import algorithm.Graph;
import algorithm.Vertex;

public class Print {
	private StringBuffer sb = new StringBuffer();
	
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
	
	public void midOrderBST (BSTNode client, int depth) {		
		//中序遍历也叫做中根遍历，可记做左根右		
		if (client.getLeft() != null) {
			midOrderBST(client.getLeft(),depth+1);
		}		
		
		for (int i = 0; i < depth; i++) {
			sb.append("   ");
		}
		
		sb.append(client.getKey()).append(":").append(client.getValue());
		System.out.println(sb.toString());
		sb.delete(0, sb.length());
		
		if (client.getRight() != null) {
			midOrderBST(client.getRight(),depth+1);
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
	
	//打印linkedlist
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
	
	//打印图topo排序结果
	public void DirectionalGraphTopo(Graph a) {
		ArrayList<Vertex> av = a.getVa();
		StringBuffer sb = new StringBuffer();
		
		for (int i =0;i<av.size();i++) {
			sb.append(av.get(i).getName()).append(":").append(av.get(i).getTopoNum()).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	//打印图topo最短路径
	public void DirectionalGraphShortest(Graph a) {
		ArrayList<Vertex> av = a.getVa();
		StringBuffer sb = new StringBuffer();
		
		for (int i =0;i<av.size();i++) {
			sb.append(av.get(i).getName()).append(":").append(" dist:").append(av.get(i).getDistance())
			          .append(" path:").append(av.get(i).getPathVertex().getName())
			          .append("\n");
		}
		System.out.println(sb.toString());
	}
}