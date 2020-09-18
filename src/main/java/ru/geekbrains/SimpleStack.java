package ru.geekbrains;

public class SimpleStack {
    private int maxSize;
    private MyClass[] stackArray;
    private int top;

    public SimpleStack(int m) {
        this.maxSize = m;
        stackArray = new MyClass [maxSize];
        top = -1;
    }

    public void addElement(MyClass element) {
        stackArray[++top] = element;
    }

    public MyClass pollElement() {
        return stackArray[top--];
    }

    public MyClass readTop() {
        return stackArray[top];

    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
}
