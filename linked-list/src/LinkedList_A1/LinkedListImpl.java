package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node sentinel;//this will be the entry point to your linked list (the head)
  Node currentNode;
  int numElts;
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel=new Node(0); //Note that the sentinel's data is not a true part of your data set!
    numElts = 0;
  }
  
  //implement all methods in interface, and include the getsentinel method we made for testing purposes. Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

public boolean insert(double elt, int index) {
	if(index < 0 || index > this.size()) {
		//throw new IllegalArgumentException("invalid size");
		return false;
	}
	Node n = new Node(elt);
	Node nNext;
	Node nPrev;
	Node zeroNode;
	
	if(index == 0) {
		if(this.size() > 0) {
			zeroNode = getIndexedNode(0);
			n.next = zeroNode;
			//n.prev = sentinel;
			//sentinel.next = n;
			zeroNode.prev = n;
			numElts++;
		}else if(this.size() == 0){
			n.next = sentinel;
			sentinel.prev = n;
			numElts++;
		}
		sentinel.next = n;
		n.prev = sentinel;
	} else if(index != 0) {
		
		nPrev = getIndexedNode(index - 1);
		
		if(index < this.size()) {
			nNext = getIndexedNode(index);
			nNext.prev = n;
			n.next = nNext;
			nPrev.next = n;
			n.prev = nPrev;
			numElts++;
			
			//n = nPrev.next;
			//n = nNext.prev;
			//nNext = n.next;
			//nPrev = n.prev;
			
		} else if(index == this.size()) {
			nPrev.next = n;
			n.prev = nPrev;
			sentinel.prev = n;
			n.next = sentinel;
			numElts++;
			
			//n = nPrev.next;
			//sentinel = n.next;
			//n = sentinel.prev;
			//nPrev = n.prev;
		}
	}
	
	//numElts++;
	
	return true;
}

public boolean remove(int index) {
	if(index < 0 || index >= this.size()) {
		return false;
	}
	
	if(index == 0) {
		
		if(this.size()==1) {
			sentinel.next = null;
			sentinel.prev = null;
			numElts--;
		} else {
			Node current = sentinel.next.next;
			sentinel.next = current;
			current.prev = sentinel;
			numElts--;
		}
	}else if(index == this.size()-1) {
		//getIndexedNode(index - 1).next = sentinel;
		int i = 0;
		Node current = sentinel.next;
		while(i < index - 1) {
			i++;
			current = current.next;
		}
		current.next = sentinel;
		sentinel.prev = current;
		numElts--;
	}else {
		Node nPrev = getIndexedNode(index - 1);
		Node nNext = getIndexedNode(index + 1);
		
		nPrev.next = nNext;
		nNext.prev = nPrev;
		numElts--;
	}
	
	//numElts--;
	return true;
}

public double get(int index) {
	if(index < 0 || index >= this.size()) {
		return Double.NaN;
	}
	
	return getIndexedNode(index).getData();
	
	
}

private Node getIndexedNode(int index) {
	if(index < 0 || index >= this.size()) {
		throw new IllegalArgumentException("Index is out of bounds");
	}
	
	currentNode = sentinel.next;
	
	int i = 0;
	while(i < index) {
		if(currentNode.next != null) {
			currentNode = currentNode.next;
		}
	i++;
	}
	
	return currentNode;
}

public int size() {
	return numElts;
}

public boolean isEmpty() {
	boolean empty;
	if(this.size() == 0) {
		empty = true;
	}else {
		empty = false;
	}
	
	return empty;
}

public void clear() {
	sentinel.next = null;
	sentinel.prev = null;
	numElts = 0;
}
}
