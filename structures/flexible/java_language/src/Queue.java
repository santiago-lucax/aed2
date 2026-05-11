// classe for create nodes
class Node {
    // atributes
    public int element;
    public Node next;

    // constructor without parameters
    public Node() {
        this(0);
    }

    // constructor with parameters
    public Node(int x) {
        this.element = x;
        this.next = null;
    }
}

public class Queue {
    // atributes
    private Node first;
    private Node last;

    // constructor for queue with a head node
    public Queue() {
        Node head = new Node(); // head node for remove one if
        first = head;
        last = first;
    }

    // method for create a new node on queue structure
    public void enqueue(int x) {
        Node temp = new Node(x);
        last.next = temp;
        last = last.next;
    }

    // function for remove a node on queue structure
    public int dequeue() {
        int x = -1;
        if (first != last) {
            Node temp = first;
            first = first.next;
            x = first.element; 
            temp.next = null;
            temp = null;
        }

        return x;
    }

    // method for show elements in queue
    public void print() {
        if (first != last) {
            Node cursor = first.next;
            while (cursor != null) {
                System.out.println(cursor.element);
                cursor = cursor.next;
            }
        }
    }

    // method for tests
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(3);
        queue.enqueue(7);
        queue.enqueue(13);
        queue.dequeue();
        queue.print();
    }
}