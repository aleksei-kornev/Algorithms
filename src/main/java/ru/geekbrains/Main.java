package ru.geekbrains;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        /* Задание 3.1
        На основе массива из домашнего задания 2.1 реализуйте простой список и коллекцию.
        Оцените время выполнения преобразования.
        */
        final int ARR_LENGTH = 500;
        int[] simpleTypeArray = new int[ARR_LENGTH];
        int[] copyOfSimpleTypeArray = null;
        fillArray(simpleTypeArray);

        long timeStart = System.nanoTime();
        ArrayList<Integer> arrList = new ArrayList<>(500);
        for (int i = 0; i < simpleTypeArray.length; i++) {
            arrList.add(simpleTypeArray[i]);
        }
        long timeStop = System.nanoTime();
        System.out.println("Время заполнения ArrayList'a " + (timeStop - timeStart) + " наносекунд.");
        Collections.sort(arrList);
        System.out.println(arrList);

        /*
        Задание 3.2
        На основе списка из задания 3.1 реализуйте основные методы добавления, удаления и получения объекта
        или элемента из списка.
        Оценить выполненные методы с помощью базового класса System.nanoTime().
         */
        timeStart = System.nanoTime();
        arrList.add(999);
        timeStop = System.nanoTime();
        System.out.println("Время добавления элемента в ArrayList " + (timeStop - timeStart) + " наносекунд.");

        timeStart = System.nanoTime();
        System.out.println(arrList.get(500));
        timeStop = System.nanoTime();
        System.out.println("Время получения элемента из ArrayList " + (timeStop - timeStart) + " наносекунд.");

        timeStart = System.nanoTime();
        arrList.remove(500);
        timeStop = System.nanoTime();
        System.out.println("Время удаления элемента из ArrayList " + (timeStop - timeStart) + " наносекунд.");

        /*
        Задание 3.3
        Реализуйте простой односвязный список и его базовые методы.
         */
        ListSimpleLinked listSimpleLinked = new ListSimpleLinked();
        listSimpleLinked.addFront("A");
        listSimpleLinked.addBack(2);
        listSimpleLinked.printList();
        listSimpleLinked.removeElemenet(2);
        listSimpleLinked.removeElemenet("A");
        listSimpleLinked.printList();

        /*
        Задание 3.4
        На основе списка из задания 3.1 (наверное, все же 3.3?) реализуйте простой двусторонний список и
        его базовые методы.
        Реализуйте список заполненный объектами из вашего класса из задания 1.3
        */
        System.out.println("\nДвусторонний список:");
        ListLinked listLinked = new ListLinked();
        listLinked.addFront(new MyClass("Alex"));
        listLinked.addFront(new MyClass("Bob"));

        listLinked.printList();
        System.out.println();
        listLinked.printReversedList();

        /*
        Задание 3.5
        Реализуйте итератор на основе связанных списков из задания 3.4 и выполните базовые операции итератора.
        Оцените время выполнения операций с помощью базового метода System.nanoTime()
         */
        LinkedList<MyClass> linkedList = new LinkedList<>();
        linkedList.add(new MyClass("Alex"));
        linkedList.add(new MyClass("Bob"));
        linkedList.add(new MyClass("Alice"));
        Iterator iterator = linkedList.iterator();

        System.out.println();

        timeStart = System.nanoTime();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
            iterator.remove();
        }
        timeStop = System.nanoTime();
        System.out.println("Время на перебор и удаление элементов итератором " + (timeStop - timeStart) + " наносекунд.");

        System.out.println("Теперь список такой: " + linkedList.toString());

    }

    private static void fillArray(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(arr.length * 2);
        }
    }

    static class MyClass {
        private String name;

        public MyClass(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "myClass name is " + name + '.';
        }

    }
}


