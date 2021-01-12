public class MyLinkedList {
    private int size;
    private Node head, tail;

    public MyLinkedList() {
        size = 0;
    }

    // Returns size of linked list
    public int size() {
        return size;
    }

    // Appends a value at the tail and returns true if successful
    public boolean add(String value) {
        Node newNode = new Node(value);
        if (size == 0) {
            head = newNode;
            tail = head;
        } else {
            link(tail, newNode);
            tail = newNode;
        }

        size++;
        return true;
    }

    // Adds a value at an index and returns true if successful
    // Throws exception if index isnt in range
    // Calls add(value) if index is at the tail
    // Replaces head if index is 0
    public void add(int index, String value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            add(value);
        } else if (index == 0) {
            Node newNode = new Node(value);
            link(newNode, head);
            head = newNode;
            size++;
        } else {
            Node curr = getNode(index);
            Node currPrev = curr.getPrev();
            Node newNode = new Node(value);
            link(currPrev, newNode);
            link(newNode, curr);
            size++;
        }
    }

    // Removes a ndoe at an index and returns value that got removed
    // Throws exception of index isnt in range
    // Clears array if size is 1
    // Switches head if index is 0
    // Switches tail if index is at the tail (size - 1)
    public String remove(int index) {
        String result;

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (size == 1) {
            result = head.getData();
            head = null;
            tail = null;
        } else if (index == 0) {
            result = head.getData();
            Node newHead = head.getNext();

            unlink(head, newHead);
            head = newHead;
        } else if (index == size - 1) {
            result = tail.getData();
            Node newTail = tail.getPrev();

            unlink(newTail, tail);
            tail = newTail;
        } else {
            Node oldNode = getNode(index);
            result = oldNode.getData();
            Node prev = oldNode.getPrev();
            Node next = oldNode.getNext();

            unlink(prev, oldNode);
            unlink(oldNode, next);
            link(prev, next);
        }

        size--;
        return result;
    }

    // Returns the value of a node at an index
    // Throws exception of index isnt in range
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return getNode(index).getData();
    }

    // Sets the data of a node at an index to a new value
    // Also returns the value that got replaced
    public String set(int index, String value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node currNode = getNode(index);
        String result = currNode.getData();
        currNode.setData(value);
        return result;
    }

    // Appends another linked list onto this one if the size of the other is > 0
    // If this list is empty, this list is set to the other
    // Increases size and clears the other list too
    public void extend(MyLinkedList other) {
        if (other.size > 0) {
            if (size == 0) {
                head = other.head;
                tail = other.tail;
            } else {
                link(tail, other.head);
                if (other.tail != null)
                    tail = other.tail;
            }

            size += other.size;
            other.clear();
        }
    }

    // Clears the list by setting head and tail to null, thus every value that is
    // connected by next or prev is no longer referenced and gets garbage collected
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    // Returns the linked list in [ 1, 2, 3, 4 ] format
    public String toString() {
        String result = "[";
        Node curr = head;
        for (int i = 0; i < size; i++) {
            result += curr.getData();
            if (i < size - 1) {
                result += ", ";
            }

            curr = curr.getNext();
        }

        return result + "]";
    }

    // Returns the linked list in [ 4, 3, 2, 1 ] reversed format
    public String toStringReversed() {
        String result = "[";
        Node curr = tail;
        for (int i = 0; i < size; ++i) {
            result += curr.getData();
            if (i < size - 1) {
                result += ", ";
            }

            curr = curr.getPrev();
        }

        return result + "]";
    }

    // ======================== //
    // === HELPER FUNCTIONS === //
    // ========================= //

    // Returns the node (not value) at and index in the list
    private Node getNode(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        return curr;
    }

    // Links 2 nodes
    private void link(Node prev, Node next) {
        prev.setNext(next);
        next.setPrev(prev);
    }

    // Unlinks 2 nodes
    private void unlink(Node prev, Node next) {
        if (prev.getNext() == next && next.getPrev() == prev) {
            prev.setNext(null);
            next.setPrev(null);
        }
    }
}