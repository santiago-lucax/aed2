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

public class List {
    private int n;
    private Node first;
    private Node last;

    // constructor
    public List() {
        first = new Node(); // head node
        last = first;
    }

    // insert on last position
    public void pushBack(int x) {
        last.next = new Node(x);
        last = last.next;
        n++;
    }

    // get number of elements
    public int get() { return this.n; }

    // insert on first position
    public void pushFront(int x) {
        Node temp = first.next;
        first.next = new Node(x);
        first.next.next = temp;
        if (first == last) last = first.next;
        n++;
    }

    // insert on random position
    public void push(int x, int pos) {
        if (pos >= 0) {
            if (pos == 0) pushFront(x);
            else if (pos == n - 1) pushBack(x);
            else {
                Node i = first;
                for (int j = 0; j < pos - 1; j++, i = i.next);
                Node temp = new Node(x);
                temp.next = i.next;
                i.next = temp;
                temp = i = null;
                n++;
            }
        }
    }

    // function for remove a last element in the list
    public int popBack() {
        int x = -1;
        if (first != last) {
            x = last.element;
            Node i = first;
            for (/**/; i.next != last; i = i.next);
            last = i;
            last.next = i = null;
            n--;
        }

        return x;
    }

    // function for remove the first element in the list
    public int popFront() {
        int x = -1;
        if (first != last) {
            x = first.next.element;
            Node temp = first.next; // temp = first == head node, but head node is not considered
            first = temp.next;
            temp = null;
            n--;
        }

        return x;
    }

    // function for remove a element of any position
    public int pop(int pos) {
        int x = -1;
        if (pos >= 0 && pos < n) {
            if (pos == 0) popFront();
            else if (pos == n - 1) popBack();
            else {
                Node i = first;
                for (int j = 0; j < pos; j++, i = i.next); // j < pos because the structure contains head node, but if not contains j < pos - 1
                Node temp = i.next;
                x = i.next.element;
                i.next = i.next.next;
                temp.next = null;
                i = temp = null;
                n--;
            }
        }

        return x;
    }

    // method for show all elements in list
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
        List list = new List();
        list.pushBack(7);
        list.pushFront(13);
        list.push(3, 0);
        list.pop(1);
        list.print();
        System.out.println(list.get());
    }
}
