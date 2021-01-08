public class MyLinkedList {
    private int size;
    private Node head, tail;

    public MyLinkedList() {
        size = 0;
    }

    public int size() {
        return size;
    }

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

    public boolean add(int index, String value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index + " out of bounds in list size " + size);
        } else if (index == size) {
            return add(value);
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
            link(newNode, head);
        }

        return true;
    }

    public String get(int index) {
        return null;
    }

    public String set(int index, String value) {
        return null;
    }

    public String toString() {
        String result = "[ ";
        Node curr = head;
        for (int i = 0; i < size; i++) {
            result += curr.getData();
            if (i < size - 1) {
                result += ", ";
            }

            curr = curr.getNext();
        }

        return result + " ]";
    }

    private void link(Node prev, Node next) {
        prev.setNext(next);
        next.setPrev(prev);
    }

    private void unlink(Node prev, Node next) {
        if (prev.getNext() == next && next.getPrev() == prev) {
            prev.setNext(null);
            next.setPrev(null);
        }
    }

    private Node getNode(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr;
    }
}