package ru.vsu.css.aisd2025.klimov_i_p.task_2_35;

import java.util.Comparator;

public class MyLinkedList {
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

    public Node getHead(){
        return this.head;
    }

    public Node getTail(){
        return this.tail;
    }

    public int getSize(){
        return this.size;
    }

    public void showNode(Node node){
        System.out.println(node.data + " " + node.next);
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

    public Node search(int index){
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

    public void delete(int index){
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
            throw new IndexOutOfBoundsException("Index out of bounds");
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

    public void add(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
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
    }

    public void bubbleSort() {
        if (size <= 1) {
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            Node prev = null;
            Node current = head;
            while (current != null && current.next != null) {
                Node nextNode = current.next;
                if (current.data > nextNode.data) {
                    if (prev == null) {
                        head = nextNode;
                    } else {
                        prev.next = nextNode;
                    }
                    current.next = nextNode.next;
                    nextNode.next = current;
                    prev = nextNode;
                    swapped = true;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
        } while (swapped);
        tail = head;
        if (tail != null) {
            while (tail.next != null) {
                tail = tail.next;
            }
        }
    }
}