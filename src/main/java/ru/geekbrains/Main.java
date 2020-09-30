package ru.geekbrains;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        /*
        Задание 8.1
        Приведите пример использование хеш-таблиц.

        Самый распространенный пример - хранение паролей пользователя с помощью хешей.

        Задание 8.2
        Приведите примеры ключей и коллизий.

        Ключ - любое значение, для которого вычисляется хеш. Например, ID пользователя.
        Коллизия - ситуация, когда получается одинаковый хеш-код для разных ключей.

        Задание 8.3
        Приведите примеры популярных и эффективных хеш-функций.

        Популярной и эффективной хеш-функцией является алгоритм хеширования MD5. Этот алгоритм
        используется для проверки целостности файлов или поиска дубликатов. Раньше активно использовался
        для хранения паролей, но потом были выявлены уязвимости, из-за которых сейчас все меньше примерняется
        для этих целей.

        Вообще хорошей хеш-функцией является такая, которая быстро вычисляется и имеет минимум коллизий.

        Таковой можно считать функцию хеширования текстового значения, когда каждой букве присваивается
        значение, после чего все значения складываются.

        Задание 8.4
        На основе данных массива из задания 2.3 реализуйте хеш-таблицу с помощью открытой адресации,
        а конкретнее метода линейного пробирования

        Задание 8.5
        Перестройте программный код задания 8.4 из алгоритма линейного пробирования в алгоритм двойного хеширования.
        Сравните отличительные черты двух алгоритмов.
        */


        Item aDataItem;
        int aKey;
        int size = 50;

        // Для задания 8.4
        HashTableLinear hTable = new HashTableLinear(size);
        // Для задания 8.5
        HashTableDoubleHash hTableDH = new HashTableDoubleHash(size);

        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            aKey = rand.nextInt(999);
            aDataItem = new Item(aKey);
            hTable.insert(aDataItem);
            hTableDH.insert(aDataItem);
        }

        System.out.println("Вывод таблицы с методом линейной пробации:");
        hTable.display();
        System.out.println("Вывод таблицы с двойным хешированием:");
        hTableDH.display();
    }

    static class Item {
        private int data;

        public Item(int data) {
            this.data = data;
        }

        public int getKey() {
            return data;
        }
    }

    static class HashTableLinear {
        private Item[] hashArr;
        private int arrSize;
        private Item nonItem;

        public HashTableLinear(int size) {
            this.arrSize = size;
            hashArr = new Item[arrSize];
            nonItem = new Item(-1);
        }

        public void display() {
            for (int i = 0; i < arrSize; i++) {
                if (hashArr[i] != null) {
                    System.out.println(hashArr[i].getKey());
                } else {
                    System.out.println("***");
                }

            }
        }

        public int hashFunc(int key) {
            return key % arrSize;
        }

        public void insert(Item item) {
            int key = item.getKey();
            int hashVal = hashFunc(key);
            while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) {
                ++hashVal;
                hashVal %= arrSize;
            }

            hashArr[hashVal] = item;
        }

        public Item delete(int key) {
            int hashVal = hashFunc(key);
            while (hashArr[hashVal] != null) {
                if (hashArr[hashVal].getKey() == key) {
                    Item temp = hashArr[hashVal];
                    hashArr[hashVal] = nonItem;
                    return temp;
                }
                ++hashVal;
                hashVal %= arrSize;
            }
            return null;
        }

        public Item find(int key) {
            int hashVal = hashFunc(key);
            while (hashArr[hashVal] != null) {
                if (hashArr[hashVal].getKey() == key) {
                    return hashArr[hashVal];
                }
                ++hashVal;
                hashVal %= arrSize;
            }
            return null;
        }

    }

    static class HashTableDoubleHash {
        private Item[] hashArr;
        private int arrSize;
        private Item nonItem;

        public HashTableDoubleHash(int size) {
            this.arrSize = size;
            hashArr = new Item[arrSize];
            nonItem = new Item(-1);
        }

        public void display() {
            for (int i = 0; i < arrSize; i++) {
                if (hashArr[i] != null) {
                    System.out.println(hashArr[i].getKey());
                } else {
                    System.out.println("***");
                }

            }
        }

        public int hashFunc(int key) {
            return key % arrSize;
        }

        public int hashFuncDouble(int key) {
            return 5 - key % 5;
        }

        public void insert(Item item) {
            int key = item.getKey();
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) {
                hashVal += stepSize;
                hashVal %= arrSize;
            }

            hashArr[hashVal] = item;
        }

        public Item delete(int key) {
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null) {
                if (hashArr[hashVal].getKey() == key) {
                    Item temp = hashArr[hashVal];
                    hashArr[hashVal] = nonItem;
                    return temp;
                }
                hashVal += stepSize;
                hashVal %= arrSize;
            }
            return null;
        }

        public Item find(int key) {
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null) {
                if (hashArr[hashVal].getKey() == key) {
                    return hashArr[hashVal];
                }
                hashVal += stepSize;
                hashVal %= arrSize;
            }
            return null;
        }

    }

}


