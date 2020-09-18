package ru.geekbrains;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue {
    private int maxSize;
    private Integer[] stackArray;
    private int top;

    public PriorityQueue(int m) {
        this.maxSize = m;
        stackArray = new Integer[maxSize];
        top = -1;
    }

    public void addElement(Integer element) {
        stackArray[++top] = element;
        Arrays.sort(stackArray,comparator);
    }

    public Integer pollElement() {
        return stackArray[top--];
    }

    public Integer readTop() {
        return stackArray[top];

    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if ((o1 == null) && (o2 == null)) return 0;
            if (o1 == null) return 1;
            if (o2 == null) return -1;
            return o1.compareTo(o2);
        }
    };

}