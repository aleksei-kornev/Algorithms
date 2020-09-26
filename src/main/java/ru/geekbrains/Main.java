package ru.geekbrains;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        /*
        Задание 6.1
        Приведите пример использования древовидной структуры.

        Ответ: организационная структура предприятия.

        Задание 6.2
        Реализуйте класс узла дерева и базовый шаблон дерева с базовыми методами.
        Задание 6.3
        Реализуйте методы поиска и вставки узла в дерево.
        Задание 6.4
        Реализуйте базовые методы обхода дерева и метода дисплей.
        Реализуйте поиск максимума и минимума.
        Задание 6.5
        Реализуйте метод удаления узла и выполните оценку времени базовых методов дерева с помощью System.nanoTime().
        Задание 6.6
        Реализуйте на основе массива из задания 2.1 алгоритм пирамидальной сортировки с реализацией бинарной пирамиды.
        Выполните оценку алгоритма пирамидальной сортировки с помощью System.nanoTime() и сравните с предыдущими алгоритмами сортировки.

        Задание 6.7
        Приведите пример сбалансированного дерева и его применения.

        Ответ: сбалансированное дерево - это полное дерево, имеющее ветви примерно равной длины.
        Сбалансированные деревья идеально подходят для быстрого поиска значений.
        */

        Tree theTree = new Tree();

        theTree.insert(new MyClass("Bob",3));
        theTree.insert(new MyClass("Alice",2));
        theTree.insert(new MyClass("John",1));
        theTree.insert(new MyClass("Smith",5));

        startTime = System.nanoTime();
        theTree.max().display();
        endTime = System.nanoTime();
        theTree.min().display();
        System.out.println("Время поиска мин/макс: " + (endTime - startTime));

        startTime = System.nanoTime();
        theTree.find(2).display();
        endTime = System.nanoTime();
        System.out.println("Время поиска конкретного элемента: " + (endTime - startTime));

        startTime = System.nanoTime();
        theTree.delete(1);
        endTime = System.nanoTime();
        System.out.println("Время удаления элемента: " + (endTime - startTime));
        System.out.println();
        theTree.displayTree();


        int[] simpleTypeArray = new int[500];
        fillArray(simpleTypeArray);
        startTime = System.nanoTime();
        heapSort(simpleTypeArray,simpleTypeArray.length);
        endTime = System.nanoTime();
        System.out.println(Arrays.toString(simpleTypeArray));
        System.out.println("Время сортировки: " + (endTime - startTime));

    }

    public static void heapSort(int[] myArray, int length) {
        int temp;
        int size = length-1;
        for (int i = (length / 2); i >= 0; i--) {
            heapify(myArray, i, size);
        };
        for(int i= size; i>=0; i--) {
            temp = myArray[0];
            myArray[0] = myArray[size];
            myArray[size] = temp;
            size--;
            heapify(myArray, 0, size);
        }
    }

    public static void heapify (int [] myArray, int i, int heapSize) {
        int a = 2*i;
        int b = 2*i+1;
        int largestElement;
        if (a<= heapSize && myArray[a] > myArray[i]) {
            largestElement = a;
        } else {
            largestElement = i;
        }
        if (b <= heapSize && myArray[b] > myArray[largestElement]) {
            largestElement = b;
        }
        if (largestElement != i) {
            int temp = myArray[i];
            myArray[i] = myArray[largestElement];
            myArray[largestElement] = temp;
            heapify(myArray, largestElement, heapSize);
        }
    }

    static class Tree {
        private Node root;

        public void insert (MyClass myClass) {
            Node node = new Node();
            node.myClass = myClass;
            if (root == null)
                root = node;
            else {
                Node current = root;
                Node parent;
                while (true) {
                    parent = current;
                    if (myClass.id < current.myClass.id){
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = node;
                            return;
                        }
                    } else {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = node;
                            return;
                        }
                    }
                }
            }

        }

        public Node find (int key) {
            Node current = root;
            while (current.myClass.id != key) {
                if (key < current.myClass.id)
                    current = current.leftChild;
                else
                    current = current.rightChild;
                if (current == null)
                    return null;
            }
            return current;
        }

        public void inOrder (Node rootNode) {
            if (rootNode != null) {
                inOrder(rootNode.leftChild);
                rootNode.display();
                inOrder(rootNode.rightChild);
            }
        }

        public Node min () {
            Node current, last = null;
            current = root;
            while (current != null) {
                last = current;
                current = current.leftChild;
            }
            return last;
        }

        public Node max () {
            Node current, last = null;
            current = root;
            while (current != null) {
                last = current;
                current = current.rightChild;
            }
            return last;
        }

        public boolean delete (int id) {
            Node current = root;
            Node parent = root;

            boolean isLeftChild = true;

            while (current.myClass.id != id) {
                parent = current;
                if (id < current.myClass.id) {
                    isLeftChild = true;
                    current = current.leftChild;
                } else {
                    isLeftChild = false;
                    current = current.rightChild;
                }
                if (current == null) {
                    return false;
                }
            }

            if (current.leftChild == null && current.rightChild == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            } else if (current.rightChild == null) {
                if (current == null) {
                    root = current.leftChild;
                } else if (isLeftChild) {
                    parent.leftChild = current.leftChild;
                } else {
                    parent.rightChild = current.leftChild;
                }
            } else if (current.leftChild == null) {
                if (current == null) {
                    root = current.rightChild;
                } else if (isLeftChild) {
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
            } else {
                Node successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.leftChild = successor;
                } else {
                    parent.rightChild = successor;
                }
                successor.leftChild = current.leftChild;
            }
            return true;
        }

        public Node getSuccessor(Node node){
            Node successorParent = node;
            Node successor = node;
            Node current = node.rightChild;

            while (current != null) {
                successorParent = successor;
                successor = current;
                current = current.leftChild;
            }
            if (successor != node.rightChild) {
                successorParent.leftChild = successor.rightChild;
                successor.rightChild = node.rightChild;
            }
            return successor;
        }

        public void displayTree () {
            Node current = root;
            System.out.println("Симметричный");
            inOrder(root);

        }
    }

    static class Node {
        public MyClass myClass;
        public Node leftChild;
        public Node rightChild;

        public void display () {
            System.out.println(myClass.toString());
        }
    }

    static class MyClass {
        private String name;
        private int id;

        public MyClass(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String toString() {
            return "ID: "+id+" Name is " + name + '.';
        }
    }

    private static void fillArray(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(arr.length * 2);
        }
    }

}


