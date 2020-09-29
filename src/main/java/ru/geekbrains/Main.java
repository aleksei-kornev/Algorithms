package ru.geekbrains;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        /*
        Задание 7.1
        Приведите пример графа.

        Ответ: поиск кратчайшего маршрута между городами; поиск наиболее дешевого перелета между двумя городами;
        в целом любые схемы авиалиний; блок-схема;

        Задание 7.2
        Реализуйте базовые методы графа.
        Задание 7.3
        В программный код из задания 7.2 добавьте реализацию метода обхода в глубину.
        Выполните оценку времени с помощью System.nanoTime().
        Задание 7.4
        В базовом графе из задания 7.2 реализуйте метод обхода в ширину.
        Выполните оценку времени с помощью System.nanoTime().
        */

        Graph graph = new Graph(100);

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");

        graph.insertEdge(0, 1);
        graph.insertEdge(0, 2);
        graph.insertEdge(1, 3);
        graph.insertEdge(2, 4);
        graph.insertEdge(2, 5);

        startTime = System.nanoTime();
        graph.dfs(0);
        endTime = System.nanoTime();
        System.out.println("Время обхода в глубину: " + (endTime - startTime));
        System.out.println();

        startTime = System.nanoTime();
        graph.bfs(0);
        endTime = System.nanoTime();
        System.out.println();
        System.out.println("Время обхода в ширину: " + (endTime - startTime));
    }


    static class Vertex{

        private String label;
        private boolean isVisited;

        public Vertex(String label) {
            this.label = label;
            this.isVisited = false;
        }

        public String getLabel() {
            return label;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(boolean visited) {
            isVisited = visited;
        }
    }

    public static class Graph {

        //массив для хранения вершин
        private Vertex[] vertexArray;
        //матрица смежности
        private int[][] matrix;
        //текущее количество вершин
        private int count;
        private Stack stack;

        public Graph(int n) {
            stack = new Stack();
            vertexArray = new Vertex[n];
            matrix = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    matrix[i][j] = 0;
        }

        public void insertVertex(String key)
        {
            Vertex v = new Vertex(key);
            vertexArray[count++] = v;
        }

        public void insertEdge(int begin, int end)
        {
            matrix[begin][end] = 1;
            matrix[end][begin] = 1;
        }

        //получение смежной непосещенной вершины
        private int getUnvisitedVertex(int vertex)
        {
            for(int i = 0; i < count; i++)
                if(matrix[vertex][i] == 1 && vertexArray[i].isVisited == false)
                    return i;

            return -1;
        }

        //реализация обхода в глубину
        public void dfs(int v)
        {
            System.out.print("Выполняем обход в глубину: " + vertexArray[v].getLabel() + " -> ");
            vertexArray[v].isVisited = true;
            stack.push(v);
            while(!stack.isEmpty())
            {
                int adjVertex = getUnvisitedVertex((int) stack.peek());
                if(adjVertex == -1)
                    stack.pop();
                else{
                    vertexArray[adjVertex].isVisited = true;
                    System.out.print(vertexArray[adjVertex].getLabel() + " -> ");
                    stack.push(adjVertex);
                }
            }

            System.out.println();
            for(Vertex vertex: vertexArray)
                if (vertex != null)
                    vertex.setVisited(false);
        }

        //реализация обхода в ширину
        public void bfs(int v)
        {
            System.out.print("Выполняем обход в ширину: " + vertexArray[v].getLabel() + " -> ");
            vertexArray[v].isVisited = true;
            int vertex;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(v);
            while(!queue.isEmpty())
            {
                int currentVertex = queue.remove();

                while((vertex = getUnvisitedVertex(currentVertex)) != -1)
                {
                    vertexArray[vertex].setVisited(true);
                    System.out.print(vertexArray[vertex].getLabel() + " -> ");
                    queue.add(vertex);
                }
            }
        }
    }

}


