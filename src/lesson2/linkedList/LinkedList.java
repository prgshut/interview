package gb.lesson2.linkedList;

import gb.lesson2.List;

public class LinkedList<E> implements List<E> {
    private Node first;
    private Node root;
    private Node last;
    private int size = 0;

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpti() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(E val) {
        Node newNode = new Node(val);

        if (size == 0) {
            first = newNode;
            last = newNode;
            size++;
            return true;
        }
        if (size > 0) {
            last.setNext(newNode);
            last = newNode;
            size++;
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(E val) {
        root = first;
        Node l = root;
        if (first.getVal().equals(val)){
            Node temp=first.getNext();
            first=temp;
            size--;
            return true;
        }
        while (root.getNext() != null) {
            if (root.getVal().equals(val)) {
                l.setNext(root.getNext());
                size--;
                return true;
            }
            l = root;
            root = root.getNext();
        }
        return false;
    }

    @Override
    public boolean contains(E val) {
        root = first;
        while (root.getNext()!=null){
            if(root.getVal().equals(val)){
                return true;
            }
            root=root.getNext();
        }
        return false;
    }

    @Override
    public boolean hasNext() {
        return root.getNext() != null;
    }

    @Override
    public Node next() {
        return root.getNext();
    }

    private class Node {
        private Node next;
        private E val;

        public Node(E val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public E getVal() {
            return val;
        }

        public void setVal(E val) {
            this.val = val;
        }
    }


}
