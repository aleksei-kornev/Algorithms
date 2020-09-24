package ru.geekbrains;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        /*
        Задание 5.1
        Приведите пример использования рекурсии.

        Простой прмер рекурсии из жизни - это вложенные матрешки.
        */

        /*
        Задание 5.2
        Реализуйте простой пример бесконечной рекурсии и обычной рекурсии с условием для выхода.
         */

        //бесконечная рекурсия
        //simpleInfinityRecurtion(10);

        //стандартная рекурсия
        simpleStandartRecurtion(10);

        /*
        Задание 5.3
        Приведите пример изображающий стек вызова и стек вызова с рекурсией.
        Стопка любых связанных предметов.

        Стек вызовов с рекурсией - стопка из вложенных коробок: открываем большую коробку,
        видим в ней такую же коробку меньшего размера, вытаскиваем, кладем сверху, открываем ее -
        видим следующую коробку, вытаскиваем, кладем своерху, открываем ее, и так пока не дойдем до
        последней коробки. Получившаяся стопка - это стек.
         */

        /*
        Задание 5.4
        Реализуйте простой алгоритм использующий цикл и простой алгоритм использующий рекурсию.
        Оцените два алгоритма с помощью базового метода System.nanoTime().
         */

        //простой цикл
        startTime = System.nanoTime();
        simpleCycle(100);
        endTime = System.nanoTime();
        System.out.println("\n Время при простом цикле: " + (endTime - startTime));

        //стандартная рекурсия
        startTime = System.nanoTime();
        simpleStandartRecurtion(100);
        endTime = System.nanoTime();
        System.out.println("\n Время при рекурсии: " + (endTime - startTime));

        /*
        Задание 5.5
        Реализуйте алгоритм двоичного рекурсивного поиска на основе массива из задания 2.1.
        Оцените алгоритм двоичного рекурсивного поиска с помощью базового метода System.nanoTime() и сравните с обычным двоичным поиском.
        */
        int[] simpleTypeArray = new int[500];
        fillArray(simpleTypeArray);
        Arrays.sort(simpleTypeArray);

        System.out.println(Arrays.toString(simpleTypeArray));
        System.out.println(simpleTypeArray[3]);
        startTime = System.nanoTime();
        System.out.println(binarySearchRecurtion(simpleTypeArray[3],simpleTypeArray));
        endTime = System.nanoTime();
        System.out.println("Время рекурсивного двоичного поиска: " + (endTime - startTime));

        /*
        Задание 5.6
        На основе массива из задания 2.1 реализуйте алгоритм сортировки слиянием.
        Оцените алгоритм сортировки слиянием с помощью базового метода System.nanoTime() и сравните с сортировкой методом sort().
         */
        int[] simpleTypeArrayTwo = new int[500];
        fillArray(simpleTypeArrayTwo);
        int[] simpleTypeArrayTwoCopy = new int[simpleTypeArrayTwo.length];
        simpleTypeArrayTwoCopy = Arrays.copyOf(simpleTypeArrayTwo,simpleTypeArrayTwo.length);

        startTime = System.nanoTime();
        Arrays.sort(simpleTypeArrayTwo);
        endTime = System.nanoTime();
        System.out.println("Время встроенной сортировки: " + (endTime - startTime));

        startTime = System.nanoTime();
        simpleTypeArrayTwoCopy = mergeSort(simpleTypeArrayTwoCopy);
        endTime = System.nanoTime();
        System.out.println("Время сортировки слиянием: " + (endTime - startTime));
    }

    public static int[] mergeSort(int[] array1) {
        int[] buffer1 = Arrays.copyOf(array1, array1.length);
        int[] buffer2 = new int[array1.length];
        int[] result = mergeSortInner(buffer1, buffer2, 0, array1.length);
        return result;
    }

    public static int[] mergeSortInner(int[] buffer1, int[] buffer2,
                                       int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        // уже отсортирован
        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);

        // Слияние
        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2]
                    ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }


    //Двоичный поиск с использованием рекурсии, возвращающий индекс найденного элемента или 0.
    private static int binarySearchRecurtion (int search, int[] arr) {
        if (arr.length == 0) return -1;

        int halfPoint = (arr.length / 2);

        if (search > arr[halfPoint]) return halfPoint + 1 + binarySearchRecurtion(search, Arrays.copyOfRange(arr,halfPoint+1, arr.length));
        else if (search < arr[halfPoint]) return binarySearchRecurtion(search,Arrays.copyOfRange(arr,0,halfPoint-1));
        else return (halfPoint);
    }

    private static void simpleCycle (int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(n);
        }
    }

    private static void simpleInfinityRecurtion (int n){
        System.out.println(n);
        simpleInfinityRecurtion(n-1);
    }

    private static void simpleStandartRecurtion (int n){
        System.out.print(n);
        if (n>0) simpleStandartRecurtion(n-1);
    }

    private static void fillArray(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(arr.length * 2);
        }
    }

}


