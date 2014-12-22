package datastructure;

public class UnixDirectoryTree {
	private static UnixDirectoryTree udt = null;
	private UnixTreeNode root;
	private StringBuffer sb = new StringBuffer();

	public static UnixDirectoryTree buildUnixDirectoryTree() {
/*		usr
		   mark
		      book
		      course
		      junk
		   alex
		      junk
		   bill
*/
		if (udt == null) {			
			UnixTreeNode usr = new UnixTreeNode("usr",1, null,null);
			UnixTreeNode mark = new UnixTreeNode("mark",1,null,null);
			UnixTreeNode alex = new UnixTreeNode("alex",1,null,null);
			UnixTreeNode bill = new UnixTreeNode("bill",1,null,null);
			
			UnixTreeNode book = new UnixTreeNode("book",3,null,null);
			UnixTreeNode course = new UnixTreeNode("course",4,null,null);
			UnixTreeNode junk1 = new UnixTreeNode("junk",5,null,null);
			UnixTreeNode junk2 = new UnixTreeNode("junk",8,null,null);
			
			usr.setFirstChild(mark);
			mark.setNextSubling(alex);
			alex.setNextSubling(bill);
			
			mark.setFirstChild(book);
			book.setNextSubling(course);
			course.setNextSubling(junk1);
			
			alex.setFirstChild(junk2);
			
			udt = new UnixDirectoryTree();
			udt.setRoot(usr);
			
			return udt;
		} else {
			return udt;
		}
	}

	public UnixTreeNode getRoot() {
		return root;
	}

	public void setRoot(UnixTreeNode root) {
		this.root = root;
	}
	
	//先序遍历 preorder，先打印父节点，再处理他的子节点。
	// 适用于打印目录结构
	private void listAll(UnixTreeNode parent, int depth) {
		//打印父节点的名字
		for (int i = 0; i < depth; i++) {
			sb.append("   ");
		}
		
		sb.append(parent.getElement()).append("(").append(size(parent)).append(")");
		System.out.println(sb.toString());
		sb.delete(0, sb.length());
		
		if (isDirectory(parent)) {
			UnixTreeNode cursor = (UnixTreeNode)parent.getFirstChild();
			while (cursor !=null) {
				listAll(cursor, depth + 1);
				cursor = (UnixTreeNode)cursor.getNextSubling();
			}
		}
	}
	
	//后序遍历，postorder，适用于计算每个目录的大小，先计算目录里面每个文件的大小，再加上父节点的大小
	public int size(UnixTreeNode tn) {
		if (tn == null) {
			return 0;
		}
		
		int totalSize = tn.getFileSize();
		UnixTreeNode cursor = (UnixTreeNode)tn.getFirstChild();
		while (cursor != null) {			
			totalSize += size(cursor);	
			cursor = (UnixTreeNode)cursor.getNextSubling();
		}
		
		return totalSize;
	}
	
	public void listAll() {
		if (root!=null) {
			listAll(root, 0);
		} else {
			System.out.println("root is null");
		}		
	}
	
	private boolean isDirectory(TreeNode tn) {
		if (tn ==null) {
			return false;
		}
		TreeNode firstChild = (UnixTreeNode)tn.getFirstChild();
		if (firstChild != null) {
			return true;
		} else {
			return false;
		}
	}
	

	
	public static void main(String[] args) {
		UnixDirectoryTree aTree = UnixDirectoryTree.buildUnixDirectoryTree();
		aTree.listAll();
		System.out.println(aTree.size(aTree.getRoot()));
	}
}