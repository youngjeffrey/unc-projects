package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

public void insert(EntryPair entry) {	
	size++;
	array[size] = entry;
	
	if(array[size].priority < array[size/2].priority) {
		this.bubbleup(size);
	}
}

public void delMin() {
	if(size==0) {
		return;
	} else if(size == 1) {
		array[1] = null;
		size--;
	} else {
		array[1] = array[size--];
		bubbledown(1);
	}

}

public EntryPair getMin() {
	if(array[1] != null) {
		return array[1];
	} else {
		return null;
	}
}

public int size() {
	return size;
}

public void build(EntryPair[] entries) {
	size = entries.length;
	
	for(int i=0; i<entries.length; i++) {
		array[i+1] = entries[i];
	}
	
	for(int j= size/2; j > 0; j--) {
		bubbledown(j);
	}
}

private void bubbleup(int index) {
	//checks if child is smaller than parent
	if(array[index].priority < array[index/2].priority) {
		EntryPair temp = array[index/2];
		array[index/2] = array[index];
		array[index] = temp;
	}
	
	if(index > 1) {
		bubbleup(index/2);
	}
}

private void bubbledown(int index) {
	int smallest = index * 2;
	
	//checks if left or right is smaller
	if(smallest+1 < size+1) {
		if(array[smallest].priority > array[smallest+1].priority) {
			smallest++;
		}
	}
	
	//checks if parent is larger than child
	if(array[index].priority > array[smallest].priority) {
		EntryPair temp = array[index];
		array[index] = array[smallest];
		array[smallest] = temp;
	}
		
	if(smallest <= size/2) {
		bubbledown(smallest);
	}	
}


}
