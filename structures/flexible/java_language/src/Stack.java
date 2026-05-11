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

// class for stack structure
public class Stack {
    // atribute
    private Node top;
    // constructor
    public Stack() {
        top = null;
    }

    // method for create a new node
    public void push(int x) {
        Node temp = new Node(x);
        temp.next = this.top;
        this.top = temp;
        temp = null;
    }

    // function for remove a top node
    public int pop() {
        int x = -1;
        if (top != null) {
            x = top.element;
            Node temp = this.top;
            this.top = this.top.next;
            temp.next = null;
            temp = null;
        }

        return x;
    }

    // method for show elements in stack
    public void print() {
        if (top != null) {
            Node cursor = top;
            while (cursor != null) {
                System.out.println(cursor.element);
                cursor = cursor.next;
            }
        }
    }

    // method for test
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(5);
        stack.push(13);
        stack.push(7);
        stack.push(3);
        stack.print();
    }
}