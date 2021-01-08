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
}