package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public boolean containsNode(String s){
	  
	  BST_Node current = this;
	  while(current != null) {
		  if(current.getData().compareTo(s) == 0) {
			  return true;
		  } else if(current.getData().compareTo(s) > 0) {
			  current = current.left;
		  } else {
			  current = current.right;
		  }
	  }
	  
	  return false; 
	  }
  
  public boolean insertNode(String s){ 
	  
	  BST_Node current = this;
	  if(current.getData().compareTo(s) ==0) {
		  return false;
	  } else if(current.getData().compareTo(s) > 0) {
		  if(current.left == null) {
			  current.left = new BST_Node(s);
			  return true;
		  } else {
			  return current.left.insertNode(s);
		  }
	  } else {
		  if(current.right == null) {
			  current.right = new BST_Node(s);
			  return true;
		  } else {
			  return current.right.insertNode(s);
		  }
	  }
  }
  
  
  public boolean removeNode(String s){ 
	  
	  BST_Node current = this;
	  BST_Node parent = this;
	  boolean isChildLeft = false;
	  
	  //finding the node that needs to be removed and its parent
	  while(current.getData() != s) {
		  parent = current;
		  
		  if(current.getData().compareTo(s) > 0) {
			  isChildLeft = true;
			  current = current.getLeft();
		  } else {
			  current = current.getRight();
		  }
		  
		  if(current == null) {
			  return false;
		  }
	  }
	  
	  //removing a leaf
	  if(current.left == null && current.right == null) {
		  if(isChildLeft) {
			  parent.left = null;
		  }else {
			  parent.right = null;
		  }
		  return true;
	  }
	  
	  //removing a node with left child
	  if(current.left != null && current.right == null) {
		  if(isChildLeft) {
			  parent.left = current.getLeft();
		  } else {
			  parent.right = current.getLeft();
		  }
		  return true;
	  }
	  
	  //removing a node with right child
	  if(current.left == null && current.right != null) {
		  if(isChildLeft) {
			  parent.left = current.getRight();
		  } else {
			  parent.right = current.getRight();
		  }
		  return true;
	  }
	  
	  //removing a node with two childs
	  if(current.left != null && current.right != null) {
		  String minString = current.right.findMin().getData();
		  this.removeNode(minString);
		  current.data = minString;
		  return true;
	  }
	  
	  return false;
  }
  
  public BST_Node findMin(){ 
	  
	  BST_Node current = this;
	  while(current.getLeft() != null) {
		  current = current.getLeft();
	  }
	  return current; 
	  
  }
  public BST_Node findMax(){
	  
	  BST_Node current = this;
	  while(current.getRight() != null) {
		  current = current.getRight();
	  }
	  return current;
	  }
  
  int ldepth = 0;
  int rdepth = 0;
  public int getHeight(BST_Node node){ 
	    
	  if(node == null) {
		  return -1;
	  }
	  int ldepth = getHeight(node.left);
	  int rdepth = getHeight(node.right);
	  
	  int depth = Math.max(ldepth, rdepth);
	  return depth + 1; 
	  }
  

  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}
