package com.ljf.algorithm;

import java.nio.file.StandardWatchEventKinds;
import java.util.*;

/**
 * @author ：ljf
 * @date ：2020/7/11 17:49
 * @description：图的相关操作，图存储，dfs，bfs，拓扑排序
 * @modified By：
 * @version: $ 1.0
 */
public class GraphLJF {
    private int vertexes;
    private LinkedList<Integer>[] adjList;//邻接表存储

    public GraphLJF(int vertexes) {
        this.vertexes = vertexes;
        adjList = new LinkedList[vertexes];

        for (int i = 0; i < vertexes; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边，from-》to
     *
     * @param from
     * @param to
     */
    public void addEdge(int from, int to) {
        adjList[from].add(to);
    }

    /**
     * dfs单元，
     *
     * @param v：待访问的值
     * @param visited：记录节点的访问情况
     */
    private void DFSUnit(int v, boolean[] visited) {
        visited[v] = true;

        System.out.print(v + "\t");

        //深处访问邻接点
        for (Integer adjValue : adjList[v]) {
            if (!visited[adjValue]) {
                DFSUnit(adjValue, visited);
            }
        }
    }

    /**
     * DFS 深度优先遍历搜索 depth first search
     */
    public void DFS() {
        boolean[] visited = new boolean[vertexes];

        for (int i = 0; i < vertexes; i++) {
            if (!visited[i]) {
                DFSUnit(i, visited);
            }
        }
    }

    /**
     * 队列实现BFS 广度优先遍历；breath first search
     */
    public void BFS() {
        boolean[] visited = new boolean[vertexes];

        Queue<Integer> queue = new LinkedList<>();

        //默认从第一个出度不为0的节点开始遍历，放入多个出度不为0的节点；防止单个出度不为0的节点造成连接不完全的情况
        for (int i = 0; i < vertexes; i++) {
            if (adjList[i].size() != 0) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        int curVertex;
        while (!queue.isEmpty()) {
            curVertex = queue.poll();
            System.out.print(curVertex + "\t");

            for (Integer adjValue : adjList[curVertex]) {
                if (!visited[adjValue]) {
                    queue.offer(adjValue);
                    visited[adjValue] = true;
                }
            }
        }
    }

    /**
     * 拓扑排序单元，DFS实现：访问最深处的节点也就是没有出度的节点最先入栈，最后才能弹出表示最后访问到
     *
     * @param stack：节点栈
     * @param v：当前访问的节点
     * @param visited：访问数组的情况
     */
    public void topologicalSortUnit(Stack<Integer> stack, int v, boolean[] visited) {
        visited[v] = true;

        for (Integer adjValue : adjList[v]) {
            if (!visited[adjValue]) {
                topologicalSortUnit(stack, adjValue, visited);
            }
        }

        stack.push(v);
    }

    /**
     * 拓扑排序，A->B表示：先访问A才能访问B；总结：必须先将B的所有入度节点访问才能访问B；
     * 答案不唯一
     */
    public void topologicalSort() {
        boolean[] visited = new boolean[vertexes];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertexes; i++) {
            if (!visited[i]) {
                topologicalSortUnit(stack, i, visited);
            }
        }

        //栈的弹出顺序就是拓扑排序的访问顺序
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "\t");
        }
    }

    public static void main(String[] args) {
        GraphLJF graph = new GraphLJF(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.print("深度优先遍历：\t");
        graph.DFS();
        System.out.print("\n广度优先遍历：\t");
        graph.BFS();
        System.out.print("\n 拓扑访问顺序：\t");
        graph.topologicalSort();


    }
}
