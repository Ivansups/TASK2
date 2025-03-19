package ru.vsu.css.aisd2025.klimov_i_p.task_2_35;

public class Main {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.isEmpty();
        list.addFirst(2);
        list.addLast(1);
        list.addLast(1);
        list.replace(1, 5);
        list.add(1, 23);
        list.bubbleSort();
        list.showList();
    }
}