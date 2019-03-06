package com.github.binarylei.collection.list;

public class MyLinkedList {

    private Node first;
    private Node last;

    private int size;

    public void add(Object obj) {
        Node n = new Node();

        if (first == null) {
            n.setPrevious(null);
            n.setObj(obj);
            n.setNext(null);

            first = n;
            last = n;
        } else {
            // 直接往 last 节点后增加新的节点
            n.setPrevious(last);
            n.setObj(obj);
            n.setNext(null);

            last.setNext(n);
            last = n;
        }
        size++;
    }

    public int size() {
        return size;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object get(int index) {   //2
        rangeCheck(index);

        // 0 1 2 3 4
        Node temp = node(index);
        if (temp != null) {
            return temp.obj;
        }
        return null;
    }

    public Node node(int index) {
        Node temp = null;
        if (first != null) {
            if (index < (size >> 1)) {
                temp = first;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
            } else {
                temp = last;
                for (int i = size - 1; i > index; i--) {
                    temp = temp.previous;
                }
            }

        }
        return temp;
    }

    public void remove(int index) {
        Node temp = node(index);

        if (temp != null) {
            Node up = temp.previous;
            Node down = temp.next;
            up.next = down;
            down.previous = up;
            size--;
        }

    }

    public void add(int index, Object obj) {
        Node temp = node(index);

        Node newNode = new Node();
        newNode.obj = obj;

        if (temp != null) {
            Node up = temp.previous;
            up.next = newNode;
            newNode.previous = up;

            newNode.next = temp;
            temp.previous = newNode;

            size++;
        }
    }

    //用来表示一个节点
    public class Node {
        Node previous;   //上一个节点
        Object obj;
        Node next;        //下一个节点

        public Node() {
        }

        public Node(Node previous, Object obj, Node next) {
            super();
            this.previous = previous;
            this.obj = obj;
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add("aaa");
        list.add("bbb");
        //list.add(1,"BBBB");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");
        //list.remove(1);
        System.out.println(list.get(3));
    }
}
