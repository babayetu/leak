package datastructure;

public class UnixTreeNode extends TreeNode {
	private int fileSize;
	
	public UnixTreeNode(Object element, int size, TreeNode firstChild,TreeNode nextSubling) {		
		super(element, firstChild, nextSubling);
		this.fileSize = size;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	
}