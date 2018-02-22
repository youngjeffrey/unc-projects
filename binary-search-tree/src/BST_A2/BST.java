package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

public boolean insert(String s) {
	
	BST_Node newNode = new BST_Node(s);
	
	if(this.contains(s)) {
		return false;
	}
	
	if(root == null) {
		root = newNode;
		size++;
		return true;
	}
	
	size++;
	return root.insertNode(s);
}


public boolean remove(String s) {
	if (size == 0) {
		return false;
	}

	if (root.getData().equals(s)) {
		size--;
		if (root.getLeft() == null && root.getRight() == null) {
			root = null;
			return true;
		} else if (root.getRight() == null && root.getLeft() != null) {
			root = root.left;
			return true;
		} else if (root.getLeft() == null && root.getRight() != null) {	
			root = root.right;
			return true;
		} else {	
			String minString = root.right.findMin().data;
			root.removeNode(minString);
			root.data = minString;
			return true;
		}
	}
	size--; 	    
	return root.removeNode(s);
}

public String findMin() {
	return root.findMin().getData();
}

public String findMax() {
	return root.findMax().getData();
}

public boolean empty() {
	if(size==0) {
		return true;
	} else {
		return false;
	}
}

public boolean contains(String s) {
	if(size == 0) {
		return false;
	}
	
	return root.containsNode(s);
}

public int size() {
	return size;
}

public int height() {
	if(empty()) {
		return -1;
	}
	
	return root.getHeight(root);
}

}