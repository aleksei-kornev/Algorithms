package ru.geekbrains;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /* Задание 2.1
        На основе программного кода из домашнего задания №1 реализуйте массив на основе существующих примитивных
        или ссылочных типов данных.
        Выполните обращение к массиву и базовые операции класса Arrays.
        Оценить выполненные методы с помощью базового класса System.nanoTime().
        */
        int[] simpleTypeArray = new int[5000];
        int[] copyOfSimpleTypeArray = null;
        fillArray(simpleTypeArray);

        long timeStart = System.nanoTime();
        copyOfSimpleTypeArray = Arrays.copyOf(simpleTypeArray, simpleTypeArray.length);
        System.out.println("Затраты времени на копирование массива: " + (System.nanoTime() - timeStart) + " наносекунд.");
        System.out.println("Массив 1: " + Arrays.toString(simpleTypeArray));
        System.out.println("Массив 2: " + Arrays.toString(copyOfSimpleTypeArray));

        timeStart = System.nanoTime();
        Arrays.sort(simpleTypeArray);
        System.out.println("Затраты времени на сортировку: " + (System.nanoTime() - timeStart) + " наносекунд.");
        System.out.println("Отсортированный массив: " + Arrays.toString(simpleTypeArray));

        /*
        Задание 2.2
        На основе написанного кода в задании 2.1 реализуйте линейный и двоичный поиск.
        Оценить алгоритмы линейного и двоичного поиска с помощью базового класса System.nanoTime(),
        при необходимости расширьте уже существующий массив данных.
         */

        //Линейный поиск перебором
        timeStart = System.nanoTime();
        System.out.println("Элемент " + copyOfSimpleTypeArray[50] + " найден как элемент с индексом " + linearSearch(copyOfSimpleTypeArray[50], simpleTypeArray));
        System.out.println("Затраты времени на линейный поиск: " + (System.nanoTime() - timeStart) + " наносекунд.");

        //Двоичный поиск
        timeStart = System.nanoTime();
        System.out.println("Элемент " + copyOfSimpleTypeArray[50] + " найден как элемент с индексом " + Arrays.binarySearch(simpleTypeArray, copyOfSimpleTypeArray[50]));
        System.out.println("Затраты времени на двоичный поиск: " + (System.nanoTime() - timeStart) + " наносекунд.");

        /*
        Задание 2.3
        Создайте массив размером 400 элементов.
        Выполните сортировку с помощью метода sort().
        Оцените сортировку с помощью базового класса System.nanoTime().
         */
        int[] arr = new int[400];
        fillArray(arr);

        int[] arr2, arr3, arr4;
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        arr4 = Arrays.copyOf(arr, arr.length);

        timeStart = System.nanoTime();
        Arrays.sort(arr);
        System.out.println("Затраты времени на стандартную сортировку: " + (System.nanoTime() - timeStart) + " наносекунд.");
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));

        /*
        Задание 2.4
        На основе существующего массива данных из задания 2.3 реализуйте алгоритм сортировки пузырьком.
        Оцените сортировку с помощью базового класса System.nanoTime().
        Сравните время выполнения алгоритмы сортировки методом sort() из задания 2.1 и сортировку пузырьком.
         */
        timeStart = System.nanoTime();
        bubbleSort(arr2);
        System.out.println("Затраты времени на сортировку пузырьком: " + (System.nanoTime() - timeStart) + " наносекунд.");
        System.out.println("Отсортированный массив: " + Arrays.toString(arr2));

        /*
        Задание 2.5
        На основе массива данных из задания 2.3 реализуйте алгоритм сортировки методом выбора.
        Оцените сортировку с помощью базового класса System.nanoTime().
        Сравните с временем выполнения алгоритмов сортировки из прошлых заданий 2.3 и 2.4.
         */
        timeStart = System.nanoTime();
        choiceSort(arr3);
        System.out.println("Затраты времени на сортировку методом выбора: " + (System.nanoTime() - timeStart) + " наносекунд.");
        System.out.println("Отсортированный массив: " + Arrays.toString(arr3));

        /*
        Задание 2.6
        На основе массива данных из задания 2.3 реализуйте алгоритм сортировки методом вставки.
        Оцените сортировку с помощью базового класса System.nanoTime().
        Сравните с временем выполнения алгоритмов сортировки из прошлых заданий 2.3, 2.4 и 2.5.
         */
        timeStart = System.nanoTime();
        insertSort(arr4);
        System.out.println("Затраты времени на сортировку методом вставки: " + (System.nanoTime() - timeStart) + " наносекунд.");
        System.out.println("Отсортированный массив: " + Arrays.toString(arr4));
    }

    private static void fillArray(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(arr.length * 2);
        }
    }

    private static void insertSort(int[] arr) {
        int buff;
        int lastJ;
        for (int i = 1; i < arr.length; i++) {
            buff = arr[i];
            lastJ = i;
            for (int j = i; j > 0; j--) {
                if (buff < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                    lastJ = j;
                } else {
                    break;
                }
            }
            arr[lastJ - 1] = buff;
        }
    }

    private static void choiceSort(int[] arr) {
        int minIndex = 0;
        int buff;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            buff = arr[i - 1];
            arr[i - 1] = arr[minIndex];
            arr[minIndex] = buff;
            minIndex = i;
        }
    }

    private static void bubbleSort(int[] arr) {
        int buff;
        for (int j = 1; j < arr.length; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    buff = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = buff;
                }
            }

        }
    }

    private static int linearSearch(int intToSearch, int[] Array) {
        for (int i = 0; i < Array.length; i++) {
            if (intToSearch == Array[i]) {
                return i;
            }
        }
        return -1;
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


