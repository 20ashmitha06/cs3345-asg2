public class DoublyLinkedList<T> implements List<T>, Iterable<T> {
	private Node head, tail;
	private int numberOfElements;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		numberOfElements = 0;
	}
	
	@Override
	public void addLast(T item) {
		// TODO 
		Node newNode = new Node(item);

		// Case 1: list is empty

		if (tail == null) {
        head = newNode;
        tail = newNode;
    } 
    // Case 2: list is not empty

    else {

		//old tail points forward

        tail.next = newNode;     
		

		//new node points back

        newNode.previous = tail; 

		//update tail

        tail = newNode;          
    }

    numberOfElements++; 
	}

	@Override
	public void addFirst(T item) {
		// TODO 
		
			Node newNode = new Node(item);

			//first case: list is empty

			if(head == null){
				head = newNode;
				tail = newNode;
			}

			//second case: list is not empty

			else{
				newNode.next = head;
				head.previous = newNode;
				head = newNode;
			}
			numberOfElements++;
	}

	@Override
	public T get(int position) {
		// TODO 

		//Handling invalid cases first

		if (head == null || position < 0 || position >= numberOfElements) {
        return null;
    	}

		Node current  = head;
		int index = 0;

		while(index < position){
			current = current.next;
			index++;
		}

		//returning the data

		return current.data;

		
	}

	@Override
	public void print() {
		// TODO
		Node current = head;
		while(current != null){
			System.out.println(current.data);
			current = current.next;
		}
				
	}

	@Override
	public void printBackwards() {
		// TODO 
		if (tail == null) { 
        System.out.println("List is empty.");
        return;
    }

    Node current = tail;
    while (current != null) {
        System.out.println(current.data);
        current = current.previous;
    }
	}

	@Override
	public boolean remove(T item) {
		// TODO 
    if (head == null) {

        // Empty list

        return false;
    }

    Node current = head;

    while (current != null) {
        if (current.data.equals(item)) {

            // Removing head

            if (current == head) {
                head = current.next;
                if (head != null) {
                    head.previous = null;
                } else {

                    // List became empty

                    tail = null;
                }
            }

            // Removing tail

            else if (current == tail) {
                tail = current.previous;
                tail.next = null;
            }

            // Removing middle node

            else {
                current.previous.next = current.next;
                current.next.previous = current.previous;
            }

            numberOfElements--;

			// item removed

            return true; 
        }
        current = current.next;
    }

	// item not found

    return false; 
	}

	@Override
	public int getLength() {
		// TODO 
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
    	return numberOfElements == 0;
	}
	
	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private T data;
		private Node next, previous;

		private Node(T data) {
			this(data,null,null);
		}

		private Node (T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.previous = prev;
		}
	}

	    /** 
     * Iterator implementation for DoublyLinkedList
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements java.util.Iterator<T> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


}

