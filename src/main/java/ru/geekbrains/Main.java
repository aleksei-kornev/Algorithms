package ru.geekbrains;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        /*
        Задание 4.1
        На основе данных объектного списка из задания 3.4 реализуйте простой стек и его базовые методы.
        Оцените время выполнения операций с помощью базового метода System.nanoTime().
        */
        SimpleStack simpleStack = new SimpleStack(10);

        startTime = System.nanoTime();
        simpleStack.addElement(new MyClass("Bob"));
        endTime = System.nanoTime();
        simpleStack.addElement(new MyClass("Alice"));

        System.out.println("Время на добавление элемента в стек: " + (endTime - startTime));

        System.out.println(simpleStack.pollElement().toString());

        /*
        Задание 4.2
        На основе данных объектного списка из задания 3.4 реализуйте простую очередь и его базовые методы.
        Реализуйте вспомогательные методы.
        Оцените время выполнения операций с помощью базового метода System.nanoTime().
         */
        SimpleQueue simpleQueue = new SimpleQueue(10);
        startTime = System.nanoTime();
        simpleQueue.insert(new MyClass("Bob"));
        endTime = System.nanoTime();
        simpleQueue.insert(new MyClass("Alice"));

        System.out.println("Время на добавление элемента в очередь: " + (endTime - startTime));

        System.out.println(simpleQueue.remove().toString());

        /*
        Задание 4.3
        На основе данных объектного списка из задания 3.4 реализуйте простой дек и его базовые методы.
        Оцените время выполнения операций с помощью базового метода System.nanoTime().
         */
        SimpleDeque simpleDeque = new SimpleDeque(3);
        startTime = System.nanoTime();
        simpleDeque.putFront(new MyClass("Alex-1"));
        endTime = System.nanoTime();
        System.out.println("Время на добавление элемента в дек: " + (endTime - startTime));
        simpleDeque.putBack(new MyClass("Alex-2"));
        System.out.println(simpleDeque.pollFront().toString());
        System.out.println(simpleDeque.getSize());
        simpleDeque.putBack(new MyClass("Alex-3"));
        simpleDeque.putBack(new MyClass("Alex-4"));
        System.out.println(simpleDeque.getSize());
        System.out.println(simpleDeque.pollFront().toString());

        /*
        Задание 4.4
        Реализуйте приоритетную очередь на основе ссылочных типов данных, например, integer.
        Оцените время выполнения операций с помощью базового метода System.nanoTime().
         */
        PriorityQueue priorityQueue = new PriorityQueue(10);
        priorityQueue.addElement(99);
        priorityQueue.addElement(4);
        priorityQueue.addElement(15);
        priorityQueue.addElement(101);
        System.out.println(priorityQueue.pollElement());
    }

    private static void fillArray(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(arr.length * 2);
        }
    }

}


