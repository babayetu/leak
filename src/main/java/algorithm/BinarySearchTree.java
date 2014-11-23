package algorithm;

import util.Print;

public class BinarySearchTree {
	private BSTNode root = null;
	
	public BSTNode add2Tree(BSTNode point, int key, String value) {
		if (point == null) {
			point = new BSTNode(key, value);
			return point;
		} else {
		
		if (point.getKey() > key) {
			//在point的左边
			if (point.getLeft() != null) {
				add2Tree(point.getLeft(), key, value);
			} else {
				point.setLeft(new BSTNode(key, value));
			}
			return point;
		} else if (point.getKey() < key) {
			//在point的右边
			if (point.getRight() != null) {
				add2Tree(point.getRight(), key, value);
			} else {
				point.setRight(new BSTNode(key, value));
			}
			return point;
		} else {
			System.out.println(key + " "+value+ " repeated!");
			return point;
		}	
	  }
	}	
	
	public BSTNode getRoot() {
		return root;
	}

	public void setRoot(BSTNode root) {
		this.root = root;
	}

//	算法思路：
//	1. 树根节点，分左右子树。先将“当前节点左子树范围内最右节点”leftR找出来，再将“右子树范围内最左节点”rightL找出来。
//	（这两步放在一开始，是因为此时左右子树内的关系还没改变，先取出来，时间消耗O(lgN)。只是查找到节点，空间上只用到一个索引，
//	不会产生新的内存分配）
//	2. 若左子树为叶子节点，则直接设置其右向索引指向其父节点，左向递归结束；否则，将此节点作为根节点，递归调用第1步。
//	3. 若右子树为叶子节点，则直接设置其左向索引指向其父节点，右向递归结束；否则，将此节点作为根节点，递归调用第1步。
//	4. 设置根节点的左向节点为leftR，leftR的右向节点为根节点（其左向节点，在2、3两步的递归过程中，已经赋值）；
//	设置根节点的右向节点为rightL，rightL的左向节点为根节点（其右向节点，在2、3两步的递归过程中，已经赋值）。
	
	public void convertToLinkedList(BSTNode r) {
		BSTNode leftSon = r.getLeft();
		BSTNode rightSon = r.getRight();
		
		BSTNode leftR = findLeftMostRight(leftSon);
		BSTNode rightL = findRightMostLeft(rightSon);
		
		if (leftSon != null) {
		if (isLeaf(leftSon)) {
			leftSon.setRight(r);
			return;
		} else {
			convertToLinkedList(leftSon);
		}
		}
		
		if (rightSon != null) {
		if (isLeaf(rightSon)) {
			rightSon.setLeft(r);
			return;
		} else {
			convertToLinkedList(rightSon);
		}
		}
		
		if (leftR != null) {
			leftR.setRight(r);
			r.setLeft(leftR);
		}
		if (rightL != null) {
			rightL.setLeft(r);
			r.setRight(rightL);
		}		
	}
	
	public BSTNode findLeftMostRight(BSTNode b) {
		if (b == null) {
			return null;
		}
		if (b.getRight() != null) {
			//还有右子树
			return findLeftMostRight(b.getRight());
		} else {
			return b;
		}
	}

	public BSTNode findRightMostLeft(BSTNode b) {
		if (b == null) {
			return null;
		}
		if (b.getLeft() != null) {
			//还有左子树
			return findRightMostLeft(b.getLeft());
		} else {
			return b;
		}
	}
	
	public boolean isLeaf(BSTNode leaf) {
		if (leaf.getLeft() == null && leaf.getRight() == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree oneBST = new BinarySearchTree();
		
		oneBST.setRoot(oneBST.add2Tree(oneBST.getRoot(),3,"c"));
		oneBST.setRoot(oneBST.add2Tree(oneBST.getRoot(),1,"a"));	
		oneBST.setRoot(oneBST.add2Tree(oneBST.getRoot(),2,"b"));			
		oneBST.setRoot(oneBST.add2Tree(oneBST.getRoot(),4,"d"));	
		oneBST.setRoot(oneBST.add2Tree(oneBST.getRoot(),5,"e"));	
		oneBST.setRoot(oneBST.add2Tree(oneBST.getRoot(),6,"f"));	
		
		//记录下树最左边叶子节点的位置
		BSTNode mr = oneBST.getRoot();
		BSTNode mostLeft = mr;
		while (mostLeft.getLeft() != null) {
			mostLeft = mostLeft.getLeft();
		}
		BSTNode mostRight = mr;
		while (mostRight.getRight() != null) {
			mostRight = mostRight.getRight();
		}
		oneBST.convertToLinkedList(oneBST.getRoot());
		//绝对不能从roor开始打印，这时候root是中间结点了，要从最左边打印		
		new Print().linkedListFromLeft(mostLeft);
		new Print().linkedListFromRight(mostRight);
		
//		new Print().preOrderBST(oneBST.getRoot());
//		System.out.println();
//		new Print().midOrderBST(oneBST.getRoot());
//		System.out.println();
//		new Print().postOrderBST(oneBST.getRoot());
		
		
	}
}