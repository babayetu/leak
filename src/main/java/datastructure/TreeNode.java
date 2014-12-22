package datastructure;

/**
 * 数据结构是所有的儿子节点用一个链表储存起来
 *                    A
 *             -------
 *             |
 *             B ---->C----> D ------> E 
 *                           |         |                     
 *                           H         I----->J
 *                           
 *                           等同于
 *                           A
 *                           |
 *                    -----------------------
 *                    |      |      |       |
 *                    B      C      D       E
 *                                  |       |
 *                                  H    -------
 *                                       |     |
 *                                       I     J
 *             
 * @author jingjliu
 *
 */
public class TreeNode {
	Object element;
	TreeNode firstChild;    //该节点的第一个儿子节点
	TreeNode nextSubling;   //该节点的下一个兄弟	
	
	public TreeNode(Object element, TreeNode firstChild, TreeNode nextSubling) {
		this.element = element;
		this.firstChild = firstChild;
		this.nextSubling = nextSubling;
	}
	
	public Object getElement() {
		return element;
	}
	public void setElement(Object element) {
		this.element = element;
	}
	public TreeNode getFirstChild() {
		return firstChild;
	}
	public void setFirstChild(TreeNode firstChild) {
		this.firstChild = firstChild;
	}
	public TreeNode getNextSubling() {
		return nextSubling;
	}
	public void setNextSubling(TreeNode nextSubling) {
		this.nextSubling = nextSubling;
	}
}

