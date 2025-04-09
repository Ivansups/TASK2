package ru.vsu.cs.course1;

public class MyLinkedList {
    public static class WrongIndexException extends RuntimeException {
        public WrongIndexException(){

        }
        public WrongIndexException(String message){
            super(message);
        }
    }

    public static class EmptyListException extends RuntimeException {
        public EmptyListException(){
        }
        public EmptyListException(String message){
            super(message);
        }
    }
    private static class Node{
        private int data;
        private Node next;
        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void showList(){
        for (Node node = head; node != null; node = node.next){
            System.out.print(node.data + " ");
        }
    }

    public boolean isEmpty() {return this.size == 0;}

    public void addLast(int data) {
        Node node = new Node(data, null);
        if (isEmpty()) {
            head = node;
            tail = node;
            size++;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void addFirst(int data){
        Node node = new Node(data, null);
        if (isEmpty()) {
            head = node;
            tail = node;
            size++;
        } else {
            node.next = head;
            head = node;
            size++;
        }
    }

    private Node search(int index){
        if (index == 0){
            return head;
        } else {
            Node node = head.next;
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
            return node;
        }
    }

    public int searchForTest(int index){
        if (index == 0){
            return head.data;
        } else {
            Node node = head.next;
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
            return node.data;
        }
    }

    public void remove(int index){
        if (isEmpty()){
            throw new EmptyListException();
        }
        if (index == 0){
            head = head.next;
        }
        Node prevNode = search(index - 1);
        Node deleteNode = prevNode.next;
        prevNode.next = deleteNode.next;
        size--;
    }

    public void replace(int index, int data) {
        if (index < 0 || index > size) {
            throw new WrongIndexException();
        } else {
            if (index == 0) {
                head.data = data;
            } else if (index == size - 1) {
                tail.data = data;
            } else {
                search(index).data = data;
            }
        }
    }

    public void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new WrongIndexException();
        } else {
            if (index == 0) {
                addFirst(data);
            } else if (index == size - 1) {
                addLast(data);
            } else {
                Node node = search(index - 1);
                Node newNode = new Node(data, node.next);
                node.next = newNode;
            }
        }
        size++;
    }

    public static void bubbleSort(MyLinkedList list) {
        if (list.size <= 1) {
            return;
        }

        boolean swapped = true;
        Node tail = null;

        while (swapped) {
            swapped = false;
            Node prev = null;
            Node current = list.head;
            Node newTail = null;

            while (current != null && current.next != null) {
                Node nextNode = current.next;

                if (current.data > nextNode.data) {
                    if (prev == null) {
                        list.head = nextNode;
                    } else {
                        prev.next = nextNode;
                    }

                    current.next = nextNode.next;
                    nextNode.next = current;

                    if (prev == null) {
                        prev = list.head;
                    } else {
                        prev = nextNode;
                    }

                    swapped = true;
                } else {
                    prev = current;
                    current = current.next;
                }

                newTail = current;
            }

            if (newTail != null) {
                tail = newTail;
            }
        }

        list.tail = tail;
    }

    public static MyLinkedList makeLinkedList(int[] arr) {
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < arr.length; i++) {
            list.addLast(arr[i]);
        }
        return list;
    }

    public static int[] makeArray(MyLinkedList list) {
        int[] arr = new int[list.size - 1];
        Node cur = list.head;
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = cur.data;
            cur = cur.next;
            if (cur == list.tail){
                arr[arr.length - 1] = list.tail.data;
                break;
            }
        }
        return arr;
    }
}